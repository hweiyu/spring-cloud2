package com.imin.user.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title 描述
 * @Description 描述
 * @date 2018/11/29 9:49
 **/
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResDto implements Serializable {

    private static final long serialVersionUID = -1940009035794487910L;

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
     * 授权令牌
     */
    @ApiModelProperty(value = "授权令牌", name = "授权令牌")
    private String token;

    /**
     * 菜单列表
     */
    @ApiModelProperty(value = "菜单列表", name = "菜单列表")
    private List<MenuResource> menus;

    /**
     * 权限码列表
     */
    @ApiModelProperty(value = "权限码列表", name = "权限码列表")
    private List<String> authCodes;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MenuResource implements Serializable {

        private static final long serialVersionUID = 8156274945419590254L;

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
         * 资源名称
         */
        @ApiModelProperty(value = "资源名称", name = "资源名称")
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
         * 资源唯一编码
         */
        @ApiModelProperty(value = "资源唯一编码", name = "资源唯一编码")
        private String resourceCode;

        /**
         * 资源国际化
         */
        @ApiModelProperty(value = "资源国际化", name = "资源国际化")
        private List<Il8n> il8ns;

        /**
         * 子资源列表
         */
        @ApiModelProperty(value = "子资源列表", name = "子资源列表")
        private List<MenuResource> children;

    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Il8n implements Serializable {

        private static final long serialVersionUID = -5948615599694553735L;

        /**
         * 语言
         */
        @ApiModelProperty(value = "语言", name = "语言")
        private String language;

        /**
         * 资源名称
         */
        @ApiModelProperty(value = "资源名称", name = "资源名称")
        private String resourceName;

    }

}
