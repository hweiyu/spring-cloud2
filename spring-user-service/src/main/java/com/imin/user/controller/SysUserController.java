package com.imin.user.controller;

import com.imin.infrastructure.common.dto.IdDto;
import com.imin.user.api.SysUserApiService;
import com.imin.user.dto.reqeust.*;
import com.imin.user.dto.response.*;
import io.swagger.annotations.Api;

import com.imin.infrastructure.common.aop.RequestProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.infrastructure.common.dto.PageInfo;
import org.springframework.validation.annotation.Validated;

import com.imin.user.service.SysUserService;

import java.util.List;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 系统用户控制器
 * @date 2018-11-27 18:19:55
 **/
@RestController
@RequestMapping("${api.url.prefix}")
@Api(tags = {"系统用户接口"})
public class SysUserController implements SysUserApiService {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 获取系统用户数据列表
     * @param reqDto
     * @return
     */
    @RequestProcess(checkLogin = true, logUrl = "/sys/user/list")
    @Override
    public ResultData<PageInfo<SysUserListResDto>> list(@RequestBody SysUserQueryReqDto reqDto){
        return ResultData.createSuccessResult(sysUserService.select(reqDto));
    }

    /**
     * 获取系统用户数据
     * @param idDto
     * @return
     */
    @RequestProcess(checkLogin = true, logUrl = "/sys/user/get")
    @Override
    public ResultData<SysUserResDto> get(@Validated() @RequestBody IdDto idDto){
        return ResultData.createSuccessResult(sysUserService.get(idDto.getId()));
    }

    /**
     * 插入系统用户数据
     * @param reqDto
     * @return
     */
    @RequestProcess(checkLogin = true, logUrl = "/sys/user/insert")
    @Override
    public ResultData<IdDto> insert(@Validated() @RequestBody SysUserInsertReqDto reqDto){
        return ResultData.createSuccessResult(sysUserService.insert(reqDto));
    }

    /**
     * 修改系统用户数据
     * @param reqDto
     * @return
     */
    @RequestProcess(checkLogin = true, logUrl = "/sys/user/update")
    @Override
    public ResultData<IdDto> update(@Validated() @RequestBody SysUserUpdateReqDto reqDto){
        return ResultData.createSuccessResult(sysUserService.update(reqDto));
    }

    /**
     * 启用/禁用账号(0:禁用;1:启用)
     * @param reqDto
     * @return
     */
    @RequestProcess(checkLogin = true, logUrl = "/sys/user/changeStatus")
    @Override
    public ResultData<IdDto> updateStatus(@Validated() @RequestBody SysUserUpdateStatusReqDto reqDto){
        return ResultData.createSuccessResult(sysUserService.updateStatus(reqDto));
    }

    /**
     * 重置密码
     * @param idDto
     * @return
     */
    @RequestProcess(checkLogin = true, logUrl = "/sys/user/resetPassword")
    @Override
    public ResultData<SysUserResetPasswordResDto> resetPassword(@Validated() @RequestBody IdDto idDto){
        return ResultData.createSuccessResult(sysUserService.resetPassword(idDto.getId()));
    }

    /**
     * 修改密码
     *
     * @param reqDto
     * @return
     */
    @RequestProcess(checkLogin = true, logUrl = "/sys/user/updatePassword")
    @Override
    public ResultData<IdDto> updatePassword(@Validated() @RequestBody SysUserUpdatePasswordReqDto reqDto) {
        return ResultData.createSuccessResult(sysUserService.updatePassword(reqDto));
    }

    /**
     * 角色列表
     *
     * @param reqDto
     * @return
     */
    @Override
    public ResultData<List<UserRoleListResDto>> roleList(@Validated() @RequestBody UserIdReqDto reqDto) {
        return ResultData.createSuccessResult(sysUserService.listByUserId(reqDto.getUserId()));
    }

}
