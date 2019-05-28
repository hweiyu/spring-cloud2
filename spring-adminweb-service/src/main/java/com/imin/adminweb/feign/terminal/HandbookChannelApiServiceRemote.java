package com.imin.adminweb.feign.terminal;

import com.imin.adminweb.feign.TerminalFeignConfiguration;
import com.imin.terminal.api.adminweb.handbook.HandbookChannelApiService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author wangyong
 * @version V1.0
 * @Title 用户手册分类信息接口API
 * @Description  用户手册分类信息接口API
 * @date 2019/1/7 11:05
 **/
@FeignClient(name = "${terminal.service.name}/${terminal.api.prefix}",
        url = "${terminal.api.host}", configuration = TerminalFeignConfiguration.class)
public interface HandbookChannelApiServiceRemote extends HandbookChannelApiService {

}
