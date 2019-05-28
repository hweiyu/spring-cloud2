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
public class RetailersDeviceInfoUpdateReqDto implements TransferObject {

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
     * 代理商编号
     */
    @ApiModelProperty(value = "代理商编号", name = "代理商编号")
    private String agentId;

    /**
     * 代理商帐号
     */
    @ApiModelProperty(value = "代理商帐号", name = "代理商帐号")
    private String agentAccount;

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
     * 所属对应商户名称
     */
    @ApiModelProperty(value = "所属对应商户名称", name = "所属对应商户名称")
    private String retailerName;


}
