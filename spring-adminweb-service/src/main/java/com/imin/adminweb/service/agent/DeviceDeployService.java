package com.imin.adminweb.service.agent;

import com.imin.adminweb.dto.request.agent.AgentInsertReqDto;
import com.imin.adminweb.dto.request.agent.AgentQueryReqDto;
import com.imin.adminweb.dto.request.agent.AgentUpdateReqDto;
import com.imin.adminweb.dto.request.agent.DeviceDeployQueryReqDto;
import com.imin.adminweb.dto.response.agent.AgentResDto;
import com.imin.adminweb.dto.response.agent.DeviceDeployResDto;
import com.imin.adminweb.model.agent.AgentInfoModel;
import com.imin.adminweb.model.agent.DeviceDeployModel;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;

import java.util.List;

/**
 * @author code
 * @version V1.0
 * @Title: 基础代码
 * @Description:  设备部署信息
 **/
public interface DeviceDeployService {

    /**
     * 返回分页列表信息
     * @param reqDto 数据
     * @return list
     */
    PageInfo<DeviceDeployResDto> select(DeviceDeployQueryReqDto reqDto);




}
