
package com.imin.adminweb.model;

import com.imin.infrastructure.common.dto.TransferObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;


/**
 * @author code
 * @version V1.0
 * @Title: 基础代码
 * @Description: 基础信息
 **/

@Table(name ="t_demo")
public class DemoModel extends BaseEntity implements TransferObject {
    private static final long serialVersionUID = 1L;

    @Getter @Setter private String name;
	@Getter @Setter private String content;
}
