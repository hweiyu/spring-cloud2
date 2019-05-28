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
public class SysUserLogResDto implements TransferObject {

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
     * 模块
     */
    @ApiModelProperty(value = "模块", name = "模块")
    private Integer module;

    /**
     * 操作类型
     */
    @ApiModelProperty(value = "操作类型", name = "操作类型")
    private Integer operateType;

    /**
     * 请求接口
     */
    @ApiModelProperty(value = "请求接口", name = "请求接口")
    private String requestUrl;

    /**
     * ip
     */
    @ApiModelProperty(value = "ip", name = "ip")
    private String ip;

    /**
     * 请求参数
     */
    @ApiModelProperty(value = "请求参数", name = "请求参数")
    private String requestData;

    /**
     * 返回参数
     */
    @ApiModelProperty(value = "返回参数", name = "返回参数")
    private String responseData;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "备注")
    private String remark;

    /**
     * 日志记录时间
     */
    @ApiModelProperty(value = "日志记录时间", name = "日志记录时间")
    private String logTime;

}
