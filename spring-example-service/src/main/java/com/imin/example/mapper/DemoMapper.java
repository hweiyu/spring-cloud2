package com.imin.example.mapper;

import com.imin.example.conf.MyMapper;
import com.imin.example.model.DemoModel;
import com.imin.infrastructure.common.persistence.MyBatisDao;

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
