package com.imin.user.dto.reqeust;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title 描述
 * @Description 描述
 * @date 2018/11/29 9:47
 **/
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginReqDto implements Serializable {

    private static final long serialVersionUID = 2873316005410933679L;

    /**
     * 登录名
     */
    @ApiModelProperty(value = "登录名", name = "登录名")
    private String loginName;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", name = "密码")
    private String loginPassword;

    /**
     * 平台类型(1:imin;2:代理商;3:开发者)
     */
    @ApiModelProperty(value = "平台类型(1:imin;2:代理商;3:开发者)", name = "平台类型(1:imin;2:代理商;3:开发者)")
    private Integer platform;

}
