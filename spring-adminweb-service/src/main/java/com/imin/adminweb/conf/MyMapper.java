package com.imin.adminweb.conf;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author code
 * @version V1.0
 * @Title: 基础代码
 * @Description: 继承自己的MyMapper
 **/
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
    //TODO
    //FIXME 特别注意，该接口不能被扫描到，否则会出错
}
