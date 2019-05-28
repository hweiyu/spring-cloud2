package com.imin.example.conf.web;

import com.imin.example.conf.BaseRestApiConfig;
import com.imin.example.conf.ParamConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author code
 * @version V1.0
 * @Title: 基础代码
 * @Description: 基础信息 Restful API 访问路径: http://IP:port/{context-path}/documentation/swagger-ui.html
 *               http://springfox.github.io/springfox/docs/current/#springfox-swagger2-with-spring-mvc-and-spring-boot
 */

@Configuration
@EnableSwagger2
public class RestApiConfig extends BaseRestApiConfig {

    @Value("${swagger.enable}")
    private boolean enableSwagger;

    @Autowired
    private ParamConfig paramConfig;

    @Value("${from}")
    private String from;

    @Value("${info.app.build.ver}")
    private String buildVer;

    @Override
    protected boolean getEnableSwagger() {
        return enableSwagger;
    }

    @Override
    protected boolean getTokenParam() {
        return !paramConfig.getNotCheckToken();
    }

    @Override
    protected String getBasePackage() {
        return "com.imin.example.controller";
    }

    @Override
    protected String getDescName() {
        return "imin example " + from;
    }

    @Override
    protected String getVersion() {
        return buildVer;
    }
}
