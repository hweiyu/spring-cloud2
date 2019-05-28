package com.imin.user.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.imin.infrastructure.common.constants.ShareConstants;
import com.imin.infrastructure.common.enums.DataStatusEnum;
import com.imin.infrastructure.common.utils.CopyDataUtil;
import com.imin.user.dto.reqeust.UserLoginReqDto;
import com.imin.user.dto.response.UserLoginResDto;
import com.imin.user.enums.PlatformTypeEnum;
import com.imin.user.exception.UserException;
import com.imin.user.model.SysResourceModel;
import com.imin.user.model.SysUserModel;
import com.imin.user.service.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title 描述
 * @Description 描述
 * @date 2018/11/29 10:11
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysResourceService sysResourceService;

    /**
     * 登录
     *
     * @param reqDto
     * @return
     */
    @Override
    public UserLoginResDto login(UserLoginReqDto reqDto) {
        PlatformTypeEnum platform = PlatformTypeEnum.of(reqDto.getPlatform());
        if (null == platform) {
            throw new UserException("platform type is null or error");
        }
        SysUserModel userModel = sysUserService.getByLoginAccount(reqDto.getLoginName());
        //校验用户是否有效
        checkEnable(userModel, reqDto.getLoginPassword(), platform);
        //获取用户信息
        UserLoginResDto result = getUser(userModel);
        //设置用户的资源权限
        setAuthResource(result);
        return result;
    }

    private void checkEnable(SysUserModel model, String password, PlatformTypeEnum platform) {
        if (null == model) {
            throw new UserException("user is not exists");
        }
        if (!ShareConstants.SUPPER_ADMIN_ID.equals(model.getId())
                && !platform.isSame(model.getPlatformType())) {
            throw new UserException("user is not exists");
        }
        if (DataStatusEnum.FORBIDDEN.isSame(model.getDataStatus())) {
            throw new UserException("user is unavailable");
        }
        if (!model.getLoginPassword().equals(password)) {
            throw new UserException("password is error");
        }
    }

    private UserLoginResDto getUser(SysUserModel model) {
        return UserLoginResDto.builder()
                .id(model.getId())
                .loginName(model.getLoginName())
                .account(model.getAccount())
                .email(model.getEmail())
                .accountType(model.getAccountType())
                .platformType(model.getPlatformType())
                .firstLoginFlag(model.getFirstLoginFlag())
                .token(UUID.randomUUID().toString().replace("-", ""))
                .build();
    }

    private void setAuthResource(UserLoginResDto result) {
        List<UserLoginResDto.MenuResource> menus = Lists.newArrayList();
        //默认拥有上传的功能
        List<String> authCodes = Lists.newArrayList("imin:storage:upload", "imin:storage:multiupload");
        List<SysResourceModel> resourceModels = sysResourceService.listEnableByUserIdAndPlatformType(
                result.getId(), result.getPlatformType());
        if (CollectionUtils.isNotEmpty(resourceModels)) {
            Multimap<Long, SysResourceModel> resourceMap = sysResourceService.getResourceMap(resourceModels);
            for (SysResourceModel model : resourceModels) {
                if (isRootResource(model)) {
                    UserLoginResDto.MenuResource curResource =
                            CopyDataUtil.copyObject(model, UserLoginResDto.MenuResource.class);
                    setChildrenResource(curResource, resourceMap);
                    menus.add(curResource);
                }
                authCodes.add(model.getAuthCode());
            }
        }
        result.setMenus(menus);
        result.setAuthCodes(authCodes);
    }

    private boolean isRootResource(SysResourceModel model) {
        return model.getParentId() <= 0;
    }

    private void setChildrenResource(UserLoginResDto.MenuResource curResource, Multimap<Long, SysResourceModel> resourceMap) {
        List<SysResourceModel> originChildren = (List<SysResourceModel>) resourceMap.get(curResource.getId());
        if (CollectionUtils.isNotEmpty(originChildren)) {
            List<UserLoginResDto.MenuResource> children = Lists.newArrayList();
            for (SysResourceModel originChild : originChildren) {
                UserLoginResDto.MenuResource child = CopyDataUtil.copyObject(originChild, UserLoginResDto.MenuResource.class);
                setChildrenResource(child, resourceMap);
                children.add(child);
            }
            curResource.setChildren(children);
        }
    }

}
