package com.imin.adminweb.dto.request.agent;

import com.imin.infrastructure.common.dto.BaseQueryDto;
import com.imin.infrastructure.common.dto.TransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author wangyong
 * @version V1.0
 * @Title: 描述
 * @Description: 设备部署信息查询请求dto
 * @date 2019-02-27 09:54:02
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDeployQueryReqDto extends BaseQueryDto implements TransferObject {

    private static final long serialVersionUID = -1L;


    /**
     * 设备型号
     */
    @ApiModelProperty(value = " 设备型号", name = " 设备型号")
    private String model;

    /**
     * sn
     */
    @ApiModelProperty(value = " 序列号", name = " 序列号")
    private String sn;



}
