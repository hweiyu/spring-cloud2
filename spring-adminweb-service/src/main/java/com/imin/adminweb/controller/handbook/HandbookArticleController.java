package com.imin.adminweb.controller.handbook;

import com.imin.adminweb.feign.terminal.HandbookArticleApiServiceRemote;
import com.imin.infrastructure.common.aop.RequestProcess;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.terminal.dto.request.handbook.HandbookArticleInsertReqDto;
import com.imin.terminal.dto.request.handbook.HandbookArticleUpdateReqDto;
import com.imin.terminal.dto.request.handbook.handbookarticleQueryReqDto;
import com.imin.terminal.dto.response.handbook.HandbookArticleListResDto;
import com.imin.terminal.dto.response.handbook.HandbookArticleResDto;
import com.imin.terminal.dto.response.handbook.HandbookChannelResDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangyong
 * @version V1.0
 * @Title: 用户手册文章管理
 * @Description: 用户手册文章管理
 * @date 2019-1-7 17:00:21
 **/
@RestController
@RequestMapping("${api.url.prefix}")
@Api(tags = {"用户手册文章接口"})
public class HandbookArticleController {

    @Autowired
    private HandbookArticleApiServiceRemote handbookArticleApiServiceRemote;

    /**
     * 获取手册文章列表
     */
    @ApiOperation(value = "获取手册文章列表", notes = "获取手册文章列表")
    @PostMapping(value = "/handbookarticle/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/handbookarticle/list")
    public ResultData<PageInfo<HandbookArticleListResDto>> list(@RequestBody handbookarticleQueryReqDto reqDto){
        return handbookArticleApiServiceRemote.list(reqDto);

    }

    /**
     * 通过主键id获取文章详情
     */
    @ApiOperation(value = "通过主键id获取文章详情", notes = "通过主键id获取文章详情")
    @PostMapping(value = "/handbookarticle/get", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/handbookarticle/get")
    public ResultData<HandbookArticleResDto> get(@Validated() @RequestBody IdDto idDto){
        return handbookArticleApiServiceRemote.get(idDto);
    }

    /**
     * 插入手册文章内容信息
     */
    @ApiOperation(value = "插入手册文章内容信息", notes = "插入手册文章内容信息")
    @PostMapping(value = "/handbookarticle/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/handbookarticle/insert")
    public ResultData<IdDto> insert(@Validated() @RequestBody HandbookArticleInsertReqDto reqDto){
        return handbookArticleApiServiceRemote.insert(reqDto);
    }

    /**
     * 修改手册文章内容信息
     */
    @ApiOperation(value = "修改手册文章内容信息", notes = "修改手册文章内容信息")
    @PostMapping(value = "/handbookarticle/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/handbookarticle/update")
    public ResultData<IdDto> update(@Validated() @RequestBody HandbookArticleUpdateReqDto reqDto){
        return handbookArticleApiServiceRemote.update(reqDto);
    }

    /**
     * 获取栏目列表
     */
    @ApiOperation(value = "获取栏目列表", notes = "获取栏目列表")
    @PostMapping(value = "/handbookchannel/getChannelList", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/handbookarticle/getChannelList")
    public ResultData<List<HandbookChannelResDto>> getChannelList() {
        return handbookArticleApiServiceRemote.getChannelList();
    }

}
