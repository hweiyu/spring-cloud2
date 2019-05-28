package com.imin.adminweb.service.retailers;

import com.imin.adminweb.dto.request.retailers.RetailersInsertReqDto;
import com.imin.adminweb.dto.request.retailers.RetailersQueryReqDto;
import com.imin.adminweb.dto.request.retailers.RetailersUpdateReqDto;
import com.imin.adminweb.dto.response.retailers.RetailersResDto;
import com.imin.adminweb.model.retailers.RetailersInfoModel;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;

import java.util.List;

/**
 * @author code
 * @version V1.0
 * @Title: 基础代码
 * @Description:  商户信息服务
 **/
public interface RetailersService {
    


    /**
     * 返回分页列表信息
     * @param reqDto 数据
     * @return list
     */
    PageInfo<RetailersResDto> select(RetailersQueryReqDto reqDto);
    /**
     * 根据id返回信息
     * @param id 数据
     * @return
     */
    RetailersResDto get(Long id);



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
    IdDto  insert(RetailersInsertReqDto dto);



    /**
     *更新
     * @param dto 数据
     * @return 
     */
    IdDto update(RetailersUpdateReqDto dto);
    

}
