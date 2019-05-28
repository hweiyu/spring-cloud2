package com.imin.adminweb.dto.request.retailers;

import com.imin.infrastructure.common.dto.BaseQueryDto;
import com.imin.infrastructure.common.dto.TransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author wangyong
 * @version V1.0
 * @Title: 描述
 * @Description: 代理商信息查询请求dto
 * @date 2019-02-28 09:54:02
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RetailersQueryReqDto extends BaseQueryDto implements TransferObject {

    private static final long serialVersionUID = -1L;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = " id", name = " id")
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








}
