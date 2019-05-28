package com.imin.adminweb.service.user;

import com.google.common.collect.Multimap;
import com.imin.adminweb.model.user.SysRoleModel;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.user.dto.reqeust.SysRoleInsertReqDto;
import com.imin.user.dto.reqeust.SysRoleQueryReqDto;
import com.imin.user.dto.reqeust.SysRoleUpdateReqDto;
import com.imin.user.dto.reqeust.SysRoleUpdateStatusReqDto;
import com.imin.user.dto.response.SysRoleListResDto;
import com.imin.user.dto.response.SysRoleResDto;

import java.util.List;
import java.util.Map;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 系统角色接口
 * @date 2018-11-27 18:19:55
 **/
public interface SysRoleService {

    /**
    * 列表
    */
    PageInfo<SysRoleListResDto> select(SysRoleQueryReqDto reqDto);

    /**
     * 通过用户id查角色列表
     * @param userId
     * @return
     */
    List<SysRoleListResDto> listByUserId(Long userId);

    /**
     * key:角色编码, value:角色名称
     * @return
     */
    Map<String, String> getRoleMap();

    /**
    * 查询
    */
    SysRoleResDto get(Long id);

    /**
    * 添加
    */
    IdDto insert(SysRoleInsertReqDto reqDto);

    /**
    * 修改
    */
    IdDto update(SysRoleUpdateReqDto reqDto);

    /**
     * 修改状态
     */
    IdDto updateStatus(SysRoleUpdateStatusReqDto reqDto);

    /**
     * 过滤出可用状态的角色id列表
     * @param ids
     * @return
     */
    List<Long> filterEnableIds(List<Long> ids);

    /**
     * 通过用户id查角色信息
     * key: 用户id, value:角色列表
     * @param userIds
     * @return
     */
    Multimap<Long, SysRoleModel> getMapByUserIds(List<Long> userIds);
}

