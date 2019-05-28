package com.imin.user.controller;

import com.imin.infrastructure.common.dto.IdDto;
import com.imin.user.api.SysRoleApiService;
import com.imin.user.dto.reqeust.SysRoleInsertReqDto;
import com.imin.user.dto.reqeust.SysRoleQueryReqDto;
import com.imin.user.dto.reqeust.SysRoleUpdateReqDto;
import com.imin.user.dto.reqeust.SysRoleUpdateStatusReqDto;
import com.imin.user.dto.response.SysRoleListResDto;
import io.swagger.annotations.Api;

import com.imin.infrastructure.common.aop.RequestProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.infrastructure.common.dto.PageInfo;
import org.springframework.validation.annotation.Validated;

import com.imin.user.dto.response.SysRoleResDto;
import com.imin.user.service.SysRoleService;

import java.util.List;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 系统角色控制器
 * @date 2018-11-27 18:19:55
 **/
@RestController
@RequestMapping("${api.url.prefix}")
@Api(tags = {"系统角色接口"})
public class SysRoleController implements SysRoleApiService {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 获取系统角色数据列表
     * @param reqDto
     * @return
     */
    @RequestProcess(checkLogin = true, logUrl = "/sys/role/list")
    @Override
    public ResultData<PageInfo<SysRoleListResDto>> list(@RequestBody SysRoleQueryReqDto reqDto){
        return ResultData.createSuccessResult(sysRoleService.select(reqDto));
    }

    /**
     * 获取系统角色数据列表
     * @param idDto
     * @return
     */
    @RequestProcess(checkLogin = true, logUrl = "/sys/role/listByUserId")
    @Override
    public ResultData<List<SysRoleListResDto>> listByUserId(@RequestBody IdDto idDto){
        return ResultData.createSuccessResult(sysRoleService.listByUserId(idDto.getId()));
    }

    /**
     * 获取系统角色数据
     * @param idDto
     * @return
     */
    @RequestProcess(checkLogin = true, logUrl = "/sys/role/get")
    @Override
    public ResultData<SysRoleResDto> get(@Validated() @RequestBody IdDto idDto){
        return ResultData.createSuccessResult(sysRoleService.get(idDto.getId()));
    }

    /**
     * 插入系统角色数据
     * @param reqDto
     * @return
     */
    @RequestProcess(checkLogin = true, logUrl = "/sys/role/insert")
    @Override
    public ResultData<IdDto> insert(@Validated() @RequestBody SysRoleInsertReqDto reqDto){
        return ResultData.createSuccessResult(sysRoleService.insert(reqDto));
    }

    /**
     * 修改系统角色数据
     * @param reqDto
     * @return
     */
    @RequestProcess(checkLogin = true, logUrl = "/sys/role/update")
    @Override
    public ResultData<IdDto> update(@Validated() @RequestBody SysRoleUpdateReqDto reqDto){
        return ResultData.createSuccessResult(sysRoleService.update(reqDto));
    }

    /**
     * 启用/禁用角色
     *
     * @param reqDto
     * @return
     */
    @RequestProcess(checkLogin = true, logUrl = "/sys/role/updateStatus")
    @Override
    public ResultData<IdDto> updateStatus(@Validated() @RequestBody SysRoleUpdateStatusReqDto reqDto) {
        return ResultData.createSuccessResult(sysRoleService.updateStatus(reqDto));
    }

}
