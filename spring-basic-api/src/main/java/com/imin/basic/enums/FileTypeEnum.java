package com.imin.basic.enums;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title 描述
 * @Description 描述
 * @date 2018/12/5 13:48
 **/
public enum FileTypeEnum {

    /**
     * 用户模块图片
     */
    USER_IMAGE("user", "user_image"),

    /**
     * 用户模块视频
     */
    USER_VIDEO("user", "user_video"),

    /**
     * 用户模块文件
     */
    USER_FILE("user", "user_file"),

    /**
     * 终端模块图片
     */
    TERMINAL_IMAGE("terminal", "terminal_image"),

    /**
     * 终端模块视频
     */
    TERMINAL_VIDEO("terminal", "terminal_video"),

    /**
     * 终端模块文件
     */
    TERMINAL_FILE("terminal", "terminal_file"),

    /**
     * 应用市场图片
     */
    APPSTORE_IMAGE("appstore", "appstore_image"),

    /**
     * 应用市场文件
     */
    APPSTORE_FILE("appstore", "appstore_file"),

    /**
     * 其它
     */
    OTHERS("others", "others");

    /**
     * 模块
     */
    private String module;

    /**
     * 保存路径
     */
    private String dir;

    FileTypeEnum(String module, String dir) {
        this.module = module;
        this.dir = dir;
    }

    public static FileTypeEnum nameOf(String name) {
        for (FileTypeEnum cur : values()) {
            if (cur.name().equals(name)) {
                return cur;
            }
        }
        return FileTypeEnum.OTHERS;
    }

    public String getModule() {
        return module;
    }

    public String getDir() {
        return dir;
    }

}
