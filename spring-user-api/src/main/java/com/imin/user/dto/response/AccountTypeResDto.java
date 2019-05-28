package com.imin.user.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title 描述
 * @Description 描述
 * @date 2018/11/29 15:54
 **/
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountTypeResDto implements Serializable {

    private static final long serialVersionUID = -8782924477188796179L;

    /**
     * 系统内置角色id
     */
    @ApiModelProperty(value = "系统内置角色id", name = "系统内置角色id")
    private Long roleId;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", name = "名称")
    private String name;

    /**
     * 账户规则前缀
     */
    @ApiModelProperty(value = "账户规则前缀", name = "账户规则前缀")
    private String prefix;

    /**
     * 平台类型
     */
    @ApiModelProperty(value = "平台类型", name = "平台类型")
    private Integer platformType;
}
