package com.imin.adminweb.dto.request.agent;

import com.imin.infrastructure.common.dto.BaseQueryDto;
import com.imin.infrastructure.common.dto.TransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 代理商信息查询请求dto
 * @date 2018-11-28 09:54:02
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgentQueryReqDto extends BaseQueryDto implements TransferObject {

    private static final long serialVersionUID = -1L;

    /**
     * ID
     */
    @ApiModelProperty(value = " ID", name = " ID")
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
    private String agentNo;

    /**
     * 代理商名称
     */
    @ApiModelProperty(value = " 代理商名称", name = " 代理商名称")
    private String agentName;

    /**
     * 代理商资质
     */
    @ApiModelProperty(value = " 代理商资质", name = " 代理商资质")
    private String agentAptitude;

    /**
     * 代理商账号
     */
    @ApiModelProperty(value = " 代理商账号", name = " 代理商账号")
    private String agentAccount;

}
