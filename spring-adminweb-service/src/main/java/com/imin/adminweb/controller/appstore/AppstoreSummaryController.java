package com.imin.adminweb.controller.appstore;

import com.imin.adminweb.feign.terminal.appstore.AppstoreSummaryApiServiceRemote;
import com.imin.infrastructure.common.aop.RequestProcess;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.terminal.dto.request.appstore.AppstoreSummaryQueryReqDto;
import com.imin.terminal.dto.response.appstore.AppstoreSummaryGroupResDto;
import com.imin.terminal.dto.response.appstore.AppstoreSummaryResDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 应用组控制器
 * @date 2019-03-18 17:35:32
 **/
@RestController
@RequestMapping("${api.url.prefix}")
@Api(tags = {"应用统计接口"})
public class AppstoreSummaryController {

    @Autowired
    private AppstoreSummaryApiServiceRemote appstoreSummaryApiServiceRemote;

    /**
     * 下载量统计
     */
    @ApiOperation(value = "下载量统计", notes = "下载量统计")
    @PostMapping(value = "/appstore/downloadSummary", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appstore/downloadSummary")
    public ResultData<List<AppstoreSummaryResDto>> downloadSummary(@RequestBody AppstoreSummaryQueryReqDto reqDto){
        return appstoreSummaryApiServiceRemote.downloadSummary(reqDto);
    }

    /**
     * 点击量统计
     */
    @ApiOperation(value = "点击量统计", notes = "点击量统计")
    @PostMapping(value = "/appstore/clickSummary", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appstore/clickSummary")
    public ResultData<List<AppstoreSummaryResDto>> clickSummary(@RequestBody AppstoreSummaryQueryReqDto reqDto){
        return appstoreSummaryApiServiceRemote.clickSummary(reqDto);
    }

    /**
     * 获取应用组列表
     */
    @ApiOperation(value = "获取应用组列表", notes = "获取应用组列表")
    @PostMapping(value = "/appstore/getGroupList", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appstore/getGroupList")
    public ResultData<List<AppstoreSummaryGroupResDto>> getGroupList() {
        return appstoreSummaryApiServiceRemote.getGroupList();
    }

}
