package com.imin.adminweb.service;

import com.imin.infrastructure.common.dto.IdDto;
import com.imin.adminweb.dto.request.QueryDemoReqDto;
import com.imin.adminweb.dto.response.DemoDto;
import com.imin.adminweb.model.DemoModel;

import java.util.List;

/**
 * @author code
 * @version V1.0
 * @Title: 基础代码
 * @Description: 基础信息
 **/
public interface DemoService {
    
    /**
     * 返回分页列表信息
     * @param dto 数据
     * @return list
     */
    List<DemoModel> getList(QueryDemoReqDto dto);
    
    /**
     * 根据id返回信息
     * @param id 数据
     * @return 
     */
    DemoModel getById(Long id);
    

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
    boolean insert(DemoDto dto);
    
    /**
     *更新
     * @param dto 数据
     * @return 
     */
    boolean update(DemoDto dto);
    
    /**
     *处理业务异常
     *@param flag
     *@return String
     */
    String handleException(int flag);
}
