package com.imin.basic.advice;

import com.imin.infrastructure.common.web.spring.RestResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * @author code
 * @version V1.0
 * @Title: 基础代码
 * @Description: 异常处理器,该类会处理所有在执行标有@RequestMapping注解的方法时发生的异常.
 **/
@ControllerAdvice
public class MyAppResponseEntityExceptionHandler extends RestResponseEntityExceptionHandler {

}
