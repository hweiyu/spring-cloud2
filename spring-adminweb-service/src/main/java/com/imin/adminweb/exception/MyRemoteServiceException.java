package com.imin.adminweb.exception;

import com.netflix.hystrix.HystrixInvokable;
import com.netflix.hystrix.exception.HystrixRuntimeException;

/**
 * @Title: TODO
 * @Description: TODO(用一句话描述该文件做什么)
 * @author Steven Liu 刘洋
 * @date 2018年7月30日 下午7:55:56
 * @version V1.0
 **/
public class MyRemoteServiceException extends HystrixRuntimeException {

    public MyRemoteServiceException(String message, Exception cause, Throwable fallbackException) {
        this(HystrixRuntimeException.FailureType.BAD_REQUEST_EXCEPTION, MyHystrixInvokable.class, message, cause,
                fallbackException);
    }

    public MyRemoteServiceException(FailureType failureCause, Class<? extends HystrixInvokable<Object>> commandClass, String message,
            Exception cause, Throwable fallbackException) {
        super(failureCause, commandClass, message, cause, fallbackException);
    }

    /**
     * @Field @serialVersionUID : TODO
     */
    private static final long serialVersionUID = 1L;

    static class MyHystrixInvokable implements HystrixInvokable<Object> {

    }
}
