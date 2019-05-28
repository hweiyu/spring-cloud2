package com.imin.user.controller;

import com.imin.infrastructure.common.aop.RequestProcess;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.user.api.UserLoginApiService;
import com.imin.user.dto.reqeust.UserLoginReqDto;
import com.imin.user.dto.response.UserLoginResDto;
import com.imin.user.service.LoginService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 系统用户登录控制器
 * @date 2018-11-27 18:19:55
 **/
@RestController
@RequestMapping("${api.url.prefix}")
@Api(tags = {"用户登录接口"})
public class LoginApiController implements UserLoginApiService {

    @Autowired
    private LoginService loginService;

    /**
     * 登录
     *
     * @param reqDto
     */
    @RequestProcess(checkLogin = false, logName = "登录", logUrl = "/platform/login")
    @Override
    public ResultData<UserLoginResDto> login(@RequestBody UserLoginReqDto reqDto) {
        return ResultData.createSuccessResult(loginService.login(reqDto));
    }

}
