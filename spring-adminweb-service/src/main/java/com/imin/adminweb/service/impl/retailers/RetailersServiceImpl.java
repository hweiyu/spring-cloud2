
package com.imin.adminweb.service.impl.retailers;

import com.github.pagehelper.PageHelper;
import com.imin.adminweb.dto.request.retailers.RetailersInsertReqDto;
import com.imin.adminweb.dto.request.retailers.RetailersQueryReqDto;
import com.imin.adminweb.dto.request.retailers.RetailersUpdateReqDto;
import com.imin.adminweb.dto.response.retailers.RetailersResDto;
import com.imin.adminweb.mapper.retailers.RetailersMapper;
import com.imin.adminweb.model.retailers.RetailersInfoModel;
import com.imin.adminweb.service.retailers.RetailersService;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.utils.CopyDataUtil;
import com.imin.infrastructure.common.utils.PageUtil;
import com.imin.infrastructure.common.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


/**
 * @author ben
 * @version V1.0
 * @Title: 商户信息管理实现类
 * @Description: 商户信息管理实现类
 * @date 2017年12月7日 下午2:07:47
 **/

@Service
public class RetailersServiceImpl implements RetailersService {
    @Autowired
    private RetailersMapper retailersMapper;


    /**
     * 列表
     */
    @Override
    public PageInfo<RetailersResDto> select(RetailersQueryReqDto reqDto) {
        PageHelper.startPage(reqDto.getPageNumberByDefault(), reqDto.getPageSizeByDefault());
        Example example = new Example(RetailersInfoModel.class);
        Example.Criteria criteria = example.createCriteria();

        //判断客户端传入的关键字是否为空
        if (null != reqDto.getId()) {
            //模糊查询model
            criteria.andLike("id", "%" + reqDto.getId()+ "%");
        }

        //判断客户端传入的关键字是否为空
        if (null != reqDto.getAgentId()) {
            //模糊查询model
            criteria.andLike("agentId", "%" + reqDto.getAgentId() + "%");
        }

        //判断客户端传入的关键字是否为空
        if (null != reqDto.getUserId()) {
            //模糊查询model
            criteria.andLike("userId", "%" + reqDto.getUserId() + "%");
        }


        if (!StringUtil.isEmptyOrNull(reqDto.getRetailerName())) {
            //模糊查询agentAptitude
            criteria.andLike("retailerName", "%" + reqDto.getRetailerName() + "%");
        }
        //id 降序排序
        example.orderBy("id").desc();
        List<RetailersInfoModel> models = retailersMapper.selectByExample(example);
        //返回数据
        return PageUtil.CopyPageList(models, RetailersResDto.class);
    }

    /**
     * 查询
     */
    @Override
    public RetailersResDto get(Long id) {
        RetailersInfoModel model = retailersMapper.selectOne(RetailersInfoModel.builder().id(id).build());
        return CopyDataUtil.copyObject(model, RetailersResDto.class);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public IdDto insert(RetailersInsertReqDto dto) {
        RetailersInfoModel insertObj = CopyDataUtil.copyObject(dto, RetailersInfoModel.class);
        retailersMapper.insertSelective(insertObj);
        return new IdDto(insertObj.getId());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public IdDto update(RetailersUpdateReqDto dto) {
        RetailersInfoModel updateObj = CopyDataUtil.copyObject(dto, RetailersInfoModel.class);
        retailersMapper.updateByPrimaryKeySelective(updateObj);
        return new IdDto(updateObj.getId());

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public boolean deleteById(IdDto id) {
        return retailersMapper.deleteByPrimaryKey(id.getId()) > 0;
    }


}
