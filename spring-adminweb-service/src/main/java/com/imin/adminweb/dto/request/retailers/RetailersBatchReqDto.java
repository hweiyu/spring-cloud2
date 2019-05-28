package com.imin.adminweb.dto.request.retailers;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangyong
 * @version V1.0
 * @Title: 描述
 * @Description: 批量操作请求dto
 * @date 2019-01-09 14:55:04
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RetailersBatchReqDto implements Serializable {
    private static final long serialVersionUID = -1L;

    List<RetailersInfo> retailersList;

    @Getter
    @Setter
    public static class RetailersInfo {
        /**
         * 设备序列号
         */
        @ApiModelProperty(value = "设备序列号", name = "设备序列号")
        private String sn;
        /**
         * 用户编号
         */
        @ApiModelProperty(value = "用户编号", name = "用户编号")
        private String userId;
        /**
         * 代理商编号
         */
        @ApiModelProperty(value = "代理商编号", name = "代理商编号")
        private String agentId;

        /**
         * 代理商帐号
         */
        @ApiModelProperty(value = "代理商帐号", name = "代理商帐号")
        private String agentAccount;
    }

}
