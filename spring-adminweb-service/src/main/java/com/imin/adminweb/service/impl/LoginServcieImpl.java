package com.imin.adminweb.service.impl;

import com.google.code.kaptcha.Producer;
import com.imin.adminweb.consts.RedisKeyConst;
import com.imin.adminweb.dto.request.LoginReqDto;
import com.imin.adminweb.exception.AuthException;
import com.imin.adminweb.service.LoginService;
import com.imin.adminweb.service.TokenService;
import com.imin.adminweb.service.user.SysLoginService;
import com.imin.adminweb.service.user.SysUserLogService;
import com.imin.infrastructure.common.exception.ServiceException;
import com.imin.infrastructure.common.redis.IRedisService;
import com.imin.infrastructure.common.utils.ServletUtil;
import com.imin.infrastructure.common.utils.StringUtil;
import com.imin.user.dto.reqeust.SysUserLogInsertReqDto;
import com.imin.user.dto.reqeust.UserLoginReqDto;
import com.imin.user.dto.reqeust.UserLogoutReqDto;
import com.imin.user.dto.response.UserLoginResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title 描述
 * @Description 描述
 * @date 2018/12/3 16:52
 **/
@Service
public class LoginServcieImpl implements LoginService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private Producer producer;

    @Autowired
    private IRedisService redisService;

    @Value("${captcha.checkFlag}")
    private Boolean captchaCheckFlag;

    @Value("${captcha.defaultCode}")
    private String defaultCaptcha;

    @Autowired
    private SysLoginService sysLoginService;

    @Autowired
    private SysUserLogService sysUserLogService;

    /**
     * 登录
     *
     * @param reqDto
     */
    @Override
    public UserLoginResDto login(LoginReqDto reqDto) {
        //todo 校验验证码是否正确
//        if (!checkCaptcha(reqDto)) {
//            throw new ServiceException("captcha error");
//        }
        UserLoginResDto userInfo = sysLoginService.login(
                UserLoginReqDto.builder()
                        .loginName(reqDto.getLoginName())
                        .loginPassword(reqDto.getLoginPassword())
                        .platform(reqDto.getPlatform())
                        .build());
        if (null != userInfo) {
            tokenService.saveToken(userInfo.getToken(), userInfo);
            //记录登录日志
            sysUserLogService.insert(SysUserLogInsertReqDto.builder()
                    .userId(userInfo.getId())
                    .userName(userInfo.getAccount())
                    .ip(ServletUtil.getRequestIpAddress())
                    .build());
        }
        return userInfo;
    }

    private boolean checkCaptcha(LoginReqDto reqDto) {
        if (StringUtil.isEmptyOrNull(reqDto.getUniqueKey())
                || StringUtil.isEmptyOrNull(reqDto.getCaptcha())) {
            return false;
        }
        if(captchaCheckFlag && defaultCaptcha.equals(reqDto.getCaptcha())) {
            return true;
        }
        String captcha = redisService.get(RedisKeyConst.CAPTCHA_KEY + reqDto.getUniqueKey());
        if (StringUtil.isEmptyOrNull(captcha)) {
            return false;
        }
        return captcha.equalsIgnoreCase(reqDto.getCaptcha());
    }

    /**
     * 登出
     *
     * @param reqDto
     */
    @Override
    public Boolean logout(UserLogoutReqDto reqDto) {
        if (!tokenService.isTokenExists(reqDto.getToken())) {
            throw new AuthException("not find token");
        }
        UserLoginResDto userInfo = tokenService.getUserInfo(reqDto.getToken());
        if (null == userInfo) {
            throw new AuthException("not find user info");
        }
        if (!userInfo.getId().equals(reqDto.getId())) {
            throw new AuthException("user info is incorrect");
        }
        boolean result = tokenService.removeToken(reqDto.getToken(), reqDto.getId());
        if (!result) {
            throw new AuthException("logout error");
        }
        return true;
    }

    /**
     * 生成验证码图片
     *
     * @param uniqueKey
     * @return
     */
    @Override
    public BufferedImage createImage(String uniqueKey) {
        if (StringUtil.isEmptyOrNull(uniqueKey)) {
            throw new ServiceException("parameter is empty");
        }
        //生成文字验证码
        String code = producer.createText();
        //放到redis中
        redisService.set(RedisKeyConst.CAPTCHA_KEY + uniqueKey, code, 60);
        return producer.createImage(code);
    }
}
