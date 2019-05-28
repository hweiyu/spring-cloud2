package com.imin.adminweb.dto.response.agent;

import com.imin.infrastructure.common.dto.TransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
public class AgentResDto implements TransferObject {

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
