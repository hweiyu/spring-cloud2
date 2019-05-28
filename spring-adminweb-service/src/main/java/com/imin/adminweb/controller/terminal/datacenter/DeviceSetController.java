package com.imin.adminweb.controller.terminal.datacenter;

import com.imin.adminweb.feign.terminal.DeviceSetApiServiceRemote;
import com.imin.infrastructure.common.aop.RequestProcess;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.terminal.dto.request.datacenter.DeviceSetInsertReqDto;
import com.imin.terminal.dto.request.datacenter.DeviceSetQueryReqDto;
import com.imin.terminal.dto.request.datacenter.DeviceSetUpdateReqDto;
import com.imin.terminal.dto.request.datacenter.DeviceSetUpdateStatusReqDto;
import com.imin.terminal.dto.response.datacenter.DeviceSetListResDto;
import com.imin.terminal.dto.response.datacenter.DeviceSetResDto;
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
 * @Description: 设备系列控制器
 * @date 2019-03-18 17:35:32
 **/
@RestController
@RequestMapping("${api.url.prefix}")
@Api(tags = {"设备系列接口"})
public class DeviceSetController {

    @Autowired
    private DeviceSetApiServiceRemote deviceSetApiServiceRemote;

    /**
     * 列表
     */
    @ApiOperation(value = "获取设备系列数据列表", notes = "获取设备系列数据列表")
    @PostMapping(value = "/deviceset/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/deviceset/list")
    public ResultData<PageInfo<DeviceSetListResDto>> list(@RequestBody DeviceSetQueryReqDto reqDto){
        return deviceSetApiServiceRemote.list(reqDto);
    }

    /**
     * 查询
     */
    @ApiOperation(value = "获取设备系列数据", notes = "获取设备系列数据")
    @PostMapping(value = "/deviceset/get", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/deviceset/get")
    public ResultData<DeviceSetResDto> get(@Validated() @RequestBody IdDto idDto){
        return deviceSetApiServiceRemote.get(idDto);
    }

    /**
     * 插入
     */
    @ApiOperation(value = "插入设备系列数据", notes = "插入设备系列数据")
    @PostMapping(value = "/deviceset/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/deviceset/insert")
    public ResultData<IdDto> insert(@Validated() @RequestBody DeviceSetInsertReqDto reqDto){
        return deviceSetApiServiceRemote.insert(reqDto);
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改设备系列数据", notes = "修改设备系列数据")
    @PostMapping(value = "/deviceset/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/deviceset/update")
    public ResultData<IdDto> update(@Validated() @RequestBody DeviceSetUpdateReqDto reqDto){
        return deviceSetApiServiceRemote.update(reqDto);
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除设备系列数据", notes = "删除设备系列数据")
    @PostMapping(value = "/deviceset/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/deviceset/delete")
    public ResultData<IdDto> delete(@Validated() @RequestBody IdDto idDto){
        return deviceSetApiServiceRemote.delete(idDto);
    }

    /**
     * 启用/禁用
     */
    @ApiOperation(value = "启用/禁用", notes = "启用/禁用")
    @PostMapping(value = "/deviceset/updateStatus", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/deviceset/updateStatus")
    public ResultData<IdDto> updateStatus(@Validated() @RequestBody DeviceSetUpdateStatusReqDto reqDto){
        return deviceSetApiServiceRemote.updateStatus(reqDto);
    }
}
