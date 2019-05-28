package com.imin.user.mapper;

import com.imin.user.model.SysUserLogModel;
import com.imin.infrastructure.common.persistence.MyBatisDao;
import com.imin.user.conf.MyMapper;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 用户日志表 mapper
 * @date 2018-11-27 18:19:55
 **/
@MyBatisDao
public interface SysUserLogMapper extends MyMapper<SysUserLogModel> {
	
}
