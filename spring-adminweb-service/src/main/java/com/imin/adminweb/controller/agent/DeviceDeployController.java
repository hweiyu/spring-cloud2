package com.imin.adminweb.controller.agent;


import com.imin.adminweb.controller.BaseController;
import com.imin.adminweb.dto.request.agent.AgentQueryReqDto;
import com.imin.adminweb.dto.request.agent.DeviceDeployQueryReqDto;
import com.imin.adminweb.dto.response.agent.AgentResDto;
import com.imin.adminweb.dto.response.agent.DeviceDeployResDto;
import com.imin.adminweb.service.agent.AgentService;
import com.imin.adminweb.service.agent.DeviceDeployService;
import com.imin.infrastructure.common.aop.PopedomType;
import com.imin.infrastructure.common.aop.RequestProcess;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.result.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author code
 * @version V1.0
 * @Title: 基础代码
 * @Description: Demo用例
 **/
@RestController
@RequestMapping("${api.url.prefix}")
@Api(tags = {"设备部署信息接口"})
@Validated
public class DeviceDeployController extends BaseController {
    @Autowired
    private DeviceDeployService deviceDeployService;

    @ApiOperation(value = "设备部署数据列表", notes = "设备部署数据列表")
    @PostMapping(value = "/devicedeploy/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logUrl = "/devicedeploy/list", popedomCode = "demo",
            popedomType = PopedomType.View)
    public ResultData<PageInfo<DeviceDeployResDto>> getListOfDto(@Valid @RequestBody DeviceDeployQueryReqDto reqDto) {
        return ResultData.createSuccessResult(deviceDeployService.select(reqDto));
    }


}
