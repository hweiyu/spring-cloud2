package com.imin.adminweb.controller.terminal.datacenter;

import com.imin.adminweb.feign.terminal.DeviceModelApiServiceRemote;
import com.imin.infrastructure.common.aop.RequestProcess;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.terminal.dto.request.datacenter.DeviceModelInsertReqDto;
import com.imin.terminal.dto.request.datacenter.DeviceModelQueryReqDto;
import com.imin.terminal.dto.request.datacenter.DeviceModelUpdateReqDto;
import com.imin.terminal.dto.request.datacenter.DeviceModelUpdateStatusReqDto;
import com.imin.terminal.dto.response.datacenter.DeviceModelListResDto;
import com.imin.terminal.dto.response.datacenter.DeviceModelResDto;
import com.imin.terminal.dto.response.datacenter.SetListResDto;
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
 * @Description: 设备型号控制器
 * @date 2019-03-18 17:35:32
 **/
@RestController
@RequestMapping("${api.url.prefix}")
@Api(tags = {"设备型号接口"})
public class DeviceModelController {

    @Autowired
    private DeviceModelApiServiceRemote deviceModelApiServiceRemote;

    /**
     * 列表
     */
    @ApiOperation(value = "获取设备型号数据列表", notes = "获取设备型号数据列表")
    @PostMapping(value = "/devicemodel/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/devicemodel/list")
    public ResultData<PageInfo<DeviceModelListResDto>> list(@RequestBody DeviceModelQueryReqDto reqDto){
        return deviceModelApiServiceRemote.list(reqDto);
    }

    /**
     * 查询
     */
    @ApiOperation(value = "获取设备型号数据", notes = "获取设备型号数据")
    @PostMapping(value = "/devicemodel/get", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/devicemodel/get")
    public ResultData<DeviceModelResDto> get(@Validated() @RequestBody IdDto idDto){
        return deviceModelApiServiceRemote.get(idDto);
    }

    /**
     * 插入
     */
    @ApiOperation(value = "插入设备型号数据", notes = "插入设备型号数据")
    @PostMapping(value = "/devicemodel/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/devicemodel/insert")
    public ResultData<IdDto> insert(@Validated() @RequestBody DeviceModelInsertReqDto reqDto){
        return deviceModelApiServiceRemote.insert(reqDto);
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改设备型号数据", notes = "修改设备型号数据")
    @PostMapping(value = "/devicemodel/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/devicemodel/update")
    public ResultData<IdDto> update(@Validated() @RequestBody DeviceModelUpdateReqDto reqDto){
        return deviceModelApiServiceRemote.update(reqDto);
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除设备型号数据", notes = "删除设备型号数据")
    @PostMapping(value = "/devicemodel/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/devicemodel/delete")
    public ResultData<IdDto> delete(@Validated() @RequestBody IdDto idDto){
        return deviceModelApiServiceRemote.delete(idDto);
    }

    /**
     * 启用/禁用
     */
    @ApiOperation(value = "启用/禁用", notes = "启用/禁用")
    @PostMapping(value = "/devicemodel/updateStatus", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/devicemodel/updateStatus")
    public ResultData<IdDto> updateStatus(@Validated() @RequestBody DeviceModelUpdateStatusReqDto reqDto){
        return deviceModelApiServiceRemote.updateStatus(reqDto);
    }

    /**
     * 设备系列列表
     */
    @ApiOperation(value = "设备系列列表", notes = "设备系列列表")
    @PostMapping(value = "/devicemodel/getSetList", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/devicemodel/getSetList")
    public ResultData<List<SetListResDto>> getSetList(){
        return deviceModelApiServiceRemote.getSetList();
    }
}
