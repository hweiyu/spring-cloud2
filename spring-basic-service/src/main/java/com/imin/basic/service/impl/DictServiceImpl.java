package com.imin.basic.service.impl;

import com.github.pagehelper.PageHelper;
import com.imin.basic.dto.request.*;
import com.imin.basic.dto.response.DictResDto;
import com.imin.basic.enums.SortEnum;
import com.imin.basic.mapper.DictMapper;
import com.imin.basic.model.DictModel;
import com.imin.basic.service.DictService;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.exception.ServiceException;
import com.imin.infrastructure.common.utils.CopyDataUtil;
import com.imin.infrastructure.common.utils.PageUtil;
import com.imin.infrastructure.common.utils.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 字典表服务
 * @date 2018-11-28 11:40:56
 **/
@Service
public class DictServiceImpl implements DictService {

    @Autowired
    private DictMapper dictMapper;

    /**
     * 条件查询列表
     *
     * @param reqDto
     * @return
     */
    @Override
    public List<DictResDto> select(DictListReqDto reqDto) {
        Example example = new Example(DictModel.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtil.isEmptyOrNull(reqDto.getDictType())) {
            criteria.andEqualTo("dictType", reqDto.getDictType());
        }
        if (!StringUtil.isEmptyOrNull(reqDto.getDictCode())) {
            criteria.andEqualTo("dictCode", reqDto.getDictCode());
        }
        if (!StringUtil.isEmptyOrNull(reqDto.getDictKey())) {
            criteria.andEqualTo("dictKey", reqDto.getDictKey());
        }
        if (null != reqDto.getSort()) {
            if (SortEnum.ASC == reqDto.getSort()) {
                example.orderBy("sortBy").asc();
            } else {
                example.orderBy("sortBy").desc();
            }
        }
        List<DictModel> models = dictMapper.selectByExample(example);
        return CopyDataUtil.copyList(models, DictResDto.class);
    }

    /**
     * 条件分页查询列表
     *
     * @param reqDto
     * @return
     */
    @Override
    public PageInfo<DictResDto> selectByPage(DictListQueryReqDto reqDto) {
        PageHelper.startPage(reqDto.getPageNumberByDefault(), reqDto.getPageSizeByDefault());
        Example example = new Example(DictModel.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtil.isEmptyOrNull(reqDto.getDictType())) {
            criteria.andLike("dictType", "%" + reqDto.getDictType() + "%");
        }
        if (!StringUtil.isEmptyOrNull(reqDto.getDictCode())) {
            criteria.andLike("dictCode", "%" + reqDto.getDictCode() + "%");
        }
        if (!StringUtil.isEmptyOrNull(reqDto.getDictKey())) {
            criteria.andLike("dictKey", "%" + reqDto.getDictKey() + "%");
        }
        if (null != reqDto.getSort()) {
            if (SortEnum.ASC == reqDto.getSort()) {
                example.orderBy("sortBy").asc();
            } else {
                example.orderBy("sortBy").desc();
            }
        }
        List<DictModel> models = dictMapper.selectByExample(example);
        return PageUtil.CopyPageList(models, DictResDto.class);
    }

    /**
     * 条件查询单个
     *
     * @param reqDto
     * @return
     */
    @Override
    public DictResDto getOne(DictReqDto reqDto) {
        List<DictModel> models = dictMapper.select(DictModel.builder()
                .dictType(reqDto.getDictType())
                .dictCode(reqDto.getDictCode())
                .dictKey(reqDto.getDictKey())
                .build());
        if (CollectionUtils.isNotEmpty(models)) {
            if (models.size() > 1) {
                throw new ServiceException("result many records, but need only one");
            }
            return CopyDataUtil.copyObject(models.get(0), DictResDto.class);
        }
        return null;
    }

    /**
     * 通过主键查询单个
     *
     * @param id
     * @return
     */
    @Override
    public DictResDto get(Long id) {
        DictModel model = dictMapper.selectOne(DictModel.builder().id(id).build());
        return CopyDataUtil.copyObject(model, DictResDto.class);
    }

    /**
     * 添加
     *
     * @param reqDto
     * @return
     */
    @Override
    public IdDto insert(DictInsertReqDto reqDto) {
        DictModel insertObj = CopyDataUtil.copyObject(reqDto, DictModel.class);
        dictMapper.insertSelective(insertObj);
        return new IdDto(insertObj.getId());
    }

    /**
     * 修改
     *
     * @param reqDto
     * @return
     */
    @Override
    public IdDto update(DictUpdateReqDto reqDto) {
        DictModel updateObj = CopyDataUtil.copyObject(reqDto, DictModel.class);
        dictMapper.updateByPrimaryKeySelective(updateObj);
        return new IdDto(updateObj.getId());
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public IdDto delete(Long id) {
        dictMapper.deleteByPrimaryKey(id);
        return new IdDto(id);
    }
}
