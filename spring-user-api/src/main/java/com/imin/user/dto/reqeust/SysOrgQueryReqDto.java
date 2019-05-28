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
public class SysOrgQueryReqDto implements TransferObject {

    private static final long serialVersionUID = -1L;

    /**
      * 组织类型(1:用户组织;2:合作伙伴组织)
      */
    @ApiModelProperty(value = "组织类型(1:用户组织;2:合作伙伴组织)", name = "组织类型(1:用户组织;2:合作伙伴组织)")
    private Integer orgType;

}
