
package com.imin.adminweb.model;

import com.imin.infrastructure.common.valid.interfaces.MustId;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


/**
 * @author code
 * @version V1.0
 * @Title: 基础代码
 * @Description: 基础信息
 **/

public class BaseEntity {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups={MustId.class}, message = "{v.id.not.empty}")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
