package com.imin.user.dto.reqeust;

import com.imin.infrastructure.common.dto.BaseQueryDto;
import com.imin.infrastructure.common.dto.TransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 用户日志表查询请求dto
 * @date 2018-11-28 09:54:02
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysUserLogQueryReqDto extends BaseQueryDto implements TransferObject {

    private static final long serialVersionUID = -1L;

    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称", name = "用户名称")
    private String userName;

    /**
     * ip
     */
    @ApiModelProperty(value = "ip", name = "ip")
    private String ip;

}
