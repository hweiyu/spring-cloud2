package com.imin.adminweb.service.user;

import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.user.dto.reqeust.SysUserLogQueryReqDto;
import com.imin.user.dto.reqeust.SysUserLogInsertReqDto;
import com.imin.user.dto.response.SysUserLogListResDto;
import com.imin.user.dto.response.SysUserLogResDto;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 用户日志表接口
 * @date 2018-11-27 18:19:55
 **/
public interface SysUserLogService {

    /**
    * 列表
    */
    PageInfo<SysUserLogListResDto> select(SysUserLogQueryReqDto reqDto);

    /**
    * 查询
    */
    SysUserLogResDto get(Long id);

    /**
    * 添加
    */
    IdDto insert(SysUserLogInsertReqDto reqDto);

}

