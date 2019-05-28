package com.imin.basic.api;

import com.imin.basic.dto.request.*;
import com.imin.basic.dto.response.DictResDto;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.result.ResultData;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title 描述
 * @Description 描述
 * @date 2018/11/28 13:17
 **/
public interface DictApiService {

    /**
     * 条件查询字典信息列表
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "条件查询字典信息列表", notes = "条件查询字典信息列表")
    @PostMapping(value = "/dict/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData<List<DictResDto>> list(@RequestBody DictListReqDto reqDto);

    /**
     * 条件分页查询字典信息列表
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "条件分页查询字典信息列表", notes = "条件分页查询字典信息列表")
    @PostMapping(value = "/dict/listByPage", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData<PageInfo<DictResDto>> listByPage(@RequestBody DictListQueryReqDto reqDto);

    /**
     * 条件查询单个字典信息
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "条件查询单个字典信息", notes = "条件查询单个字典信息")
    @PostMapping(value = "/dict/getOne", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData<DictResDto> getOne(@RequestBody DictReqDto reqDto);

    /**
     * 通过主键id单个字典信息
     * @param idDto
     * @return
     */
    @ApiOperation(value = "通过主键id单个字典信息", notes = "通过主键id单个字典信息")
    @PostMapping(value = "/dict/get", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData<DictResDto> get(@RequestBody IdDto idDto);

    /**
     * 添加
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "添加", notes = "添加")
    @PostMapping(value = "/dict/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData<IdDto> insert(@Validated() @RequestBody DictInsertReqDto reqDto);

    /**
     * 修改
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "修改", notes = "修改")
    @PostMapping(value = "/dict/update", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData<IdDto> update(@Validated() @RequestBody DictUpdateReqDto reqDto);

    /**
     * 删除
     * @param idDto
     * @return
     */
    @ApiOperation(value = "删除", notes = "删除")
    @PostMapping(value = "/dict/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData<IdDto> delete(@Validated() @RequestBody IdDto idDto);
}
