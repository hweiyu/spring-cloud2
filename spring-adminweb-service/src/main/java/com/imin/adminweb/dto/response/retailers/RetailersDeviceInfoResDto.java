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
public class RetailersDeviceInfoResDto implements TransferObject {

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
    @ApiModelProperty(value = "商户名称", name = "商户名称")
    private String retailerName;

    /**
     * 序列号
     */
    @ApiModelProperty(value = " 序列号", name = " 序列号")
    private String sn;

    /**
     * 设备激活状态（0：未激活,1:已激活）
     */
    @ApiModelProperty(value = " 设备激活状态（0：未激活,1:已激活）", name = " 设备激活状态（0：未激活,1:已激活）")
    private Integer deviceStatus;

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
