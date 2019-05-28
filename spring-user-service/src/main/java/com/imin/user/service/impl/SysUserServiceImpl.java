package com.imin.user.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.imin.basic.dto.request.DictListReqDto;
import com.imin.basic.dto.response.DictResDto;
import com.imin.infrastructure.common.constants.ShareConstants;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.enums.DataStatusEnum;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.infrastructure.common.result.ResultDataUtil;
import com.imin.infrastructure.common.utils.CopyDataUtil;
import com.imin.infrastructure.common.utils.JodaTimeUtil;
import com.imin.infrastructure.common.utils.StringUtil;
import com.imin.infrastructure.common.utils.encrypt.EncryptMD5Util;
import com.imin.user.dto.reqeust.*;
import com.imin.user.dto.response.*;
import com.imin.user.enums.PlatformTypeEnum;
import com.imin.user.exception.UserException;
import com.imin.user.feign.basic.DictApiServiceRemote;
import com.imin.user.model.SysRoleModel;
import com.imin.user.service.SysRoleService;
import com.imin.user.service.SysUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.utils.PageUtil;

import com.imin.user.mapper.SysUserMapper;
import com.imin.user.model.SysUserModel;
import com.imin.user.service.SysUserService;
import tk.mybatis.mapper.entity.Example;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 系统用户服务
 * @date 2018-11-27 18:19:55
 **/
