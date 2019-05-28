package com.imin.adminweb.controller.retailers;


import com.imin.adminweb.controller.BaseController;
import com.imin.adminweb.dto.request.retailers.*;
import com.imin.adminweb.dto.response.retailers.RetailersDeviceInfoResDto;
import com.imin.adminweb.service.retailers.RetailersDeviceInfoService;
import com.imin.infrastructure.common.aop.PopedomType;
import com.imin.infrastructure.common.aop.RequestProcess;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.infrastructure.common.valid.interfaces.MustId;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author code
 * @version V1.0
 * @Title: 商户终端设备接口
 * @Description: 商户终端设备接口
 **/
@RestController
@RequestMapping("${api.url.prefix}")
@Api(tags = {"商户终端设备接口"})
@Validated
public class RetailersDeviceController extends BaseController {
    @Autowired
    private RetailersDeviceInfoService retailersDeviceInfoService;

    /**
     * 列表
     */
    @ApiOperation(value = "商户终端设备数据列表", notes = "商户终端设备数据列表")
    @PostMapping(value = "/retailersdeviceinfo/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logUrl = "/retailersdeviceinfo/list", popedomCode = "demo",
            popedomType = PopedomType.View)
    public ResultData<PageInfo<RetailersDeviceInfoResDto>> getListOfDto(@Valid @RequestBody RetailersDeviceInfoQueryReqDto dto) {
        return ResultData.createSuccessResult(retailersDeviceInfoService.select(dto));
    }

    /**
     * 查询
     */
    @ApiOperation(value = "获取商户终端设备", notes = "获取商户终端设备")
    @PostMapping(value = "/retailersdeviceinfo/get", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logUrl = "/retailersdeviceinfo/get")
    public ResultData<PageInfo<RetailersDeviceInfoResDto>> get(@Validated() @RequestBody RetailersDeviceInfoQueryReqDto dto) {
        return ResultData.createSuccessResult(retailersDeviceInfoService.get(dto));
    }


    /**
     * 增加
     */
    @ApiOperation(value = "增加商户终端设备", notes = "增加商户终端设备")
    @PostMapping(value = "/retailersdeviceinfo/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logName = "增加商户终端设备", logUrl = "/retailersdeviceinfo/insert",
            popedomCode = "demo", popedomType = PopedomType.Insert, saveResult = true)
    public ResultData<IdDto> insert(@RequestBody RetailersDeviceInfoInsertReqDto dto) {
        return ResultData.createSuccessResult(retailersDeviceInfoService.insert(dto));

    }

    /**
     * 更新（单个）
     */
    @ApiOperation(value = "更新商户终端设备", notes = "更新商户终端设备")
    @PostMapping(value = "/retailersdeviceinfo/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logUrl = "/retailersdeviceinfo/update", popedomCode = "demo",
            popedomType = PopedomType.Update, saveResult = true)
    public ResultData<Integer> update(@Validated({MustId.class}) @RequestBody RetailersDeviceInfoUpdateReqDto dto) {
        return ResultData.createSuccessResult(retailersDeviceInfoService.update(dto));

    }

    /**
     * 批量增加
     */
    @ApiOperation(value = "批量增加商户终端设备", notes = "批量增加商户终端设备")
    @PostMapping(value = "/retailersdeviceinfo/batchInsert", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logUrl = "/retailersdeviceinfo/batchInsert", popedomCode = "demo",
            popedomType = PopedomType.Update, saveResult = true)
    public ResultData<Integer> batchInsert(@Validated({MustId.class}) @RequestBody RetailersDeviceInfobatchInsertReqDto dto) {
        return ResultData.createSuccessResult(retailersDeviceInfoService.batchInsert(dto));

    }

    /**
     * 批量更新
     */
    @ApiOperation(value = "批量更新商户终端设备", notes = "批量更新商户终端设备")
    @PostMapping(value = "/retailersdeviceinfo/bacthUpdate", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logUrl = "/retailersdeviceinfo/bacthUpdate", popedomCode = "demo",
            popedomType = PopedomType.Update, saveResult = true)
    public ResultData<Integer> bacthUpdate(@Validated({MustId.class}) @RequestBody RetailersBatchReqDto dto) {
        return ResultData.createSuccessResult(retailersDeviceInfoService.BatchUpdate(dto));

    }

    /**
     * 删除（单个）
     */
    @ApiOperation(value = "删除商户终端设备", notes = "删除商户终端设备")
    @PostMapping(value = "/retailersdeviceinfo/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logUrl = "/retailersdeviceinfo/delete", popedomCode = "demo",
            popedomType = PopedomType.Delete, saveResult = true)
    public ResultData<Integer> delete(@RequestBody RetailersDeviceInfoQueryReqDto dto) {
        return ResultData.createSuccessResult(retailersDeviceInfoService.deleteBySn(dto));
    }

    /**
     * 删除(批量)
     */
    @ApiOperation(value = "批量删除商户终端设备", notes = "删除商户终端设备")
    @PostMapping(value = "/retailersdeviceinfo/batchDelete", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logUrl = "/retailersdeviceinfo/batchDelete", popedomCode = "demo",
            popedomType = PopedomType.Delete, saveResult = true)
    public ResultData<Integer> batchDelete(@RequestBody RetailersBatchDeleteReqDto dto) {
        return ResultData.createSuccessResult(retailersDeviceInfoService.retailersBatchDelete(dto));
    }


}
