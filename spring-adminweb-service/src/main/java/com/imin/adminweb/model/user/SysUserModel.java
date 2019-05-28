package com.imin.adminweb.model.user;


import lombok.*;

import javax.persistence.*;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 系统用户模型
 * @date 2018-11-28 09:54:02
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_sys_user")
public class SysUserModel {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 登录名
     */
    @Column(name = "login_name")
    private String loginName;

    /**
     * 密码
     */
    @Column(name = "login_password")
    private String loginPassword;

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 账号
     */
    @Column(name = "account")
    private String account;

    /**
     * 账户类型(1:超级管理员;2:普通用户)
     */
    @Column(name = "account_type")
    private Integer accountType;

    /**
     * 平台类型(1:imin;2:代理商;3:开发者)
     */
    @Column(name = "platform_type")
    private Integer platformType;

    /**
     * 是否第一次登录(0:非;1:是)
     */
    @Column(name = "first_login_flag")
    private Integer firstLoginFlag;

    /**
     * 状态(0:禁用;1:已用;2:已删除)
     */
    @Column(name = "data_status")
    private Integer dataStatus;

    /**
     * 微信openId
     */
    @Column(name = "open_id")
    private String openId;

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
