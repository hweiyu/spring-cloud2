package com.imin.adminweb.controller.agent;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.imin.adminweb.controller.BaseController;
import com.imin.adminweb.dto.request.agent.AgentInsertReqDto;
import com.imin.adminweb.dto.request.agent.AgentQueryReqDto;
import com.imin.adminweb.dto.request.agent.AgentUpdateReqDto;
import com.imin.adminweb.dto.request.agent.weixinCodeQueryReqDto;
import com.imin.adminweb.dto.response.agent.AgentResDto;
import com.imin.adminweb.model.agent.AgentInfoModel;
import com.imin.adminweb.service.agent.AgentService;
import com.imin.adminweb.util.HttpClientUtil;
import com.imin.infrastructure.common.aop.PopedomType;
import com.imin.infrastructure.common.aop.RequestProcess;
import com.imin.infrastructure.common.dto.BaseQueryDto;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.dto.TransferObject;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.infrastructure.common.valid.interfaces.MustId;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author code
 * @version V1.0
 * @Title: 基础代码
 * @Description: Demo用例
 **/
@RestController
@RequestMapping("${api.url.prefix}")
@Api(tags = { "代理商信息接口"})
@Validated
public class AgentController extends BaseQueryDto implements TransferObject {
    @Autowired
    private AgentService agentService;

    @ApiOperation(value = "代理商数据列表", notes = "代理商数据列表")
    @PostMapping(value = "/agent/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logUrl = "/agent/list", popedomCode = "demo",
            popedomType = PopedomType.View)
    public ResultData<PageInfo<AgentResDto>> getListOfDto(@Valid @RequestBody AgentQueryReqDto reqDto) {
        return ResultData.createSuccessResult(agentService.select(reqDto));
    }


    /**
     * 获取微信OpenId
     */
    @ApiOperation(value = "获取微信OpenId", notes = "获取微信OpenId")
    @PostMapping(value = "/agent/getOpenId", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logUrl = "/agent/getOpenId")
    public ResultData<String> getOpenId(@Validated() @RequestBody weixinCodeQueryReqDto reqDto){
        List<Map> list = new ArrayList();
        String appid = "wx005a856fc67da4a1";
        String appsecret = "01ed26c5fef0841d097306a8fda92712";
        Map params = new HashMap();
        params.put("secret", appsecret);
        params.put("appid", appid);
        params.put("grant_type", "authorization_code");
        params.put("code", reqDto.getCode());
        String gettoken_UserId = HttpClientUtil.httpRequestToString("https://api.weixin.qq.com/sns/oauth2/access_token",params);// 返回token和UserId
        JSONObject gettoken_UserId_result = JSON.parseObject(gettoken_UserId); // 将String转换为
        String token = gettoken_UserId_result.getString("access_token");
        String openId = gettoken_UserId_result.getString("openid");
        return ResultData.createSuccessResult(openId);
    }

    /**
     * 查询
     */
    @ApiOperation(value = "获取代理商数据", notes = "获取代理商数据")
    @PostMapping(value = "/agent/get", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logUrl = "/appbooking/get")
    public ResultData<AgentResDto> get(@Validated() @RequestBody IdDto idDto){
        return ResultData.createSuccessResult(agentService.get(idDto.getId()));
    }



    @ApiOperation(value = "增加代理商", notes = "增加代理商")
    @PostMapping(value = "/agent/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logName = "增加代理商", logUrl = "/agent/insert", popedomCode = "agent", popedomType = PopedomType.Insert, saveResult = true)
    public ResultData<IdDto> insert(@Valid @RequestBody AgentInsertReqDto dto) {
        return ResultData.createSuccessResult(agentService.insert(dto));

    }

    @ApiOperation(value = "更新代理商", notes = "更新代理商")
    @PostMapping(value = "/agent/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logUrl = "/agent/update", popedomCode = "agent",
            popedomType = PopedomType.Update, saveResult = true)
    public ResultData<IdDto> update(@Validated({MustId.class}) @RequestBody AgentUpdateReqDto dto) {
        return ResultData.createSuccessResult(agentService.update(dto));

    }

    @ApiOperation(value = "删除代理商", notes = "删除代理商")
    @PostMapping(value = "/agent/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestProcess(checkLogin = false, logUrl = "/agent/delete", popedomCode = "agent",
            popedomType = PopedomType.Delete, saveResult = true)
    public ResultData<Void> delete(@RequestBody IdDto idDto) {
        return ResultData.createDeleteResult(agentService.deleteById(idDto));
    }
    
    
}
