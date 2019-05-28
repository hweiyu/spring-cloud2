package com.imin.user.service;

import com.imin.user.dto.reqeust.UserLoginReqDto;
import com.imin.user.dto.reqeust.UserLogoutReqDto;
import com.imin.user.dto.response.UserLoginResDto;
import com.imin.user.dto.response.UserLogoutResDto;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title 描述
 * @Description 描述
 * @date 2018/11/29 10:11
 **/
public interface LoginService {

    /**
     * 登录
     * @param reqDto
     * @return
     */
    UserLoginResDto login(UserLoginReqDto reqDto);

}
