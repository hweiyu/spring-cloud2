
package com.imin.user.controller;

import com.imin.user.conf.ResoureMessage;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 *
 * @Title: TODO
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author ben
 * @date 2017年12月7日 下午2:31:25
 * @version V1.0  
 * 
 **/
public class BaseController {
    @Autowired
    private ResoureMessage resoureMessage;
    
    protected <T> PageInfo<T> createListByPageInfo(List<T> list) {
        return new PageInfo<T>(list);
    }
    
    protected  <T, V> PageInfo<V> convertListByPageInfo(List<T> list, Class<V> clazz) {
        return PageUtil.CopyPageList(list, clazz);
    }
    
    protected String getMessage(String name) {
        return resoureMessage.getMessage(name);
    }
}
