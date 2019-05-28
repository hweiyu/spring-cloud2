package com.imin.adminweb.controller.user;

import com.imin.adminweb.service.user.SysOrgService;
import com.imin.infrastructure.common.aop.RequestProcess;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.user.dto.reqeust.*;
import com.imin.user.dto.response.SysOrgListResDto;
import com.imin.user.dto.response.SysOrgResDto;
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
 * @Description: 系统组织机构控制器
 * @date 2018-11-27 18:19:55
 **/
@RestController
@RequestMapping("${api.url.prefix}")
@Api(tags = {"系统组织机构接口"})
public class SysOrgController {

    @Autowired
    private SysOrgService sysOrgService;

    /**
     * 获取系统组织机构数据列表
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "获取系统组织机构数据列表", notes = "获取系统组织机构数据列表")
    @PostMapping(value = "/sys/org/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, popedomCode = "cloud:platform:org:select", logName = "获取系统组织机构数据列表", logUrl = "/sys/org/list")
    public ResultData<List<SysOrgListResDto>> list(@RequestBody SysOrgQueryReqDto reqDto){
        return ResultData.createSuccessResult(sysOrgService.select(reqDto));
    }

    /**
     * 获取系统组织机构数据
     * @param idDto
     * @return
     */
    @ApiOperation(value = "获取系统组织机构数据", notes = "获取系统组织机构数据")
    @PostMapping(value = "/sys/org/get", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, popedomCode = "cloud:platform:org:select", logName = "获取系统组织机构数据", logUrl = "/sys/org/get")
    public ResultData<SysOrgResDto> get(@Validated() @RequestBody IdDto idDto){
        return ResultData.createSuccessResult(sysOrgService.get(idDto.getId()));
    }

    /**
     * 插入系统组织机构数据
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "插入系统组织机构数据", notes = "插入系统组织机构数据")
    @PostMapping(value = "/sys/org/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, popedomCode = "cloud:platform:org:insert", logName = "插入系统组织机构数据", logUrl = "/sys/org/insert")
    public ResultData<IdDto> insert(@Validated() @RequestBody SysOrgInsertReqDto reqDto){
        return ResultData.createSuccessResult(sysOrgService.insert(reqDto));
    }

    /**
     * 修改系统组织机构数据
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "修改系统组织机构数据", notes = "修改系统组织机构数据")
    @PostMapping(value = "/sys/org/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, popedomCode = "cloud:platform:org:update", logName = "修改系统组织机构数据", logUrl = "/sys/org/update")
    public ResultData<IdDto> update(@Validated() @RequestBody SysOrgUpdateReqDto reqDto){
        return ResultData.createSuccessResult(sysOrgService.update(reqDto));
    }

    /**
     * 获取系统组织机构数据
     * @param idDto
     * @return
     */
    @ApiOperation(value = "获取系统组织机构数据", notes = "获取系统组织机构数据")
    @PostMapping(value = "/sys/org/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, popedomCode = "cloud:platform:org:delete", logName = "获取系统组织机构数据", logUrl = "/sys/org/get")
    public ResultData<IdDto> delete(@Validated() @RequestBody IdDto idDto){
        return ResultData.createSuccessResult(sysOrgService.delete(idDto.getId()));
    }

}
