package com.imin.user.mapper;

import com.imin.user.model.SysRoleResourceModel;
import com.imin.infrastructure.common.persistence.MyBatisDao;
import com.imin.user.conf.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 用户资源关系 mapper
 * @date 2018-11-27 18:19:55
 **/
@MyBatisDao
public interface SysRoleResourceMapper extends MyMapper<SysRoleResourceModel> {

    /**
     * 批量插入
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<SysRoleResourceModel> list);
	
}
