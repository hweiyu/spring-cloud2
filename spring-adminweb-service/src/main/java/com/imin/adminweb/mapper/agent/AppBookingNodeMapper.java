package com.imin.adminweb.mapper.agent;

import com.imin.adminweb.conf.MyMapper;
import com.imin.adminweb.model.agent.AppBookingNodeModel;
import com.imin.infrastructure.common.persistence.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 应用定制审批流 mapper
 * @date 2019-03-01 11:11:07
 **/
@MyBatisDao
public interface AppBookingNodeMapper extends MyMapper<AppBookingNodeModel> {

    /**
     * 批量插入
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<AppBookingNodeModel> list);

}
