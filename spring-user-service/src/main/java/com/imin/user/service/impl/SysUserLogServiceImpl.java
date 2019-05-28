package com.imin.user.service.impl;

import com.imin.user.dto.reqeust.SysUserLogQueryReqDto;
import com.imin.user.dto.reqeust.SysUserLogInsertReqDto;
import com.imin.user.dto.response.SysUserLogListResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.utils.CopyDataUtil;
import com.imin.infrastructure.common.utils.PageUtil;

import com.imin.user.mapper.SysUserLogMapper;
import com.imin.user.model.SysUserLogModel;
import com.imin.user.service.SysUserLogService;
import com.imin.user.dto.response.SysUserLogResDto;

import java.util.List;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 用户日志表服务
 * @date 2018-11-27 18:19:55
 **/
@Service
public class SysUserLogServiceImpl implements SysUserLogService {

    @Autowired
    private SysUserLogMapper sysUserLogMapper;

    /**
    * 列表
    */
    @Override
    public PageInfo<SysUserLogListResDto> select(SysUserLogQueryReqDto reqDto) {
        PageHelper.startPage(reqDto.getPageNumberByDefault(), reqDto.getPageSizeByDefault());
        List<SysUserLogModel> models = sysUserLogMapper.select(SysUserLogModel.builder().build());
        return PageUtil.CopyPageList(models, SysUserLogListResDto.class);
    }

    /**
    * 查询
    */
    @Override
    public SysUserLogResDto get(Long id) {
        SysUserLogModel model = sysUserLogMapper.selectOne(SysUserLogModel.builder().id(id).build());
        return CopyDataUtil.copyObject(model, SysUserLogResDto.class);
    }

    /**
    * 添加
    */
    @Override
    public int insert(SysUserLogInsertReqDto reqDto) {
        SysUserLogModel insertObj = CopyDataUtil.copyObject(reqDto, SysUserLogModel.class);
        return sysUserLogMapper.insertSelective(insertObj);
    }

    /**
    * 修改
    */
    @Override
    public int update(SysUserLogInsertReqDto reqDto) {
        SysUserLogModel updateObj = CopyDataUtil.copyObject(reqDto, SysUserLogModel.class);
        return sysUserLogMapper.updateByPrimaryKeySelective(updateObj);
    }

    /**
    * 删除
    */
    @Override
    public int delete(SysUserLogInsertReqDto reqDto) {
        SysUserLogModel updateObj = CopyDataUtil.copyObject(reqDto, SysUserLogModel.class);
        return sysUserLogMapper.delete(updateObj);
    }

}
