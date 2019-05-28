package com.imin.adminweb.controller.user;

import com.imin.adminweb.service.user.SysUserLogService;
import com.imin.infrastructure.common.aop.RequestProcess;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.user.dto.reqeust.SysUserLogQueryReqDto;
import com.imin.user.dto.response.SysUserLogListResDto;
import com.imin.user.dto.response.SysUserLogResDto;
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
 * @Description: 用户日志表控制器
 * @date 2018-11-27 18:19:55
 **/
@RestController
@RequestMapping("${api.url.prefix}")
@Api(tags = {"用户日志表接口"})
public class SysUserLogController {

    @Autowired
    private SysUserLogService sysUserLogService;

    /**
     * 获取用户日志表数据列表
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "获取用户日志表数据列表", notes = "获取用户日志表数据列表")
    @PostMapping(value = "/sys/userlog/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, popedomCode = "cloud:platform:userlog:select", logUrl = "/sys/userlog/list")
    public ResultData<PageInfo<SysUserLogListResDto>> list(@RequestBody SysUserLogQueryReqDto reqDto){
        return ResultData.createSuccessResult(sysUserLogService.select(reqDto));
    }

    /**
     * 获取用户日志表数据
     * @param idDto
     * @return
     */
    @ApiOperation(value = "获取用户日志表数据", notes = "获取用户日志表数据")
    @PostMapping(value = "/sys/userlog/get", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, popedomCode = "cloud:platform:userlog:select", logUrl = "/sys/userlog/get")
    public ResultData<SysUserLogResDto> get(@Validated() @RequestBody IdDto idDto){
        return ResultData.createSuccessResult(sysUserLogService.get(idDto.getId()));
    }

}
