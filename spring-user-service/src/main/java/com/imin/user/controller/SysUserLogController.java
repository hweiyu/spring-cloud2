package com.imin.user.controller;

import com.imin.infrastructure.common.dto.IdDto;
import com.imin.user.api.SysUserLogApiService;
import com.imin.user.dto.reqeust.SysUserLogQueryReqDto;
import com.imin.user.dto.reqeust.SysUserLogInsertReqDto;
import com.imin.user.dto.response.SysUserLogListResDto;
import io.swagger.annotations.Api;

import com.imin.infrastructure.common.aop.RequestProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.infrastructure.common.dto.PageInfo;
import org.springframework.validation.annotation.Validated;

import com.imin.user.dto.response.SysUserLogResDto;
import com.imin.user.service.SysUserLogService;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 用户日志表控制器
 * @date 2018-11-27 18:19:55
 **/
@RestController
@RequestMapping("${api.url.prefix}")
@Api(tags = {"用户日志表接口"})
public class SysUserLogController implements SysUserLogApiService {

    @Autowired
    private SysUserLogService sysUserLogService;

    /**
     * 获取用户日志表数据列表
     * @param reqDto
     * @return
     */
    @RequestProcess(checkLogin = false, logUrl = "/sys/userlog/list")
    @Override
    public ResultData<PageInfo<SysUserLogListResDto>> list(@RequestBody SysUserLogQueryReqDto reqDto){
        return ResultData.createSuccessResult(sysUserLogService.select(reqDto));
    }

    /**
     * 获取用户日志表数据
     * @param idDto
     * @return
     */
    @RequestProcess(checkLogin = false, logUrl = "/sys/userlog/get")
    @Override
    public ResultData<SysUserLogResDto> get(@Validated() @RequestBody IdDto idDto){
        return ResultData.createSuccessResult(sysUserLogService.get(idDto.getId()));
    }

    /**
     * 插入用户日志表数据
     * @param reqDto
     * @return
     */
    @RequestProcess(checkLogin = false, logUrl = "/sys/userlog/insert")
    @Override
    public ResultData<Void> insert(@Validated() @RequestBody SysUserLogInsertReqDto reqDto){
        sysUserLogService.insert(reqDto);
        return ResultData.createSuccessResult(null);
    }

}
