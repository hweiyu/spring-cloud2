
package com.imin.user.feign;

import com.imin.infrastructure.common.constants.ShareConstants;
import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 公共认证拦截器
 * @Description: 描述
 * @date 2018/4/25 10:20
 **/
public class FeignBasicAuthRequestInterceptor implements RequestInterceptor {

    private String token;
    private String serviceName;
    
    public FeignBasicAuthRequestInterceptor(String token, String serviceName) {
        this.token = token;
        this.serviceName = serviceName;
    }

    @Override
    public void apply(RequestTemplate template) {
        template.header(ShareConstants.TOKEN_HEADER_NAME, token);
        template.header(ShareConstants.TOKEN_SERVICE_NAME, serviceName);
    }

}
