package com.imin.basic.dto.request;

import com.imin.basic.enums.SortEnum;
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
public class DictListReqDto implements TransferObject {

    private static final long serialVersionUID = 752611341986657728L;

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
     * 排序
     */
    @ApiModelProperty(value = "排序", name = "排序")
    private SortEnum sort;

}
