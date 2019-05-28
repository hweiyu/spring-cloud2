
package com.imin.adminweb.service.impl.retailers;

import com.github.pagehelper.PageHelper;
import com.imin.adminweb.dto.request.retailers.*;
import com.imin.adminweb.dto.response.retailers.RetailersDeviceInfoResDto;
import com.imin.adminweb.feign.terminal.DeviceInfoApiServiceRemote;
import com.imin.adminweb.mapper.retailers.RetailersDeviceInfoMapper;
import com.imin.adminweb.model.retailers.RetailersDeviceInfoModel;
import com.imin.adminweb.model.retailers.RetailersInfoModel;
import com.imin.adminweb.service.retailers.RetailersDeviceInfoService;
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
 * @Title: 商户终端设备管理实现类
 * @Description: 商户终端设备管理实现类
 * @date 2019年3月19日 下午2:07:47
 **/

@Service
public class RetailersDeviceInfoServiceImpl implements RetailersDeviceInfoService {
    @Autowired
    private RetailersDeviceInfoMapper retailersDeviceInfoMapper;

    @Autowired
    private DeviceInfoApiServiceRemote deviceInfoApiServiceRemote;

    @Autowired
    private RetailersDeviceInfoService retailersDeviceInfoService;

    /**
     * 列表
     */
    @Override
    public PageInfo<RetailersDeviceInfoResDto> select(RetailersDeviceInfoQueryReqDto reqDto) {
        PageHelper.startPage(reqDto.getPageNumberByDefault(), reqDto.getPageSizeByDefault());
        Example example = new Example(RetailersDeviceInfoModel.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtil.isEmptyOrNull(reqDto.getSn())) {
            //拼接查询SN编号
            criteria.andLike("sn", "%" + reqDto.getSn() + "%");
        }

        //判断客户端传入的关键字是否为空
        if (null != reqDto.getUserId()) {
            //模糊查询model
            criteria.andLike("userId", "%" + reqDto.getUserId() + "%");
        }
        if (null != reqDto.getRetailerId()) {
            //拼接查询商户编号
            criteria.andLike("retailerId", "%" + reqDto.getRetailerId() + "%");
        }

        if (!StringUtil.isEmptyOrNull(reqDto.getDeviceStatus())) {
            //拼接查询激活状态
            criteria.andLike("deviceStatus", "%" + reqDto.getDeviceStatus() + "%");
        }
        //id 降序排序
        example.orderBy("id").desc();
        List<RetailersDeviceInfoModel> models = retailersDeviceInfoMapper.selectByExample(example);
        //返回数据
        return PageUtil.CopyPageList(models, RetailersDeviceInfoResDto.class);
    }

    /**
     * 查询
     */
    @Override
    public PageInfo<RetailersDeviceInfoResDto> get(RetailersDeviceInfoQueryReqDto reqDto) {
        Example example = new Example(RetailersDeviceInfoModel.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtil.isEmptyOrNull(reqDto.getSn())) {
            //拼接查询SN编号
            criteria.andLike("sn", "%" + reqDto.getSn() + "%");
        }
        if (!StringUtil.isEmptyOrNull(reqDto.getAgentAccount())) {
            //拼接查询代理商帐号
            criteria.andLike("agentAccount", "%" + reqDto.getAgentAccount() + "%");
        }
        if (null != reqDto.getRetailerId()) {
            //拼接查询商户编号
            criteria.andLike("retailerId", "%" + reqDto.getRetailerId() + "%");
        }
        if (!StringUtil.isEmptyOrNull(reqDto.getDeviceStatus())) {
            //拼接查询激活状态
            criteria.andLike("deviceStatus", "%" + reqDto.getDeviceStatus() + "%");
        }
        //id 降序排序
        example.orderBy("id").desc();
        List<RetailersDeviceInfoModel> models = retailersDeviceInfoMapper.selectByExample(example);
        return PageUtil.CopyPageList(models, RetailersDeviceInfoResDto.class);
    }

    /**
     * 保存
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public IdDto insert(RetailersDeviceInfoInsertReqDto dto) {
        RetailersDeviceInfoModel insertObj = CopyDataUtil.copyObject(dto, RetailersDeviceInfoModel.class);
        //查询设备终端信息是否存在记录，
        if (deviceInfoApiServiceRemote.getDeviceStatus(dto.getSn()) > 0) {
            //设置激活状态为:1
            insertObj.setDeviceStatus(1);
        } else {

        }
        List<RetailersDeviceInfoModel> retailer_model = retailersDeviceInfoMapper.select(RetailersDeviceInfoModel.builder().sn(dto.getSn()).build());
        //通过sn查询是否存在对应的代理商信息
        if (null != retailer_model && retailer_model.size() == 1) {
            System.out.println("当前SN号：" + dto.getSn() + " 的商户终端设备已存在，不保存");
        } else {
            retailersDeviceInfoMapper.insertSelective(insertObj);
        }
        return new IdDto(insertObj.getId());
    }

    /**
     * 批量增加
     *
     * @param reqDtos
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public int batchInsert(RetailersDeviceInfobatchInsertReqDto reqDtos) {
        //批量删除终端商户数据
        retailersBatchDelete(RetailersBatchDeleteReqDto.builder().sns(reqDtos.getSns()).build());
        //批量保存终端商户数据
        return retailersDeviceInfoMapper.batchInsert(reqDtos);
    }

    /**
     * 更新
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public int update(RetailersDeviceInfoUpdateReqDto dto) {
        Example example = new Example(RetailersDeviceInfoModel.class);
        example.createCriteria().andEqualTo("sn", dto.getSn());
        RetailersDeviceInfoModel param = RetailersDeviceInfoModel.builder().build();
        param.setAgentId(dto.getAgentId());
        param.setAgentAccount(dto.getAgentAccount());
        param.setUserId(dto.getUserId());
        param.setRetailerName(dto.getRetailerName());
        param.setRetailerId(dto.getId());
        return retailersDeviceInfoMapper.updateByExampleSelective(param, example);

    }


    /**
     * 商户终端批量更新
     *
     * @param dto
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public int BatchUpdate(RetailersBatchReqDto dto) {
        return retailersDeviceInfoMapper.BatchUpdate(dto);
    }


    /**
     * 删除
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public int deleteBySn(RetailersDeviceInfoQueryReqDto dto) {
        Example example = new Example(RetailersDeviceInfoModel.class);
        example.createCriteria().andEqualTo("sn", dto.getSn());
        return retailersDeviceInfoMapper.deleteByExample(example);


    }

    /**
     * 商户终端批量删除
     *
     * @param dto
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public int retailersBatchDelete(RetailersBatchDeleteReqDto dto) {
        return retailersDeviceInfoService.batchDelete(dto.getSns());
    }


    /**
     * 批量删除
     *
     * @param sns
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public int batchDelete(List<String> sns) {
        Example example = new Example(RetailersDeviceInfoModel.class);
        example.createCriteria().andIn("sn", sns);
        return retailersDeviceInfoMapper.deleteByExample(example);
    }


}
