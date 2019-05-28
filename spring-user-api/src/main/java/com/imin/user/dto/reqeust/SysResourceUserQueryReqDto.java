package com.imin.user.dto.reqeust;

import com.imin.infrastructure.common.dto.TransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 系统菜单权限资源查询请求dto
 * @date 2018-11-28 09:54:02
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysResourceUserQueryReqDto implements TransferObject {

    private static final long serialVersionUID = -1L;
	
    /**
      * 用户id
      */
    @ApiModelProperty(value = "用户id", name = "用户id")
    private Long userId;

}
