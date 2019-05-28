package com.imin.adminweb.dto.response.agent;

import com.imin.infrastructure.common.dto.TransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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
public class DeviceDeployResDto implements TransferObject {

    private static final long serialVersionUID = 2759361600880987004L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", name = "主键")
    private Long id;

    /**
     * 设备型号
     */
    @ApiModelProperty(value = "设备型号", name = "设备型号")
    private String model;

    /**
     * SN
     */
    @ApiModelProperty(value = "SN序号", name = "设备型号")
    private String sn;

    /**
     * 出货日期
     */
    @ApiModelProperty(value = " 出货日期", name = " 出货日期")
    private String shippingDate;

    /**
     * 激活日期
     */
    @ApiModelProperty(value = " 激活日期", name = " 激活日期")
    private String activationDate;

    /**
     * 累计使用时间
     */
    @ApiModelProperty(value = " 累计使用时间", name = " 累计使用时间")
    private String cumulativeUsetime;






}
