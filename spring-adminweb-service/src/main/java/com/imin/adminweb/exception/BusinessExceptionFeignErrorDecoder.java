package com.imin.adminweb.exception;

import com.netflix.hystrix.exception.HystrixBadRequestException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Title: TODO
 * @Description: TODO(用一句话描述该文件做什么)
 * @author Steven Liu 刘洋
 * @date 2018年7月31日 上午9:32:02
 * @version V1.0
 **/

@Component
@Slf4j
public class BusinessExceptionFeignErrorDecoder extends ErrorDecoder.Default {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (isRequestSuccess(response.status())) {
            return super.decode(methodKey, response);
        }
        String error = "feign请求异常, url:" + response.request().url() + ", " +
                "status:" + response.status() + ", " +
                "reason:" + response.reason();
        return new HystrixBadRequestException(error);
    }

    private boolean isRequestSuccess(int status) {
        //http状态码在[200, 300)之间时，表示成功
        final int successCodeStart = 200;
        final int successCodeEnd = 300;
        return status >= successCodeStart && status < successCodeEnd;
    }
}
