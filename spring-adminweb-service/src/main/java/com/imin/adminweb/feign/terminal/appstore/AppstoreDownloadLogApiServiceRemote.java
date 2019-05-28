package com.imin.adminweb.feign.terminal.appstore;

import com.imin.adminweb.feign.TerminalFeignConfiguration;
import com.imin.terminal.api.adminweb.appstore.AppstoreDownloadLogApiService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title 描述
 * @Description 描述
 * @date 2019/3/19 16:12
 **/
@FeignClient(name = "${terminal.service.name}/${terminal.api.prefix}",
        url = "${terminal.api.host}", configuration = TerminalFeignConfiguration.class)
public interface AppstoreDownloadLogApiServiceRemote extends AppstoreDownloadLogApiService {

}
