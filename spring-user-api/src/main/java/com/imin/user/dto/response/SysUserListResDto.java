package com.imin.user.dto.response;

import com.imin.infrastructure.common.dto.TransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 系统用户返回dto
 * @date 2018-11-28 09:54:02
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysUserListResDto implements TransferObject {

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
      * 平台类型(1:imin;2:代理商;3:开发者)
      */
    @ApiModelProperty(value = "平台类型(1:imin;2:代理商;3:开发者)", name = "平台类型(1:imin;2:代理商;3:开发者)")
    private Integer platformType;

    /**
     * 角色
     */
    @ApiModelProperty(value = "角色", name = "角色")
    private String roleName;

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

    /**
      * 创建时间
      */
    @ApiModelProperty(value = "创建时间", name = "创建时间")
    private String createTime;

    /**
      * 修改时间
      */
    @ApiModelProperty(value = "修改时间", name = "修改时间")
    private String updateTime;

    /**
     * 微信openId
     */
    @ApiModelProperty(value = "微信openId", name = "微信openId")
    private String openId;

}
