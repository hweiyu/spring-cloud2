package com.imin.adminweb.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title 描述
 * @Description 描述
 * @date 2018/12/6 15:53
 **/
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginReqDto implements Serializable {

    private static final long serialVersionUID = -8677890382890892055L;

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
     * 验证码
     */
    @ApiModelProperty(value = "验证码", name = "验证码")
    private String captcha;

    /**
     * 唯一码
     */
    @ApiModelProperty(value = "唯一码", name = "唯一码")
    private String uniqueKey;

    /**
     * 平台类型(1:imin;2:代理商;3:开发者)
     */
    @ApiModelProperty(value = "平台类型(1:imin;2:代理商;3:开发者)", name = "平台类型(1:imin;2:代理商;3:开发者)")
    private Integer platform;
}
