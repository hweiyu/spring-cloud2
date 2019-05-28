
package com.imin.adminweb.feign;

import com.imin.infrastructure.common.constants.ShareConstants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

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
        //il8n
//        String language = getLanguageFromHttpRequest();
//        if (null != language) {
//            template.header(ShareConstants.IL8N_LANGUAGE, language);
//        }
    }

    private String getLanguageFromHttpRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return request.getHeader(ShareConstants.IL8N_LANGUAGE);
    }
}
