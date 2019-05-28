package com.imin.basic.handle;

import com.imin.basic.dto.response.FileUploadResDto;
import com.imin.infrastructure.common.utils.DateUtil;
import com.imin.infrastructure.common.utils.FileUtil;
import com.imin.infrastructure.common.utils.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

/**
 * @author code
 * @version V1.0
 * @Title: code
 * @Description: code
 * @date 2018年2月28日 下午4:47:55
 **/
@Slf4j
public class FileUploadHandle {

    private Integer zoomWidth = 300;

    private Integer zoomHeight = 300;

    private String zoomSuffix = "_min";

    private String getTimeFilePath() {
        return File.separator + DateUtil.getYear() + File.separator + DateUtil.getMonth()
                + File.separator + DateUtil.getDay() + File.separator;
    }

    public FileUploadHandle() {}

    public FileUploadHandle(Integer zoomWidth, Integer zoomHeight, String zoomSuffix) {
        this.zoomWidth = zoomWidth;
        this.zoomHeight = zoomHeight;
        this.zoomSuffix = zoomSuffix;
    }

    /**
     * 上传文件
     *
     * @param mf
     * @param rootPath
     * @param folderName
     * @return
     */
    public FileUploadResDto fileUpload(MultipartFile mf, String rootPath, String folderName) {
        // 文件后缀
        String suffix = FileUtil.getFileSuffix(mf.getOriginalFilename());
        // 文件夹相对路径，不包含文件名称及后缀
        String relativePath = File.separator + folderName + getTimeFilePath();
        // 相对路径
        String absolutePath = rootPath + relativePath;
        //SnowFlake 生成唯一id
        String newFileName = new SnowFlake().nextId() + suffix;
        int width = 0;
        int height = 0;
        // 文件上传
        boolean isSuccess = saveFileToDir(mf, absolutePath, newFileName);
        log.info("文件上传路径:{}, 文件名:{}", absolutePath, newFileName);
        if (isSuccess) {
            // 文件全路径
            String fileAllPath = absolutePath + newFileName;
            //String rePath = "";// 文件相对路径 如：/images/2017/02/20/1475908572766.png
            File file = new File(fileAllPath);
            if (null != zoomSuffix
                    && zoomSuffix.length() > 0
                    && FileUtil.isImage(file)) {
                // 如果为图片则生成缩略图
//                ImageCompress imageCompress = new ImageCompress(zoomWidth, zoomHeight, zoomSuffix);
//                imageCompress.zipImageFile(file);
                ImageCompress imageCompress = new ImageCompress();
                imageCompress.zipImageFileByRatio(file);
                try(InputStream input = new FileInputStream(file)) {
                    BufferedImage sourceImg = ImageIO.read(input);
                    // 源图宽度
                    width = sourceImg.getWidth();
                    // 源图高度
                    height = sourceImg.getHeight();
                } catch (Exception e) {
                    log.error("保存图片异常", e);
                }
            }
            String relativeUrl = relativePath;
            relativeUrl = relativeUrl.replaceAll("\\\\", "/");
            return FileUploadResDto.builder()
                    .width(width)
                    .height(height)
                    .size(getFileSize(file.length()))
                    .code(1)
                    .result(Boolean.TRUE.toString())
                    .relativePath(relativeUrl + newFileName)
                    .build();
        }
        return FileUploadResDto.error();
    }

    /**
     * 获取文件大小
     * 1 KB = 1 * 1024 = 1024
     * 1 M = 1 * 1024 * 1024 = 1048576
     * 1 G = 1 * 1024 * 1024 *1024 = 1073741824
     * @param size
     * @return
     */
    private String getFileSize(long size) {
        DecimalFormat format = new DecimalFormat("#0.00");
        double s;
        String unit;
        if (size < 1048576) {
            s = size * 1.0 / 1024;
            unit = "KB";
        } else if (size < 1073741824) {
            s = size * 1.0 / 1048576;
            unit = "M";
        } else {
            s = size * 1.0 / 1073741824;
            unit = "G";
        }
        return format.format(s) + unit;
    }

    /**
     * 文件上传到指定目录
     *
     * @param file
     * @param fileDir
     * @param rename
     * @return
     */
    private boolean saveFileToDir(MultipartFile file, String fileDir, String rename) {
        if (null == file || file.isEmpty()) {
            return false;
        }
        try {
            // 验证磁盘地址是否存在
            File folderPath = new File(fileDir);
            if (!folderPath.exists()) {
                // 建一个文件夹
                folderPath.mkdirs();
            }
            //
            String saveFileName = file.getOriginalFilename();
            if (null != rename && rename.length() > 0) {
                saveFileName = rename;
            }
            // 保存文件
            file.transferTo(new File(folderPath, saveFileName));
            return true;
        } catch (IOException e) {
            log.error("保存文件异常, fileName:{}, fileDir:{}", file.getOriginalFilename(), fileDir, e);
        }
        return false;
    }
}
