package com.imin.adminweb.dto.request.retailers;

import com.imin.infrastructure.common.dto.TransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * @author wangyong
 * @version V1.0
 * @Title: 描述
 * @Description: 商户信息更新请求dto
 * @date 2019-02-22 09:54:02
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RetailersUpdateReqDto implements TransferObject {

    private static final long serialVersionUID = -1L;

    /**
     * 主键
     */
    @NotNull(message = "{v.id.not.empty}")
    @ApiModelProperty(value = "主键", name = "主键")
    private Long id;


    /**
     * 用户ID
     */
    @ApiModelProperty(value = " 用户ID", name = " 用户ID")
    private Long userId;


    /**
     * 代理商帐号
     */
    @ApiModelProperty(value = "代理商帐号", name = "代理商帐号")
    private String agentAccount;

    /**
     * 代理商编号
     */
    @ApiModelProperty(value = "代理商ID", name = "代理商ID")
    private String agentId;



    /**
     * 商户名称
     */
    @ApiModelProperty(value = " 商户名称", name = " 商户名称")
    private String retailerName;

    /**
     * 商户地址
     */
    @ApiModelProperty(value = " 商户地址", name = " 商户地址")
    private String retailerAddress;



    /**
     * 联系人
     */
    @ApiModelProperty(value = " 联系人", name = " 联系人")
    private String retailerContact;

    /**
     * 联系方式
     */
    @ApiModelProperty(value = " 联系方式", name = " 联系方式")
    private String retailerTel;



}
