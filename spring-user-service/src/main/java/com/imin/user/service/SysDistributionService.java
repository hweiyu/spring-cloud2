package com.imin.user.service;

import com.imin.infrastructure.common.dto.PageInfo;

import com.imin.user.dto.reqeust.SysDistributionInsertReqDto;
import com.imin.user.dto.reqeust.SysDistributionQueryReqDto;
import com.imin.user.dto.reqeust.SysDistributionUpdateReqDto;
import com.imin.user.dto.response.SysDistributionListResDto;
import com.imin.user.dto.response.SysDistributionResDto;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 用户分配接口
 * @date 2018-11-27 18:19:55
 **/
public interface SysDistributionService {

    /**
    * 列表
    */
    PageInfo<SysDistributionListResDto> select(SysDistributionQueryReqDto reqDto);

    /**
    * 查询
    */
    SysDistributionResDto get(Long id);

    /**
    * 添加
    */
    int insert(SysDistributionInsertReqDto reqDto);

    /**
    * 修改
    */
    int update(SysDistributionUpdateReqDto reqDto);

    /**
    * 删除
    */
    int delete(SysDistributionUpdateReqDto reqDto);
}

