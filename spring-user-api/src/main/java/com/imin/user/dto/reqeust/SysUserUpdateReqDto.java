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
 * @Description: 系统用户请求dto
 * @date 2018-11-28 09:54:02
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysUserUpdateReqDto implements TransferObject {

    private static final long serialVersionUID = -1L;

    /**
     * 主键
     */
    @NotNull(message = "{v.id.not.empty}")
    @ApiModelProperty(value = "主键", name = "主键")
    private Long id;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", name = "邮箱")
    private String email;

    /**
     * 账号规则前缀
     */
    @NotNull(message = "{m.accountTypeValue.not.empty}")
    @ApiModelProperty(value = "账号规则前缀", name = "账号规则前缀")
    private String accountPrefix;

    /**
     * 平台类型(1:imin;2:代理商;3:开发者)
     */
    @NotNull(message = "{m.platformType.not.empty}")
    @ApiModelProperty(value = "平台类型(1:imin;2:代理商;3:开发者)", name = "平台类型(1:imin;2:代理商;3:开发者)")
    private Integer platformType;

    /**
     * 角色id列表
     */
    @ApiModelProperty(value = "角色id列表", name = "角色id列表")
    private List<Long> roleIds;

    /**
     * 状态(0:禁用;1:已用;2:已删除)
     */
    @ApiModelProperty(value = "状态(0:禁用;1:已用;2:已删除)", name = "状态(0:禁用;1:已用;2:已删除)")
    private Integer dataStatus;

    /**
     * 微信openId
     */
    @ApiModelProperty(value = "微信openId", name = "微信openId")
    private String openId;

}
