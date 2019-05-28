package com.imin.adminweb.controller.user;

import com.imin.adminweb.dto.request.RoleQueryReqDto;
import com.imin.adminweb.service.TokenService;
import com.imin.adminweb.service.user.SysRoleService;
import com.imin.infrastructure.common.aop.RequestProcess;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.user.dto.reqeust.SysRoleInsertReqDto;
import com.imin.user.dto.reqeust.SysRoleQueryReqDto;
import com.imin.user.dto.reqeust.SysRoleUpdateReqDto;
import com.imin.user.dto.reqeust.SysRoleUpdateStatusReqDto;
import com.imin.user.dto.response.SysRoleListResDto;
import com.imin.user.dto.response.SysRoleResDto;
import com.imin.user.dto.response.UserLoginResDto;
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
 * @Description: 系统角色控制器
 * @date 2018-11-27 18:19:55
 **/
@RestController
@RequestMapping("${api.url.prefix}")
@Api(tags = {"系统角色接口"})
public class SysRoleController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 获取系统角色数据列表
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "获取系统角色数据列表", notes = "获取系统角色数据列表")
    @PostMapping(value = "/sys/role/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, popedomCode = "cloud:platform:role:select", logName = "获取系统角色数据列表", logUrl = "/sys/role/list")
    public ResultData<PageInfo<SysRoleListResDto>> list(@RequestBody RoleQueryReqDto reqDto){
        UserLoginResDto userInfo = tokenService.getAndCheckCurrentUserInfo();
        return ResultData.createSuccessResult(sysRoleService.select(SysRoleQueryReqDto.builder()
                .roleName(reqDto.getRoleName())
                .dataStatus(reqDto.getDataStatus())
                .userId(userInfo.getId())
                .build()));
    }

    /**
     * 通过用户id获取系统角色数据列表
     * @param idDto
     * @return
     */
    @ApiOperation(value = "通过用户id获取系统角色数据列表", notes = "通过用户id获取系统角色数据列表")
    @PostMapping(value = "/sys/role/listByUserId", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logName = "通过用户id获取系统角色数据列表", logUrl = "/sys/role/listByUserId")
    public ResultData<List<SysRoleListResDto>> listByUserId(@RequestBody IdDto idDto){
        return ResultData.createSuccessResult(sysRoleService.listByUserId(idDto.getId()));
    }

    /**
     * 获取系统角色数据
     * @param idDto
     * @return
     */
    @ApiOperation(value = "获取系统角色数据", notes = "获取系统角色数据")
    @PostMapping(value = "/sys/role/get", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, popedomCode = "cloud:platform:role:select", logName = "获取系统角色数据", logUrl = "/sys/role/get")
    public ResultData<SysRoleResDto> get(@Validated() @RequestBody IdDto idDto){
        return ResultData.createSuccessResult(sysRoleService.get(idDto.getId()));
    }

    /**
     * 插入系统角色数据
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "插入系统角色数据", notes = "插入系统角色数据")
    @PostMapping(value = "/sys/role/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, popedomCode = "cloud:platform:role:insert", logName = "插入系统角色数据", logUrl = "/sys/role/insert")
    public ResultData<IdDto> insert(@Validated() @RequestBody SysRoleInsertReqDto reqDto){
        return ResultData.createSuccessResult(sysRoleService.insert(reqDto));
    }

    /**
     * 修改系统角色数据
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "修改系统角色数据", notes = "修改系统角色数据")
    @PostMapping(value = "/sys/role/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, popedomCode = "cloud:platform:role:update", logName = "修改系统角色数据", logUrl = "/sys/role/update")
    public ResultData<IdDto> update(@Validated() @RequestBody SysRoleUpdateReqDto reqDto){
        return ResultData.createSuccessResult(sysRoleService.update(reqDto));
    }

    /**
     * 启用/禁用角色
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "启用/禁用角色", notes = "启用/禁用角色")
    @PostMapping(value = "/sys/role/updateStatus", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, popedomCode = "cloud:platform:role:update", logName = "启用/禁用角色", logUrl = "/sys/role/updateStatus")
    public ResultData<IdDto> updateStatus(@Validated() @RequestBody SysRoleUpdateStatusReqDto reqDto){
        return ResultData.createSuccessResult(sysRoleService.updateStatus(reqDto));
    }

}