@Service
@Slf4j
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private DictApiServiceRemote dictApiServiceRemote;

    @Autowired
    private SysRoleService sysRoleService;

    /**
    * 列表
    */
    @Override
    public PageInfo<SysUserListResDto> select(SysUserQueryReqDto reqDto) {
        PageHelper.startPage(reqDto.getPageNumberByDefault(), reqDto.getPageSizeByDefault());
        List<SysUserModel> models = sysUserMapper.selectByExample(wrapCondition(reqDto));
        List<SysUserListResDto> result = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(models)) {
            List<Long> userIds = Lists.newArrayList();
            for (SysUserModel model : models) {
                userIds.add(model.getId());
                result.add(CopyDataUtil.copyObject(model, SysUserListResDto.class));
            }
            Multimap<Long, SysRoleModel> userRoleMap = sysRoleService.getMapByUserIds(userIds);
            for (SysUserListResDto curDto : result) {
                setRoleName(curDto, userRoleMap);
            }
        }
        return PageUtil.getPageList(result, PageUtil.getPaginationInfo(models));
    }

    /**
     * 封装查询条件
     * @param reqDto
     * @return
     */
    private Example wrapCondition(SysUserQueryReqDto reqDto) {
        Example example = new Example(SysUserModel.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotEqualTo("id", ShareConstants.SUPPER_ADMIN_ID);
        if (!StringUtil.isEmptyOrNull(reqDto.getLoginName())) {
            criteria.andLike("loginName", "%" + reqDto.getLoginName() + "%");
        }
        if (!StringUtil.isEmptyOrNull(reqDto.getAccount())) {
            criteria.andLike("account", "%" + reqDto.getAccount() + "%");
        }
        if (null != reqDto.getPlatformType()) {
            criteria.andEqualTo("platformType", reqDto.getPlatformType());
        }
        if (null == reqDto.getDataStatus()) {
            criteria.andNotEqualTo("dataStatus", DataStatusEnum.DELETED.getStatus());
        } else {
            criteria.andEqualTo("dataStatus", reqDto.getDataStatus());
        }
        return example;
    }

    /**
     * 封装角色名称
     * @param resDto
     * @param userRoleMap
     */
    private void setRoleName(SysUserListResDto resDto, Multimap<Long, SysRoleModel> userRoleMap) {
        Collection<SysRoleModel> roleModels = userRoleMap.get(resDto.getId());
        if (CollectionUtils.isNotEmpty(roleModels)) {
            StringBuilder buf = new StringBuilder();
            for (SysRoleModel roleModel : roleModels) {
                buf.append(",").append(roleModel.getRoleName());
            }
            resDto.setRoleName(buf.substring(1));
        }
    }

    /**
    * 查询
    */
    @Override
    public SysUserResDto get(Long id) {
        SysUserModel model = sysUserMapper.selectOne(SysUserModel.builder().id(id).build());
        SysUserResDto resDto = CopyDataUtil.copyObject(model, SysUserResDto.class);
        setRoles(resDto);
        return resDto;
    }

    private void setRoles(SysUserResDto resDto) {
        if (null != resDto) {
            resDto.setRoleIds(sysUserRoleService.listByUserId(resDto.getId()));
        }
    }

    /**
    * 添加
    */
    @Override
    public IdDto insert(SysUserInsertReqDto reqDto) {
        SysUserModel insertObj = CopyDataUtil.copyObject(reqDto, SysUserModel.class);
        setAccount(reqDto.getAccountPrefix(), insertObj);
        sysUserMapper.insertSelective(insertObj);
        sysUserRoleService.batchOperate(insertObj.getId(), reqDto.getRoleIds());
        return new IdDto(insertObj.getId());
    }

    private void setAccount(String accountPrefix, SysUserModel model) {
        String prefix = accountPrefix + JodaTimeUtil.getCurrentFormatDate("yyMMdd");
        Example example = new Example(SysUserModel.class);
        example.createCriteria().andLike("account", prefix + "%");
        List<SysUserModel> models = sysUserMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(models)) {
            SysUserModel lastModel = models.get(models.size() - 1);
            int lastNum = Integer.valueOf(lastModel.getAccount().substring(lastModel.getAccount().length() - 2));
            int curNum = lastNum + 1;
            final int limit = 100;
            if (curNum >= limit) {
                throw new UserException("over the maximum registration, please try again tomorrow");
            }
            model.setAccount(prefix + (curNum >= 10 ? curNum : "0" + curNum));
        } else {
            model.setAccount(prefix + "01");
        }
    }

    /**
    * 修改
    */
    @Override
    public IdDto update(SysUserUpdateReqDto reqDto) {
        SysUserModel updateObj = CopyDataUtil.copyObject(reqDto, SysUserModel.class);
        if (changeInnerRole(reqDto.getAccountPrefix(), updateObj)) {
            setAccount(reqDto.getAccountPrefix(), updateObj);
        }
        sysUserMapper.updateByPrimaryKeySelective(updateObj);
        sysUserRoleService.batchOperate(updateObj.getId(), reqDto.getRoleIds());
        return new IdDto(reqDto.getId());
    }

    /**
     * 修改状态
     *
     * @param reqDto
     * @return
     */
    @Override
    public IdDto updateStatus(SysUserUpdateStatusReqDto reqDto) {
        sysUserMapper.updateByPrimaryKeySelective(SysUserModel.builder()
                .id(reqDto.getId())
                .dataStatus(reqDto.getDataStatus())
                .build());
        return new IdDto(reqDto.getId());
    }

    private boolean changeInnerRole(String accountPrefix, SysUserModel model) {
        Example example = new Example(SysUserModel.class);
        example.createCriteria()
                .andEqualTo("id", model.getId())
                .andNotEqualTo("dataStatus", DataStatusEnum.DELETED.getStatus());
        List<SysUserModel> models = sysUserMapper.selectByExample(example);
        return CollectionUtils.isNotEmpty(models)
                && !models.get(0).getAccount().startsWith(accountPrefix);
    }

    /**
     * 重置密码
     *
     * @param id
     * @return
     */
    @Override
    public SysUserResetPasswordResDto resetPassword(Long id) {
        sysUserMapper.updateByPrimaryKeySelective(SysUserModel.builder()
                .id(id)
                .loginPassword(EncryptMD5Util.getMD5(ShareConstants.DEFAULT_PASSWORD))
                .firstLoginFlag(1)
                .build());
        return SysUserResetPasswordResDto.builder()
                .id(id)
                .password(ShareConstants.DEFAULT_PASSWORD)
                .build();
    }

    /**
     * 修改密码
     *
     * @param reqDto
     * @return
     */
    @Override
    public IdDto updatePassword(SysUserUpdatePasswordReqDto reqDto) {
        Example example = new Example(SysUserModel.class);
        example.createCriteria()
                .andEqualTo("id", reqDto.getId())
                .andNotEqualTo("dataStatus", DataStatusEnum.DELETED.getStatus());
        SysUserModel model = sysUserMapper.selectOneByExample(example);
        if (null == model) {
            throw new UserException("user info no query");
        }
        if (!model.getLoginPassword().equals(reqDto.getOldPassword())) {
            throw new UserException("password is error");
        }
        sysUserMapper.updateByPrimaryKeySelective(SysUserModel.builder()
                .id(reqDto.getId())
                .loginPassword(reqDto.getNewPassword())
                .firstLoginFlag(0)
                .build());
        return new IdDto(reqDto.getId());
    }

    /**
     * 系统内置账号类型列表
     *
     * @return
     */
    @Override
    public List<AccountTypeResDto> accountTypeList() {
        List<AccountTypeResDto> resDtos = Lists.newArrayList();
        try {
            ResultData<List<DictResDto>> result = dictApiServiceRemote.list(
                    DictListReqDto.builder().dictType("user_type").build());
            if (ResultDataUtil.checkCodeAndData(result)) {
                for (DictResDto resDto : result.getData()) {
                    /**
                     * 说明
                     * dict_code:平台类型
                     * dict_key:内置角色id
                     * dict_value:账号编码规则前缀
                     */
                    resDtos.add(AccountTypeResDto.builder()
                            .roleId(Long.valueOf(resDto.getDictKey()))
                            .name(resDto.getExtra())
                            .platformType(Integer.valueOf(resDto.getDictCode()))
                            .prefix(resDto.getDictValue())
                            .build());
                }
            }
        } catch (Exception e) {
            log.error("查询账户类型异常", e);
        }
        return resDtos;
    }

    /**
     * 通过登录名查用户信息
     *
     * @param loginAccount
     * @return
     */
    @Override
    public SysUserModel getByLoginAccount(String loginAccount) {
        return sysUserMapper.getByLoginAccount(loginAccount);
    }

    /**
     * 根据用户id查角色列表
     *
     * @param userId
     */
    @Override
    public List<UserRoleListResDto> listByUserId(Long userId) {
        List<UserRoleListResDto> result = Lists.newArrayList();
        List<SysRoleListResDto> resDtos = sysRoleService.listByUserId(userId);
        List<AccountTypeResDto> accountTypes = accountTypeList();
        //key:角色id, value:实体对象
        Map<Long, AccountTypeResDto> typeMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(accountTypes)) {
            for (AccountTypeResDto accountType : accountTypes) {
                typeMap.put(accountType.getRoleId(), accountType);
            }
        }
        if (CollectionUtils.isNotEmpty(resDtos)) {
            for (SysRoleListResDto resDto : resDtos) {
                UserRoleListResDto cur = UserRoleListResDto.builder()
                        .roleId(resDto.getId())
                        .name(resDto.getRoleName())
                        .build();
                setInnerInfo(cur, typeMap);
                result.add(cur);
            }
        }
        return result;
    }

    private void setInnerInfo(UserRoleListResDto resDto, Map<Long, AccountTypeResDto> typeMap) {
        AccountTypeResDto typeDto = typeMap.get(resDto.getRoleId());
        resDto.setPrefix(null != typeDto ? typeDto.getPrefix() : "0000");
        resDto.setPlatformType(null != typeDto ? typeDto.getPlatformType() : PlatformTypeEnum.IMIN.getType());
    }

}
