package com.imin.basic.controller;

import com.imin.basic.api.StorageApiService;
import com.imin.basic.dto.response.FileUploadResDto;
import com.imin.basic.service.StorageService;
import com.imin.infrastructure.common.aop.RequestProcess;
import com.imin.infrastructure.common.result.ResultData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Title: 文件上传
 * @Description: 文件传
 * @author ben
 * @date 2018年3月1日 上午11:08:54
 * @version V1.0
 **/

@RestController
@RequestMapping("${api.url.prefix}")
@Api(tags = {"文件上传及下载"})
public class StorageController implements StorageApiService {

    @Autowired
    private StorageService storageService;

    @RequestProcess(checkLogin = true, logName = "文件上传", logUrl = "/file/upload")
    @Override
    public ResultData<FileUploadResDto> upload(@RequestParam(value = "file") MultipartFile file, String type) {
        return ResultData.createSuccessResult(storageService.fileUpload(file, type));
    }

    @RequestProcess(checkLogin = true, logName = "多文件上传", logUrl = "/file/multiUpload")
    @Override
    public ResultData<List<FileUploadResDto>> multiUpload(@RequestParam(value = "files") MultipartFile[] files, String type) {
        return ResultData.createSuccessResult(storageService.multiFileUpload(files, type));
    }
}
