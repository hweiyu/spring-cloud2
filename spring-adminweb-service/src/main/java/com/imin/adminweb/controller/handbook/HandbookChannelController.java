package com.imin.adminweb.controller.handbook;

import com.imin.adminweb.feign.terminal.HandbookChannelApiServiceRemote;
import com.imin.infrastructure.common.aop.RequestProcess;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.terminal.dto.request.handbook.HandbookChannelInsertReqDto;
import com.imin.terminal.dto.request.handbook.HandbookChannelQueryReqDto;
import com.imin.terminal.dto.request.handbook.HandbookChannelUpdateReqDto;
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

/**
 * @author wangyong
 * @version V1.0
 * @Title: 描述
 * @Description: 用户手册分类管理
 * @date 2019-1-7 17:00:21
 **/
@RestController
@RequestMapping("${api.url.prefix}")
@Api(tags = {"用户手册分类接口"})
public class HandbookChannelController {

    @Autowired
    private HandbookChannelApiServiceRemote handbookChannelApiServiceRemote;

    /**
     * 获取用户手册分类列表
     */
    @ApiOperation(value = "获取用户手册分类列表", notes = "获取用户手册分类列表")
    @PostMapping(value = "/handbookchannel/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/handbookchanne/list")
    public ResultData<PageInfo<HandbookChannelResDto>> list(@RequestBody HandbookChannelQueryReqDto reqDto) {
        return handbookChannelApiServiceRemote.list(reqDto);
    }

    /**
     * 插入用户手册分类信息
     */
    @ApiOperation(value = "插入用户手册分类信息", notes = "插入用户手册分类信息")
    @PostMapping(value = "/handbookchannel/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/handbookchanne/insert")
    public ResultData<IdDto> insert(@Validated() @RequestBody HandbookChannelInsertReqDto reqDto) {
        return handbookChannelApiServiceRemote.insert(reqDto);
    }

    /**
     * 修改用户手册分类信息
     */
    @ApiOperation(value = "修改用户手册分类信息", notes = "修改用户手册分类信息")
    @PostMapping(value = "/handbookchannel/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/handbookchanne/update")
    public ResultData<IdDto> update(@Validated() @RequestBody HandbookChannelUpdateReqDto reqDto) {
        return handbookChannelApiServiceRemote.update(reqDto);
    }

    /**
     * 获取用户手册分类信息
     */
    @ApiOperation(value = "获取用户手册分类信息", notes = "获取用户手册分类信息")
    @PostMapping(value = "/handbookchannel/get", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/handbookchanne/get")
    public ResultData<HandbookChannelResDto> get(@Validated() @RequestBody IdDto idDto) {
        return handbookChannelApiServiceRemote.get(idDto);
    }

}
