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
 * @Description: 应用定制审批流模型
 * @date 2019-03-01 11:11:07
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_app_booking_node")
public class AppBookingNodeModel {

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
     * 审核人id
     */
    @Column(name = "auditor_id")
    private Long auditorId;

    /**
     * 审核人名称
     */
    @Column(name = "auditor_name")
    private String auditorName;

    /**
     * 审核人所属角色
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 当前审核节点标记(0:非当前审核人;1:当前审核人)
     */
    @Column(name = "audit_flag")
    private Integer auditFlag;

    /**
     * 审核人所属节点
     */
    @Column(name = "node")
    private String node;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 审核时间
     */
    @Column(name = "audit_time")
    private String auditTime;
}
