
package com.imin.user.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 *
 * @Title: TODO
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author ben
 * @date 2018年1月8日 上午9:25:44
 * @version V1.0  
 * 
 **/

@Component
public class ResoureMessage {
    @Autowired
    private ResourceBundleMessageSource messageSource;
    
    public String getMessage(String code) {
        Locale locale = LocaleContextHolder.getLocale();
        if (locale == null) {
            locale = new Locale("zh","CN");
        }
        try {
            return messageSource.getMessage(code, null, locale);
        } catch (Exception ex) {
            return code;
        }
    }
}
