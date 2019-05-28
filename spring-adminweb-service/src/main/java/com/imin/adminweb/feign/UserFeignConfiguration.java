package com.imin.adminweb.feign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * @Title: 基础信息feign配置
 * @Description: 基础信息feign配置
 * @author 黄维瑜
 * @date 2018/7/9 14:16
 * @version V1.0
 */
@Slf4j
public class UserFeignConfiguration {

    @Value("${user.verifyCode}")
    private String userToken;

    @Bean
    public FeignBasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        if (log.isDebugEnabled()) {
            log.debug("userToken:" + userToken);
        }
        return new FeignBasicAuthRequestInterceptor(userToken, "imin-user-service");
    }
}
