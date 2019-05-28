package com.imin.adminweb.mapper.user;

import com.imin.adminweb.conf.MyMapper;
import com.imin.adminweb.model.user.SysRoleResourceModel;
import com.imin.infrastructure.common.persistence.MyBatisDao;
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
