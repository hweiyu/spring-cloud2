package com.imin.adminweb.exception;

import com.imin.infrastructure.common.exception.ServiceException;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title 描述
 * @Description 描述
 * @date 2018/11/29 10:30
 **/
public class UserException extends ServiceException {

    private static final long serialVersionUID = -5523793019501079346L;

    public UserException(String message) {
        super(message);
    }
}
