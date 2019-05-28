package com.imin.adminweb.feign.terminal;

import com.imin.adminweb.feign.TerminalFeignConfiguration;
import com.imin.terminal.api.adminweb.handbook.HandbookArticleApiService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author wangyong
 * @version V1.0
 * @Title 用户手册文章信息接口API
 * @Description  用户手册文章信息接口API
 * @date 2018/11/29 11:05
 **/
@FeignClient(name = "${terminal.service.name}/${terminal.api.prefix}",
        url = "${terminal.api.host}", configuration = TerminalFeignConfiguration.class)
public interface HandbookArticleApiServiceRemote extends HandbookArticleApiService {

}
