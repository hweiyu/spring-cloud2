
package com.imin.adminweb.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.exception.ControllerException;
import com.imin.infrastructure.common.exception.DaoException;
import com.imin.infrastructure.common.exception.ServiceException;
import com.imin.infrastructure.common.utils.CopyDataUtil;
import com.imin.adminweb.dto.request.QueryDemoReqDto;
import com.imin.adminweb.dto.response.DemoDto;
import com.imin.adminweb.mapper.DemoMapper;
import com.imin.adminweb.model.DemoModel;
import com.imin.adminweb.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 *
 * @Title: TODO
 * @Description: TODO(用一句话描述该文件做什么)
 * @author ben
 * @date 2017年12月7日 下午2:07:47
 * @version V1.0
 * 
 **/

@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    private DemoMapper demoMapper;

    @Override
    public List<DemoModel> getList(QueryDemoReqDto dto) {
        PageHelper.startPage(dto.getPageNumberByDefault(), dto.getPageSizeByDefault());
        return demoMapper.selectAll();
    }

    @Override
    public DemoModel getById(Long id) {
        return demoMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public boolean insert(DemoDto dto) {
        DemoModel insertObj = CopyDataUtil.copyObject(dto, DemoModel.class);
        insertObj.setId(null);
        return demoMapper.insert(insertObj) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public boolean update(DemoDto dto) {
        DemoModel updateObj = CopyDataUtil.copyObject(dto, DemoModel.class);
        return demoMapper.updateByPrimaryKey(updateObj) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public boolean deleteById(IdDto id) {
        return demoMapper.deleteByPrimaryKey(id.getId()) > 0;
    }
    
    @Override
    public String handleException(int flag) {
        switch(flag) {
            case 1:
                throw new ControllerException("ControllerException");
            case 2:
                throw new ServiceException("ServiceException");
            case 3:
                throw new DaoException("DaoException");
            default:
                return "ok";       
        }
    }
    
    @SuppressWarnings("unused")
	private void selectByConstants() {
    	System.out.println(JSON.toJSONString(demoMapper.selectByConstants()));
    }
}
