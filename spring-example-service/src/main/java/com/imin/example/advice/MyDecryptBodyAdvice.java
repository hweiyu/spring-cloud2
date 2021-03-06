package com.imin.example.advice;

import com.imin.infrastructure.common.aop.RequestProcess;
import com.imin.infrastructure.common.utils.encrypt.EncryptDesUtil;
import com.imin.infrastructure.common.web.spring.AbstractDecryptBodyAdvice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * @see RequestProcess#decrypt(), 解密时请设置此值为true
 * @see EncryptDesUtil#decrypt(java.lang.String, java.lang.String), 解密使用算法
 * @author huangweiyu
 * @version V1.0
 * @Title 描述
 * @Description 解密
 * @date 2019/2/13 16:32
 **/
@ControllerAdvice
public class MyDecryptBodyAdvice extends AbstractDecryptBodyAdvice {

    @Value("${security.request.key}")
    private String key;

    @Override
    public String getKey() {
        return key;
    }
}
