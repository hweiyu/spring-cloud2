package com.imin.user.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title 描述
 * @Description 描述
 * @date 2018/11/29 9:49
 **/
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLogoutResDto implements Serializable {

    private static final long serialVersionUID = -7713284086909905736L;

    /**
     * 登出结果(true:成功, false:失败)
     */
    @ApiModelProperty(value = "登出结果(true:成功, false:失败)", name = "登出结果(true:成功, false:失败)")
    private Boolean result;
}
