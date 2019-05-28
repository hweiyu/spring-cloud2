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
public class AppBookingTestReqDto implements TransferObject {

    private static final long serialVersionUID = -1L;

    /**
      * 主键
      */
    @NotNull(message = "{v.id.not.empty}")
    @ApiModelProperty(value = "主键", name = "主键")
    private Long id;

    /**
     * 测试周期
     */
    @ApiModelProperty(value = "测试周期", name = "测试周期")
    private String testPeriod;

    /**
     * 测试开始时间
     */
    @ApiModelProperty(value = "测试开始时间", name = "测试开始时间")
    private String testStartTime;

    /**
     * 测试结束时间
     */
    @ApiModelProperty(value = "测试结束时间", name = "测试结束时间")
    private String testEndTime;

}
