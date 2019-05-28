package com.imin.basic.handle;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;

/**
*
* @Title: code
* @Description: code
* @author code
* @date 2018年2月28日 下午4:47:55
* @version V1.0
*
**/

public class ImageCompress {
    protected static final String FILESUFFIXSYMBOL=".";
    protected static Logger logger = LoggerFactory.getLogger(ImageCompress.class);
    private int zoomWidth = 300;
    private int zoomHeight = 300;
    private String zoomSuffix = "zoom";
    private String cutPrefix = "cut_";


    public ImageCompress() {}

    public ImageCompress(int zoomWidth, int zoomHeight, String zoomSuffix) {
        this.zoomWidth = zoomWidth;
        this.zoomHeight = zoomHeight;
        this.zoomSuffix = zoomSuffix;
    }


    /**
     * 压缩指定文件夹下的文件
     */
    public void zipAllImageFile(String path) {
        File file = new File(path);
        if (file.isFile()) {
            zipImageFile(file);
        } else {
            File[] files = file.listFiles();
            if (null != files) {
                for (File f : files) {
                    // 递归每一个文件
                    zipAllImageFile(f.getAbsolutePath());
                }
            }
        }
    }



    /**
     * Title: cutImage Description: 根据原图与裁切size截取局部图片
     *
     * @param srcImg 源图片
     * @param output 图片输出流
     * @param rect 需要截取部分的坐标和大小
     */
    public void cutImage(File srcImg, OutputStream output, Rectangle rect) {
        if (srcImg.exists()) {
            FileInputStream fis = null;
            ImageInputStream iis = null;
            try {
                fis = new FileInputStream(srcImg);
                // ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG,
                // JPEG, WBMP, GIF, gif]
                String specStr = ",";
                String types = Arrays.toString(ImageIO.getReaderFormatNames()).replace("]", specStr);
                String suffix = null;
                // 获取图片后缀
                if (srcImg.getName().indexOf(FILESUFFIXSYMBOL) > -1) {
                    suffix = srcImg.getName().substring(srcImg.getName().lastIndexOf(FILESUFFIXSYMBOL) + 1);
                } // 类型和图片后缀全部小写，然后判断后缀是否合法
                if (suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase() + specStr) < 0) {
                    logger.error(
                            "Sorry, the image suffix is illegal. the standard image suffix is {}."
                                    + types);
                    return;
                }
                // 将FileInputStream 转换为ImageInputStream
                iis = ImageIO.createImageInputStream(fis);
                // 根据图片类型获取该种类型的ImageReader
                ImageReader reader = ImageIO.getImageReadersBySuffix(suffix).next();
                reader.setInput(iis, true);
                ImageReadParam param = reader.getDefaultReadParam();
                param.setSourceRegion(rect);
                BufferedImage bi = reader.read(0, param);
                ImageIO.write(bi, suffix, output);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fis != null) {
                        fis.close();
                    }
                    if (iis != null) {
                        iis.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            logger.error("the src image is not exist.");
        }
    }

    public void cutImage(File srcImg, OutputStream output, int x, int y, int width, int height) {
        cutImage(srcImg, output, new Rectangle(x, y, width, height));
    }

    public void cutImage(File srcImg, String destImgPath, Rectangle rect) {
        File destImg = new File(destImgPath);
        if (destImg.exists()) {
            String p = destImg.getPath();
            try {
                if (!destImg.isDirectory()) {
                    p = destImg.getParent();
                }
                if (!p.endsWith(File.separator)) {
                    p = p + File.separator;
                }
                cutImage(srcImg, new FileOutputStream(p + cutPrefix + "_"
                        + new java.util.Date().getTime() + "_" + srcImg.getName()), rect);
            } catch (FileNotFoundException e) {
                logger.error("the dest image is not exist.");
            }
        } else {
            logger.error("the dest image folder is not exist.");
        }
    }

    public void cutImage(File srcImg, String destImg, int x, int y, int width, int height) {
        cutImage(srcImg, destImg, new Rectangle(x, y, width, height));
    }

    public void cutImage(String srcImg, String destImg, int x, int y, int width, int height) {
        cutImage(new File(srcImg), destImg, new Rectangle(x, y, width, height));
    }

