package com.imin.user.api;

import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.user.dto.reqeust.*;
import com.imin.user.dto.response.SysUserLogListResDto;
import com.imin.user.dto.response.SysUserLogResDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title 描述
 * @Description 描述
 * @date 2018/11/29 9:49
 **/
public interface SysUserLogApiService {

    /**
     * 获取用户日志表数据列表
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "获取用户日志表数据列表", notes = "获取用户日志表数据列表")
    @PostMapping(value = "/sys/userlog/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData<PageInfo<SysUserLogListResDto>> list(@RequestBody SysUserLogQueryReqDto reqDto);

    /**
     * 获取用户日志表数据
     * @param idDto
     * @return
     */
    @ApiOperation(value = "获取用户日志表数据", notes = "获取用户日志表数据")
    @PostMapping(value = "/sys/userlog/get", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData<SysUserLogResDto> get(@Validated() @RequestBody IdDto idDto);

    /**
     * 插入用户日志表数据
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "插入用户日志表数据", notes = "插入用户日志表数据")
    @PostMapping(value = "/sys/userlog/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData<Void> insert(@Validated() @RequestBody SysUserLogInsertReqDto reqDto);

}
