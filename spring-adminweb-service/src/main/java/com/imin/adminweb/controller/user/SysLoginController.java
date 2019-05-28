package com.imin.adminweb.controller.user;

import com.imin.adminweb.dto.request.LoginReqDto;
import com.imin.adminweb.service.LoginService;
import com.imin.infrastructure.common.aop.RequestProcess;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.user.dto.reqeust.UserLogoutReqDto;
import com.imin.user.dto.response.UserLoginResDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
@Slf4j
public class SysLoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录
     *
     * @param reqDto
     */
    @ApiOperation(value = "登录", notes = "登录")
    @PostMapping(value = "/platform/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logName = "登录", logUrl = "/platform/login")
    public ResultData<UserLoginResDto> login(@Validated() @RequestBody LoginReqDto reqDto) {
        return ResultData.createSuccessResult(loginService.login(reqDto));
    }

    /**
     * 登出
     *
     * @param reqDto
     */
    @ApiOperation(value = "登出", notes = "登出")
    @PostMapping(value = "/platform/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logName = "登出", logUrl = "/platform/logout")
    public ResultData<Boolean> logout(@Validated() @RequestBody UserLogoutReqDto reqDto) {
        return ResultData.createSuccessResult(loginService.logout(reqDto));
    }

    /**
     * 获取验证码
     * @param response
     * @param uniqueKey
     * @throws IOException
     */
    @ApiOperation(value = "获取验证码", notes = "获取验证码")
    @GetMapping(value = "/platform/captcha")
    @RequestProcess(checkLogin = false, logUrl = "/platform/captcha")
    public void captcha(HttpServletResponse response, String uniqueKey) {
        setResponseInfo(response);
        // 获取图片验证码
        BufferedImage image = loginService.createImage(uniqueKey);
        try(ServletOutputStream out = response.getOutputStream()) {
            ImageIO.write(image, "jpg", out);
        } catch (Exception e) {
            log.error("生成验证码异常", e);
        }
    }

    private void setResponseInfo(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
    }
}
