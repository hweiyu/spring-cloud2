package com.imin.adminweb.service.user;

import com.imin.user.dto.reqeust.UserLoginReqDto;
import com.imin.user.dto.response.UserLoginResDto;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title 描述
 * @Description 描述
 * @date 2018/11/29 10:11
 **/
public interface SysLoginService {

    /**
     * 登录
     * @param reqDto
     * @return
     */
    UserLoginResDto login(UserLoginReqDto reqDto);

}
