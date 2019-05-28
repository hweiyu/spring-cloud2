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
public class SysOrgInsertReqDto implements TransferObject {

    private static final long serialVersionUID = -1L;

    /**
     * 父节点id
     */
    @ApiModelProperty(value = "父节点id", name = "父节点id")
    private Long parentId;

    /**
     * 组织名称
     */
    @ApiModelProperty(value = "组织名称", name = "组织名称")
    private String orgName;

    /**
     * 组织类型(1:用户组织;2:合作伙伴组织)
     */
    @ApiModelProperty(value = "组织类型(1:用户组织;2:合作伙伴组织)", name = "组织类型(1:用户组织;2:合作伙伴组织)")
    private Integer orgType;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", name = "排序")
    private Integer sortBy;

}
