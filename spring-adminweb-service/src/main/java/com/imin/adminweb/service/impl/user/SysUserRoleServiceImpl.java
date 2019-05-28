package com.imin.adminweb.service.impl.user;

import com.google.common.collect.Lists;
import com.imin.adminweb.mapper.user.SysUserRoleMapper;
import com.imin.adminweb.model.user.SysUserRoleModel;
import com.imin.adminweb.service.user.SysUserRoleService;
import com.imin.infrastructure.common.utils.LangUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
     * 通过角色id查用户列表
     *
     * @param roleId
     * @return
     */
    @Override
    public List<Long> listByRoleId(Long roleId) {
        List<SysUserRoleModel> models = sysUserRoleMapper.select(SysUserRoleModel.builder().roleId(roleId).build());
        return LangUtils.transform(models, new Function<SysUserRoleModel, Long>() {
            @Override
            public Long apply(SysUserRoleModel sysUserRoleModel) {
                return sysUserRoleModel.getUserId();
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
