package com.imin.user.model;


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
 * @Description: 用户分配模型
 * @date 2018-11-28 09:54:02
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_sys_distribution")
public class SysDistributionModel {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 主用户id
     */
    @Column(name = "master_id")
    private Long masterId;

    /**
     * 下级用户id
     */
    @Column(name = "salve_id")
    private Long salveId;

    /**
     * 关系ids
     */
    @Column(name = "rel_ids")
    private String relIds;
}
