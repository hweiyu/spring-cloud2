package com.imin.user.controller;

import com.imin.infrastructure.common.dto.IdDto;
import com.imin.user.api.SysResourceApiService;
import com.imin.user.dto.reqeust.*;
import com.imin.user.dto.response.SysResourceListResDto;
import io.swagger.annotations.Api;

import com.imin.infrastructure.common.aop.RequestProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.imin.infrastructure.common.result.ResultData;
import org.springframework.validation.annotation.Validated;

import com.imin.user.dto.response.SysResourceResDto;
import com.imin.user.service.SysResourceService;

import java.util.List;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 系统菜单权限资源控制器
 * @date 2018-11-27 18:19:55
 **/
@RestController
@RequestMapping("${api.url.prefix}")
@Api(tags = {"系统菜单权限资源接口"})
public class SysResourceController implements SysResourceApiService {

    @Autowired
    private SysResourceService sysResourceService;

    /**
     * 获取系统菜单权限资源数据列表
     * @param reqDto
     * @return
     */
    @RequestProcess(checkLogin = false, logUrl = "/sys/resource/list")
    @Override
    public ResultData<List<SysResourceListResDto>> list(@RequestBody SysResourceQueryReqDto reqDto){
        return ResultData.createSuccessResult(sysResourceService.select(reqDto));
    }

    /**
     * 通过用户id获取系统菜单权限资源数据列表
     *
     * @param reqDto
     * @return
     */
    @RequestProcess(checkLogin = true, logUrl = "/sys/resource/listByUserId")
    @Override
    public ResultData<List<SysResourceListResDto>> listByUserId(@RequestBody SysResourceUserQueryReqDto reqDto) {
        return ResultData.createSuccessResult(sysResourceService.listByUserId(reqDto.getUserId()));
    }

    /**
     * 获取系统菜单权限资源数据
     * @param idDto
     * @return
     */
    @RequestProcess(checkLogin = true, logUrl = "/sys/resource/get")
    @Override
    public ResultData<SysResourceResDto> get(@Validated() @RequestBody IdDto idDto){
        return ResultData.createSuccessResult(sysResourceService.get(idDto.getId()));
    }

    /**
     * 插入系统菜单权限资源数据
     * @param reqDto
     * @return
     */
    @RequestProcess(checkLogin = true, logUrl = "/sys/resource/insert")
    @Override
    public ResultData<IdDto> insert(@Validated() @RequestBody SysResourceInsertReqDto reqDto){
        return ResultData.createSuccessResult(sysResourceService.insert(reqDto));
    }

    /**
     * 修改系统菜单权限资源数据
     * @param reqDto
     * @return
     */
    @RequestProcess(checkLogin = true, logUrl = "/sys/resource/update")
    @Override
    public ResultData<IdDto> update(@Validated() @RequestBody SysResourceUpdateReqDto reqDto){
        return ResultData.createSuccessResult(sysResourceService.update(reqDto));
    }

    /**
     * 启用/禁用权限菜单
     *
     * @param reqDto
     * @return
     */
    @RequestProcess(checkLogin = true, logUrl = "/sys/resource/updateStatus")
    @Override
    public ResultData<IdDto> updateStatus(@Validated() @RequestBody SysResourceUpdateStatusReqDto reqDto) {
        return ResultData.createSuccessResult(sysResourceService.update(reqDto.to()));
    }

}
