package com.imin.adminweb.controller.terminal.datacenter;

import com.imin.adminweb.feign.terminal.DeviceInfoApiServiceRemote;
import com.imin.infrastructure.common.aop.RequestProcess;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.terminal.dto.request.datacenter.DeviceAppDestroyReqDto;
import com.imin.terminal.dto.request.datacenter.DeviceAppPasswordReqDto;
import com.imin.terminal.dto.request.datacenter.DeviceInfoQueryReqDto;
import com.imin.terminal.dto.response.datacenter.DeviceInfoListResDto;
import com.imin.terminal.dto.response.datacenter.DeviceInfoResDto;
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
 * @Description: 设备信息控制器
 * @date 2018-11-20 13:52:22
 **/
@RestController
@RequestMapping("${api.url.prefix}")
@Api(tags = {"设备信息接口"})
public class DeviceInfoController {

    @Autowired
    private DeviceInfoApiServiceRemote deviceInfoApiServiceRemote;

    /**
     * 列表
     */
    @ApiOperation(value = "获取设备信息数据列表", notes = "获取设备信息数据列表")
    @PostMapping(value = "/deviceinfo/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/deviceinfo/list")
    public ResultData<PageInfo<DeviceInfoListResDto>> list(@RequestBody DeviceInfoQueryReqDto reqDto){
        return deviceInfoApiServiceRemote.list(reqDto);
    }


    /**
     * 查询
     */
    @ApiOperation(value = "获取设备信息数据", notes = "获取设备信息数据")
    @PostMapping(value = "/deviceinfo/get", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/deviceinfo/get")
    public ResultData<DeviceInfoResDto> get(@Validated() @RequestBody DeviceInfoQueryReqDto reqDto){
        return deviceInfoApiServiceRemote.get(reqDto);
    }

    /**
     * 重置设备应用锁密码
     */
    @ApiOperation(value = "重置设备应用锁密码", notes = "重置设备应用锁密码")
    @PostMapping(value = "/deviceinfo/resetAppPassword", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/deviceinfo/resetAppPassword")
    public ResultData<Boolean> resetAppPassword(@Validated() @RequestBody DeviceAppPasswordReqDto reqDto){
        return deviceInfoApiServiceRemote.resetAppPassword(reqDto);
    }

    /**
     * 销毁程序
     */
    @ApiOperation(value = "销毁程序", notes = "销毁程序")
    @PostMapping(value = "/deviceinfo/destroyApp", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/deviceinfo/destroyApp")
    public ResultData<Boolean> destroyApp(@Validated() @RequestBody DeviceAppDestroyReqDto reqDto){
        return deviceInfoApiServiceRemote.destroyApp(reqDto);
    }

}
