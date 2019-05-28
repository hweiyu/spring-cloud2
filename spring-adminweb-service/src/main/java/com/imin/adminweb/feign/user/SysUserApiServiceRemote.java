package com.imin.adminweb.feign.user;

import com.imin.adminweb.feign.UserFeignConfiguration;
import com.imin.user.api.SysUserApiService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title 描述
 * @Description 描述
 * @date 2018/11/29 9:49
 **/
@FeignClient(name = "${user.service.name}/${user.api.prefix}",
        url = "${user.api.host}", configuration = UserFeignConfiguration.class)
public interface SysUserApiServiceRemote extends SysUserApiService {

}
