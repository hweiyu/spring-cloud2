package com.imin.basic.dto.request;

import com.imin.infrastructure.common.dto.TransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title 描述
 * @Description 描述
 * @date 2018/11/28 13:26
 **/
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DictInsertReqDto implements TransferObject {

    private static final long serialVersionUID = 752611341986657728L;

    /**
     * 字典类型
     */
    @ApiModelProperty(value = "字典类型", name = "字典类型")
    private String dictType;

    /**
     * 字典码
     */
    @ApiModelProperty(value = "字典类型", name = "字典类型")
    private String dictCode;

    /**
     * 字典key
     */
    @ApiModelProperty(value = "字典类型", name = "字典类型")
    private String dictKey;

    /**
     * 字典value
     */
    @ApiModelProperty(value = "字典类型", name = "字典类型")
    private String dictValue;

    /**
     * 额外信息
     */
    @ApiModelProperty(value = "字典类型", name = "字典类型")
    private String extra;

    /**
     * 备注
     */
    @ApiModelProperty(value = "字典类型", name = "字典类型")
    private String remark;

    /**
     * 排序
     */
    @ApiModelProperty(value = "字典类型", name = "字典类型")
    private Integer sortBy;

}
