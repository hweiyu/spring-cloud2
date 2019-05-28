
package com.imin.example.dto.request;

import com.imin.infrastructure.common.dto.BasePageDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 *
 * @Title: TODO
 * @Description: TODO(用一句话描述该文件做什么)
 * @author ben
 * @date 2017年12月7日 下午7:50:44
 * @version V1.0
 * 
 **/

@Data
@EqualsAndHashCode(callSuper=false)
public class QueryDemoReqDto extends BasePageDto {
    @ApiModelProperty(value = "名称", name = "名称", dataType="String", example= "名称", required=true)
    private String name;
}
