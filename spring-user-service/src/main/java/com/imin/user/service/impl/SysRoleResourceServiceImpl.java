package com.imin.user.service.impl;

import com.google.common.collect.Lists;
import com.imin.infrastructure.common.utils.LangUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imin.user.mapper.SysRoleResourceMapper;
import com.imin.user.model.SysRoleResourceModel;
import com.imin.user.service.SysRoleResourceService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.function.Function;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 用户资源关系服务
 * @date 2018-11-27 18:19:55
 **/
@Service
public class SysRoleResourceServiceImpl implements SysRoleResourceService {

    @Autowired
    private SysRoleResourceMapper sysRoleResourceMapper;


    /**
     * 通过角色id查资源列表
     *
     * @param roleId
     * @return
     */
    @Override
    public List<Long> listByRoleId(Long roleId) {
        List<SysRoleResourceModel> models = sysRoleResourceMapper.select(
                SysRoleResourceModel.builder().roleId(roleId).build());
        return LangUtils.transform(models, new Function<SysRoleResourceModel, Long>() {
            @Override
            public Long apply(SysRoleResourceModel sysRoleResourceModel) {
                return sysRoleResourceModel.getResourceId();
            }
        });
    }

    /**
     * 通过角色id列表查资源列表
     *
     * @param roleIds
     * @return
     */
    @Override
    public List<Long> listByRoleIds(List<Long> roleIds) {
        Example example = new Example(SysRoleResourceModel.class);
        example.createCriteria().andIn("roleId", roleIds);
        List<SysRoleResourceModel> models = sysRoleResourceMapper.selectByExample(example);
        return LangUtils.transform(models, new Function<SysRoleResourceModel, Long>() {
            @Override
            public Long apply(SysRoleResourceModel sysRoleResourceModel) {
                return sysRoleResourceModel.getResourceId();
            }
        });
    }

    /**
     * 通过角色id删除资源
     *
     * @param roleId
     * @return
     */
    @Override
    public int deleteByRoleId(Long roleId) {
        Example example = new Example(SysRoleResourceModel.class);
        example.createCriteria().andEqualTo("roleId", roleId);
        return sysRoleResourceMapper.deleteByExample(example);
    }

    /**
     * 批量操作(为了方便操作，先删除，后添加)
     * @param roleId
     * @param resourceIds
     * @return
     */
    @Override
    public int batchOperate(Long roleId, List<Long> resourceIds) {
        int res = deleteByRoleId(roleId);
        if (CollectionUtils.isNotEmpty(resourceIds)) {
            List<SysRoleResourceModel> models = Lists.newArrayList();
            for (Long resourceId : resourceIds) {
                models.add(SysRoleResourceModel.builder()
                        .roleId(roleId)
                        .resourceId(resourceId)
                        .build());
            }
            res += sysRoleResourceMapper.batchInsert(models);
        }
        return res;
    }
}
