package com.imin.adminweb.dto.request.retailers;

import com.imin.infrastructure.common.dto.TransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;


/**
 * @author wangyong
 * @version V1.0
 * @Title: 描述
 * @Description: 批量增加商户终端设备信息表请求dto
 * @date 2019-02-22 13:52:22
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RetailersDeviceInfobatchInsertReqDto implements TransferObject {

    private static final long serialVersionUID = -1L;

    List<RetailersDeviceInfo> retailersList;


    @Getter
    @Setter
    public static class RetailersDeviceInfo {

        /**
         * 用户ID
         */
        @ApiModelProperty(value = " 用户ID", name = " 用户ID")
        private Long userId;


        /**
         * 代理商编号
         */
        @ApiModelProperty(value = "代理商编号", name = "代理商编号")
        private String agentId;


        /**
         * 代理商帐号
         */
        @ApiModelProperty(value = "商户编号", name = "商户编号")
        private String agentAccount;


        /**
         * 商户编号
         */
        @ApiModelProperty(value = "商户编号", name = "商户编号")
        private String retailerId;


        /**
         * 所属对应商户名称
         */
        @ApiModelProperty(value = "所属对应商户名称", name = "所属对应商户名称")
        private String retailerName;

        /**
         * 序列号
         */
        @ApiModelProperty(value = " 序列号", name = " 序列号")
        private String sn;

    }

    /**
     * sns
     */
    @ApiModelProperty(value = "sns", name = "sns")

    private List<String> sns;
}
