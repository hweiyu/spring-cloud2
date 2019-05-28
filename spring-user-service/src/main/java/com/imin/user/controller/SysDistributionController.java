package com.imin.user.controller;

import com.imin.infrastructure.common.dto.IdDto;
import com.imin.user.api.SysDistributionApiService;
import com.imin.user.dto.reqeust.SysDistributionInsertReqDto;
import com.imin.user.dto.reqeust.SysDistributionQueryReqDto;
import com.imin.user.dto.reqeust.SysDistributionUpdateReqDto;
import com.imin.user.dto.response.SysDistributionListResDto;
import io.swagger.annotations.Api;

import com.imin.infrastructure.common.aop.RequestProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.infrastructure.common.dto.PageInfo;
import org.springframework.validation.annotation.Validated;

import com.imin.user.dto.response.SysDistributionResDto;
import com.imin.user.service.SysDistributionService;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 用户分配控制器
 * @date 2018-11-27 18:19:55
 **/
@RestController
@RequestMapping("${api.url.prefix}")
@Api(tags = {"用户分配接口"})
public class SysDistributionController implements SysDistributionApiService {

    @Autowired
    private SysDistributionService sysDistributionService;

    /**
     * 获取用户分配数据列表
     * @param reqDto
     * @return
     */
    @RequestProcess(checkLogin = true, logUrl = "/sys/distribution/list")
    @Override
    public ResultData<PageInfo<SysDistributionListResDto>> list(@RequestBody SysDistributionQueryReqDto reqDto){
        return ResultData.createSuccessResult(sysDistributionService.select(reqDto));
    }

    /**
     * 获取用户分配数据
     * @param idDto
     * @return
     */
    @RequestProcess(checkLogin = true, logUrl = "/sys/distribution/get")
    @Override
    public ResultData<SysDistributionResDto> get(@Validated() @RequestBody IdDto idDto){
        return ResultData.createSuccessResult(sysDistributionService.get(idDto.getId()));
    }

    /**
     * 插入用户分配数据
     * @param reqDto
     * @return
     */
    @RequestProcess(checkLogin = true, logUrl = "/sys/distribution/insert")
    @Override
    public ResultData<Void> insert(@Validated() @RequestBody SysDistributionInsertReqDto reqDto){
        sysDistributionService.insert(reqDto);
        return ResultData.createSuccessResult(null);
    }

    /**
     * 修改用户分配数据
     * @param reqDto
     * @return
     */
    @RequestProcess(checkLogin = true, logUrl = "/sys/distribution/update")
    @Override
    public ResultData<Void> update(@Validated() @RequestBody SysDistributionUpdateReqDto reqDto){
        sysDistributionService.update(reqDto);
        return ResultData.createSuccessResult(null);
    }

}
