package com.imin.adminweb.service.impl.user;

import com.github.pagehelper.PageHelper;
import com.imin.adminweb.mapper.user.SysUserLogMapper;
import com.imin.adminweb.model.user.SysUserLogModel;
import com.imin.adminweb.service.user.SysUserLogService;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.utils.CopyDataUtil;
import com.imin.infrastructure.common.utils.PageUtil;
import com.imin.infrastructure.common.utils.StringUtil;
import com.imin.user.dto.reqeust.SysUserLogQueryReqDto;
import com.imin.user.dto.reqeust.SysUserLogInsertReqDto;
import com.imin.user.dto.response.SysUserLogListResDto;
import com.imin.user.dto.response.SysUserLogResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
        Example example = new Example(SysUserLogModel.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtil.isEmptyOrNull(reqDto.getUserName())) {
            criteria.andLike("userName", "%" + reqDto.getUserName() + "%");
        }
        if (!StringUtil.isEmptyOrNull(reqDto.getIp())) {
            criteria.andEqualTo("ip", reqDto.getIp());
        }
        List<SysUserLogModel> models = sysUserLogMapper.selectByExample(example);
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
    public IdDto insert(SysUserLogInsertReqDto reqDto) {
        SysUserLogModel insertObj = CopyDataUtil.copyObject(reqDto, SysUserLogModel.class);
        sysUserLogMapper.insertSelective(insertObj);
        return new IdDto(insertObj.getId());
    }

}
