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
public class SysRoleInsertReqDto implements TransferObject {

    private static final long serialVersionUID = -1L;

    /**
     * 用户id
     */
    @NotNull(message = "{m.userId.not.empty}")
    @ApiModelProperty(value = "用户id", name = "用户id")
    private Long userId;

    /**
     * 角色名称
     */
    @NotNull(message = "{m.roleName.not.empty}")
    @ApiModelProperty(value = "角色名称", name = "角色名称")
    private String roleName;

    /**
     * 资源id列表
     */
    @ApiModelProperty(value = "资源id列表", name = "资源id列表")
    private List<Long> resourceIds;

}
