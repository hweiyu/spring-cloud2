
package com.imin.adminweb.service.impl.agent;

import com.github.pagehelper.PageHelper;
import com.imin.adminweb.dto.request.agent.DeviceDeployQueryReqDto;
import com.imin.adminweb.dto.response.agent.DeviceDeployResDto;
import com.imin.adminweb.mapper.agent.DeviceDeployMapper;
import com.imin.adminweb.model.agent.DeviceDeployModel;
import com.imin.adminweb.service.agent.DeviceDeployService;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.utils.PageUtil;
import com.imin.infrastructure.common.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


/**
 * @author ben
 * @version V1.0
 * @Title: TODO
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2017年12月7日 下午2:07:47
 **/

@Service
public class DeviceDeployServiceImpl implements DeviceDeployService {
    @Autowired
    private DeviceDeployMapper deviceDeployMapper;

    /**
     * 列表
     */
    @Override
    public PageInfo<DeviceDeployResDto> select(DeviceDeployQueryReqDto reqDto) {
        PageHelper.startPage(reqDto.getPageNumberByDefault(), reqDto.getPageSizeByDefault());
        Example example = new Example(DeviceDeployModel.class);
        Example.Criteria criteria = example.createCriteria();
        //判断客户端传入的关键字是否为空
        if (!StringUtil.isEmptyOrNull(reqDto.getModel())) {
            //模糊查询model
            criteria.andLike("model", "%" + reqDto.getModel() + "%");
        }

        if (!StringUtil.isEmptyOrNull(reqDto.getSn())) {
            //模糊查询sn
            criteria.andLike("sn", "%" + reqDto.getSn() + "%");
        }

        //id 降序排序
        example.orderBy("id").desc();
        List<DeviceDeployModel> models = deviceDeployMapper.selectByExample(example);
        //返回数据
        return PageUtil.CopyPageList(models, DeviceDeployResDto.class);
    }


}
