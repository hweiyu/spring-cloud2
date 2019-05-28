package com.imin.adminweb.feign.terminal;

import com.imin.adminweb.feign.TerminalFeignConfiguration;
import com.imin.terminal.api.adminweb.DeviceLifeCycleInfoApiService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author wangyong
 * @version V1.0
 * @Title 设备地图信息远程调用服务
 * @Description 描述
 * @date 2018/11/28 14:38
 **/
@FeignClient(name = "${terminal.service.name}/${terminal.api.prefix}",
        url = "${terminal.api.host}", configuration = TerminalFeignConfiguration.class)
public interface DeviceLifeCycleInfoApiServiceRemote extends DeviceLifeCycleInfoApiService {

}
