package com.imin.user.dto.response;

import com.imin.infrastructure.common.dto.TransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 用户角色关系返回dto
 * @date 2018-11-28 09:54:02
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysUserRoleResDto implements TransferObject {

    private static final long serialVersionUID = -1L;
	
    /**
      * 主键
      */
    @ApiModelProperty(value = "主键", name = "主键")
    private Long id;
	
    /**
      * 用户id
      */
    @ApiModelProperty(value = "用户id", name = "用户id")
    private Long userId;
	
    /**
      * 角色id
      */
    @ApiModelProperty(value = "角色id", name = "角色id")
    private Long roleId;
	
}
