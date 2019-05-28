package com.imin.adminweb.controller.appstore;

import com.imin.adminweb.feign.terminal.appstore.AppstoreGroupApiServiceRemote;
import com.imin.infrastructure.common.aop.RequestProcess;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.terminal.dto.request.appstore.*;
import com.imin.terminal.dto.response.appstore.AppstoreGroupAgentResDto;
import com.imin.terminal.dto.response.appstore.AppstoreGroupAppResDto;
import com.imin.terminal.dto.response.appstore.AppstoreGroupListResDto;
import com.imin.terminal.dto.response.appstore.AppstoreGroupResDto;
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
 * @Description: 应用组控制器
 * @date 2019-03-18 17:35:32
 **/
@RestController
@RequestMapping("${api.url.prefix}")
@Api(tags = {"应用发布策略接口"})
public class AppstoreGroupController {

    @Autowired
    private AppstoreGroupApiServiceRemote appstoreGroupApiServiceRemote;

    /**
     * 列表
     */
    @ApiOperation(value = "获取应用组数据列表", notes = "获取应用组数据列表")
    @PostMapping(value = "/appstoregroup/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appstoregroup/list")
    public ResultData<PageInfo<AppstoreGroupListResDto>> list(@RequestBody AppstoreGroupQueryReqDto reqDto){
        return appstoreGroupApiServiceRemote.list(reqDto);
    }

    /**
     * 查询
     */
    @ApiOperation(value = "获取应用组数据", notes = "获取应用组数据")
    @PostMapping(value = "/appstoregroup/get", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appstoregroup/get")
    public ResultData<AppstoreGroupResDto> get(@Validated() @RequestBody IdDto idDto){
        return appstoreGroupApiServiceRemote.get(idDto);
    }

    /**
     * 插入
     */
    @ApiOperation(value = "插入应用组数据", notes = "插入应用组数据")
    @PostMapping(value = "/appstoregroup/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appstoregroup/insert")
    public ResultData<IdDto> insert(@Validated() @RequestBody AppstoreGroupInsertReqDto reqDto){
        return appstoreGroupApiServiceRemote.insert(reqDto);
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改应用组数据", notes = "修改应用组数据")
    @PostMapping(value = "/appstoregroup/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appstoregroup/update")
    public ResultData<IdDto> update(@Validated() @RequestBody AppstoreGroupUpdateReqDto reqDto){
        return appstoreGroupApiServiceRemote.update(reqDto);
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除应用组数据", notes = "删除应用组数据")
    @PostMapping(value = "/appstoregroup/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appstoregroup/delete")
    public ResultData<IdDto> delete(@Validated() @RequestBody IdDto idDto){
        return appstoreGroupApiServiceRemote.delete(idDto);
    }

    /**
     * 启用/禁用
     */
    @ApiOperation(value = "启用/禁用", notes = "启用/禁用")
    @PostMapping(value = "/appstoregroup/updateStatus", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appstoregroup/updateStatus")
    public ResultData<IdDto> updateStatus(@Validated() @RequestBody AppstoreGroupUpdateStatusReqDto reqDto){
        return appstoreGroupApiServiceRemote.updateStatus(reqDto);
    }

    /**
     * 当前应用组对应的代理商列表
     */
    @ApiOperation(value = "当前应用组对应的代理商列表", notes = "当前应用组对应的代理商列表")
    @PostMapping(value = "/appstoregroup/agentList", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appstoregroup/agentList")
    public ResultData<PageInfo<AppstoreGroupAgentResDto>> agentList(@Validated() @RequestBody AppstoreGroupAgentQueryReqDto reqDto){
        return appstoreGroupApiServiceRemote.agentList(reqDto);
    }

    /**
     * 绑定代理商
     */
    @ApiOperation(value = "绑定代理商", notes = "绑定代理商")
    @PostMapping(value = "/appstoregroup/bindAgent", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appstoregroup/bindAgent")
    public ResultData<IdDto> bindAgent(@Validated() @RequestBody AppstoreGroupBindReqDto reqDto){
        return appstoreGroupApiServiceRemote.bindAgent(reqDto);
    }

    /**
     * 解绑代理商
     */
    @ApiOperation(value = "解绑代理商", notes = "解绑代理商")
    @PostMapping(value = "/appstoregroup/unBindAgent", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appstoregroup/unBindAgent")
    public ResultData<IdDto> unBindAgent(@Validated() @RequestBody AppstoreGroupBindReqDto reqDto){
        return appstoreGroupApiServiceRemote.unBindAgent(reqDto);
    }

    /**
     * 当前应用组对应的应用列表
     */
    @ApiOperation(value = "当前应用组对应的应用列表", notes = "当前应用组对应的应用列表")
    @PostMapping(value = "/appstoregroup/appList", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appstoregroup/appList")
    public ResultData<PageInfo<AppstoreGroupAppResDto>> appList(@Validated() @RequestBody AppstoreGroupAppQueryReqDto reqDto){
        return appstoreGroupApiServiceRemote.appList(reqDto);
    }

    /**
     * 绑定应用
     */
    @ApiOperation(value = "绑定应用", notes = "绑定应用")
    @PostMapping(value = "/appstoregroup/bindApp", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appstoregroup/bindApp")
    public ResultData<IdDto> bindApp(@Validated() @RequestBody AppstoreGroupBindReqDto reqDto){
        return appstoreGroupApiServiceRemote.bindApp(reqDto);
    }

    /**
     * 解绑应用
     */
    @ApiOperation(value = "解绑应用", notes = "解绑应用")
    @PostMapping(value = "/appstoregroup/unBindApp", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appstoregroup/unBindApp")
    public ResultData<IdDto> unBindApp(@Validated() @RequestBody AppstoreGroupBindReqDto reqDto){
        return appstoreGroupApiServiceRemote.unBindApp(reqDto);
    }

    /**
     * 查当前应用组的未包含在内的代理商列表
     */
    @ApiOperation(value = "查当前应用组的未包含在内的代理商列表", notes = "查当前应用组的未包含在内的代理商列表")
    @PostMapping(value = "/appstoregroup/searchAgent", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appstoregroup/searchAgent")
    public ResultData<PageInfo<AppstoreGroupAgentResDto>> searchAgent(@Validated() @RequestBody AppstoreGroupAgentQueryReqDto reqDto){
        return appstoreGroupApiServiceRemote.searchAgent(reqDto);
    }

    /**
     * 当前应用组对应的应用列表
     */
    @ApiOperation(value = "查当前应用组的未包含在内的应用列表", notes = "查当前应用组的未包含在内的应用列表")
    @PostMapping(value = "/appstoregroup/searchApp", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appstoregroup/searchApp")
    public ResultData<PageInfo<AppstoreGroupAppResDto>> searchApp(@Validated() @RequestBody AppstoreGroupAppQueryReqDto reqDto){
        return appstoreGroupApiServiceRemote.searchApp(reqDto);
    }

}
