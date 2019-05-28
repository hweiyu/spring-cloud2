package com.imin.adminweb.controller.user;

import com.imin.adminweb.service.user.SysResourceService;
import com.imin.infrastructure.common.aop.RequestProcess;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.user.dto.reqeust.*;
import com.imin.user.dto.response.SysResourceListResDto;
import com.imin.user.dto.response.SysResourceResDto;
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
 * @Description: 系统菜单权限资源控制器
 * @date 2018-11-27 18:19:55
 **/
@RestController
@RequestMapping("${api.url.prefix}")
@Api(tags = {"系统菜单权限资源接口"})
public class SysResourceController {

    @Autowired
    private SysResourceService sysResourceService;

    /**
     * 获取系统菜单权限资源数据列表
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "获取系统菜单权限资源数据列表", notes = "获取系统菜单权限资源数据列表")
    @PostMapping(value = "/sys/resource/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, popedomCode = "imin:resource:select", logName = "获取系统菜单权限资源数据列表", logUrl = "/sys/resource/list")
    public ResultData<List<SysResourceListResDto>> list(@RequestBody SysResourceQueryReqDto reqDto){
        return ResultData.createSuccessResult(sysResourceService.select(reqDto));
    }

    /**
     * 获取系统菜单权限资源数据
     * @param idDto
     * @return
     */
    @ApiOperation(value = "获取系统菜单权限资源数据", notes = "获取系统菜单权限资源数据")
    @PostMapping(value = "/sys/resource/get", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, popedomCode = "imin:resource:select", logName = "获取系统菜单权限资源数据", logUrl = "/sys/resource/get")
    public ResultData<SysResourceResDto> get(@Validated() @RequestBody IdDto idDto){
        return ResultData.createSuccessResult(sysResourceService.get(idDto.getId()));
    }

    /**
     * 插入系统菜单权限资源数据
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "插入系统菜单权限资源数据", notes = "插入系统菜单权限资源数据")
    @PostMapping(value = "/sys/resource/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, popedomCode = "imin:resource:insert", logName = "插入系统菜单权限资源数据", logUrl = "/sys/resource/insert")
    public ResultData<IdDto> insert(@Validated() @RequestBody SysResourceInsertReqDto reqDto){
        return ResultData.createSuccessResult(sysResourceService.insert(reqDto));
    }

    /**
     * 修改系统菜单权限资源数据
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "修改系统菜单权限资源数据", notes = "修改系统菜单权限资源数据")
    @PostMapping(value = "/sys/resource/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, popedomCode = "imin:resource:update", logName = "修改系统菜单权限资源数据", logUrl = "/sys/resource/update")
    public ResultData<IdDto> update(@Validated() @RequestBody SysResourceUpdateReqDto reqDto){
        return ResultData.createSuccessResult(sysResourceService.update(reqDto));
    }

    /**
     * 启用/禁用权限菜单
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "启用/禁用权限菜单", notes = "启用/禁用权限菜单")
    @PostMapping(value = "/sys/resource/updateStatus", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, popedomCode = "imin:resource:update", logName = "启用/禁用权限菜单", logUrl = "/sys/resource/updateStatus")
    public ResultData<IdDto> updateStatus(@Validated() @RequestBody SysResourceUpdateStatusReqDto reqDto){
        return ResultData.createSuccessResult(sysResourceService.update(reqDto.to()));
    }

    /**
     * 通过用户id获取系统菜单权限资源数据列表
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "通过用户id获取系统菜单权限资源数据列表", notes = "通过用户id获取系统菜单权限资源数据列表")
    @PostMapping(value = "/sys/resource/listByUserId", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logName = "通过用户id获取系统菜单权限资源数据列表", logUrl = "/sys/resource/listByUserId")
    public ResultData<List<SysResourceListResDto>> listByUserId(@RequestBody SysResourceUserQueryReqDto reqDto) {
        return ResultData.createSuccessResult(sysResourceService.listByUserId(reqDto.getUserId()));
    }

}
