package com.imin.user.dto.response;

import com.imin.infrastructure.common.dto.TransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 系统角色返回dto
 * @date 2018-11-28 09:54:02
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysRoleListResDto implements TransferObject {

    private static final long serialVersionUID = -1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", name = "主键")
    private Long id;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", name = "用户id")
    private Long userId;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称", name = "角色名称")
    private String roleName;

    /**
     * 系统内置角色(0:不是;1:是)
     */
    @ApiModelProperty(value = "系统内置角色(0:不是;1:是)", name = "系统内置角色(0:不是;1:是)")
    private Integer innerFlag;

    /**
     * 状态(0:禁用;1:已用;2:已删除)
     */
    @ApiModelProperty(value = "状态(0:禁用;1:已用;2:已删除)", name = "状态(0:禁用;1:已用;2:已删除)")
    private Integer dataStatus;

    /**
     * 账号前缀
     */
    @ApiModelProperty(value = "账号前缀", name = "账号前缀")
    private String accountPrefix;

    /**
     * 平台类型(1:imin;2:代理商;3:开发者)
     */
    @ApiModelProperty(value = "平台类型(1:imin;2:代理商;3:开发者)", name = "平台类型(1:imin;2:代理商;3:开发者)")
    private Integer platformType;

}
