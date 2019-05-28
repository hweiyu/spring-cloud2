package com.imin.adminweb.service.user;

import com.imin.adminweb.dto.request.UserBindOpenIdReqDto;
import com.imin.adminweb.dto.request.UserOpenIdReqDto;
import com.imin.adminweb.model.user.SysUserModel;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.user.dto.reqeust.*;
import com.imin.user.dto.response.*;

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
     * 通过登录名查用户信息
     * @param loginAccount
     * @return
     */
    SysUserModel getByLoginAccount(String loginAccount);

    /**
     * 根据用户id查角色列表
     */
    List<UserRoleListResDto> listByUserId(Long userId);

    /**
     * 通过id集合查列表
     * @param ids
     * @return
     */
    List<SysUserModel> listByIds(List<Long> ids);

    /**
     * 通过角色id查用户
     * @param roleId
     * @return
     */
    List<SysUserModel> listByRoleId(Long roleId);

    /**
     * 查询
     */
    SysUserResDto getByOpenId(String openId);

    /**
     * 绑定openId
     * @param reqDto
     * @return
     */
    boolean bindOpenId(UserBindOpenIdReqDto reqDto);

    /**
     * 解绑openId
     * @param reqDto
     * @return
     */
    boolean unBindOpenId(UserBindOpenIdReqDto reqDto);
}

