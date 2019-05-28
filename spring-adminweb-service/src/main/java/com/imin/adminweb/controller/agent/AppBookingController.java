package com.imin.adminweb.controller.agent;

import com.imin.adminweb.dto.request.agent.*;
import com.imin.adminweb.dto.response.agent.AppBookingListResDto;
import com.imin.adminweb.dto.response.agent.AppBookingResDto;
import com.imin.adminweb.service.agent.AppBookingService;
import com.imin.infrastructure.common.dto.IdDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.imin.infrastructure.common.aop.RequestProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.infrastructure.common.dto.PageInfo;
import org.springframework.validation.annotation.Validated;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 应用定制控制器
 * @date 2019-02-27 10:53:58
 **/
@RestController
@RequestMapping("${api.url.prefix}")
@Api(tags = {"应用定制接口"})
public class AppBookingController {

    @Autowired
    private AppBookingService appBookingService;

    /**
     * 应用定制单据列表(申请人查看使用)
     */
    @ApiOperation(value = "应用定制单据列表(申请人查看使用)", notes = "应用定制单据列表(申请人查看使用)")
    @PostMapping(value = "/appbooking/applicant/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appbooking/applicant/list")
    public ResultData<PageInfo<AppBookingListResDto>> applicantList(@RequestBody AppBookingQueryReqDto reqDto){
        return ResultData.createSuccessResult(appBookingService.selectByApplicant(reqDto));
    }

    /**
     * 应用定制单据列表(审批人查看使用)
     */
    @ApiOperation(value = "应用定制单据列表(审批人查看使用)", notes = "应用定制单据列表(审批人查看使用)")
    @PostMapping(value = "/appbooking/auditor/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appbooking/auditor/list")
    public ResultData<PageInfo<AppBookingListResDto>> auditorList(@RequestBody AppBookingQueryReqDto reqDto){
        return ResultData.createSuccessResult(appBookingService.selectByAuditor(reqDto));
    }

    /**
     * 查询
     */
    @ApiOperation(value = "获取应用定制数据", notes = "获取应用定制数据")
    @PostMapping(value = "/appbooking/get", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appbooking/get")
    public ResultData<AppBookingResDto> get(@Validated() @RequestBody IdDto idDto){
        return ResultData.createSuccessResult(appBookingService.get(idDto.getId()));
    }

    /**
     * 保存(存草稿、存开发计划，存测试计划，存打包部署)
     */
    @ApiOperation(value = "保存(存草稿、存开发计划，存测试计划，存打包部署)", notes = "保存(存草稿、存开发计划，存测试计划，存打包部署)")
    @PostMapping(value = "/appbooking/save", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appbooking/save")
    public ResultData<IdDto> save(@Validated() @RequestBody AppBookingSaveReqDto reqDto){
        return ResultData.createSuccessResult(appBookingService.save(reqDto));
    }

    /**
     * 提交申请
     */
    @ApiOperation(value = "提交申请", notes = "提交申请")
    @PostMapping(value = "/appbooking/submit", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appbooking/submit")
    public ResultData<IdDto> submit(@Validated() @RequestBody AppBookingSubmitReqDto reqDto){
        return ResultData.createSuccessResult(appBookingService.submit(reqDto));
    }

    /**
     * 撤回
     */
    @ApiOperation(value = "撤回", notes = "撤回")
    @PostMapping(value = "/appbooking/revoke", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appbooking/revoke")
    public ResultData<IdDto> revoke(@Validated() @RequestBody IdDto idDto){
        return ResultData.createSuccessResult(appBookingService.revoke(idDto.getId()));
    }

    /**
     * 审核
     */
    @ApiOperation(value = "审核", notes = "审核")
    @PostMapping(value = "/appbooking/audit", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appbooking/audit")
    public ResultData<IdDto> audit(@Validated() @RequestBody AppBookingAuditReqDto reqDto){
        return ResultData.createSuccessResult(appBookingService.audit(reqDto));
    }

    /**
     * 开发中
     */
    @ApiOperation(value = "开发中", notes = "开发中")
    @PostMapping(value = "/appbooking/develop", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appbooking/develop")
    public ResultData<IdDto> develop(@Validated() @RequestBody AppBookingDevelopReqDto reqDto){
        return ResultData.createSuccessResult(appBookingService.develop(reqDto));
    }

    /**
     * 开发完成，测试通过并打包部署
     */
    @ApiOperation(value = "开发完成，测试通过并打包部署", notes = "开发完成，测试通过并打包部署")
    @PostMapping(value = "/appbooking/deploy", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appbooking/deploy")
    public ResultData<IdDto> deploy(@Validated() @RequestBody AppBookingDeployReqDto reqDto){
        return ResultData.createSuccessResult(appBookingService.deploy(reqDto));
    }

    /**
     * 验收
     */
    @ApiOperation(value = "验收", notes = "验收")
    @PostMapping(value = "/appbooking/accept", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = true, logUrl = "/appbooking/accept")
    public ResultData<IdDto> accept(@Validated() @RequestBody AppBookingAcceptReqDto reqDto){
        return ResultData.createSuccessResult(appBookingService.accept(reqDto));
    }

}
