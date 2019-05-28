package com.imin.user.service.impl;

import com.google.common.collect.Lists;
import com.imin.infrastructure.common.utils.LangUtils;
import com.imin.user.model.SysUserRoleModel;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imin.user.mapper.SysUserRoleMapper;
import com.imin.user.service.SysUserRoleService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.function.Function;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 用户角色关系服务
 * @date 2018-11-27 18:19:55
 **/
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 通过用户id查角色列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<Long> listByUserId(Long userId) {
        List<SysUserRoleModel> models = sysUserRoleMapper.select(SysUserRoleModel.builder().userId(userId).build());
        return LangUtils.transform(models, new Function<SysUserRoleModel, Long>() {
            @Override
            public Long apply(SysUserRoleModel sysUserRoleModel) {
                return sysUserRoleModel.getRoleId();
            }
        });
    }

    /**
     * 通过用户id查角色列表
     *
     * @param userIds
     * @return
     */
    @Override
    public List<SysUserRoleModel> listByUserIds(List<Long> userIds) {
        Example example = new Example(SysUserRoleModel.class);
        example.createCriteria().andIn("userId", userIds);
        return sysUserRoleMapper.selectByExample(example);
    }

    /**
     * 通过用户id删除资源
     *
     * @param userId
     * @return
     */
    @Override
    public int deleteByUserId(Long userId) {
        Example example = new Example(SysUserRoleModel.class);
        example.createCriteria().andEqualTo("userId", userId);
        return sysUserRoleMapper.deleteByExample(example);
    }

    /**
     * 批量操作(为了方便操作，先删除，后添加)
     *
     * @param userId
     * @param roleIds
     * @return
     */
    @Override
    public int batchOperate(Long userId, List<Long> roleIds) {
        int res = deleteByUserId(userId);
        if (CollectionUtils.isNotEmpty(roleIds)) {
            List<SysUserRoleModel> models = Lists.newArrayList();
            for (Long roleId : roleIds) {
                if (roleId > 0) {
                    models.add(SysUserRoleModel.builder()
                            .userId(userId)
                            .roleId(roleId)
                            .build());
                }
            }
            res += sysUserRoleMapper.batchInsert(models);
        }
        return res;
    }
}
