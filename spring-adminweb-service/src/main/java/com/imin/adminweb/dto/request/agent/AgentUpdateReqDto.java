package com.imin.adminweb.dto.request.agent;

import com.imin.infrastructure.common.dto.TransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author wangyong
 * @version V1.0
 * @Title: 描述
 * @Description: 代理商信息请求dto
 * @date 2019-02-22 09:54:02
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgentUpdateReqDto implements TransferObject {

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

    /**
     * 详细地址
     */
    @ApiModelProperty(value = " 详细地址", name = " 详细地址")
    private String agentAddress;

    /**
     * 联系人
     */
    @ApiModelProperty(value = " 联系人", name = " 联系人")
    private String agentContacts;

    /**
     * 联系方式
     */
    @ApiModelProperty(value = " 联系方式", name = " 联系方式")
    private String agentTel;



}
