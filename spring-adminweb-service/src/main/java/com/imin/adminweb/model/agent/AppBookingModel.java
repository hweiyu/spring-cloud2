package com.imin.adminweb.model.agent;


import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.*;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 应用定制模型
 * @date 2019-02-27 10:53:59
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_app_booking")
public class AppBookingModel {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 流水号
     */
    @Column(name = "seq")
    private String seq;

    /**
     * 软件名称
     */
    @Column(name = "software_name")
    private String softwareName;

    /**
     * 应用类型(0:OS;1:APK)
     */
    @Column(name = "app_type")
    private Integer appType;

    /**
     * 定制内容(0:桌面;1:壁纸;2:小部件;3:零售;4:餐饮;5:其它)
     */
    @Column(name = "content_type")
    private Integer contentType;

    /**
     * 定制原因
     */
    @Column(name = "booking_reason")
    private String bookingReason;

    /**
     * 升级策略(0:按地区;1:按SN;2:按设备型号3:按代理商)
     */
    @Column(name = "upgrade_strategy")
    private Integer upgradeStrategy;

    /**
     * 开发周期
     */
    @Column(name = "dev_period")
    private String devPeriod;

    /**
     * 开发开始时间
     */
    @Column(name = "dev_start_time")
    private String devStartTime;

    /**
     * 开发结束时间
     */
    @Column(name = "dev_end_time")
    private String devEndTime;

    /**
     * 测试周期
     */
    @Column(name = "test_period")
    private String testPeriod;

    /**
     * 测试开始时间
     */
    @Column(name = "test_start_time")
    private String testStartTime;

    /**
     * 测试结束时间
     */
    @Column(name = "test_end_time")
    private String testEndTime;

    /**
     * 软件版本号
     */
    @Column(name = "version")
    private String version;

    /**
     * 上传时间
     */
    @Column(name = "deploy_time")
    private String deployTime;

    /**
     * 申请人
     */
    @Column(name = "applicant")
    private Long applicant;

    /**
     * 审批状态(0:草稿;1:撤回;2:审核中;3:审核通过;4:审核不通过;5:开发中;6:待验收;7:验收通过;8:验收不通过)
     */
    @Column(name = "audit_status")
    private Integer auditStatus;

    /**
     * 审批意见
     */
    @Column(name = "audit_suggest")
    private String auditSuggest;

    /**
     * 验收意见
     */
    @Column(name = "accept_suggest")
    private String acceptSuggest;

    /**
     * 申请时间
     */
    @Column(name = "applicant_time")
    private String applicantTime;

    /**
     * 审核时间
     */
    @Column(name = "audit_time")
    private String auditTime;

    /**
     * 验收时间
     */
    @Column(name = "accept_time")
    private String acceptTime;

    /**
     * 审核标记(0:未审核;1:审核通过;2:审核不通过)
     */
    @Column(name = "audit_flag")
    private Integer auditFlag;

    /**
     * 验收标记(0:未验收;1:验收通过;2:验收不通过)
     */
    @Column(name = "accept_flag")
    private Integer acceptFlag;

    /**
     * 最新流程标记(0:不是;1:是)
     */
    @Column(name = "new_flag")
    private Integer newFlag;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private String createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private String updateTime;
}
