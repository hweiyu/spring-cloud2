package com.imin.adminweb.exception;

import com.imin.infrastructure.common.exception.ServiceException;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title 描述
 * @Description 描述
 * @date 2018/12/3 17:28
 **/
public class AuthException extends ServiceException {

    private static final long serialVersionUID = -8291061501567019208L;

    public AuthException(String message) {
        super(message);
    }
}
