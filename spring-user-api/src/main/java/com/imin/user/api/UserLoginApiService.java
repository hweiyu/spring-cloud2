package com.imin.user.api;

import com.imin.infrastructure.common.result.ResultData;
import com.imin.user.dto.reqeust.UserLoginReqDto;
import com.imin.user.dto.response.UserLoginResDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title 描述
 * @Description 描述
 * @date 2018/11/29 9:49
 **/
public interface UserLoginApiService {

    /**
     * 登录
     */
    @ApiOperation(value = "登录", notes = "登录")
    @PostMapping(value = "/platform/login", produces = MediaType.APPLICATION_JSON_VALUE)
    ResultData<UserLoginResDto> login(@RequestBody UserLoginReqDto reqDto);

}
