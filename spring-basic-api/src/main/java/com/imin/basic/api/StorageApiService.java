package com.imin.basic.api;

import com.imin.basic.dto.response.FileUploadResDto;
import com.imin.infrastructure.common.result.ResultData;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title 描述
 * @Description 描述
 * @date 2018/12/5 15:06
 **/
public interface StorageApiService {

    @ApiOperation(value = "文件上传", notes = "文件上传")
    @RequestMapping(value = "/file/upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({@ApiImplicitParam(name = "type", value = "上传的类型(USER_IMAGE:用户图片;USER_VIDEO:用户视频;USER_FILE:用户文件;TERMINAL_IMAGE:终端图片;TERMINAL_VIDEO:终端视频;TERMINAL_FILE:终端文件;APPSTORE_IMAGE:应用市场图片;APPSTORE_FILE:应用市场文件;OTHERS:其它)", paramType = "query", dataType = "String")})
    ResultData<FileUploadResDto> upload(@RequestParam(value = "file") MultipartFile file, String type);

    @ApiOperation(value = "多文件上传", notes = "多文件上传")
    @RequestMapping(value = "/file/multiUpload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({@ApiImplicitParam(name = "type", value = "上传的类型(USER_IMAGE:用户图片;USER_VIDEO:用户视频;USER_FILE:用户文件;TERMINAL_IMAGE:终端图片;TERMINAL_VIDEO:终端视频;TERMINAL_FILE:终端文件;APPSTORE_IMAGE:应用市场图片;APPSTORE_FILE:应用市场文件;OTHERS:其它)", paramType = "query", dataType = "String")})
    ResultData<List<FileUploadResDto>> multiUpload(@RequestParam(value = "files") MultipartFile[] files, String type);
}
