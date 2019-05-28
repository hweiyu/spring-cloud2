package com.imin.adminweb.dto.request.agent;

import com.imin.infrastructure.common.dto.TransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

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
public class AppBookingDevelopReqDto implements TransferObject {

    private static final long serialVersionUID = -1L;

    /**
     * 主键
     */
    @NotNull(message = "{v.id.not.empty}")
    @ApiModelProperty(value = "主键", name = "主键")
    private Long id;

    /**
     * 开发周期
     */
    @NotBlank(message = "{v.devPeriod.not.empty}")
    @ApiModelProperty(value = "开发周期", name = "开发周期")
    private String devPeriod;

    /**
     * 开发开始时间
     */
    @NotBlank(message = "{v.devStartTime.not.empty}")
    @ApiModelProperty(value = "开发开始时间", name = "开发开始时间")
    private String devStartTime;

    /**
     * 开发结束时间
     */
    @NotBlank(message = "{v.devEndTime.not.empty}")
    @ApiModelProperty(value = "开发结束时间", name = "开发结束时间")
    private String devEndTime;

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

    /**
     * 软件版本号
     */
    @ApiModelProperty(value = "软件版本号", name = "软件版本号")
    private String version;

    /**
     * 上传时间
     */
    @ApiModelProperty(value = "上传时间", name = "上传时间")
    private String deployTime;

    /**
     * 审批意见
     */
    @ApiModelProperty(value = "审批意见", name = "审批意见")
    private String auditSuggest;

}
