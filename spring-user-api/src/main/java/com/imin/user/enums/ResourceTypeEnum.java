package com.imin.user.enums;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title 描述
 * @Description 资源类型(1:目录;2:菜单;3:按钮)
 * @date 2018/12/5 8:59
 **/
public enum ResourceTypeEnum {

    /**
     * 目录
     */
    CATALOG(1, "目录"),

    /**
     * 菜单
     */
    MENU(2, "菜单"),

    /**
     * 按钮
     */
    BUTTON(3, "按钮");

    private int type;

    private String desc;

    ResourceTypeEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public String getDesc() {
        return this.desc;
    }

    public static ResourceTypeEnum of(Integer type) {
        if (null == type) {
            return null;
        }
        for (ResourceTypeEnum cur : values()) {
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
