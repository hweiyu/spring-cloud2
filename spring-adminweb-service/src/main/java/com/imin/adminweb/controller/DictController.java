package com.imin.adminweb.controller;

import com.imin.adminweb.feign.basic.DictApiServiceRemote;
import com.imin.basic.dto.request.*;
import com.imin.basic.dto.response.DictResDto;
import com.imin.infrastructure.common.aop.RequestProcess;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.result.ResultData;
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
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 字典表控制器
 * @date 2018-11-28 11:40:56
 **/
@RestController
@RequestMapping("${api.url.prefix}")
@Api(tags = {"字典表接口"})
public class DictController {

    @Autowired
    private DictApiServiceRemote dictApiServiceRemote;

    /**
     * 条件查询字典信息列表
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "条件查询字典信息列表", notes = "条件查询字典信息列表")
    @PostMapping(value = "/dict/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, popedomCode = "cloud:platform:param:select", logName = "条件查询字典信息列表", logUrl = "/dict/list")
    public ResultData<PageInfo<DictResDto>> list(@RequestBody DictListQueryReqDto reqDto) {
        return dictApiServiceRemote.listByPage(reqDto);
    }

    /**
     * 通过主键id单个字典信息
     * @param idDto
     * @return
     */
    @ApiOperation(value = "通过主键id单个字典信息", notes = "通过主键id单个字典信息")
    @PostMapping(value = "/dict/get", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, popedomCode = "cloud:platform:param:select", logName = "通过主键id单个字典信息", logUrl = "/dict/get")
    public ResultData<DictResDto> get(@Validated() @RequestBody IdDto idDto) {
        return dictApiServiceRemote.get(idDto);
    }

    /**
     * 添加
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "添加", notes = "添加")
    @PostMapping(value = "/dict/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, popedomCode = "cloud:platform:param:insert", logName = "添加", logUrl = "/dict/insert")
    public ResultData<IdDto> insert(@Validated() @RequestBody DictInsertReqDto reqDto) {
        return dictApiServiceRemote.insert(reqDto);
    }

    /**
     * 修改
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "修改", notes = "修改")
    @PostMapping(value = "/dict/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, popedomCode = "cloud:platform:param:update", logName = "修改", logUrl = "/dict/update")
    public ResultData<IdDto> update(@Validated() @RequestBody DictUpdateReqDto reqDto) {
        return dictApiServiceRemote.update(reqDto);
    }

    /**
     * 删除
     * @param idDto
     * @return
     */
    @ApiOperation(value = "删除", notes = "删除")
    @PostMapping(value = "/dict/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, popedomCode = "cloud:platform:param:delete", logName = "删除", logUrl = "/dict/delete")
    public ResultData<IdDto> delete(@Validated() @RequestBody IdDto idDto) {
        return dictApiServiceRemote.delete(idDto);
    }
}
