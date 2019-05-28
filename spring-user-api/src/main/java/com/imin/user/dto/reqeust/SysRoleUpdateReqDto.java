package com.imin.user.dto.reqeust;

import com.imin.infrastructure.common.dto.TransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 系统角色请求dto
 * @date 2018-11-28 09:54:02
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysRoleUpdateReqDto implements TransferObject {

    private static final long serialVersionUID = -1L;

    /**
     * 主键
     */
    @NotNull(message = "{v.id.not.empty}")
    @ApiModelProperty(value = "主键", name = "主键")
    private Long id;

    /**
     * 角色名称
     */
    @NotNull(message = "{m.roleName.not.empty}")
    @ApiModelProperty(value = "角色名称", name = "角色名称")
    private String roleName;

    /**
     * 状态(0:禁用;1:已用;2:已删除)
     */
    @ApiModelProperty(value = "状态(0:禁用;1:已用;2:已删除)", name = "状态(0:禁用;1:已用;2:已删除)")
    private Integer dataStatus;

    /**
     * 资源id列表
     */
    @ApiModelProperty(value = "资源id列表", name = "资源id列表")
    private List<Long> resourceIds;

}
