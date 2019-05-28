package com.imin.adminweb.dto.request;

import com.imin.infrastructure.common.dto.TransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 设备应用信息查询请求dto
 * @date 2018-11-20 13:52:22
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceAppQueryReqDto implements TransferObject {

    private static final long serialVersionUID = -1L;

    /**
     * 设备类型
     */
    @ApiModelProperty(value = "设备类型", name = "设备类型")
    private String deviceType;

}
