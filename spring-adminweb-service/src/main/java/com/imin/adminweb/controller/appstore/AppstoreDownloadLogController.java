package com.imin.adminweb.controller.appstore;

import com.imin.adminweb.feign.terminal.appstore.AppstoreDownloadLogApiServiceRemote;
import com.imin.infrastructure.common.aop.RequestProcess;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.terminal.dto.request.appstore.AppstoreDownloadLogQueryDto;
import com.imin.terminal.dto.response.appstore.AppstoreDownloadLogResDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title 描述
 * @Description 描述
 * @date 2019/5/22 13:34
 **/
@RestController
@RequestMapping("${api.url.prefix}")
@Api(tags = {"应用下载日志接口"})
public class AppstoreDownloadLogController {

    @Autowired
    private AppstoreDownloadLogApiServiceRemote appstoreDownloadLogApiServiceRemote;

    /**
     * 数据列表
     *
     * @param reqDto
     */
    @ApiOperation(value = "数据列表", notes = "数据列表")
    @PostMapping(value = "/appstoredownloadlog/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appstoredownloadlog/list")
    public ResultData<PageInfo<AppstoreDownloadLogResDto>> list(@RequestBody AppstoreDownloadLogQueryDto reqDto) {
        return appstoreDownloadLogApiServiceRemote.list(reqDto);
    }
}
