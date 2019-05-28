package com.imin.adminweb.dto.request.agent;

import com.imin.infrastructure.common.dto.TransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 应用定制请求dto
 * @date 2019-02-27 10:53:59
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppBookingSubmitReqDto implements TransferObject {

    private static final long serialVersionUID = -1L;

    /**
      * 主键
      */
    @ApiModelProperty(value = "主键", name = "主键")
    private Long id;

    /**
      * 软件名称
      */
    @NotBlank(message = "{v.softwareName.not.empty}")
    @ApiModelProperty(value = "软件名称", name = "软件名称")
    private String softwareName;

    /**
      * 应用类型(0:OS;1:APK)
      */
    @NotNull(message = "{v.appType.not.empty}")
    @ApiModelProperty(value = "应用类型(0:OS;1:APK)", name = "应用类型(0:OS;1:APK)")
    private Integer appType;

    /**
      * 定制内容(0:桌面;1:壁纸;2:小部件;3:零售;4:餐饮;5:其它)
      */
    @NotNull(message = "{v.contentType.not.empty}")
    @ApiModelProperty(value = "定制内容(0:桌面;1:壁纸;2:小部件;3:零售;4:餐饮;5:其它)", name = "定制内容(0:桌面;1:壁纸;2:小部件;3:零售;4:餐饮;5:其它)")
    private Integer contentType;

    /**
      * 定制原因
      */
    @NotBlank(message = "{v.bookingReason.not.empty}")
    @ApiModelProperty(value = "定制原因", name = "定制原因")
    private String bookingReason;

    /**
     * 升级策略(0:按地区;1:按SN;2:按设备型号3:按代理商)
     */
    @NotNull(message = "{v.upgradeStrategy.not.empty}")
    @ApiModelProperty(value = "升级策略(0:按地区;1:按SN;2:按设备型号3:按代理商)", name = "升级策略(0:按地区;1:按SN;2:按设备型号3:按代理商)")
    private Integer upgradeStrategy;

}
