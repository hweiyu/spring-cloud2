package com.imin.user.mapper;

import com.imin.user.conf.MyMapper;
import com.imin.user.model.DemoModel;
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
