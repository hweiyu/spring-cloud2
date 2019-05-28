package com.imin.adminweb.dto.response.agent;

import com.imin.infrastructure.common.dto.TransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 应用定制返回dto
 * @date 2019-02-27 10:53:59
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppBookingAuditorResDto implements TransferObject {

    private static final long serialVersionUID = -1L;

    /**
      * 审核人id
      */
    @ApiModelProperty(value = "审核人id", name = "审核人id")
    private Long auditorId;

    /**
      * 流水号
      */
    @ApiModelProperty(value = "流水号", name = "流水号")
    private String auditorName;

}
