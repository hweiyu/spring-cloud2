package com.imin.adminweb.dto.request.agent;

import com.imin.infrastructure.common.dto.TransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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
public class AppBookingInsertReqDto implements TransferObject {

    private static final long serialVersionUID = -1L;

    /**
      * 软件名称
      */
    @ApiModelProperty(value = "软件名称", name = "软件名称")
    private String softwareName;

    /**
      * 应用类型(0:OS;1:APK)
      */
    @ApiModelProperty(value = "应用类型(0:OS;1:APK)", name = "应用类型(0:OS;1:APK)")
    private Integer appType;

    /**
      * 定制内容(0:桌面;1:壁纸;2:小部件;3:零售;4:餐饮;5:其它)
      */
    @ApiModelProperty(value = "定制内容(0:桌面;1:壁纸;2:小部件;3:零售;4:餐饮;5:其它)", name = "定制内容(0:桌面;1:壁纸;2:小部件;3:零售;4:餐饮;5:其它)")
    private Integer contentType;

    /**
      * 定制原因
      */
    @ApiModelProperty(value = "定制原因", name = "定制原因")
    private String bookingReason;

    /**
      * 开发周期
      */
    @ApiModelProperty(value = "开发周期", name = "开发周期")
    private String devPeriod;

    /**
      * 开发开始时间
      */
    @ApiModelProperty(value = "开发开始时间", name = "开发开始时间")
    private String devStartTime;

    /**
      * 开发结束时间
      */
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
      * 升级策略(0:按地区;1:按SN;2:按设备型号3:按代理商)
      */
    @ApiModelProperty(value = "升级策略(0:按地区;1:按SN;2:按设备型号3:按代理商)", name = "升级策略(0:按地区;1:按SN;2:按设备型号3:按代理商)")
    private Integer upgradeStrategy;

    /**
      * 申请人
      */
    @ApiModelProperty(value = "申请人", name = "申请人")
    private Long applicant;

    /**
      * 审核人
      */
    @ApiModelProperty(value = "审核人", name = "审核人")
    private Long auditor;

    /**
      * 审批状态(0:草稿;1:撤回;2:审核中;3:审核通过;4:审核不通过;5:开发中;6:待验收;7:验收通过;8:验收不通过)
      */
    @ApiModelProperty(value = "审批状态(0:草稿;1:撤回;2:审核中;3:审核通过;4:审核不通过;5:开发中;6:待验收;7:验收通过;8:验收不通过)", name = "审批状态(0:草稿;1:撤回;2:审核中;3:审核通过;4:审核不通过;5:开发中;6:待验收;7:验收通过;8:验收不通过)")
    private Integer auditStatus;

    /**
      * 审批意见
      */
    @ApiModelProperty(value = "审批意见", name = "审批意见")
    private String auditSuggest;

    /**
      * 验收意见
      */
    @ApiModelProperty(value = "验收意见", name = "验收意见")
    private String acceptSuggest;

    /**
      * 审核时间
      */
    @ApiModelProperty(value = "审核时间", name = "审核时间")
    private String auditTime;

    /**
      * 验收时间
      */
    @ApiModelProperty(value = "验收时间", name = "验收时间")
    private String acceptTime;

    /**
      * 审核标记(0:未审核;1:审核通过;2:审核不通过)
      */
    @ApiModelProperty(value = "审核标记(0:未审核;1:审核通过;2:审核不通过)", name = "审核标记(0:未审核;1:审核通过;2:审核不通过)")
    private Integer auditFlag;

    /**
      * 验收标记(0:未验收;1:验收通过;2:验收不通过)
      */
    @ApiModelProperty(value = "验收标记(0:未验收;1:验收通过;2:验收不通过)", name = "验收标记(0:未验收;1:验收通过;2:验收不通过)")
    private Integer acceptFlag;

}
