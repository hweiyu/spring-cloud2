package com.imin.adminweb.model.user;

import lombok.*;

import javax.persistence.*;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 系统组织机构
 * @date 2018-11-28 09:54:02
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_sys_org")
public class SysOrgModel {

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
     * 父节点ids
     */
    @Column(name = "parent_ids")
    private String parentIds;

    /**
     * 组织名称
     */
    @Column(name = "org_name")
    private String orgName;

    /**
     * 组织编码
     */
    @Column(name = "org_queue")
    private String orgQueue;

    /**
     * 组织类型(1:用户组织;2:合作伙伴组织)
     */
    @Column(name = "org_type")
    private Integer orgType;

    /**
     * 排序
     */
    @Column(name = "sort_by")
    private Integer sortBy;

}
