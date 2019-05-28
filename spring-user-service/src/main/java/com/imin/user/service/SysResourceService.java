package com.imin.user.service;

import com.google.common.collect.Multimap;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.user.dto.reqeust.SysResourceInsertReqDto;
import com.imin.user.dto.reqeust.SysResourceQueryReqDto;
import com.imin.user.dto.reqeust.SysResourceUpdateReqDto;
import com.imin.user.dto.response.SysResourceListResDto;
import com.imin.user.dto.response.SysResourceResDto;
import com.imin.user.model.SysResourceModel;

import java.util.List;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 系统菜单权限资源接口
 * @date 2018-11-27 18:19:55
 **/
public interface SysResourceService {

    /**
    * 列表
    */
    List<SysResourceListResDto> select(SysResourceQueryReqDto reqDto);

    /**
     * 通过用户id查资源列表
     * @param userId
     * @return
     */
    List<SysResourceListResDto> listByUserId(Long userId);

    /**
    * 查询
    */
    SysResourceResDto get(Long id);

    /**
    * 添加
    */
    IdDto insert(SysResourceInsertReqDto reqDto);

    /**
    * 修改
    */
    IdDto update(SysResourceUpdateReqDto reqDto);

    /**
     * 封装资源列表
     * key:父节点id, value:资源信息
     * @param models
     * @return
     */
    Multimap<Long, SysResourceModel> getResourceMap(List<SysResourceModel> models);

    /**
     * 通过用户id查资源列表
     * @param userId
     * @return
     */
    List<SysResourceModel> listModelsByUserId(Long userId);

    /**
     * 通过用户id和平台类型查可用状态的资源列表
     * @param userId
     * @param platformType
     * @return
     */
    List<SysResourceModel> listEnableByUserIdAndPlatformType(Long userId, Integer platformType);
}

