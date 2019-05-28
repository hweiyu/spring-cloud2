package com.imin.adminweb.service;

import com.imin.user.dto.response.UserLoginResDto;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title 描述
 * @Description 描述
 * @date 2018/12/3 16:48
 **/
public interface TokenService {

    /**
     * 获取当前登录的用户信息
     * @return
     */
    UserLoginResDto getCurrentUserInfo();

    /**
     * 校验并获取当前登录的用户信息
     * @return
     */
    UserLoginResDto getAndCheckCurrentUserInfo();

    /**
     * 获取当前用户信息
     * @return
     */
    UserLoginResDto getUserInfo(String token);

    /**
     * 保存用户token
     * @param token
     * @param userInfo
     * @return
     */
    void saveToken(String token, UserLoginResDto userInfo);

    /**
     * 删除用户token
     * @param token
     * @param userId
     * @return
     */
    boolean removeToken(String token, Long userId);

    /**
     * 判断用户token是否存在
     * @param token
     * @return
     */
    boolean isTokenExists(String token);

    /**
     * 设置key的失效时间
     * @param tokenStr
     * @param expireTime
     * @return
     */
    boolean expire(String tokenStr, long expireTime);
}
