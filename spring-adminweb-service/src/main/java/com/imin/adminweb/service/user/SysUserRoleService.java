package com.imin.adminweb.service.user;

import com.imin.adminweb.model.user.SysUserRoleModel;

import java.util.List;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 用户角色关系接口
 * @date 2018-11-27 18:19:55
 **/
public interface SysUserRoleService {

    /**
     * 通过用户id查角色列表
     * @param userId
     * @return
     */
    List<Long> listByUserId(Long userId);

    /**
     * 通过角色id查用户列表
     * @param roleId
     * @return
     */
    List<Long> listByRoleId(Long roleId);

    /**
     * 通过用户id查角色列表
     * @param userIds
     * @return
     */
    List<SysUserRoleModel> listByUserIds(List<Long> userIds);

    /**
     * 通过用户id删除资源
     * @param userId
     * @return
     */
    int deleteByUserId(Long userId);

    /**
     * 批量操作(为了方便操作，先删除，后添加)
     * @param userId
     * @param roleIds
     * @return
     */
    int batchOperate(Long userId, List<Long> roleIds);
}

