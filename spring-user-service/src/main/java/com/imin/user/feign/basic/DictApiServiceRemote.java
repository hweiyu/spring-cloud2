package com.imin.user.feign.basic;

import com.imin.basic.api.DictApiService;
import com.imin.user.feign.BasicFeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title 描述
 * @Description 描述
 * @date 2018/11/28 14:38
 **/
@FeignClient(name = "${basic.service.name}/${basic.api.prefix}",
        url = "${basic.api.host}", configuration = BasicFeignConfiguration.class)
public interface DictApiServiceRemote extends DictApiService {
}
