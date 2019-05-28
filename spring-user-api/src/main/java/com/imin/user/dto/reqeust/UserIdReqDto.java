package com.imin.user.dto.reqeust;

import com.imin.infrastructure.common.dto.TransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 系统角色请求dto
 * @date 2018-11-28 09:54:02
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserIdReqDto implements TransferObject {

    private static final long serialVersionUID = -1L;

    /**
     * 用户id
     */
    @NotNull(message = "{m.userId.not.empty}")
    @ApiModelProperty(value = "用户id", name = "用户id")
    private Long userId;

}
