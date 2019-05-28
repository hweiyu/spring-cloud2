package com.imin.user.api;

import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.user.dto.reqeust.*;
import com.imin.user.dto.response.SysUserListResDto;
import com.imin.user.dto.response.SysUserResDto;
import com.imin.user.dto.response.SysUserResetPasswordResDto;
import com.imin.user.dto.response.UserRoleListResDto;
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
public interface SysUserApiService {

    /**
     * 获取系统用户数据列表
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "获取系统用户数据列表", notes = "获取系统用户数据列表")
    @PostMapping(value = "/sys/user/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData<PageInfo<SysUserListResDto>> list(@RequestBody SysUserQueryReqDto reqDto);

    /**
     * 获取系统用户数据
     * @param idDto
     * @return
     */
    @ApiOperation(value = "获取系统用户数据", notes = "获取系统用户数据")
    @PostMapping(value = "/sys/user/get", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData<SysUserResDto> get(@Validated() @RequestBody IdDto idDto);

    /**
     * 插入系统用户数据
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "插入系统用户数据", notes = "插入系统用户数据")
    @PostMapping(value = "/sys/user/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData<IdDto> insert(@Validated() @RequestBody SysUserInsertReqDto reqDto);

    /**
     * 修改系统用户数据
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "修改系统用户数据", notes = "修改系统用户数据")
    @PostMapping(value = "/sys/user/update", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData<IdDto> update(@Validated() @RequestBody SysUserUpdateReqDto reqDto);

    /**
     * 启用/禁用账号(0:禁用;1:启用)
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "启用/禁用账号(0:禁用;1:启用)", notes = "启用/禁用账号(0:禁用;1:启用)")
    @PostMapping(value = "/sys/user/changeStatus", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData<IdDto> updateStatus(@Validated() @RequestBody SysUserUpdateStatusReqDto reqDto);

    /**
     * 重置密码
     * @param idDto
     * @return
     */
    @ApiOperation(value = "重置密码", notes = "重置密码")
    @PostMapping(value = "/sys/user/resetPassword", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData<SysUserResetPasswordResDto> resetPassword(@Validated() @RequestBody IdDto idDto);

    /**
     * 修改密码
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "修改密码", notes = "修改密码")
    @PostMapping(value = "/sys/user/updatePassword", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData<IdDto> updatePassword(@Validated() @RequestBody SysUserUpdatePasswordReqDto reqDto);

    /**
     * 角色列表
     * @return
     */
    @ApiOperation(value = "角色列表", notes = "角色列表")
    @PostMapping(value = "/sys/user/roleList", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData<List<UserRoleListResDto>> roleList(@Validated() @RequestBody UserIdReqDto reqDto);
}
