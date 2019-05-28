package com.imin.user.api;

import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.user.dto.reqeust.*;
import com.imin.user.dto.response.SysResourceListResDto;
import com.imin.user.dto.response.SysResourceResDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title 描述
 * @Description 描述
 * @date 2018/11/29 9:49
 **/
public interface SysResourceApiService {

    /**
     * 获取系统菜单权限资源数据列表
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "获取系统菜单权限资源数据列表", notes = "获取系统菜单权限资源数据列表")
    @PostMapping(value = "/sys/resource/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData<List<SysResourceListResDto>> list(@RequestBody SysResourceQueryReqDto reqDto);

    /**
     * 通过用户id获取系统菜单权限资源数据列表
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "通过用户id获取系统菜单权限资源数据列表", notes = "通过用户id获取系统菜单权限资源数据列表")
    @PostMapping(value = "/sys/resource/listByUserId", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData<List<SysResourceListResDto>> listByUserId(@RequestBody SysResourceUserQueryReqDto reqDto);

    /**
     * 获取系统菜单权限资源数据
     * @param idDto
     * @return
     */
    @ApiOperation(value = "获取系统菜单权限资源数据", notes = "获取系统菜单权限资源数据")
    @PostMapping(value = "/sys/resource/get", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData<SysResourceResDto> get(@Validated() @RequestBody IdDto idDto);

    /**
     * 插入系统菜单权限资源数据
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "插入系统菜单权限资源数据", notes = "插入系统菜单权限资源数据")
    @PostMapping(value = "/sys/resource/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData<IdDto> insert(@Validated() @RequestBody SysResourceInsertReqDto reqDto);

    /**
     * 修改系统菜单权限资源数据
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "修改系统菜单权限资源数据", notes = "修改系统菜单权限资源数据")
    @PostMapping(value = "/sys/resource/update", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData<IdDto> update(@Validated() @RequestBody SysResourceUpdateReqDto reqDto);

    /**
     * 启用/禁用权限菜单
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "启用/禁用权限菜单", notes = "启用/禁用权限菜单")
    @PostMapping(value = "/sys/resource/updateStatus", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData<IdDto> updateStatus(@Validated() @RequestBody SysResourceUpdateStatusReqDto reqDto);
}