    /**
     * <p>
     * Title: thumbnailImage
     * </p>
     * <p>
     * Description: 根据图片路径生成缩略图
     * </p>
     *
     * @param srcImg 原图片路径
     * @param w 缩略图宽
     * @param h 缩略图高
     * @param prevfix 生成缩略图的前缀
     * @param force 是否强制按照宽高生成缩略图(如果为false，则生成最佳比例缩略图)
     */
    public void thumbnailImage(File srcImg, OutputStream output, int w, int h, String prevfix,
            boolean force) {
        if (srcImg.exists()) {
            try {
                // ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG,
                // JPEG, WBMP, GIF, gif]
                String specStr = ",";
                String types = Arrays.toString(ImageIO.getReaderFormatNames()).replace("]", specStr);
                String suffix = null;
                // 获取图片后缀
                if (srcImg.getName().indexOf(FILESUFFIXSYMBOL) > -1) {
                    suffix = srcImg.getName().substring(srcImg.getName().lastIndexOf(FILESUFFIXSYMBOL) + 1);
                } // 类型和图片后缀全部小写，然后判断后缀是否合法
                if (suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase() + specStr) < 0) {
                    logger.error(
                            "Sorry, the image suffix is illegal. the standard image suffix is {}."
                                    + types);
                    return;
                }
                // System.out.println("target image's size, width:{}, height:{}." + "," + w + "," +
                // h);
                Image img = ImageIO.read(srcImg);
                // 根据原图与要求的缩略图比例，找到最合适的缩略图比例
                if (!force) {
                    int width = img.getWidth(null);
                    int height = img.getHeight(null);
                    if ((width * 1.0) / w < (height * 1.0) / h) {
                        if (width > w) {
                            h = Integer.parseInt(new java.text.DecimalFormat("0")
                                    .format(height * w / (width * 1.0)));
                            // System.out.println(
                            // "change image's height, width:{}, height:{}." + w + "," + h);
                        }
                    } else {
                        if (height > h) {
                            w = Integer.parseInt(new java.text.DecimalFormat("0")
                                    .format(width * h / (height * 1.0)));
                            // System.out.println("change image's width, width:{}, height:{}." + ","
                            // + w + "," + h);
                        }
                    }
                }
                BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                Graphics g = bi.getGraphics();
                g.drawImage(img, 0, 0, w, h, Color.LIGHT_GRAY, null);
                g.dispose();
                // 将图片保存在原目录并加上前缀
                ImageIO.write(bi, suffix, output);
                output.close();
            } catch (IOException e) {
                logger.error("generate thumbnail image failed.");
            }
        } else {
            logger.error("the src image is not exist.");
        }
    }

    public void thumbnailImage(File srcImg, int w, int h, String prevfix, boolean force) {
        String p = srcImg.getAbsolutePath();
        try {
            if (!srcImg.isDirectory()) {
                p = srcImg.getParent();
            }
            if (!p.endsWith(File.separator)) {
                p = p + File.separator;
            }
            thumbnailImage(srcImg, new FileOutputStream(p + prevfix + srcImg.getName()), w,
                    h, prevfix, force);
        } catch (FileNotFoundException e) {
            logger.error("the dest image is not exist." + e.toString());
        }
    }

    /**
     * 判读是否为图片文件
     *
     */
    private boolean isImage(File file) {
        boolean flag = false;
        try {
            ImageInputStream is = ImageIO.createImageInputStream(file);
            if (null == is) {
                return flag;
            }
            is.close();
            flag = true;
        } catch (Exception e) {

        }
        return flag;
    }

    /**
     * 生成缩略图
     *
     */
    public void zipImageFile(File file) {
        try {
            if (isImage(file)) {
                String url = file.getAbsolutePath();
                int index = url.lastIndexOf(".");
                String thumUrl = url.substring(0, index) + zoomSuffix + url.substring(index);

                BufferedImage image = ImageIO.read(file);

                int imageWidth = image.getWidth();

                int imageHeitht = image.getHeight();

                // 图片原始长宽比
                float originalRatio = (float) ((imageWidth * 1.0) / imageHeitht);
                // 指定的长宽比
                float ratio = (float) ((zoomWidth * 1.0) / zoomHeight);
                if (originalRatio == ratio) {
                    Thumbnails.of(url).size(zoomWidth, zoomHeight).toFile(thumUrl);
                } else if (originalRatio > ratio) {
                    Thumbnails.of(url).sourceRegion(Positions.CENTER, (int) (imageHeitht * ratio),
                            imageHeitht).size(zoomWidth, zoomHeight).toFile(thumUrl);
                } else {
                    Thumbnails.of(url)
                            .sourceRegion(Positions.CENTER, imageWidth, (int) (imageWidth / ratio))
                            .size(zoomWidth, zoomHeight).toFile(thumUrl);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 按图片比率生成缩略图
     *
     * A.原始图（宽/高） <= 300 X 300  :
     * 缩略图（宽/高） = 原始图 ; 放大图（宽/高） = 原始图
     *
     * B.300 X 300 < 原始图（宽/高）<= 1000 X 1000 :
     * 缩略图（宽/高） = 50% 原始图 ; 放大图（宽/高） = 原始图
     *
     * C. 1000 X 1000 < 原始图（宽/高）<= 3000 X 3000 :
     * 缩略图（宽/高） = 20% 原始图 ; 放大图（宽/高） = 50% 原始图
     *
     * D. 原始图（宽/高）> 3000 X 3000 :
     * 缩略图（宽/高） = 10% 原始图 ; 放大图（宽/高） = 20% 原始图
     *
     */
    public void zipImageFileByRatio(File file) {
        try {
            if (isImage(file)) {
                String url = file.getAbsolutePath();
                int index = url.lastIndexOf(".");
                String start = url.substring(0, index);
                String end = url.substring(index);
                BufferedImage image = ImageIO.read(file);
                int imageWidth = image.getWidth();
                int imageHeitht = image.getHeight();
                final int size300 = 300;
                final int size1000 = 1000;
                final int size3000 = 3000;
                if (isRange(imageWidth, imageHeitht, size300)) {
                    Thumbnails.of(url).scale(1.0).toFile(outPath(start, "_min", end));
                    Thumbnails.of(url).scale(1.0).toFile(outPath(start, "_max", end));
                } else if (isRange(imageWidth, imageHeitht, size1000)) {
                    Thumbnails.of(url).scale(0.5).toFile(outPath(start, "_min", end));
                    Thumbnails.of(url).scale(1.0).toFile(outPath(start, "_max", end));
                } else if (isRange(imageWidth, imageHeitht, size3000)) {
                    Thumbnails.of(url).scale(0.2).toFile(outPath(start, "_min", end));
                    Thumbnails.of(url).scale(0.5).toFile(outPath(start, "_max", end));
                } else {
                    Thumbnails.of(url).scale(0.1).toFile(outPath(start, "_min", end));
                    Thumbnails.of(url).scale(0.2).toFile(outPath(start, "_max", end));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isRange(int width, int height, int size) {
        return width <= size && height <= size;
    }

    private String outPath(String start, String customize, String end) {
        return start + customize + end;
    }

    /**
     * 生成缩略图
     *
     * @param imagePath 图片路径
     * @param width 指定宽度
     * @param heitht 指定高度
     */
    public void zipImageFile(String imagePath, int width, int heitht) {
        try {
            File file = new File(imagePath);
            if (isImage(file)) {
                String url = file.getAbsolutePath();
                int index = url.lastIndexOf(".");
                String thumUrl = url.substring(0, index) + zoomSuffix + url.substring(index);

                BufferedImage image = ImageIO.read(new File(imagePath));

                int imageWidth = image.getWidth();

                int imageHeitht = image.getHeight();

                // 图片原始长宽比
                float originalRatio = (float) ((imageWidth * 1.0) / imageHeitht);
                // 指定的长宽比
                float ratio = (float) ((zoomWidth * 1.0) / zoomHeight);

                if (originalRatio == ratio) {
                    Thumbnails.of(url).size(width, heitht).toFile(thumUrl);
                } else if (originalRatio > ratio) {
                    Thumbnails.of(url).sourceRegion(Positions.CENTER, (int) (imageHeitht * ratio),
                            imageHeitht).size(width, heitht).toFile(thumUrl);
                } else {
                    Thumbnails.of(url)
                            .sourceRegion(Positions.CENTER, imageWidth, (int) (imageWidth / ratio))
                            .size(width, heitht).toFile(thumUrl);
                }

                if (logger.isDebugEnabled()) {
                    logger.debug("生成缩略图：" + thumUrl);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 通过递归调用生成一个文件夹及下面的所有图片的缩略图
     *
     * @param file
     */
    public void zipImageAllFiles(File file) {
        if (logger.isDebugEnabled()) {
            logger.debug("生成文件夹缩略图：" + file.getAbsolutePath());
        }

        if (!file.exists()) {
            return;
        }
        if (file.isFile()) {
            // 表示该文件不是文件夹
            zipImageFile(file.getAbsolutePath(), 300, 300);
        } else {

            File[] files = file.listFiles();
            if (null != files) {
                for (File f : files) {
                    // 递归每一个文件
                    zipImageAllFiles(f);
                }
            }
        }
    }
}
