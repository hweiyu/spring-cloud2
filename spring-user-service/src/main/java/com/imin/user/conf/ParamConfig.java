
package com.imin.user.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author code
 * @version V1.0
 * @Title: 基础代码
 * @Description: 基础信息
 **/
@Component
public class ParamConfig {
    @Autowired
    private Environment env;

    public boolean getNotCheckToken() {
        String value = env.getProperty("defconf.nottoken");
        return "1".equals(value);
    }

    public boolean getWebLog() {
        String value = env.getProperty("defconf.weblog");
        return "1".equals(value);
    }

    public long getTokenExpire() {
        String value = env.getProperty("defconf.tokenExpire");
        try {
            return Long.parseLong(value);
        } catch (Exception ex) {
            ex.printStackTrace();
            // 一天
            return 60 * 60 * 24; 
        }
    }

    public String getVerifyCode() {
        return env.getProperty("defconf.verifyCode");
    }
}
