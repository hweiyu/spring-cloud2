package com.imin.user.enums;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title 描述
 * @Description 描述
 * @date 2018/11/29 10:22
 **/
public enum PlatformTypeEnum {

    /**
     * imin云平台
     */
    IMIN(1, "imin云平台"),

    /**
     * 代理商平台
     */
    AGENT(2, "代理商平台"),

    /**
     * 开发者平台
     */
    DEVELOPER(3, "开发者平台");

    private int type;

    private String desc;

    PlatformTypeEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public String getDesc() {
        return this.desc;
    }

    public static PlatformTypeEnum of(Integer type) {
        if (null == type) {
            return null;
        }
        for (PlatformTypeEnum cur : values()) {
            if (type == cur.getType()) {
                return cur;
            }
        }
        return null;
    }

    public boolean isSame(Integer type) {
        return null != type && type == this.getType();
    }
}
