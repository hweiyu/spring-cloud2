
package com.imin.example.dto.response;

import com.imin.infrastructure.common.valid.interfaces.MustId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 *
 * @Title: TODO
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author ben
 * @date 2018年2月28日 下午4:47:55
 * @version V1.0  
 * 
 **/

@Data
public class DemoDto {
    
    @NotNull(groups={MustId.class}, message = "{v.id.not.empty}" )
    @ApiModelProperty(value = "id", name = "id", required=true)
    private Long id;
    
    @ApiModelProperty(value = "名称", name = "名称")
    private String name;

    @ApiModelProperty(value = "内容", name = "内容")
    @Getter @Setter private String content;
    
//    @ApiModelProperty(value = "密码", name = "密码")
//    @Password(minLen=8, maxLen=16, message = "密码格式不正确!")
//    private String password;
//
//    @Mobile()
//    private String mobile;
//
//    @Email()
//    private String mail;
}
