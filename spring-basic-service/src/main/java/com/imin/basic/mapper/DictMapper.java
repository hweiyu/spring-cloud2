package com.imin.basic.mapper;

import com.imin.basic.conf.MyMapper;
import com.imin.basic.model.DictModel;
import com.imin.infrastructure.common.persistence.MyBatisDao;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 字典表 mapper
 * @date 2018-11-28 11:40:56
 **/
@MyBatisDao
public interface DictMapper extends MyMapper<DictModel> {
	
}
