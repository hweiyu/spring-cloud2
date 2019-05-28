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
public class AppBookingAuditReqDto implements TransferObject {

    private static final long serialVersionUID = -1L;

    /**
      * 主键
      */
    @NotNull(message = "{v.id.not.empty}")
    @ApiModelProperty(value = "主键", name = "主键")
    private Long id;

    /**
      * 审核标记(0:未审核;1:审核通过;2:审核不通过)
      */
    @NotNull(message = "{v.auditFlag.not.empty}")
    @ApiModelProperty(value = "审核标记(0:未审核;1:审核通过;2:审核不通过)", name = "审核标记(0:未审核;1:审核通过;2:审核不通过)")
    private Integer auditFlag;

    /**
      * 审批意见
      */
    @ApiModelProperty(value = "审批意见", name = "审批意见")
    private String auditSuggest;

}
