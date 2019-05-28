package com.imin.adminweb.advice;

import com.imin.adminweb.service.TokenService;
import com.imin.infrastructure.common.aop.PopedomType;
import com.imin.infrastructure.common.aop.RequestProcess;
import com.imin.infrastructure.common.aop.RequestProcessAop;
import com.imin.adminweb.conf.ParamConfig;
import com.imin.infrastructure.common.constants.ShareConstants;
import com.imin.infrastructure.common.utils.StringUtil;
import com.imin.user.dto.response.UserLoginResDto;
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
public class WebLogWebAspect extends RequestProcessAop {

    @Autowired
    private ParamConfig paramConfig;

    @Autowired
    private TokenService tokenService;

    @Override
    protected boolean getCheckToken() {
        return !paramConfig.getNotCheckToken();
    }

    @Override
    protected boolean checkLoginAuth(String tokenStr) {
        if (StringUtil.isEmptyOrNull(tokenStr)) {
            return false;
        }
        boolean res = tokenService.isTokenExists(tokenStr);
        if (res) {
            tokenService.expire(tokenStr, paramConfig.getTokenExpire());
        }
        return res;
    }

    @Override
    protected boolean checkPopedom(RequestProcess process) {
        if (null == process) {
            return false;
        }
        if (PopedomType.None == process.popedomType()) {
            return true;
        }
        String authCode = process.popedomCode();
        if (StringUtil.isEmptyOrNull(authCode)) {
            return false;
        }
        UserLoginResDto userInfo = tokenService.getCurrentUserInfo();
        if (null == userInfo) {
            return false;
        }
        if (ShareConstants.SUPPER_ADMIN_ID.equals(userInfo.getId())) {
            return true;
        }
        return null != userInfo.getAuthCodes()
                && userInfo.getAuthCodes().contains(authCode);
    }

}
