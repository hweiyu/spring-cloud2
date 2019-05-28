package com.imin.basic.service;

import com.imin.basic.dto.request.*;
import com.imin.basic.dto.response.DictResDto;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;

import java.util.List;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 字典表接口
 * @date 2018-11-28 11:40:56
 **/
public interface DictService {

    /**
     * 条件查询列表
     * @param reqDto
     * @return
     */
    List<DictResDto> select(DictListReqDto reqDto);

    /**
     * 条件分页查询列表
     * @param reqDto
     * @return
     */
    PageInfo<DictResDto> selectByPage(DictListQueryReqDto reqDto);

    /**
     * 条件查询单个
     * @param reqDto
     * @return
     */
    DictResDto getOne(DictReqDto reqDto);

    /**
     * 通过主键查询单个
     * @param id
     * @return
     */
    DictResDto get(Long id);

    /**
     * 添加
     * @param reqDto
     * @return
     */
    IdDto insert(DictInsertReqDto reqDto);

    /**
     * 修改
     * @param reqDto
     * @return
     */
    IdDto update(DictUpdateReqDto reqDto);

    /**
     * 删除
     * @param id
     * @return
     */
    IdDto delete(Long id);
}

