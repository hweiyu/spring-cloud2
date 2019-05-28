package com.imin.basic.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title 描述
 * @Description 描述
 * @date 2018/12/5 11:39
 **/
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadResDto implements Serializable {

    private static final long serialVersionUID = -7028014752139872261L;

    @ApiModelProperty(value = "上传返回标志:1,成功;2,检验码不正确;3,存储目录不存在;4,上传字段不存在;5,上传类别不支持;6,文件类型不支持;7,未知异常",
            name = "上传返回标志")
    private Integer code;

    @ApiModelProperty(value = "上传结果true:成功, false:失败", name = "上传结果true:成功, false:失败")
    private String result;

    @ApiModelProperty(value = "上传图像宽度", name = "上传图像宽度")
    private Integer width;

    @ApiModelProperty(value = "上传图像高度", name = "上传图像高度")
    private Integer height;

    @ApiModelProperty(value = "上传后的相对url路径", name = "上传后的相对url路径")
    private String relativePath;

    @ApiModelProperty(value = "文件大小", name = "文件大小")
    private String size;

    public FileUploadResDto(Integer code) {
        this.code = code;
    }

    public FileUploadResDto(Integer code, String result) {
        this.code = code;
        this.result = result;
    }

    public static FileUploadResDto success() {
        return new FileUploadResDto(1);
    }

    public static FileUploadResDto verifyCodeError() {
        return new FileUploadResDto(2, Boolean.FALSE.toString());
    }

    public static FileUploadResDto storageDirIsNotExists() {
        return new FileUploadResDto(3, Boolean.FALSE.toString());
    }

    public static FileUploadResDto fieldIsNotExists() {
        return new FileUploadResDto(4, Boolean.FALSE.toString());
    }

    public static FileUploadResDto uploadTypeError() {
        return new FileUploadResDto(5, Boolean.FALSE.toString());
    }

    public static FileUploadResDto fileTypeIsNotSupport() {
        return new FileUploadResDto(6, Boolean.FALSE.toString());
    }

    public static FileUploadResDto error() {
        return new FileUploadResDto(7, Boolean.FALSE.toString());
    }
}
