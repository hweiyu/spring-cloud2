package com.imin.basic.advice;

import com.imin.basic.conf.ParamConfig;
import com.imin.infrastructure.common.aop.RequestProcess;
import com.imin.infrastructure.common.aop.RequestProcessAop;
import com.imin.infrastructure.common.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

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
public class WebLogBasicAspect extends RequestProcessAop {

    @Autowired
    private ParamConfig paramConfig;

    @Override
    protected boolean getCheckToken() {
        return !paramConfig.getNotCheckToken();
    }

    @Override
    protected boolean checkLoginAuth(String tokenStr) {
        return !StringUtil.isEmptyOrNull(tokenStr)
                && tokenStr.equals(paramConfig.getVerifyCode());
    }

    @Override
    protected boolean checkPopedom(RequestProcess process) {
        return true;
    }
}
