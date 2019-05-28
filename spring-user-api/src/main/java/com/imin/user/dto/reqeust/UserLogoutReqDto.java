package com.imin.user.dto.reqeust;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
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
public class UserLogoutReqDto implements Serializable {

    private static final long serialVersionUID = -2701723007576993291L;

    /**
     * 主键
     */
    @NotNull(message = "{v.id.not.empty}")
    @ApiModelProperty(value = "主键", name = "主键")
    private Long id;

    /**
     * 授权令牌
     */
    @NotNull(message = "{v.token.not.empty}")
    @ApiModelProperty(value = "授权令牌", name = "授权令牌")
    private String token;

}
