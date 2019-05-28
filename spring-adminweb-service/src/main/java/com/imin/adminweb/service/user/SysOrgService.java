package com.imin.adminweb.service.user;

import com.imin.infrastructure.common.dto.IdDto;
import com.imin.user.dto.reqeust.SysOrgInsertReqDto;
import com.imin.user.dto.reqeust.SysOrgQueryReqDto;
import com.imin.user.dto.reqeust.SysOrgUpdateReqDto;
import com.imin.user.dto.response.SysOrgListResDto;
import com.imin.user.dto.response.SysOrgResDto;

import java.util.List;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 系统菜单权限资源接口
 * @date 2018-11-27 18:19:55
 **/
public interface SysOrgService {

    /**
     * 列表
     */
    List<SysOrgListResDto> select(SysOrgQueryReqDto reqDto);

    /**
     * 查询
     */
    SysOrgResDto get(Long id);

    /**
     * 添加
     */
    IdDto insert(SysOrgInsertReqDto reqDto);

    /**
     * 修改
     */
    IdDto update(SysOrgUpdateReqDto reqDto);

    /**
     * 删除
     */
    IdDto delete(Long id);
}

