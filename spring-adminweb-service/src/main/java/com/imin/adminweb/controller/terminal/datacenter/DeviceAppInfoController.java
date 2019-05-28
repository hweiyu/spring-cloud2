package com.imin.adminweb.controller.terminal.datacenter;

import com.imin.adminweb.dto.request.DeviceAppQueryReqDto;
import com.imin.adminweb.feign.terminal.DeviceAppInfoApiServiceRemote;
import com.imin.infrastructure.common.aop.RequestProcess;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.infrastructure.common.utils.Il8nSupportUtil;
import com.imin.terminal.dto.request.datacenter.DeviceAppInfoQueryReqDto;
import com.imin.terminal.dto.response.datacenter.DeviceAppInfoListResDto;
import com.imin.terminal.dto.response.datacenter.DeviceAppInfoResDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 设备应用信息控制器
 * @date 2018-11-20 13:52:22
 **/
@RestController
@RequestMapping("${api.url.prefix}")
@Api(tags = {"设备应用信息接口"})
public class DeviceAppInfoController {

    @Autowired
    private DeviceAppInfoApiServiceRemote deviceAppInfoServiceRemote;

    /**
     * 列表
     */
    @ApiOperation(value = "获取设备应用信息数据列表", notes = "获取设备应用信息数据列表")
    @PostMapping(value = "/deviceappinfo/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/deviceappinfo/list")
    public ResultData<List<DeviceAppInfoListResDto>> list(@RequestBody DeviceAppQueryReqDto reqDto){
        return deviceAppInfoServiceRemote.list(DeviceAppInfoQueryReqDto.builder()
                .deviceType(reqDto.getDeviceType())
                .language(Il8nSupportUtil.getSupportLanguageFromHttpRequest())
                .build());
    }

    /**
     * 查询
     */
    @ApiOperation(value = "获取设备应用信息数据", notes = "获取设备应用信息数据")
    @PostMapping(value = "/deviceappinfo/get", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/deviceappinfo/get")
    public ResultData<DeviceAppInfoResDto> get(@Validated() @RequestBody IdDto idDto){
        return deviceAppInfoServiceRemote.get(idDto);
    }

}
