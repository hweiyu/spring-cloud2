package com.imin.adminweb.dto.request.retailers;

import com.imin.infrastructure.common.dto.BaseQueryDto;
import com.imin.infrastructure.common.dto.TransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author wangyong
 * @version V1.0
 * @Title: 描述
 * @Description: 商户终端设备信息查询请求dto
 * @date 2019-02-28 09:54:02
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RetailersDeviceInfoQueryReqDto extends BaseQueryDto implements TransferObject {

    private static final long serialVersionUID = -1L;


    /**
     * 用户ID
     */
    @ApiModelProperty(value = " 用户ID", name = " 用户ID")
    private Long userId;


    /**
     * 商户编号
     */
    @ApiModelProperty(value = "商户编号", name = "商户编号")
    private Integer retailerId;

    /**
     * 商户名称
     */
    @ApiModelProperty(value = "商户名称", name = "商户名称")
    private String retailerName;


    /**
     * 代理商帐号
     */
    @ApiModelProperty(value = "代理商帐号", name = "代理商帐号")
    private String agentAccount;

    /**
     * SN序号
     */
    @ApiModelProperty(value = "SN序号", name = "SN序号")
    private String  sn ;


    /**
     * 设备激活状态（0：未激活,1:已激活）
     */
    @ApiModelProperty(value = " 设备激活状态（0：未激活,1:已激活）", name = " 设备激活状态（0：未激活,1:已激活）")
    private String deviceStatus;

}
