package com.imin.adminweb.dto.request;

import com.imin.infrastructure.common.dto.TransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 用户修改密码reqDto
 * @date 2018-11-28 09:54:02
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserBindOpenIdReqDto implements TransferObject {

    private static final long serialVersionUID = -1L;

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
     * 微信openId
     */
    @ApiModelProperty(value = "微信openId", name = "微信openId")
    private String openId;

}
