package com.imin.adminweb.service.agent;

import com.imin.adminweb.dto.request.agent.AgentInsertReqDto;
import com.imin.adminweb.dto.request.agent.AgentQueryReqDto;
import com.imin.adminweb.dto.request.agent.AgentUpdateReqDto;

import com.imin.adminweb.dto.response.agent.AgentResDto;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;



/**
 * @author code
 * @version V1.0
 * @Title: 基础代码
 * @Description:  代理商信息
 **/
public interface AgentService {
    
    /**
     * 返回分页列表信息
     * @param reqDto 数据
     * @return list
     */
    PageInfo<AgentResDto> select(AgentQueryReqDto reqDto);


    /**
     * 根据id返回信息
     * @param id 数据
     * @return
     */
    AgentResDto get(Long id);
    


    /**
     *根据ID删除信息
     * @param idDto 数据
     * @return 
     */
    boolean deleteById(IdDto idDto);

    /**
     *增加
     * @param dto 数据
     * @return 
     */
    IdDto  insert(AgentInsertReqDto dto);



    /**
     *更新
     * @param dto 数据
     * @return 
     */
    IdDto update(AgentUpdateReqDto dto);
    

}
