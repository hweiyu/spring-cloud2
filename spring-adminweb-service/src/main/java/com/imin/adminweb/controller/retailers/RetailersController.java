package com.imin.adminweb.controller.retailers;


import com.imin.adminweb.controller.BaseController;
import com.imin.adminweb.dto.request.retailers.RetailersInsertReqDto;
import com.imin.adminweb.dto.request.retailers.RetailersQueryReqDto;
import com.imin.adminweb.dto.request.retailers.RetailersUpdateReqDto;
import com.imin.adminweb.dto.response.retailers.RetailersResDto;
import com.imin.adminweb.model.retailers.RetailersInfoModel;
import com.imin.adminweb.service.retailers.RetailersService;
import com.imin.infrastructure.common.aop.PopedomType;
import com.imin.infrastructure.common.aop.RequestProcess;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.infrastructure.common.valid.interfaces.MustId;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author code
 * @version V1.0
 * @Title: 基础代码
 * @Description: Demo用例
 **/
@RestController
@RequestMapping("${api.url.prefix}")
@Api(tags = { "商户信息接口"})
@Validated
public class RetailersController extends BaseController {
    @Autowired
    private RetailersService retailersService;

    @ApiOperation(value = "商户信息数据列表", notes = "商户信息数据列表")
    @PostMapping(value = "/retailers/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logUrl = "/retailers/list", popedomCode = "demo",
            popedomType = PopedomType.View)
    public ResultData<PageInfo<RetailersResDto>> getListOfDto(@Valid @RequestBody RetailersQueryReqDto dto) {
        return ResultData.createSuccessResult(retailersService.select(dto));
    }

    /**
     * 查询
     */
    @ApiOperation(value = "获取商户信息", notes = "获取商户信息")
    @PostMapping(value = "/retailers/get", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logUrl = "/retailers/get")
    public ResultData<RetailersResDto> get(@Validated() @RequestBody IdDto idDto){
        return ResultData.createSuccessResult(retailersService.get(idDto.getId()));
    }



    @ApiOperation(value = "增加商户信息", notes = "增加商户信息")
    @PostMapping(value = "/retailers/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logName = "增加商户信息", logUrl = "/retailers/insert",
            popedomCode = "demo", popedomType = PopedomType.Insert, saveResult = true)
    public ResultData<IdDto> insert( @RequestBody RetailersInsertReqDto dto) {
        return ResultData.createSuccessResult(retailersService.insert(dto));

    }

    @ApiOperation(value = "更新商户信息", notes = "更新商户信息")
    @PostMapping(value = "/retailers/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logUrl = "/agent/update", popedomCode = "demo",
            popedomType = PopedomType.Update, saveResult = true)
    public ResultData<IdDto> update(@Validated({MustId.class}) @RequestBody RetailersUpdateReqDto dto) {
        return ResultData.createSuccessResult(retailersService.update(dto));

    }

    @ApiOperation(value = "删除商户信息", notes = "删除商户信息")
    @PostMapping(value = "/retailers/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logUrl = "/retailers/delete", popedomCode = "demo",
            popedomType = PopedomType.Delete, saveResult = true)
    public ResultData<Void> delete(@RequestBody IdDto idDto) {
        return ResultData.createDeleteResult(retailersService.deleteById(idDto));
    }
    
    
}
