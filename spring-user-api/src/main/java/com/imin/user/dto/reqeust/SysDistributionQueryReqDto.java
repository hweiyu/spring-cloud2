package com.imin.user.dto.reqeust;

import com.imin.infrastructure.common.dto.BaseQueryDto;
import com.imin.infrastructure.common.dto.TransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 用户分配查询请求dto
 * @date 2018-11-27 18:19:55
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysDistributionQueryReqDto extends BaseQueryDto implements TransferObject {

    private static final long serialVersionUID = -1L;

    /**
     * 主账号id
     */
    @NotNull
    @ApiModelProperty(value = "主账号id", name = "主账号id")
    private Long masterId;

    /**
     * 登录名
     */
    @ApiModelProperty(value = "登录名", name = "登录名")
    private String loginName;

    /**
     * 账号
     */
    @ApiModelProperty(value = "账号", name = "账号")
    private String account;
	
}
