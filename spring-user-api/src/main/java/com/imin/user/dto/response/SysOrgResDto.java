package com.imin.user.dto.response;

import com.imin.infrastructure.common.dto.TransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 系统菜单权限资源返回dto
 * @date 2018-11-28 09:54:02
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysOrgResDto implements TransferObject {

    private static final long serialVersionUID = -1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", name = "主键")
    private Long id;

    /**
     * 父节点id
     */
    @ApiModelProperty(value = "父节点id", name = "父节点id")
    private Long parentId;

    /**
     * 父节点ids
     */
    @ApiModelProperty(value = "父节点ids", name = "父节点ids")
    private String parentIds;

    /**
     * 组织名称
     */
    @ApiModelProperty(value = "组织名称", name = "组织名称")
    private String orgName;

    /**
     * 组织编码
     */
    @ApiModelProperty(value = "组织编码", name = "组织编码")
    private String orgQueue;

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
