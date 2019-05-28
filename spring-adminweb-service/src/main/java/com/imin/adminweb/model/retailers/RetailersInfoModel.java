package com.imin.adminweb.model.retailers;


import lombok.*;

import javax.persistence.*;

/**
 * @author wangyong
 * @version V1.0
 * @Title: 描述
 * @Description: 商户信息模型
 * @date 2019-02-22 09:54:02
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_retailers_info")
public class RetailersInfoModel {

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
     * 代理商id
     */
    @Column(name = "agent_id")
    private Integer agentId;


    /**
     * 商户名称
     */
    @Column(name = "retailer_name")
    private String retailerName;


    /**
     * 商户地址
     */
    @Column(name = "retailer_address")
    private String retailerAddress;


    /**
     * 联系人
     */
    @Column(name = "retailer_contact")
    private String retailerContact;

    /**
     * 联系方式
     */
    @Column(name = "retailer_tel")
    private String retailerTel;


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
