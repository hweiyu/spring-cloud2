package com.imin.adminweb.model.retailers;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

/**
 * @author wangyong
 * @version V1.0
 * @Title: 描述
 * @Description: 商户终端设备信息表
 * @date 2019-02-22 09:54:02
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_retailers_device_info")
public class RetailersDeviceInfoModel {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;


    /**
     * 代理商帐号
     */
    @Column(name = "agent_account")
    private String agentAccount;

    /**
     * 代理商编号
     */
    @Column(name = "agent_id")
    private String agentId;

    /**
     * 商户编号
     */
    @Column(name = "retailer_id")
    private Long retailerId;


    /**
     * 商户名称
     */
    @Column(name = "retailer_name")
    private String retailerName;


    /**
     * 序列号
     */
    @Column(name = "sn")
    private String sn;


    /**
     * 设备激活状态（0：未激活,1:已激活）
     */
    @Column(name = "device_status")
    private Integer deviceStatus;


    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private String createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private String updateTime;
}
