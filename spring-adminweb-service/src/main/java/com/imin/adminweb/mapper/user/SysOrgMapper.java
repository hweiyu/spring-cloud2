package com.imin.adminweb.mapper.user;

import com.imin.adminweb.conf.MyMapper;
import com.imin.adminweb.model.user.SysOrgModel;
import com.imin.infrastructure.common.persistence.MyBatisDao;
import org.apache.ibatis.annotations.Param;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 系统菜单权限资源 mapper
 * @date 2018-11-27 18:19:55
 **/
@MyBatisDao
public interface SysOrgMapper extends MyMapper<SysOrgModel> {

    /**
     * 获取最大的编码
     * @param parentId
     * @return
     */
    String getMaxQueue(@Param("parentId") Long parentId);
}
