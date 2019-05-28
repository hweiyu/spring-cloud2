package com.imin.adminweb.model.agent;


import lombok.*;

import javax.persistence.*;

/**
 * @author wangyong
 * @version V1.0
 * @Title: 描述
 * @Description: 代理商信息模型
 * @date 2019-02-22 09:54:02
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_agent_info")
public class AgentInfoModel {

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
     * 代理商编号(暂时不用)
     */
    @Column(name = "agent_no")
    private String agentNo;

    /**
     * 代理商名称
     */
    @Column(name = "agent_name")
    private String agentName;

    /**
     * 代理商资质
     */
    @Column(name = "agent_aptitude")
    private String agentAptitude;

    /**
     * 代理商账号
     */
    @Column(name = "agent_account")
    private String agentAccount;

    /**
     * 详细地址
     */
    @Column(name = "agent_address")
    private String agentAddress;

    /**
     * 联系人
     */
    @Column(name = "agent_contacts")
    private String agentContacts;

    /**
     * 联系方式
     */
    @Column(name = "agent_tel")
    private String agentTel;


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
