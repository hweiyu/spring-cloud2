package com.imin.basic.controller;


import com.imin.basic.request.QueryDemoReqDto;
import com.imin.basic.response.DemoDto;
import com.imin.basic.service.DemoService;
import com.imin.infrastructure.common.aop.PopedomType;
import com.imin.infrastructure.common.aop.RequestProcess;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.exception.ServiceException;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.infrastructure.common.utils.CopyDataUtil;
import com.imin.infrastructure.common.valid.interfaces.MustId;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author code
 * @version V1.0
 * @Title: 基础代码
 * @Description: Demo用例
 **/
@RestController
@RequestMapping("${api.url.prefix}")
@Api(tags = { "演示接口"})
@Validated
public class DemoController extends BaseController {
    @Autowired
    private DemoService demoService;

    @ApiOperation(value = "业务异常", notes = "业务异常")
    @ApiImplicitParams({@ApiImplicitParam(name = "flag", value = "标志(1,Controller异常;2,Service异常;3,Dao异常;否则正常)",
            paramType = "query", required = false, dataType = "Long")})
    @GetMapping(value = "/demo/handleException", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logUrl = "/demo/handleException")
    public ResultData<String> handleServiceException(int flag) throws ServiceException {
        return ResultData.createSuccessResult(demoService.handleException(flag));
    }

    @ApiOperation(value = "获取数据列表", notes = "获取数据列表")
    @PostMapping(value = "/demo/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logUrl = "/demo/list", popedomCode = "demo",
            popedomType = PopedomType.View)
    public ResultData<PageInfo<DemoDto>> getListOfDto(@Valid @RequestBody QueryDemoReqDto dto) {
        return ResultData.createSuccessResult(
                convertListByPageInfo(demoService.getList(dto), DemoDto.class));
    }

    @ApiOperation(value = "查询指定数据", notes = "查询指定数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "数据Id", paramType = "query",
            required = false, dataType = "Long")})

    @GetMapping(value = "/demo/query", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logUrl = "/demo/query")
    public ResultData<DemoDto> query(@NotNull(message = "v.id.not.empty") Long id) {
        return ResultData.createQuerySuccessResult(
                CopyDataUtil.copyObject(demoService.getById(id), DemoDto.class));
    }

    @ApiOperation(value = "增加数据", notes = "增加数据")
    @PostMapping(value = "/demo/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logName = "增加数据", logUrl = "/demo/insert",
            popedomCode = "demo", popedomType = PopedomType.Insert, saveResult = true)
    public ResultData<DemoDto> insert(@Valid @RequestBody DemoDto dto) {
        return ResultData.createAddResult(demoService.insert(dto), dto);
    }

    @ApiOperation(value = "更新数据", notes = "更新数据")
    @PostMapping(value = "/demo/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logUrl = "/demo/update", popedomCode = "demo",
            popedomType = PopedomType.Update, saveResult = true)
    public ResultData<DemoDto> update(@Validated({MustId.class}) @RequestBody DemoDto dto) {
        return ResultData.createUpdateResult(demoService.update(dto), dto);
    }

    @ApiOperation(value = "删除数据", notes = "删除数据")
    @PostMapping(value = "/demo/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logUrl = "/demo/delete", popedomCode = "demo",
            popedomType = PopedomType.Delete, saveResult = true)
    public ResultData<Void> delete(@RequestBody IdDto idDto) {
        return ResultData.createDeleteResult(demoService.deleteById(idDto));
    }
    
    
}
