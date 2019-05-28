package com.imin.adminweb.dto.response.retailers;

import com.imin.infrastructure.common.dto.TransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author wangyong
 * @version V1.0
 * @Title 描述
 * @Description 描述
 * @date 2019/2/22 16:29
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RetailersResDto implements TransferObject {

    private static final long serialVersionUID = 2759361600880987004L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", name = "主键")
    private Long id;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = " 用户ID", name = " 用户ID")
    private Long userId;

    /**
     * 代理商ID
     */
    @ApiModelProperty(value = "代理商id", name = "代理商id")
    private String agentId;


    /**
     * 代理商帐号
     */
    @ApiModelProperty(value = "代理商帐号", name = "代理商帐号")
    private String agentAccount;


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

    /**
     * 创建时间
     */
    @ApiModelProperty(value = " 创建时间", name = " 创建时间")
    private String createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = " 修改时间", name = " 修改时间")
    private String updateTime;


}
