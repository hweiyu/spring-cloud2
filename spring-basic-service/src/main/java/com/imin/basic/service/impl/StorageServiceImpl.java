package com.imin.basic.service.impl;

import com.google.common.collect.Lists;
import com.imin.basic.dto.response.FileUploadResDto;
import com.imin.basic.enums.FileTypeEnum;
import com.imin.basic.handle.FileUploadHandle;
import com.imin.basic.service.StorageService;
import com.imin.infrastructure.common.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title 描述
 * @Description 描述
 * @date 2018/12/5 11:18
 **/
@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Value("${storage.uploadPath}")
    private String uploadPath;

    /**
     * 文件上传
     *
     * @param file
     * @param type
     * @return
     */
    @Override
    public FileUploadResDto fileUpload(MultipartFile file, String type) {
        try {
            FileTypeEnum fileType = FileTypeEnum.nameOf(type);
            if (null == fileType) {
                return FileUploadResDto.uploadTypeError();
            }
            if (!FileUtil.isExistDir(uploadPath)) {
                return FileUploadResDto.storageDirIsNotExists();
            }
            if (null == file || file.getSize() <= 0) {
                return FileUploadResDto.fieldIsNotExists();
            }
            return new FileUploadHandle().fileUpload(file, uploadPath, fileType.getDir());
        } catch (Exception e) {
            log.error("文件上传失败", e);
        }
        return FileUploadResDto.error();
    }

    /**
     * 多文件上传
     *
     * @param files
     * @param type
     * @return
     */
    @Override
    public List<FileUploadResDto> multiFileUpload(MultipartFile[] files, String type) {
        List<FileUploadResDto> result = Lists.newArrayList();
        for (MultipartFile file : files) {
            result.add(fileUpload(file, type));
        }
        return result;
    }

}
