package com.imin.adminweb.model.user;


import lombok.*;

import javax.persistence.*;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 系统菜单权限资源模型
 * @date 2018-11-28 09:54:02
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_sys_resource")
public class SysResourceModel {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 父节点id
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 资源名称
     */
    @Column(name = "resource_name")
    private String resourceName;

    /**
     * 资源类型(1:目录;2:菜单;3:按钮)
     */
    @Column(name = "resource_type")
    private Integer resourceType;

    /**
     * 资源地址
     */
    @Column(name = "path")
    private String path;

    /**
     * 权限码
     */
    @Column(name = "auth_code")
    private String authCode;

    /**
     * 资源唯一编码
     */
    @Column(name = "resource_code")
    private String resourceCode;

    /**
     * icon
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 排序
     */
    @Column(name = "sort_by")
    private Integer sortBy;

    /**
     * 平台类型(1:imin;2:代理商;3:开发者)
     */
    @Column(name = "platform_type")
    private Integer platformType;

    /**
     * 状态(0:禁用;1:已用;2:已删除)
     */
    @Column(name = "data_status")
    private Integer dataStatus;

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
