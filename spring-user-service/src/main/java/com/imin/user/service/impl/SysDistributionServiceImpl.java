package com.imin.user.service.impl;

import com.imin.infrastructure.common.utils.CopyDataUtil;
import com.imin.user.dto.reqeust.SysDistributionInsertReqDto;
import com.imin.user.dto.reqeust.SysDistributionQueryReqDto;
import com.imin.user.dto.reqeust.SysDistributionUpdateReqDto;
import com.imin.user.dto.response.SysDistributionListResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.utils.PageUtil;

import com.imin.user.mapper.SysDistributionMapper;
import com.imin.user.model.SysDistributionModel;
import com.imin.user.service.SysDistributionService;
import com.imin.user.dto.response.SysDistributionResDto;

import java.util.List;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 用户分配服务
 * @date 2018-11-27 18:19:55
 **/
@Service
public class SysDistributionServiceImpl implements SysDistributionService {

    @Autowired
    private SysDistributionMapper sysDistributionMapper;

    /**
    * 列表
    */
    @Override
    public PageInfo<SysDistributionListResDto> select(SysDistributionQueryReqDto reqDto) {
        PageHelper.startPage(reqDto.getPageNumberByDefault(), reqDto.getPageSizeByDefault());
        List<SysDistributionModel> models = sysDistributionMapper.select(null);
        return PageUtil.CopyPageList(models, SysDistributionListResDto.class);
    }

    /**
    * 查询
    */
    @Override
    public SysDistributionResDto get(Long id) {
        SysDistributionModel model = sysDistributionMapper.selectOne(SysDistributionModel.builder().id(id).build());
        return CopyDataUtil.copyObject(model, SysDistributionResDto.class);
    }

    /**
    * 添加
    */
    @Override
    public int insert(SysDistributionInsertReqDto reqDto) {
        SysDistributionModel insertObj = CopyDataUtil.copyObject(reqDto, SysDistributionModel.class);
        return sysDistributionMapper.insertSelective(insertObj);
    }

    /**
    * 修改
    */
    @Override
    public int update(SysDistributionUpdateReqDto reqDto) {
        SysDistributionModel updateObj = CopyDataUtil.copyObject(reqDto, SysDistributionModel.class);
        return sysDistributionMapper.updateByPrimaryKeySelective(updateObj);
    }

    /**
    * 删除
    */
    @Override
    public int delete(SysDistributionUpdateReqDto reqDto) {
        SysDistributionModel deleteObj = CopyDataUtil.copyObject(reqDto, SysDistributionModel.class);
        return sysDistributionMapper.deleteByPrimaryKey(deleteObj);
    }

}
