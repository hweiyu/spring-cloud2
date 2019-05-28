package com.imin.adminweb.controller.user;

import com.imin.adminweb.dto.request.UserBindOpenIdReqDto;
import com.imin.adminweb.dto.request.UserOpenIdReqDto;
import com.imin.adminweb.dto.request.UserUpdatePasswordReqDto;
import com.imin.adminweb.service.TokenService;
import com.imin.adminweb.service.user.SysUserService;
import com.imin.infrastructure.common.aop.RequestProcess;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.user.dto.reqeust.*;
import com.imin.user.dto.response.*;
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
 * @Description: 系统用户控制器
 * @date 2018-11-27 18:19:55
 **/
@RestController
@RequestMapping("${api.url.prefix}")
@Api(tags = {"系统用户接口"})
public class SysUserController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 获取系统用户数据列表
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "获取系统用户数据列表", notes = "获取系统用户数据列表")
    @PostMapping(value = "/sys/user/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, popedomCode = "cloud:platform:user:select", logName = "获取系统用户数据列表", logUrl = "/sys/user/list")
    public ResultData<PageInfo<SysUserListResDto>> list(@RequestBody SysUserQueryReqDto reqDto){
        return ResultData.createSuccessResult(sysUserService.select(reqDto));
    }

    /**
     * 获取系统用户数据
     * @param idDto
     * @return
     */
    @ApiOperation(value = "获取系统用户数据", notes = "获取系统用户数据")
    @PostMapping(value = "/sys/user/get", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, popedomCode = "cloud:platform:user:select", logName = "获取系统用户数据", logUrl = "/sys/user/get")
    public ResultData<SysUserResDto> get(@Validated() @RequestBody IdDto idDto){
        return ResultData.createSuccessResult(sysUserService.get(idDto.getId()));
    }

    /**
     * 插入系统用户数据
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "插入系统用户数据", notes = "插入系统用户数据")
    @PostMapping(value = "/sys/user/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, popedomCode = "cloud:platform:user:insert", logName = "插入系统用户数据", logUrl = "/sys/user/insert")
    public ResultData<IdDto> insert(@Validated() @RequestBody SysUserInsertReqDto reqDto){
        return ResultData.createSuccessResult(sysUserService.insert(reqDto));
    }

    /**
     * 修改系统用户数据
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "修改系统用户数据", notes = "修改系统用户数据")
    @PostMapping(value = "/sys/user/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, popedomCode = "cloud:platform:user:update", logName = "修改系统用户数据", logUrl = "/sys/user/update")
    public ResultData<IdDto> update(@Validated() @RequestBody SysUserUpdateReqDto reqDto){
        return ResultData.createSuccessResult(sysUserService.update(reqDto));
    }

    /**
     * 启用/禁用账号(0:禁用;1:启用)
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "启用/禁用账号(0:禁用;1:启用)", notes = "启用/禁用账号(0:禁用;1:启用)")
    @PostMapping(value = "/sys/user/changeStatus", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, popedomCode = "cloud:platform:user:updatestatus", logName = "启用/禁用账号(0:禁用;1:启用)", logUrl = "/sys/user/changeStatus")
    public ResultData<IdDto> updateStatus(@Validated() @RequestBody SysUserUpdateStatusReqDto reqDto){
        return ResultData.createSuccessResult(sysUserService.updateStatus(reqDto));
    }

    /**
     * 重置密码
     * @param idDto
     * @return
     */
    @ApiOperation(value = "重置密码", notes = "重置密码")
    @PostMapping(value = "/sys/user/resetPassword", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, popedomCode = "cloud:platform:user:resetpassword", logName = "启用/禁用账号(0:禁用;1:启用)", logUrl = "/sys/user/resetPassword")
    public ResultData<SysUserResetPasswordResDto> resetPassword(@Validated() @RequestBody IdDto idDto){
        return ResultData.createSuccessResult(sysUserService.resetPassword(idDto.getId()));
    }

    /**
     * 修改密码
     *
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "修改密码", notes = "修改密码")
    @PostMapping(value = "/sys/user/updatePassword", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, popedomCode = "cloud:platform:user:updatepassword", logName = "修改密码", logUrl = "/sys/user/updatePassword")
    public ResultData<IdDto> updatePassword(@Validated() @RequestBody UserUpdatePasswordReqDto reqDto) {
        UserLoginResDto userInfo = tokenService.getAndCheckCurrentUserInfo();
        IdDto idDto = sysUserService.updatePassword(SysUserUpdatePasswordReqDto.builder()
                .id(userInfo.getId())
                .oldPassword(reqDto.getOldPassword())
                .newPassword(reqDto.getNewPassword())
                .build());
        if (null != idDto) {
            tokenService.removeToken(userInfo.getToken(), userInfo.getId());
        }
        return ResultData.createSuccessResult(idDto);
    }

    /**
     * 角色列表
     * @return
     */
    @ApiOperation(value = "角色列表", notes = "角色列表")
    @PostMapping(value = "/sys/user/roleList", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/sys/user/roleList")
    public ResultData<List<UserRoleListResDto>> roleList(){
        UserLoginResDto userInfo = tokenService.getAndCheckCurrentUserInfo();
        return ResultData.createSuccessResult(sysUserService.listByUserId(userInfo.getId()));
    }

    /**
     * 通过openId获取系统用户数据
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "通过openId获取系统用户数据", notes = "通过openId获取系统用户数据")
    @PostMapping(value = "/sys/user/getByOpenId", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, popedomCode = "imin:user:get", logName = "通过openId获取系统用户数据", logUrl = "/sys/user/getByOpenId")
    public ResultData<SysUserResDto> getByOpenId(@Validated() @RequestBody UserOpenIdReqDto reqDto){
        return ResultData.createSuccessResult(sysUserService.getByOpenId(reqDto.getOpenId()));
    }

    /**
     * 绑定openId
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "绑定openId", notes = "绑定openId")
    @PostMapping(value = "/sys/user/bindOpenId", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, popedomCode = "imin:user:update", logName = "绑定openId", logUrl = "/sys/user/bindOpenId")
    public ResultData<Boolean> bindOpenId(@Validated() @RequestBody UserBindOpenIdReqDto reqDto){
        return ResultData.createSuccessResult(sysUserService.bindOpenId(reqDto));
    }

    /**
     * 解定openId
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "解定openId", notes = "解定openId")
    @PostMapping(value = "/sys/user/unBindOpenId", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, popedomCode = "imin:user:update", logName = "解定openId", logUrl = "/sys/user/unBindOpenId")
    public ResultData<Boolean> unBindOpenId(@Validated() @RequestBody UserBindOpenIdReqDto reqDto){
        return ResultData.createSuccessResult(sysUserService.unBindOpenId(reqDto));
    }

}
