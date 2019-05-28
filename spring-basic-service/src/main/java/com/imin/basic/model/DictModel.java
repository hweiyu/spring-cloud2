package com.imin.basic.model;


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
 * @Description: 字典表模型
 * @date 2018-11-28 11:40:56
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_dict")
public class DictModel {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 字典类型
     */
    @Column(name = "dict_type")
    private String dictType;

    /**
     * 字典码
     */
    @Column(name = "dict_code")
    private String dictCode;

    /**
     * 字典key
     */
    @Column(name = "dict_key")
    private String dictKey;

    /**
     * 字典value
     */
    @Column(name = "dict_value")
    private String dictValue;

    /**
     * 额外信息
     */
    @Column(name = "extra")
    private String extra;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 排序
     */
    @Column(name = "sort_by")
    private Integer sortBy;
}
