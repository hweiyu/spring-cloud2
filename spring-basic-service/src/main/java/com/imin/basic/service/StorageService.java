package com.imin.basic.service;

import com.imin.basic.dto.response.FileUploadResDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title 描述
 * @Description 描述
 * @date 2018/12/5 11:18
 **/
public interface StorageService {

    /**
     * 文件上传
     * @param file
     * @param type
     * @return
     */
    FileUploadResDto fileUpload(MultipartFile file, String type);

    /**
     * 多文件上传
     * @param files
     * @param type
     * @return
     */
    List<FileUploadResDto> multiFileUpload(MultipartFile[] files, String type);
}
