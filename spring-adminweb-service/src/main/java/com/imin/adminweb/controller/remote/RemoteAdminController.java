package com.imin.adminweb.controller.remote;

import com.imin.adminweb.feign.remote.RemoteApiServiceRemote;
import com.imin.infrastructure.common.aop.RequestProcess;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.result.ResultData;

import com.imin.terminal.dto.request.remote.RemoteInfoQueryReqDto;
import com.imin.terminal.dto.request.remote.RemoteInfoUpdateReqDto;

import com.imin.terminal.dto.response.remote.RemoteListResDto;
import com.imin.terminal.dto.response.remote.RemoteStatusResDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangyong
 * @version V1.0
 * @Title: 描述
 * @Description: 远程协助控制器
 * @date 2018-11-20 13:52:22
 **/
@RestController
@RequestMapping("${api.url.prefix}")
@Api(tags = {"后台远程协助接口"})
public class RemoteAdminController {

    @Autowired
    private RemoteApiServiceRemote remoteApiServiceRemote;

    /**
     * 获取远程协助设备列表
     */
    @ApiOperation(value = "获取远程协助设备列表1", notes = "获取远程协助设备列表1")
    @PostMapping(value = "/remoteinfo/getremotelist", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logUrl = "/remoteinfo/getremotelist")
    public ResultData<PageInfo<RemoteListResDto>> getremotelist(@RequestBody RemoteInfoQueryReqDto reqDto) {
        return remoteApiServiceRemote.getRemotelist(reqDto);
    }

    /**
     * 通过SN变更远程控制状态
     */
    @ApiOperation(value = " 通过SN 更新终端设备远程控制状态", notes = " 通过SN 更新终端设备远程控制状态")
    @PostMapping(value = "/remoteinfo/updateRemoteStatus", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logUrl = "/remoteinfo/updateRemoteStatus")

    public ResultData<RemoteStatusResDto> updateRemoteStatus(@RequestBody RemoteInfoUpdateReqDto reqDto) {
        return remoteApiServiceRemote.updateRemoteStatus(reqDto);
    }


}
