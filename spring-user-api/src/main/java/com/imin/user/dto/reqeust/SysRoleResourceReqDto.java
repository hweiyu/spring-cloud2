package com.imin.user.dto.reqeust;

import com.imin.infrastructure.common.dto.TransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 用户资源关系请求dto
 * @date 2018-11-28 09:54:02
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysRoleResourceReqDto implements TransferObject {

    private static final long serialVersionUID = -1L;
	
    /**
      * 主键
      */
    @ApiModelProperty(value = "主键", name = "主键")
    private Long id;
	
    /**
      * 角色id
      */
    @ApiModelProperty(value = "角色id", name = "角色id")
    private Long roleId;
	
    /**
      * 资源id
      */
    @ApiModelProperty(value = "资源id", name = "资源id")
    private Long resourceId;
	
}
