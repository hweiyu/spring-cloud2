package com.imin.adminweb.service.retailers;

import com.imin.adminweb.dto.request.retailers.*;
import com.imin.adminweb.dto.response.retailers.RetailersDeviceInfoResDto;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;

import java.util.List;

/**
 * @author code
 * @version V1.0
 * @Title: 基础代码
 * @Description: 商户终端设备信息服务接口
 **/
public interface RetailersDeviceInfoService {


    /**
     * 返回分页列表信息
     *
     * @param reqDto 数据
     * @return list
     */
    PageInfo<RetailersDeviceInfoResDto> select(RetailersDeviceInfoQueryReqDto reqDto);

    /**
     * 根据id返回信息
     *
     * @param reqDto 数据
     * @return
     */
    PageInfo<RetailersDeviceInfoResDto> get(RetailersDeviceInfoQueryReqDto reqDto);


    /**
     * 根据sn删除信息
     *
     * @param dto 数据
     * @return
     */
    int deleteBySn(RetailersDeviceInfoQueryReqDto dto);



    /**
     * 批量删除
     *
     * @param sn
     * @return
     */
    int batchDelete(List<String> sn);

    /**
     * 终端商户批量删除
     *
     * @param dto
     * @return
     */
    int retailersBatchDelete(RetailersBatchDeleteReqDto dto);

    /**
     * 增加
     *
     * @param dto 数据
     * @return
     */
    IdDto insert(RetailersDeviceInfoInsertReqDto dto);


    /**
     * 批量增加
     *
     * @param dto 数据
     * @return
     */
    int batchInsert(RetailersDeviceInfobatchInsertReqDto dto);


    /**
     * 更新
     *
     * @param dto 数据
     * @return
     */
    int update(RetailersDeviceInfoUpdateReqDto dto);

    /**
     * 终端商户批量更新
     *
     * @param dto
     * @return
     */
    int BatchUpdate(RetailersBatchReqDto dto);


}
