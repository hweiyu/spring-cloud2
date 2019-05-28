package com.imin.adminweb.mapper.retailers;


import com.imin.adminweb.conf.MyMapper;
import com.imin.adminweb.dto.request.retailers.RetailersBatchReqDto;
import com.imin.adminweb.dto.request.retailers.RetailersDeviceInfobatchInsertReqDto;
import com.imin.adminweb.model.retailers.RetailersDeviceInfoModel;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.persistence.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author code
 * @version V1.0
 * @Title: 商户终端设备信息管理dao
 * @Description: 商户终端设备信息管理dao
 **/
@MyBatisDao
public interface RetailersDeviceInfoMapper extends MyMapper<RetailersDeviceInfoModel> {


    /**
     * 批量更新终端商户信息
     *
     * @param
     * @return
     */
    int BatchUpdate(RetailersBatchReqDto dto);

    /**
     * 批量更新终端商户信息
     *
     * @param
     * @return
     */
    int batchDelete(@Param("list") List<String> list);

    /**
     * 批量增加终端商户信息
     *
     * @param
     * @return
     */
    int batchInsert(RetailersDeviceInfobatchInsertReqDto list);



}
