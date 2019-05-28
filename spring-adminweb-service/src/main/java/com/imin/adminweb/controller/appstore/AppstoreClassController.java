package com.imin.adminweb.controller.appstore;

import com.imin.adminweb.feign.terminal.appstore.AppstoreClassApiServiceRemote;
import com.imin.infrastructure.common.aop.RequestProcess;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.terminal.dto.request.appstore.AppstoreClassInsertReqDto;
import com.imin.terminal.dto.request.appstore.AppstoreClassQueryReqDto;
import com.imin.terminal.dto.request.appstore.AppstoreClassUpdateReqDto;
import com.imin.terminal.dto.request.appstore.AppstoreClassUpdateStatusReqDto;
import com.imin.terminal.dto.response.appstore.AppstoreClassListResDto;
import com.imin.terminal.dto.response.appstore.AppstoreClassResDto;
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
 * @Description: 应用分类控制器
 * @date 2019-03-18 17:35:32
 **/
@RestController
@RequestMapping("${api.url.prefix}")
@Api(tags = {"应用分类接口"})
public class AppstoreClassController {

    @Autowired
    private AppstoreClassApiServiceRemote appstoreClassApiServiceRemote;

    /**
     * 列表
     */
    @ApiOperation(value = "获取应用分类数据列表", notes = "获取应用分类数据列表")
    @PostMapping(value = "/appstoreclass/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appstoreclass/list")
    public ResultData<PageInfo<AppstoreClassListResDto>> list(@RequestBody AppstoreClassQueryReqDto reqDto){
        return appstoreClassApiServiceRemote.list(reqDto);
    }

    /**
     * 查询
     */
    @ApiOperation(value = "获取应用分类数据", notes = "获取应用分类数据")
    @PostMapping(value = "/appstoreclass/get", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appstoreclass/get")
    public ResultData<AppstoreClassResDto> get(@Validated() @RequestBody IdDto idDto){
        return appstoreClassApiServiceRemote.get(idDto);
    }

    /**
     * 插入
     */
    @ApiOperation(value = "插入应用分类数据", notes = "插入应用分类数据")
    @PostMapping(value = "/appstoreclass/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appstoreclass/insert")
    public ResultData<IdDto> insert(@Validated() @RequestBody AppstoreClassInsertReqDto reqDto){
        return appstoreClassApiServiceRemote.insert(reqDto);
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改应用分类数据", notes = "修改应用分类数据")
    @PostMapping(value = "/appstoreclass/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appstoreclass/update")
    public ResultData<IdDto> update(@Validated() @RequestBody AppstoreClassUpdateReqDto reqDto){
        return appstoreClassApiServiceRemote.update(reqDto);
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除应用分类数据", notes = "删除应用分类数据")
    @PostMapping(value = "/appstoreclass/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appstoreclass/delete")
    public ResultData<IdDto> delete(@Validated() @RequestBody IdDto idDto){
        return appstoreClassApiServiceRemote.delete(idDto);
    }

    /**
     * 启用/禁用
     */
    @ApiOperation(value = "启用/禁用", notes = "启用/禁用")
    @PostMapping(value = "/appstoreclass/updateStatus", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appstoreclass/updateStatus")
    public ResultData<IdDto> updateStatus(@Validated() @RequestBody AppstoreClassUpdateStatusReqDto reqDto){
        return appstoreClassApiServiceRemote.updateStatus(reqDto);
    }
}
