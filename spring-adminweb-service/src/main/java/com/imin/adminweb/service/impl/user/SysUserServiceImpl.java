package com.imin.adminweb.service.impl.user;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.imin.adminweb.conf.ResoureMessage;
import com.imin.adminweb.dto.request.UserBindOpenIdReqDto;
import com.imin.adminweb.exception.UserException;
import com.imin.adminweb.feign.basic.DictApiServiceRemote;
import com.imin.adminweb.mapper.user.SysUserMapper;
import com.imin.adminweb.model.user.SysRoleModel;
import com.imin.adminweb.model.user.SysUserModel;
import com.imin.adminweb.service.user.SysRoleService;
import com.imin.adminweb.service.user.SysUserRoleService;
import com.imin.adminweb.service.user.SysUserService;
import com.imin.infrastructure.common.constants.ShareConstants;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.enums.DataStatusEnum;
import com.imin.infrastructure.common.utils.*;
import com.imin.infrastructure.common.utils.encrypt.EncryptMD5Util;
import com.imin.user.dto.reqeust.*;
import com.imin.user.dto.response.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    private SysRoleService sysRoleService;

    @Autowired
    private ResoureMessage resoureMessage;

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
            Map<String, String> roleNameMap = sysRoleService.getRoleMap();
            for (SysUserListResDto curDto : result) {
                setRoleName(curDto, roleNameMap, userRoleMap);
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
    private void setRoleName(SysUserListResDto resDto,
                             Map<String, String> roleNameMap,
                             Multimap<Long, SysRoleModel> userRoleMap) {
        Collection<SysRoleModel> roleModels = userRoleMap.get(resDto.getId());
        if (CollectionUtils.isNotEmpty(roleModels)) {
            String name;
            StringBuilder buf = new StringBuilder();
            for (SysRoleModel roleModel : roleModels) {
                name = roleNameMap.get(roleModel.getRoleCode());
                buf.append(",").append(null == name ? roleModel.getRoleName() : name);
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
                throw new UserException(
                        resoureMessage.getMessage("m.register.error"
                                + Il8nSupportUtil.getSupportLocalFromHttpRequest()));
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
            throw new UserException(
                    resoureMessage.getMessage("m.user.not.exists"
                            + Il8nSupportUtil.getSupportLocalFromHttpRequest()));
        }
        if (!model.getLoginPassword().equals(reqDto.getOldPassword())) {
            throw new UserException(
                    resoureMessage.getMessage("m.user.password.error"
                            + Il8nSupportUtil.getSupportLocalFromHttpRequest()));
        }
        sysUserMapper.updateByPrimaryKeySelective(SysUserModel.builder()
                .id(reqDto.getId())
                .loginPassword(reqDto.getNewPassword())
                .firstLoginFlag(0)
                .build());
        return new IdDto(reqDto.getId());
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
        if (CollectionUtils.isNotEmpty(resDtos)) {
            for (SysRoleListResDto resDto : resDtos) {
                UserRoleListResDto cur = UserRoleListResDto.builder()
                        .roleId(resDto.getId())
                        .name(resDto.getRoleName())
                        .prefix(resDto.getAccountPrefix())
                        .platformType(resDto.getPlatformType())
                        .build();
                result.add(cur);
            }
        }
        return result;
    }

    /**
     * 通过id集合查列表
     *
     * @param ids
     * @return
     */
    @Override
    public List<SysUserModel> listByIds(List<Long> ids) {
        Example example = new Example(SysUserModel.class);
        example.createCriteria()
                .andIn("id", ids)
                .andNotEqualTo("dataStatus", DataStatusEnum.DELETED.getStatus());
        return sysUserMapper.selectByExample(example);
    }

    /**
     * 通过角色id查用户
     *
     * @param roleId
     * @return
     */
    @Override
    public List<SysUserModel> listByRoleId(Long roleId) {
        List<Long> userIds = sysUserRoleService.listByRoleId(roleId);
        if (CollectionUtils.isEmpty(userIds)) {
            return Lists.newArrayList();
        }
        Example example = new Example(SysUserModel.class);
        example.createCriteria()
                .andIn("id", userIds)
                .andNotEqualTo("dataStatus", DataStatusEnum.DELETED.getStatus());
        return sysUserMapper.selectByExample(example);
    }

    /**
     * 查询
     *
     * @param openId
     */
    @Override
    public SysUserResDto getByOpenId(String openId) {
        Example example = new Example(SysUserModel.class);
        example.createCriteria()
                .andEqualTo("openId", openId)
                .andNotEqualTo("dataStatus", DataStatusEnum.DELETED.getStatus());
        SysUserModel model = sysUserMapper.selectOneByExample(example);
        if (null == model) {
            throw new UserException("查询不到账户信息");
        }
        return CopyDataUtil.copyObject(model, SysUserResDto.class);
    }

    /**
     * 绑定openId
     *
     * @param reqDto
     * @return
     */
    @Override
    public boolean bindOpenId(UserBindOpenIdReqDto reqDto) {
        Example example = new Example(SysUserModel.class);
        example.createCriteria()
                .andEqualTo("account", reqDto.getLoginName())
                .andNotEqualTo("dataStatus", DataStatusEnum.DELETED.getStatus());
        SysUserModel model = sysUserMapper.selectOneByExample(example);
        if (null == model) {
            throw new UserException("用户不存在");
        }
        if (!Objects.equals(reqDto.getLoginPassword(), model.getLoginPassword())) {
            throw new UserException("密码不正确");
        }
        //清空旧的绑定
        Example example2 = new Example(SysUserModel.class);
        example2.createCriteria()
                .andEqualTo("openId", reqDto.getOpenId())
                .andNotEqualTo("dataStatus", DataStatusEnum.DELETED.getStatus());
        SysUserModel model2 = sysUserMapper.selectOneByExample(example2);
        if (null != model2) {
            sysUserMapper.updateByPrimaryKeySelective(SysUserModel.builder()
                    .id(model2.getId()).openId("").build());
        }
        //绑定新账户
        return sysUserMapper.updateByPrimaryKeySelective(SysUserModel.builder()
                .id(model.getId()).openId(reqDto.getOpenId()).build()) > 0;
    }

    /**
     * 解绑openId
     *
     * @param reqDto
     * @return
     */
    @Override
    public boolean unBindOpenId(UserBindOpenIdReqDto reqDto) {
        Example example = new Example(SysUserModel.class);
        example.createCriteria()
                .andEqualTo("account", reqDto.getLoginName())
                .andNotEqualTo("dataStatus", DataStatusEnum.DELETED.getStatus());
        SysUserModel model = sysUserMapper.selectOneByExample(example);
        if (null == model) {
            throw new UserException("用户不存在");
        }
        if (!Objects.equals(reqDto.getLoginPassword(), model.getLoginPassword())) {
            throw new UserException("密码不正确");
        }
        if (!Objects.equals(reqDto.getOpenId(), model.getOpenId())) {
            throw new UserException("当前账号绑定的openId不一致");
        }
        //绑定新账户
        return sysUserMapper.updateByPrimaryKeySelective(SysUserModel.builder()
                .id(model.getId()).openId("").build()) > 0;
    }

}
