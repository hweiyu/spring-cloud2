package com.imin.adminweb.advice;

import com.imin.infrastructure.common.result.ResultData;
import com.imin.infrastructure.common.web.spring.RestResponseEntityExceptionHandler;
import com.imin.adminweb.exception.MyRemoteServiceException;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * @author code
 * @version V1.0
 * @Title: 基础代码
 * @Description: 异常处理器,该类会处理所有在执行标有@RequestMapping注解的方法时发生的异常.
 **/
@ControllerAdvice
public class MyAppResponseEntityExceptionHandler extends RestResponseEntityExceptionHandler {

    @ExceptionHandler(value = MyRemoteServiceException.class)
    public ResponseEntity<Object> myException(final Exception ex, final WebRequest request) {
        log.error("500 Status Code", ex);

        final ResultData<?> apiError = message(ex);

        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = HystrixRuntimeException.class)
    public ResponseEntity<Object> hystrixRuntimeException(final Exception ex, final WebRequest request) {
        log.error("500 Status Code", ex);

        final ResultData<?> apiError = message(ex);

        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
