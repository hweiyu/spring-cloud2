package com.imin.user.api;

import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.user.dto.reqeust.*;
import com.imin.user.dto.response.*;
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
public interface SysDistributionApiService {

    /**
     * 获取用户分配数据列表
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "获取用户分配数据列表", notes = "获取用户分配数据列表")
    @PostMapping(value = "/sys/distribution/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData<PageInfo<SysDistributionListResDto>> list(@RequestBody SysDistributionQueryReqDto reqDto);

    /**
     * 获取用户分配数据
     * @param idDto
     * @return
     */
    @ApiOperation(value = "获取用户分配数据", notes = "获取用户分配数据")
    @PostMapping(value = "/sys/distribution/get", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData<SysDistributionResDto> get(@Validated() @RequestBody IdDto idDto);

    /**
     * 插入用户分配数据
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "插入用户分配数据", notes = "插入用户分配数据")
    @PostMapping(value = "/sys/distribution/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData<Void> insert(@Validated() @RequestBody SysDistributionInsertReqDto reqDto);

    /**
     * 修改用户分配数据
     * @param reqDto
     * @return
     */
    @ApiOperation(value = "修改用户分配数据", notes = "修改用户分配数据")
    @PostMapping(value = "/sys/distribution/update", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData<Void> update(@Validated() @RequestBody SysDistributionUpdateReqDto reqDto);

}
