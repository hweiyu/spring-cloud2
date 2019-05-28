package com.imin.user.dto.reqeust;

import com.imin.infrastructure.common.dto.TransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 系统菜单权限资源请求dto
 * @date 2018-11-28 09:54:02
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysResourceInsertReqDto implements TransferObject {

    private static final long serialVersionUID = -1L;

    /**
     * 父节点id
     */
    @NotNull(message = "m.parentId.not.empty")
    @ApiModelProperty(value = "父节点id", name = "父节点id")
    private Long parentId;

    /**
     * 资源名称
     */
    @NotNull(message = "m.resourceName.not.empty")
    @ApiModelProperty(value = "资源名称", name = "资源名称")
    private String resourceName;

    /**
     * 资源类型(1:目录;2:菜单;3:按钮)
     */
    @NotNull(message = "m.resourceType.not.empty")
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
    @NotNull(message = "m.authCode.not.empty")
    @ApiModelProperty(value = "权限码", name = "权限码")
    private String authCode;

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
    @NotNull(message = "m.platformType.not.empty")
    @ApiModelProperty(value = "平台类型(1:imin;2:代理商;3:开发者)", name = "平台类型(1:imin;2:代理商;3:开发者)")
    private Integer platformType;

}
