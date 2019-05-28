package com.imin.adminweb.controller.terminal.datacenter;

import com.imin.adminweb.feign.terminal.DeviceLifeCycleInfoApiServiceRemote;
import com.imin.infrastructure.common.aop.RequestProcess;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.terminal.dto.request.datacenter.DeviceLifeCycleInfoQueryReqDto;
import com.imin.terminal.dto.response.datacenter.DeviceLifeCycleInfoListResDto;
import com.imin.terminal.dto.response.datacenter.DeviceLifeCycleInfoResDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 设备生命周期信息控制器
 * @date 2018-11-20 13:52:22
 **/
@RestController
@RequestMapping("${api.url.prefix}")
@Api(tags = {"设备生命周期信息接口"})
public class DeviceLifeCycleInfoController {

    @Autowired
    private DeviceLifeCycleInfoApiServiceRemote deviceLifeCycleInfoApiServiceRemote;

    /**
     * 列表
     */
    @ApiOperation(value = "获取设备生命周期信息数据列表", notes = "获取设备生命周期信息数据列表")
    @PostMapping(value = "/devicelifecycleinfo/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/devicelifecycleinfo/list")
    public ResultData<PageInfo<DeviceLifeCycleInfoListResDto>> list(@RequestBody DeviceLifeCycleInfoQueryReqDto reqDto){
        return deviceLifeCycleInfoApiServiceRemote.list(reqDto);
    }

    /**
     * 查询
     */
    @ApiOperation(value = "获取设备生命周期信息数据", notes = "获取设备生命周期信息数据")
    @PostMapping(value = "/devicelifecycleinfo/get", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/devicelifecycleinfo/get")
    public ResultData<DeviceLifeCycleInfoResDto> get(@Validated() @RequestBody IdDto idDto){
        return deviceLifeCycleInfoApiServiceRemote.get(idDto);
    }

}
