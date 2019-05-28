package com.imin.adminweb.service.user;

import java.util.List;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 用户资源关系接口
 * @date 2018-11-27 18:19:55
 **/
public interface SysRoleResourceService {

    /**
     * 通过角色id查资源列表
     * @param roleId
     * @return
     */
    List<Long> listByRoleId(Long roleId);

    /**
     * 通过角色id列表查资源列表
     * @param roleIds
     * @return
     */
    List<Long> listByRoleIds(List<Long> roleIds);

    /**
     * 通过角色id删除资源
     * @param roleId
     * @return
     */
    int deleteByRoleId(Long roleId);

    /**
     * 批量操作(为了方便操作，先删除，后添加)
     * @param roleId
     * @param resourceIds
     * @return
     */
    int batchOperate(Long roleId, List<Long> resourceIds);
}

