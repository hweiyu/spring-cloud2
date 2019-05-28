package com.imin.adminweb.controller.customerservice;

import com.imin.adminweb.feign.customerservice.FeedbackinfoApiServiceRemote;
import com.imin.infrastructure.common.aop.RequestProcess;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.terminal.dto.request.customerservice.FeedbackInsertReqDto;
import com.imin.terminal.dto.request.customerservice.FeedbackReplyReqDto;
import com.imin.terminal.dto.request.customerservice.FeedbackQueryReqDto;
import com.imin.terminal.dto.request.customerservice.FeedbackSnQueryReqDto;
import com.imin.terminal.dto.response.customerservice.FeedbackListResDto;
import com.imin.terminal.dto.response.customerservice.FeedbackResDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangyong
 * @version V1.0
 * @Title: 描述
 * @Description: 客服反馈控制器
 * @date 2018-12-05 13:52:22
 **/
@RestController
@RequestMapping("${api.url.prefix}")
@Api(tags = {"客服反馈信息接口"})
public class FeedbackinfoController {

    @Autowired
    private FeedbackinfoApiServiceRemote feedbackinfoApiServiceRemote;

    /**
     * 列表
     */
    @ApiOperation(value = "客服反馈信息数据列表", notes = "客服反馈信息数据列表")
    @PostMapping(value = "/feedbackinfo/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logName = "客服反馈信息数据列表", logUrl = "/feedbackinfo/list")
    public ResultData<PageInfo<FeedbackListResDto>> list(@RequestBody FeedbackQueryReqDto reqDto) {
        return feedbackinfoApiServiceRemote.list(reqDto);


    }

    /**
     * 查询
     */
    @ApiOperation(value = "客服反馈信息数据", notes = "客服反馈信息数据")
    @PostMapping(value = "/feedbackinfo/get", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logName = "客服反馈信息数据", logUrl = "/feedbackinfo/get")
    public ResultData<FeedbackResDto> get(@Validated() @RequestBody IdDto idDto) {
        return feedbackinfoApiServiceRemote.get(idDto);
    }

    /**
     * 通过sn查数据
     */
    @ApiOperation(value = "通过sn查数据", notes = "通过sn查数据")
    @PostMapping(value = "/feedbackinfo/listBySn", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logName = "通过sn查数据", logUrl = "/feedbackinfo/listBySn")
    public ResultData<List<FeedbackListResDto>> listBySn(@Validated() @RequestBody FeedbackSnQueryReqDto reqDto) {
        return feedbackinfoApiServiceRemote.listBySn(reqDto);
    }

    /**
     * 客服意见反馈
     */
    @ApiOperation(value = "客服意见反馈", notes = "客服意见反馈")
    @PostMapping(value = "/feedbackinfo/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logName = "客服意见反馈", logUrl = "/feedbackinfo/insert")
    public ResultData<IdDto> insert(@Validated() @RequestBody FeedbackInsertReqDto reqDto) {
        return feedbackinfoApiServiceRemote.insert(reqDto);
    }

    /**
     * 回复
     */
    @ApiOperation(value = "回复", notes = "回复")
    @PostMapping(value = "/feedbackinfo/reply", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logName = "回复", logUrl = "/feedbackinfo/reply")
    public ResultData<IdDto> reply(@Validated() @RequestBody FeedbackReplyReqDto reqDto) {
        return feedbackinfoApiServiceRemote.reply(reqDto);
    }

}
