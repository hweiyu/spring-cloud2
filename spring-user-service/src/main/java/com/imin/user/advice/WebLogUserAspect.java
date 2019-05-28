package com.imin.user.advice;

import com.imin.user.conf.ParamConfig;
import com.imin.infrastructure.common.aop.RequestProcess;
import com.imin.infrastructure.common.aop.RequestProcessAop;
import com.imin.infrastructure.common.result.TokenResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;


/**
 * @author code
 * @version V1.0
 * @Title: 基础代码
 * @Description: 基础信息
 **/
@Component
@Order(2)
@Aspect
@Slf4j
public class WebLogUserAspect extends RequestProcessAop {

    @Autowired
    private ParamConfig paramConfig;

    @Override
    protected boolean getCheckToken() {
        return !paramConfig.getNotCheckToken();
    }

    @Override
    protected boolean checkLoginAuth(String tokenStr) {
        return true;
    }

    @Override
    protected TokenResultEnum checkLoginAuthEx(String tokenStr, RequestProcess pjp){
        // 外部请求，签名校验
        if (2 == pjp.groupFlag()) {
            log.info("--------- member外部请求 ------------->");

            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            
            // 获取 POST 请求body中的所有参数
            try {
                BufferedReader bufferedReader = request.getReader();
                String bodyParam = getBodyString(bufferedReader);
                System.out.println("bodyParam:" + bodyParam);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return TokenResultEnum.TOKEN_OK;
        }

        // 内部请求
        if (null == tokenStr) {
            log.info("--------- member内部请求 -------------> tokenStr 为空");
            return TokenResultEnum.TOKEN_FAIL;
        }
        boolean tokenIsRight = tokenStr.equals(paramConfig.getVerifyCode());
        if (!tokenIsRight) {
            log.info("--------- member内部请求 -------------> tokenStr 不正确");
            return TokenResultEnum.TOKEN_FAIL;
        }

        // default
        return TokenResultEnum.TOKEN_OK;
    }

    @Override
    protected boolean checkPopedom(RequestProcess process) {
        return true;
    }

    //获取request请求body中参数
    private static String getBodyString(BufferedReader br) {
        String inputLine;
        String str = "";
        try {
            while ((inputLine = br.readLine()) != null) {
                str += inputLine;
            }
            br.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        return str;
    }
}
