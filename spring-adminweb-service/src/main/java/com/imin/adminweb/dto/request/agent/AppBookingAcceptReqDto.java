package com.imin.adminweb.dto.request.agent;

import com.imin.infrastructure.common.dto.TransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 应用定制请求dto
 * @date 2019-02-27 10:53:59
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppBookingAcceptReqDto implements TransferObject {

    private static final long serialVersionUID = -1L;

    /**
      * 主键
      */
    @NotNull(message = "{v.id.not.empty}")
    @ApiModelProperty(value = "主键", name = "主键")
    private Long id;

    /**
     * 验收标记(0:未验收;1:验收通过;2:验收不通过)
     */
    @NotNull(message = "{v.acceptFlag.not.empty}")
    @ApiModelProperty(value = "验收标记(0:未验收;1:验收通过;2:验收不通过)", name = "验收标记(0:未验收;1:验收通过;2:验收不通过)")
    private Integer acceptFlag;

    /**
     * 验收意见
     */
    @ApiModelProperty(value = "验收意见", name = "验收意见")
    private String acceptSuggest;

}
