package com.imin.adminweb.model.user;


import lombok.*;

import javax.persistence.*;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 系统角色模型
 * @date 2018-11-28 09:54:02
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_sys_role")
public class SysRoleModel {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 角色编码
     */
    @Column(name = "role_code")
    private String roleCode;

    /**
     * 系统内置角色(0:不是;1:是)
     */
    @Column(name = "inner_flag")
    private Integer innerFlag;

    /**
     * 状态(0:禁用;1:已用;2:已删除)
     */
    @Column(name = "data_status")
    private Integer dataStatus;

    /**
     * 账号前缀
     */
    @Column(name = "account_prefix")
    private String accountPrefix;

    /**
     * 平台类型(1:imin;2:代理商;3:开发者)
     */
    @Column(name = "platform_type")
    private Integer platformType;

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
