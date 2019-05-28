
package com.imin.adminweb.service.impl.agent;

import com.github.pagehelper.PageHelper;
import com.imin.adminweb.dto.request.agent.AgentInsertReqDto;
import com.imin.adminweb.dto.request.agent.AgentQueryReqDto;
import com.imin.adminweb.dto.request.agent.AgentUpdateReqDto;
import com.imin.adminweb.dto.response.agent.AgentResDto;
import com.imin.adminweb.mapper.agent.AgentMapper;
import com.imin.adminweb.model.agent.AgentInfoModel;
import com.imin.adminweb.service.agent.AgentService;
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
 * @Title: 代理商信息持久层
 * @Description: 代理商信息持久层
 * @date 2017年12月7日 下午2:07:47
 **/

@Service
public class AgentServiceImpl implements AgentService {
    @Autowired
    private AgentMapper agentMapper;

    /**
     * 列表
     */
    @Override
    public PageInfo<AgentResDto> select(AgentQueryReqDto reqDto) {
        PageHelper.startPage(reqDto.getPageNumberByDefault(), reqDto.getPageSizeByDefault());
        Example example = new Example(AgentInfoModel.class);
        Example.Criteria criteria = example.createCriteria();


        //判断客户端传入的关键字是否为空
        if (null != reqDto.getId()) {
            //模糊查询model
            criteria.andLike("id", "%" + reqDto.getId()+ "%");
        }

        //判断客户端传入的关键字是否为空
        if (!StringUtil.isEmptyOrNull(reqDto.getAgentNo())) {
            //模糊查询agentId
            criteria.andLike("agentId", "%" + reqDto.getAgentNo() + "%");
        }

        if (!StringUtil.isEmptyOrNull(reqDto.getAgentName())) {
            //模糊查询agentName
            criteria.andLike("agentName", "%" + reqDto.getAgentName() + "%");
        }

        //判断客户端传入的关键字是否为空
        if (null != reqDto.getUserId()) {
            //模糊查询model
            criteria.andLike("userId", "%" + reqDto.getUserId() + "%");
        }



        if (!StringUtil.isEmptyOrNull(reqDto.getAgentAptitude())) {
            //模糊查询agentAptitude
            criteria.andLike("agentAptitude", "%" + reqDto.getAgentAptitude() + "%");
        }
        //id 降序排序
        example.orderBy("id").desc();
        List<AgentInfoModel> models = agentMapper.selectByExample(example);
        //返回数据
        return PageUtil.CopyPageList(models, AgentResDto.class);
    }


    /**
     * 查询
     */
    @Override
    public AgentResDto get(Long id) {
        AgentInfoModel model = agentMapper.selectOne(AgentInfoModel.builder().id(id).build());
        return CopyDataUtil.copyObject(model, AgentResDto.class);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public IdDto insert(AgentInsertReqDto dto) {
        AgentInfoModel insertObj = CopyDataUtil.copyObject(dto, AgentInfoModel.class);
        agentMapper.insertSelective(insertObj);
        return new IdDto(insertObj.getId());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public IdDto update(AgentUpdateReqDto dto) {
        AgentInfoModel updateObj = CopyDataUtil.copyObject(dto, AgentInfoModel.class);
        agentMapper.updateByPrimaryKeySelective(updateObj);
        return new IdDto(updateObj.getId());

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public boolean deleteById(IdDto id) {
        return agentMapper.deleteByPrimaryKey(id.getId()) > 0;
    }

}
