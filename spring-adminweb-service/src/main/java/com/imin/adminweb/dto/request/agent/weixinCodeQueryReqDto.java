package com.imin.adminweb.dto.request.agent;

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
public class weixinCodeQueryReqDto implements TransferObject {

    private static final long serialVersionUID = -1L;

    /**
     * 微信Code
     */
    @ApiModelProperty(value = "微信Code", name = "微信Code")
    private String code;



}
