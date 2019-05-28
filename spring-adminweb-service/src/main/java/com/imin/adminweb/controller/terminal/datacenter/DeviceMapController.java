package com.imin.adminweb.controller.terminal.datacenter;

import com.imin.adminweb.feign.terminal.DeviceMapApiServiceRemote;
import com.imin.infrastructure.common.aop.RequestProcess;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.terminal.dto.request.datacenter.DeviceMapQueryReqDto;
import com.imin.terminal.dto.response.datacenter.DeviceMapSummaryResDto;
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
 * @Description: 设备地图信息控制器
 * @date 2018-11-20 13:52:22
 **/
@RestController
@RequestMapping("${api.url.prefix}")
@Api(tags = {"设备地图信息接口"})
public class DeviceMapController {

    @Autowired
    private DeviceMapApiServiceRemote deviceMapApiServiceRemote;

    /**
     * 查询
     */
    @ApiOperation(value = "获取设备地图信息数据", notes = "获取设备地图信息数据")
    @PostMapping(value = "/devicemap/getMap", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/devicemap/getMap")
    public ResultData<DeviceMapSummaryResDto> getMap(@Validated() @RequestBody DeviceMapQueryReqDto reqDto){
        return deviceMapApiServiceRemote.getMap(reqDto);
    }

}
