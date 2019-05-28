package com.imin.adminweb.mapper.user;

import com.imin.adminweb.conf.MyMapper;
import com.imin.adminweb.model.user.SysUserModel;
import com.imin.infrastructure.common.persistence.MyBatisDao;
import org.apache.ibatis.annotations.Param;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 系统用户 mapper
 * @date 2018-11-27 18:19:55
 **/
@MyBatisDao
public interface SysUserMapper extends MyMapper<SysUserModel> {

    /**
     * 通过登录名查用户信息
     * @param loginName
     * @return
     */
    SysUserModel getByLoginAccount(@Param("loginName") String loginName);

}
