package com.imin.adminweb.service.impl.user;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.imin.adminweb.conf.ResoureMessage;
import com.imin.adminweb.exception.UserException;
import com.imin.adminweb.feign.basic.DictApiServiceRemote;
import com.imin.adminweb.model.user.SysResourceModel;
import com.imin.adminweb.model.user.SysUserModel;
import com.imin.adminweb.service.user.SysLoginService;
import com.imin.adminweb.service.user.SysResourceService;
import com.imin.adminweb.service.user.SysUserService;
import com.imin.basic.dto.request.DictListReqDto;
import com.imin.basic.dto.response.DictResDto;
import com.imin.infrastructure.common.constants.ShareConstants;
import com.imin.infrastructure.common.enums.DataStatusEnum;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.infrastructure.common.result.ResultDataUtil;
import com.imin.infrastructure.common.utils.CopyDataUtil;
import com.imin.infrastructure.common.utils.Il8nSupportUtil;
import com.imin.user.dto.reqeust.UserLoginReqDto;
import com.imin.user.dto.response.UserLoginResDto;
import com.imin.user.enums.PlatformTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title 描述
 * @Description 描述
 * @date 2018/11/29 10:11
 **/
@Service
@Slf4j
public class SysLoginServiceImpl implements SysLoginService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysResourceService sysResourceService;

    @Autowired
    private DictApiServiceRemote dictApiServiceRemote;

    @Autowired
    private ResoureMessage resoureMessage;

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
            throw new UserException(
                    resoureMessage.getMessage("m.platform.type.error"
                            + Il8nSupportUtil.getSupportLocalFromHttpRequest()));
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
        Locale locale = Il8nSupportUtil.getSupportLocalFromHttpRequest();
        if (null == model) {
            throw new UserException(resoureMessage.getMessage("m.user.does.not.exist", locale));
        }
        if (!ShareConstants.SUPPER_ADMIN_ID.equals(model.getId())
                && !platform.isSame(model.getPlatformType())) {
            throw new UserException(resoureMessage.getMessage("m.user.does.not.exist", locale));
        }
        if (DataStatusEnum.FORBIDDEN.isSame(model.getDataStatus())) {
            throw new UserException(resoureMessage.getMessage("m.user.unavailability", locale));
        }
        if (!model.getLoginPassword().equals(password)) {
            throw new UserException(resoureMessage.getMessage("m.incorrect.password", locale));
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
            Multimap<String, UserLoginResDto.Il8n> il8nMap = getIl8nMap();
            String language = Il8nSupportUtil.getSupportLanguageFromHttpRequest();
            for (SysResourceModel model : resourceModels) {
                if (isRootResource(model)) {
                    UserLoginResDto.MenuResource curResource =
                            CopyDataUtil.copyObject(model, UserLoginResDto.MenuResource.class);
                    setChildrenResource(language, curResource, resourceMap, il8nMap);
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

    private void setChildrenResource(String language,
                                     UserLoginResDto.MenuResource curResource,
                                     Multimap<Long, SysResourceModel> resourceMap,
                                     Multimap<String, UserLoginResDto.Il8n> il8nMap) {
        List<SysResourceModel> originChildren = (List<SysResourceModel>) resourceMap.get(curResource.getId());
        if (CollectionUtils.isNotEmpty(originChildren)) {
            List<UserLoginResDto.MenuResource> children = Lists.newArrayList();
            for (SysResourceModel originChild : originChildren) {
                UserLoginResDto.MenuResource child = CopyDataUtil.copyObject(originChild, UserLoginResDto.MenuResource.class);
                setChildrenResource(language, child, resourceMap, il8nMap);
                children.add(child);
            }
            children.sort(new Comparator<UserLoginResDto.MenuResource>() {
                @Override
                public int compare(UserLoginResDto.MenuResource o1, UserLoginResDto.MenuResource o2) {
                    return o1.getSortBy().compareTo(o2.getSortBy());
                }
            });
            curResource.setChildren(children);
        }
        setIl8n(language, curResource, il8nMap);
    }

    private Multimap<String, UserLoginResDto.Il8n> getIl8nMap() {
        Multimap<String, UserLoginResDto.Il8n> map = ArrayListMultimap.create();
        try {
            ResultData<List<DictResDto>> resultData = dictApiServiceRemote.list(DictListReqDto.builder()
                    .dictType("sys_resource").build());
            if (ResultDataUtil.checkCodeAndData(resultData)) {
                for (DictResDto data : resultData.getData()) {
                    map.put(data.getDictCode(), UserLoginResDto.Il8n.builder()
                            .language(data.getDictKey()).resourceName(data.getDictValue()).build());
                }
            }
        } catch (Exception e) {
            log.error("查询资源菜单国际化信息异常", e);
        }
        return map;
    }

    private void setIl8n(String language,
                         UserLoginResDto.MenuResource curResource,
                         Multimap<String, UserLoginResDto.Il8n> il8nMap) {
        List<UserLoginResDto.Il8n> il8ns = (List<UserLoginResDto.Il8n>) il8nMap.get(curResource.getResourceCode());
        for (UserLoginResDto.Il8n il8n : il8ns) {
            if (il8n.getLanguage().equals(language)) {
                curResource.setResourceName(il8n.getResourceName());
                break;
            }
        }
        curResource.setIl8ns(il8ns);
    }

}
