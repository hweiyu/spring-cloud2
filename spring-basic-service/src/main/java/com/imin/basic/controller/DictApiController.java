package com.imin.basic.controller;

import com.imin.basic.api.DictApiService;
import com.imin.basic.dto.request.*;
import com.imin.basic.dto.response.DictResDto;
import com.imin.basic.service.DictService;
import com.imin.infrastructure.common.aop.RequestProcess;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.imin.infrastructure.common.result.ResultData;

import java.util.List;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 字典表控制器
 * @date 2018-11-28 11:40:56
 **/
@RestController
@RequestMapping("${api.url.prefix}")
@Api(tags = {"字典表接口"})
public class DictApiController implements DictApiService {

    @Autowired
    private DictService dictService;

    /**
     * 条件查询字典信息列表
     *
     * @param reqDto
     * @return
     */
    @RequestProcess(checkLogin = true, logName = "条件查询字典信息列表", logUrl = "/dict/list")
    @Override
    public ResultData<List<DictResDto>> list(@RequestBody DictListReqDto reqDto) {
        return ResultData.createSuccessResult(dictService.select(reqDto));
    }

    /**
     * 条件分页查询字典信息列表
     *
     * @param reqDto
     * @return
     */
    @RequestProcess(checkLogin = true, logName = "条件分页查询字典信息列表", logUrl = "/dict/listByPage")
    @Override
    public ResultData<PageInfo<DictResDto>> listByPage(@RequestBody DictListQueryReqDto reqDto) {
        return ResultData.createSuccessResult(dictService.selectByPage(reqDto));
    }

    /**
     * 条件查询单个字典信息
     *
     * @param reqDto
     * @return
     */
    @RequestProcess(checkLogin = true, logName = "条件查询单个字典信息", logUrl = "/dict/getOne")
    @Override
    public ResultData<DictResDto> getOne(@RequestBody DictReqDto reqDto) {
        return ResultData.createSuccessResult(dictService.getOne(reqDto));
    }

    /**
     * 通过主键id单个字典信息
     *
     * @param idDto
     * @return
     */
    @RequestProcess(checkLogin = true, logName = "通过主键id单个字典信息", logUrl = "/dict/get")
    @Override
    public ResultData<DictResDto> get(@Validated() @RequestBody IdDto idDto) {
        return ResultData.createSuccessResult(dictService.get(idDto.getId()));
    }

    /**
     * 添加
     *
     * @param reqDto
     * @return
     */
    @RequestProcess(checkLogin = true, logName = "添加", logUrl = "/dict/insert")
    @Override
    public ResultData<IdDto> insert(@Validated() @RequestBody DictInsertReqDto reqDto) {
        return ResultData.createSuccessResult(dictService.insert(reqDto));
    }

    /**
     * 修改
     *
     * @param reqDto
     * @return
     */
    @RequestProcess(checkLogin = true, logName = "修改", logUrl = "/dict/update")
    @Override
    public ResultData<IdDto> update(@Validated() @RequestBody DictUpdateReqDto reqDto) {
        return ResultData.createSuccessResult(dictService.update(reqDto));
    }

    /**
     * 删除
     *
     * @param idDto
     * @return
     */
    @RequestProcess(checkLogin = true, logName = "删除", logUrl = "/dict/delete")
    @Override
    public ResultData<IdDto> delete(@Validated() @RequestBody IdDto idDto) {
        return ResultData.createSuccessResult(dictService.delete(idDto.getId()));
    }
}
