package com.imin.adminweb.dto.request;

import com.imin.infrastructure.common.dto.TransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

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
public class UserUpdatePasswordReqDto implements TransferObject {

    private static final long serialVersionUID = -1L;

    /**
     * 旧密码
     */
    @ApiModelProperty(value = "旧密码", name = "旧密码")
    private String oldPassword;

    /**
     * 新密码
     */
    @ApiModelProperty(value = "新密码", name = "新密码")
    private String newPassword;
}
