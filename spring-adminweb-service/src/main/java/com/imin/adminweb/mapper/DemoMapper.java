package com.imin.adminweb.mapper;

import com.imin.infrastructure.common.persistence.MyBatisDao;
import com.imin.adminweb.conf.MyMapper;
import com.imin.adminweb.model.DemoModel;

import java.util.Map;

/**
 * @author code
 * @version V1.0
 * @Title: 基础代码
 * @Description: Demo用例
 **/
@MyBatisDao
public interface DemoMapper extends MyMapper<DemoModel> {

    /**
     * Demo用例
     * 
     * @return
     */
    Map<String, Object> selectByConstants();
}
