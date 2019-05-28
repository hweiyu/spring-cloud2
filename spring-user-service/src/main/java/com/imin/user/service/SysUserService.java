package com.imin.user.service;

import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;

import com.imin.user.dto.reqeust.*;
import com.imin.user.dto.response.*;
import com.imin.user.model.SysUserModel;

import java.util.List;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 系统用户接口
 * @date 2018-11-27 18:19:55
 **/
public interface SysUserService {

    /**
    * 列表
    */
    PageInfo<SysUserListResDto> select(SysUserQueryReqDto reqDto);

    /**
    * 查询
    */
    SysUserResDto get(Long id);

    /**
    * 添加
    */
    IdDto insert(SysUserInsertReqDto reqDto);

    /**
    * 修改
    */
    IdDto update(SysUserUpdateReqDto reqDto);

    /**
     * 修改状态
     * @param reqDto
     * @return
     */
    IdDto updateStatus(SysUserUpdateStatusReqDto reqDto);

    /**
     * 重置密码
     * @param id
     * @return
     */
    SysUserResetPasswordResDto resetPassword(Long id);

    /**
     * 修改密码
     * @param reqDto
     * @return
     */
    IdDto updatePassword(SysUserUpdatePasswordReqDto reqDto);

    /**
     * 系统内置账号类型列表
     * @return
     */
    List<AccountTypeResDto> accountTypeList();

    /**
     * 通过登录名查用户信息
     * @param loginAccount
     * @return
     */
    SysUserModel getByLoginAccount(String loginAccount);

    /**
     * 根据用户id查角色列表
     */
    List<UserRoleListResDto> listByUserId(Long userId);
}

