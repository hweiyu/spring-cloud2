package com.imin.adminweb.feign.remote;

import com.imin.adminweb.feign.TerminalFeignConfiguration;
import com.imin.terminal.api.adminweb.Remote.RemoteAdminApiService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author wangyong
 * @version V1.0
 * @Title 远程控制远程调用服务
 * @Description 描述
 * @date 2018/11/28 14:38
 **/
@FeignClient(name = "${terminal.service.name}/${terminal.api.prefix}",
        url = "${terminal.api.host}", configuration = TerminalFeignConfiguration.class)
public interface RemoteApiServiceRemote extends RemoteAdminApiService {

}
