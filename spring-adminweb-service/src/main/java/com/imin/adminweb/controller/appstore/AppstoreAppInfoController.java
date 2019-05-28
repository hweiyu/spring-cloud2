package com.imin.adminweb.controller.appstore;

import com.imin.adminweb.feign.terminal.appstore.AppstoreAppInfoApiServiceRemote;
import com.imin.infrastructure.common.aop.RequestProcess;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.terminal.dto.request.appstore.*;
import com.imin.terminal.dto.response.appstore.AppstoreAppInfoListResDto;
import com.imin.terminal.dto.response.appstore.AppstoreAppInfoResDto;
import com.imin.terminal.dto.response.appstore.AppstoreListResDto;
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
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 应用信息控制器
 * @date 2019-03-18 17:35:32
 **/
@RestController
@RequestMapping("${api.url.prefix}")
@Api(tags = {"应用信息接口"})
public class AppstoreAppInfoController {

    @Autowired
    private AppstoreAppInfoApiServiceRemote appstoreAppInfoApiServiceRemote;

    /**
     * 列表
     */
    @ApiOperation(value = "获取应用信息数据列表", notes = "获取应用信息数据列表")
    @PostMapping(value = "/appstoreappinfo/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appstoreappinfo/list")
    public ResultData<PageInfo<AppstoreAppInfoListResDto>> list(@RequestBody AppstoreAppInfoQueryReqDto reqDto){
        return appstoreAppInfoApiServiceRemote.list(reqDto);
    }

    /**
     * 查询
     */
    @ApiOperation(value = "获取应用信息数据", notes = "获取应用信息数据")
    @PostMapping(value = "/appstoreappinfo/get", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appstoreappinfo/get")
    public ResultData<AppstoreAppInfoResDto> get(@Validated() @RequestBody IdDto idDto){
        return appstoreAppInfoApiServiceRemote.get(idDto);
    }

    /**
     * 插入
     */
    @ApiOperation(value = "插入应用信息数据", notes = "插入应用信息数据")
    @PostMapping(value = "/appstoreappinfo/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appstoreappinfo/insert")
    public ResultData<IdDto> insert(@Validated() @RequestBody AppstoreAppInfoInsertReqDto reqDto){
        return appstoreAppInfoApiServiceRemote.insert(reqDto);
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改应用信息数据", notes = "修改应用信息数据")
    @PostMapping(value = "/appstoreappinfo/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appstoreappinfo/update")
    public ResultData<IdDto> update(@Validated() @RequestBody AppstoreAppInfoUpdateReqDto reqDto){
        return appstoreAppInfoApiServiceRemote.update(reqDto);
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除应用信息数据", notes = "删除应用信息数据")
    @PostMapping(value = "/appstoreappinfo/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appstoreappinfo/delete")
    public ResultData<IdDto> delete(@Validated() @RequestBody IdDto idDto){
        return appstoreAppInfoApiServiceRemote.delete(idDto);
    }

    /**
     * 上架
     */
    @ApiOperation(value = "上架", notes = "上架")
    @PostMapping(value = "/appstoreappinfo/online", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appstoreappinfo/online")
    public ResultData<IdDto> online(@Validated() @RequestBody AppstoreAppInfoOnlineReqDto reqDto){
        return appstoreAppInfoApiServiceRemote.online(reqDto);
    }

    /**
     * 下架
     */
    @ApiOperation(value = "下架", notes = "下架")
    @PostMapping(value = "/appstoreappinfo/offline", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appstoreappinfo/offline")
    public ResultData<IdDto> offline(@Validated() @RequestBody AppstoreAppInfoOfflineReqDto reqDto){
        return appstoreAppInfoApiServiceRemote.offline(reqDto);
    }

    /**
     * 系列列表
     */
    @ApiOperation(value = "系列列表", notes = "系列列表")
    @PostMapping(value = "/appstoreappinfo/setList", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appstoreappinfo/setList")
    public ResultData<List<AppstoreListResDto>> setList(){
        return appstoreAppInfoApiServiceRemote.setList();
    }

    /**
     * 通过系列id查型号列表
     */
    @ApiOperation(value = "通过系列id查型号列表", notes = "通过系列id查型号列表")
    @PostMapping(value = "/appstoreappinfo/modelList", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appstoreappinfo/modelList")
    public ResultData<List<AppstoreListResDto>> modelList(@Validated() @RequestBody AppstoreSetIdReqDto reqDto){
        return appstoreAppInfoApiServiceRemote.modelList(reqDto);
    }

    /**
     * 应用类别列表
     */
    @ApiOperation(value = "应用类别列表", notes = "应用类别列表")
    @PostMapping(value = "/appstoreappinfo/appClassList", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appstoreappinfo/appClassList")
    public ResultData<List<AppstoreListResDto>> appClassList(){
        return appstoreAppInfoApiServiceRemote.appClassList();
    }
}
