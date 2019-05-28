package com.imin.adminweb.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.imin.basic.dto.response.FileUploadResDto;
import com.imin.infrastructure.common.aop.PopedomType;
import com.imin.infrastructure.common.aop.RequestProcess;
import com.imin.infrastructure.common.exception.ControllerException;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.infrastructure.common.spring.SpringContextHolder;
import com.imin.infrastructure.common.utils.HttpUtil;
import com.imin.infrastructure.common.utils.JsonUtil;
import com.imin.infrastructure.common.utils.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @Title: 文件上传及下载
 * @Description: 文件上传及下载
 * @author ben
 * @date 2018年2月28日 上午9:15:43
 * @version V1.0
 **/

@RestController
@RequestMapping("${api.url.prefix}")
@Api(tags = {"文件上传及下载" })
@Slf4j
public class FileUploadClient {

    @ApiOperation(value = "单文件上传", notes = "单文件上传")
    @RequestMapping(value = "/file/upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({@ApiImplicitParam(name = "type", value = "上传的类型(USER_IMAGE:用户图片;USER_VIDEO:用户视频;USER_FILE:用户文件;TERMINAL_IMAGE:终端图片;TERMINAL_VIDEO:终端视频;TERMINAL_FILE:终端文件;APPSTORE_IMAGE:应用市场图片;APPSTORE_FILE:应用市场文件;OTHERS:其它)", paramType = "query", dataType = "String")})
    @RequestProcess(checkLogin = false, popedomCode = "imin:storage:upload", popedomType = PopedomType.Insert, logName = "终端图片上传", logUrl = "/file/uploadTerminalImage")
    public ResultData<FileUploadResDto> upload(@RequestParam(value = "file") MultipartFile file, String type) {
        return doUpload(file, type);
    }

    @ApiOperation(value = "多文件上传", notes = "多文件上传")
    @RequestMapping(value = "/file/multiUpload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({@ApiImplicitParam(name = "type", value = "上传的类型(USER_IMAGE:用户图片;USER_VIDEO:用户视频;USER_FILE:用户文件;TERMINAL_IMAGE:终端图片;TERMINAL_VIDEO:终端视频;TERMINAL_FILE:终端文件;APPSTORE_IMAGE:应用市场图片;APPSTORE_FILE:应用市场文件;OTHERS:其它)", paramType = "query", dataType = "String")})
    @RequestProcess(checkLogin = false, popedomCode = "imin:storage:multiupload", popedomType = PopedomType.Insert, logName = "多文件上传", logUrl = "/file/multiUpload")
    public ResultData<List<FileUploadResDto>> multiUpload(@RequestParam(value = "files") MultipartFile[] files, String type) {
        return doMultiUpload(files, type);
    }

    @SuppressWarnings("unchecked")
    public static ResultData<FileUploadResDto> doUpload(MultipartFile file, String type) {
        if (null == file || null == type) {
            return ResultData.createErrorResult(null, 2, "file or type is null");
        }
        StorageServiceInfo storageInfo = getStorageServiceInfo();
        String url = wrapUrl(storageInfo.getUploadUrl(), type);
        try {
            String response = HttpUtil.postFile(file, url, createHeaders(storageInfo), "file");
            log.info("文件上传，请求响应为：{}", response);
            if (!StringUtil.isEmptyOrNull(response)) {
                return (ResultData<FileUploadResDto>) JsonUtil.fromJson(response, ResultData.class);
            }
        } catch (Exception e) {
            log.error("请求上传文件异常", e);
        }
        return ResultData.createErrorResult(null, 2, "server error");
    }

    @SuppressWarnings("unchecked")
    public static ResultData<List<FileUploadResDto>> doMultiUpload(MultipartFile[] files, String type) {
        if (null == files || files.length <= 0 || null == type) {
            return ResultData.createErrorResult(null, 2, "file or type is null");
        }
        StorageServiceInfo storageInfo = getStorageServiceInfo();
        String url = wrapUrl(storageInfo.getUploadUrlMulti(), type);
        try {
            String response = HttpUtil.postFileMulti(Lists.newArrayList(files), url, createHeaders(storageInfo), "file");
            log.info("多文件上传，请求响应为：{}", response);
            if (!StringUtil.isEmptyOrNull(response)) {
                return (ResultData<List<FileUploadResDto>>) JsonUtil.fromJson(response, ResultData.class);
            }
        } catch (Exception e) {
            log.error("请求上传多个文件异常", e);
        }
        return ResultData.createErrorResult(null, 2, "server error");
    }

    private static String wrapUrl(String url, String type) {
        return url + "?type=" + type;
    }

    private static Map<String, String> createHeaders(StorageServiceInfo storageInfo) {
        Map<String, String> headers = Maps.newHashMap();
        headers.put("x-access-token", storageInfo.getVerifyCode());
        headers.put("x-service-name", storageInfo.getServiceName());
        return headers;
    }

    private static StorageServiceInfo getStorageServiceInfo() {
        Environment env = SpringContextHolder.getBean(Environment.class);
        if (null == env) {
            throw new ControllerException("obtain environment info error");
        }
        String uploadUrlMulti = env.getProperty("storage.multiUploadUrl");
        String uploadUrl = env.getProperty("storage.uploadUrl");
        String verifyCode = env.getProperty("storage.verifyCode");
        String serviceName = env.getProperty("storage.serviceName");
        if (null == uploadUrlMulti
                || null == uploadUrl
                || null == verifyCode
                || null == serviceName) {
            throw new ControllerException("obtain storage server info error");
        }
        return StorageServiceInfo.builder()
                .uploadUrlMulti(uploadUrlMulti)
                .uploadUrl(uploadUrl)
                .verifyCode(verifyCode)
                .serviceName(serviceName)
                .build();
    }

    @Getter
    @Builder
    @AllArgsConstructor
    private static class StorageServiceInfo {

        private String uploadUrlMulti;

        private String uploadUrl;

        private String verifyCode;

        private String serviceName;
    }
}
