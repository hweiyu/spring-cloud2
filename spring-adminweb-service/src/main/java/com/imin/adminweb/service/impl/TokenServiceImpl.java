package com.imin.adminweb.service.impl;

import com.imin.adminweb.conf.ParamConfig;
import com.imin.adminweb.consts.RedisKeyConst;
import com.imin.adminweb.exception.AuthException;
import com.imin.adminweb.service.TokenService;
import com.imin.infrastructure.common.constants.ShareConstants;
import com.imin.infrastructure.common.redis.IRedisService;
import com.imin.infrastructure.common.utils.StringUtil;
import com.imin.user.dto.response.UserLoginResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title 描述
 * @Description 描述
 * @date 2018/12/3 16:48
 **/
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private IRedisService redisService;

    @Autowired
    private ParamConfig paramConfig;

    /**
     * 获取当前登录的用户信息
     *
     * @return
     */
    @Override
    public UserLoginResDto getCurrentUserInfo() {
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return null;
        }
        HttpServletRequest request = attributes.getRequest();
        if (request == null) {
            return null;
        }
        String token = request.getHeader(ShareConstants.TOKEN_HEADER_NAME);
        if (StringUtil.isEmptyOrNull(token)) {
            token = request.getParameter("token");
        }
        return getUserInfo(token);
    }

    /**
     * 校验并获取当前登录的用户信息
     *
     * @return
     */
    @Override
    public UserLoginResDto getAndCheckCurrentUserInfo() {
        UserLoginResDto userInfo = getCurrentUserInfo();
        if (null == userInfo) {
            throw new AuthException("authentication fails");
        }
        return userInfo;
    }

    /**
     * 获取当前用户信息
     *
     * @return
     */
    @Override
    public UserLoginResDto getUserInfo(String token) {
        String key = getTokenKey(token);
        if (token != null && redisService.exists(key)) {
            return redisService.getObject(key, UserLoginResDto.class);
        }
        return null;
    }

    /**
     * 保存用户token
     *
     * @param token
     * @param userInfo
     * @return
     */
    @Override
    public void saveToken(String token, UserLoginResDto userInfo) {
        String originToken = redisService.get(getUserKey(userInfo.getId()));
        if (!StringUtil.isEmptyOrNull(originToken)) {
            //清除旧token
            redisService.delKey(getTokenKey(originToken));
        }
        //设置用户最新的token
        redisService.set(getUserKey(userInfo.getId()), userInfo.getToken(), paramConfig.getTokenExpire());
        redisService.setObject(getTokenKey(token), userInfo, paramConfig.getTokenExpire());
    }

    private String getTokenKey(String token) {
        return RedisKeyConst.TOKEN_KEY + token;
    }

    private String getUserKey(Long userId) {
        return RedisKeyConst.USER_KEY + userId;
    }

    /**
     * 删除用户token
     *
     * @param token
     * @param userId
     * @return
     */
    @Override
    public boolean removeToken(String token, Long userId) {
        if (token != null) {
            UserLoginResDto userInfo = getUserInfo(token);
            if (userInfo != null && userInfo.getId().equals(userId)) {
                redisService.delKey(getUserKey(userId));
                redisService.delKey(getTokenKey(token));
                return true;
            }
        }
        return false;
    }

    /**
     * 判断用户token是否存在
     *
     * @param token
     * @return
     */
    @Override
    public boolean isTokenExists(String token) {
        return null != token && redisService.exists(getTokenKey(token));
    }

    /**
     * 设置key的失效时间
     * @param tokenStr
     * @param expireTime
     * @return
     */
    @Override
    public boolean expire(String tokenStr, long expireTime) {
        return redisService.expire(getTokenKey(tokenStr), expireTime);
    }
}
