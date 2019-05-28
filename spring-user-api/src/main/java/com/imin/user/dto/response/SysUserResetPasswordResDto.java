package com.imin.user.dto.response;

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
public class SysUserResetPasswordResDto implements TransferObject {

    private static final long serialVersionUID = -1L;

    /**
     * 用户id
     */
    @NotNull(message = "m.userId.not.empty")
    @ApiModelProperty(value = "用户id", name = "用户id")
    private Long id;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", name = "密码")
    private String password;

}
