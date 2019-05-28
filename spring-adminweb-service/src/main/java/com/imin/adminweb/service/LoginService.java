package com.imin.adminweb.service;

import com.imin.adminweb.dto.request.LoginReqDto;
import com.imin.user.dto.reqeust.UserLogoutReqDto;
import com.imin.user.dto.response.UserLoginResDto;

import java.awt.image.BufferedImage;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title 描述
 * @Description 描述
 * @date 2018/12/3 16:51
 **/
public interface LoginService {

    /**
     * 登录
     */
    UserLoginResDto login(LoginReqDto reqDto);

    /**
     * 登出
     */
    Boolean logout(UserLogoutReqDto reqDto);

    /**
     * 生成验证码图片
     * @param uniqueKey
     * @return
     */
    BufferedImage createImage(String uniqueKey);
}
