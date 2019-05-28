package com.imin.adminweb.dto.request.retailers;

import io.swagger.annotations.ApiModelProperty;
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
public class RetailersBatchDeleteReqDto implements Serializable {

    private static final long serialVersionUID = -1L;
    @ApiModelProperty(value = "sns", name = "sns")
    private List<String> sns;


}
