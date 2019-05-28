package com.imin.adminweb.controller.terminal.datacenter;

import com.imin.adminweb.feign.terminal.DeviceAppAliasApiServiceRemote;
import com.imin.infrastructure.common.aop.RequestProcess;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.infrastructure.common.utils.Il8nSupportUtil;
import com.imin.terminal.dto.request.datacenter.AppClassReqDto;
import com.imin.terminal.dto.response.datacenter.AppClassResDto;
import com.imin.terminal.dto.request.datacenter.DeviceAppAliasInsertReqDto;
import com.imin.terminal.dto.request.datacenter.DeviceAppAliasQueryReqDto;
import com.imin.terminal.dto.request.datacenter.DeviceAppAliasUpdateReqDto;
import com.imin.terminal.dto.response.datacenter.DeviceAppAliasListResDto;
import com.imin.terminal.dto.response.datacenter.DeviceAppAliasResDto;
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
 * @Description: 控制器
 * @date 2018-12-26 17:00:21
 **/
@RestController
@RequestMapping("${api.url.prefix}")
@Api(tags = {"app应用别名接口"})
public class DeviceAppAliasController {

    @Autowired
    private DeviceAppAliasApiServiceRemote deviceAppAliasServiceRemote;

    /**
     * 获取应用别名列表
     */
    @ApiOperation(value = "获取应用别名列表", notes = "获取应用别名列表")
    @PostMapping(value = "/deviceappalias/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logName = "获取应用别名列表", logUrl = "/deviceappalias/list")
    public ResultData<PageInfo<DeviceAppAliasListResDto>> list(@RequestBody DeviceAppAliasQueryReqDto reqDto){
        return deviceAppAliasServiceRemote.list(reqDto);
    }

    /**
     * 通过主键id获取应用别名
     */
    @ApiOperation(value = "通过主键id获取应用别名", notes = "通过主键id获取应用别名")
    @PostMapping(value = "/deviceappalias/get", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logName = "通过主键id获取应用别名", logUrl = "/deviceappalias/get")
    public ResultData<DeviceAppAliasResDto> get(@Validated() @RequestBody IdDto idDto){
        return deviceAppAliasServiceRemote.get(idDto);
    }

    /**
     * 插入应用别名信息
     */
    @ApiOperation(value = "插入应用别名信息", notes = "插入应用别名信息")
    @PostMapping(value = "/deviceappalias/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logName = "插入应用别名信息", logUrl = "/deviceappalias/insert")
    public ResultData<IdDto> insert(@Validated() @RequestBody DeviceAppAliasInsertReqDto reqDto){
        return deviceAppAliasServiceRemote.insert(reqDto);
    }

    /**
     * 修改应用别名信息
     */
    @ApiOperation(value = "修改应用别名信息", notes = "修改应用别名信息")
    @PostMapping(value = "/deviceappalias/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logName = "修改应用别名信息", logUrl = "/deviceappalias/update")
    public ResultData<IdDto> update(@Validated() @RequestBody DeviceAppAliasUpdateReqDto reqDto){
        return deviceAppAliasServiceRemote.update(reqDto);
    }

    /**
     * 查app应用分类列表
     */
    @ApiOperation(value = "查app应用分类列表", notes = "查app应用分类列表")
    @PostMapping(value = "/deviceappinfo/listAppClass", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logUrl = "/deviceappinfo/listAppClass")
    public ResultData<List<AppClassResDto>> listAppClass() {
        return deviceAppAliasServiceRemote.listAppClass(AppClassReqDto.builder()
                .language(Il8nSupportUtil.getSupportLanguageFromHttpRequest())
                .build());
    }
}
