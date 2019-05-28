package com.imin.user.dto.reqeust;

import com.imin.infrastructure.common.dto.TransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 用户分配请求dto
 * @date 2018-11-28 09:54:02
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysDistributionUpdateReqDto implements TransferObject {

    private static final long serialVersionUID = -1L;
	
    /**
      * 主键
      */
    @ApiModelProperty(value = "主键", name = "主键")
    private Long id;
	
    /**
      * 主用户id
      */
    @ApiModelProperty(value = "主用户id", name = "主用户id")
    private Long masterId;
	
    /**
      * 下级用户id
      */
    @ApiModelProperty(value = "下级用户id", name = "下级用户id")
    private Long salveId;
	
    /**
      * 关系ids
      */
    @ApiModelProperty(value = "关系ids", name = "关系ids")
    private String relIds;
	
}
