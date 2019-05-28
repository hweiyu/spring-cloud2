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
public class SysResourceUpdateStatusReqDto implements TransferObject {

    private static final long serialVersionUID = -1L;

    /**
     * 主键
     */
    @NotNull(message = "v.id.not.empty")
    @ApiModelProperty(value = "主键", name = "主键")
    private Long id;

    /**
     * 状态(0:禁用;1:启用)
     */
    @NotNull(message = "v.dataStatus.not.empty")
    @ApiModelProperty(value = "状态(0:禁用;1:启用)", name = "状态(0:禁用;1:启用)")
    private Integer dataStatus;

    public SysResourceUpdateReqDto to() {
        return SysResourceUpdateReqDto.builder()
                .id(getId())
                .dataStatus(getDataStatus())
                .build();
    }
}
