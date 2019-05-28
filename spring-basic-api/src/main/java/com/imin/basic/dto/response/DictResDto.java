package com.imin.basic.dto.response;

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
public class DictResDto implements TransferObject {

    private static final long serialVersionUID = 3603107617826435004L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", name = "主键")
    private Long id;

    /**
     * 字典类型
     */
    @ApiModelProperty(value = "字典类型", name = "字典类型")
    private String dictType;

    /**
     * 字典码
     */
    @ApiModelProperty(value = "字典码", name = "字典码")
    private String dictCode;

    /**
     * 字典key
     */
    @ApiModelProperty(value = "字典key", name = "字典key")
    private String dictKey;

    /**
     * 字典value
     */
    @ApiModelProperty(value = "字典value", name = "字典value")
    private String dictValue;

    /**
     * 额外信息
     */
    @ApiModelProperty(value = "额外信息", name = "额外信息")
    private String extra;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", name = "排序")
    private Integer sortBy;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "备注")
    private String remark;
}
