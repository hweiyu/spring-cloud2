package com.imin.user.dto.response;

import com.imin.infrastructure.common.dto.TransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 用户分配返回dto
 * @date 2018-11-28 09:54:02
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysDistributionListResDto implements TransferObject {

    private static final long serialVersionUID = -1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", name = "主键")
    private Long id;

    /**
     * 登录名
     */
    @ApiModelProperty(value = "登录名", name = "登录名")
    private String loginName;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", name = "密码")
    private String loginPassword;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", name = "邮箱")
    private String email;

    /**
     * 账号
     */
    @ApiModelProperty(value = "账号", name = "账号")
    private String account;

    /**
     * 账户类型(1:超级管理员;2:普通用户)
     */
    @ApiModelProperty(value = "账户类型(1:超级管理员;2:普通用户)", name = "账户类型(1:超级管理员;2:普通用户)")
    private Integer accountType;

    /**
     * 平台类型(1:imin;2:代理商;3:开发者)
     */
    @ApiModelProperty(value = "平台类型(1:imin;2:代理商;3:开发者)", name = "平台类型(1:imin;2:代理商;3:开发者)")
    private Integer platformType;

    /**
     * 是否第一次登录(0:非;1:是)
     */
    @ApiModelProperty(value = "是否第一次登录(0:非;1:是)", name = "是否第一次登录(0:非;1:是)")
    private Integer firstLoginFlag;

    /**
     * 状态(0:禁用;1:已用;2:已删除)
     */
    @ApiModelProperty(value = "状态(0:禁用;1:已用;2:已删除)", name = "状态(0:禁用;1:已用;2:已删除)")
    private Integer dataStatus;

}
