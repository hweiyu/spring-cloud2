package com.imin.user.dto.response;

import com.imin.infrastructure.common.dto.TransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

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
public class SysResourceListResDto implements TransferObject {

    private static final long serialVersionUID = -1L;

    /**
     * 序号
     */
    @ApiModelProperty(value = "序号", name = "序号")
    private String serialNum ;

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
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称", name = "角色名称")
    private String resourceName;

    /**
     * 资源类型(1:目录;2:菜单;3:按钮)
     */
    @ApiModelProperty(value = "资源类型(1:目录;2:菜单;3:按钮)", name = "资源类型(1:目录;2:菜单;3:按钮)")
    private Integer resourceType;

    /**
     * 资源地址
     */
    @ApiModelProperty(value = "资源地址", name = "资源地址")
    private String path;

    /**
     * 权限码
     */
    @ApiModelProperty(value = "权限码", name = "权限码")
    private String authCode;

    /**
     * 资源唯一编码
     */
    @ApiModelProperty(value = "资源唯一编码", name = "资源唯一编码")
    private String resourceCode;

    /**
     * icon
     */
    @ApiModelProperty(value = "icon", name = "icon")
    private String icon;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", name = "排序")
    private Integer sortBy;

    /**
     * 平台类型(1:imin;2:代理商;3:开发者)
     */
    @ApiModelProperty(value = "平台类型(1:imin;2:代理商;3:开发者)", name = "平台类型(1:imin;2:代理商;3:开发者)")
    private Integer platformType;

    /**
     * 状态(0:禁用;1:已用;2:已删除)
     */
    @ApiModelProperty(value = "状态(0:禁用;1:已用;2:已删除)", name = "状态(0:禁用;1:已用;2:已删除)")
    private Integer dataStatus;

    /**
     * 子资源列表
     */
    @ApiModelProperty(value = "子资源列表", name = "子资源列表")
    private List<SysResourceListResDto> children;

}
