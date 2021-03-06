package com.imin.user.dto.response;

import com.imin.infrastructure.common.dto.TransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 用户日志表返回dto
 * @date 2018-11-28 09:54:02
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysUserLogListResDto implements TransferObject {

    private static final long serialVersionUID = -1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", name = "主键")
    private Long id;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", name = "用户id")
    private Long userId;

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

    /**
     * 日志记录时间
     */
    @ApiModelProperty(value = "日志记录时间", name = "日志记录时间")
    private String logTime;

}
