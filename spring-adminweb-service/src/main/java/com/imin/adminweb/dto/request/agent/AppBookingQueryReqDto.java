package com.imin.adminweb.dto.request.agent;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import com.imin.infrastructure.common.dto.BaseQueryDto;
import com.imin.infrastructure.common.dto.TransferObject;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 应用定制查询请求dto
 * @date 2019-02-27 10:53:59
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppBookingQueryReqDto extends BaseQueryDto implements TransferObject {

    private static final long serialVersionUID = -1L;

    /**
      * 软件名称
      */
    @ApiModelProperty(value = "软件名称", name = "软件名称")
    private String softwareName;

    /**
     * 审批状态
     */
    @ApiModelProperty(value = "审批状态", name = "审批状态")
    private Integer auditStatus;

    /**
     * 应用类型
     */
    @ApiModelProperty(value = "应用类型", name = "应用类型")
    private Integer appType;

}
