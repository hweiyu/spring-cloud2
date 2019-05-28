package com.imin.user.api;

import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.user.dto.reqeust.SysRoleInsertReqDto;
import com.imin.user.dto.reqeust.SysRoleQueryReqDto;
import com.imin.user.dto.reqeust.SysRoleUpdateReqDto;
import com.imin.user.dto.reqeust.SysRoleUpdateStatusReqDto;
import com.imin.user.dto.response.SysRoleListResDto;
import com.imin.user.dto.response.SysRoleResDto;
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
public interface SysRoleApiService {

    /**
     * 获取系统角色数据列表
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "获取系统角色数据列表", notes = "获取系统角色数据列表")
    @PostMapping(value = "/sys/role/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData<PageInfo<SysRoleListResDto>> list(@RequestBody SysRoleQueryReqDto reqDto);

    /**
     * 获取系统角色数据列表
     * @param idDto
     * @return
     */
    @ApiOperation(value = "获取系统角色数据列表", notes = "获取系统角色数据列表")
    @PostMapping(value = "/sys/role/listByUserId", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData<List<SysRoleListResDto>> listByUserId(@RequestBody IdDto idDto);

    /**
     * 获取系统角色数据
     * @param idDto
     * @return
     */
    @ApiOperation(value = "获取系统角色数据", notes = "获取系统角色数据")
    @PostMapping(value = "/sys/role/get", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData<SysRoleResDto> get(@Validated() @RequestBody IdDto idDto);

    /**
     * 插入系统角色数据
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "插入系统角色数据", notes = "插入系统角色数据")
    @PostMapping(value = "/sys/role/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData<IdDto> insert(@Validated() @RequestBody SysRoleInsertReqDto reqDto);

    /**
     * 修改系统角色数据
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "修改系统角色数据", notes = "修改系统角色数据")
    @PostMapping(value = "/sys/role/update", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData<IdDto> update(@Validated() @RequestBody SysRoleUpdateReqDto reqDto);

    /**
     * 启用/禁用角色
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "启用/禁用角色", notes = "启用/禁用角色")
    @PostMapping(value = "/sys/role/updateStatus", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData<IdDto> updateStatus(@Validated() @RequestBody SysRoleUpdateStatusReqDto reqDto);
}
