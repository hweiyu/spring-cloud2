package com.imin.adminweb.enums;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title 描述
 * @Description 审批状态(0 : 草稿 ; 1 : 撤回 ; 2 : 审核中 ; 3 : 审核通过 ; 4 : 审核不通过 ; 5 : 开发中 ; 6 : 待验收 ; 7 : 验收通过 ; 8 : 验收不通过; 9 : 开发中(测试阶段))
 * @date 2019/2/27 11:31
 **/
public enum AuditStatusEnum {

    /**
     * 审批状态(0 : 草稿 ; 1 : 撤回 ; 2 : 审核中 ; 3 : 审核通过 ; 4 : 审核不通过 ; 5 : 开发中 ; 6 : 待验收 ; 7 : 验收通过 ; 8 : 验收不通过; 9 : 开发中(测试阶段))
     */
    DRAFT(0, "草稿"),
    REVOKE(1, "撤回"),
    AUDITING(2, "审核中"),
    AUDIT_PASS(3, "审核通过"),
    AUDIT_REFUSE(4, "审核不通过"),
    DEVELOP(5, "开发中"),
    ACCEPTING(6, "待验收"),
    ACCEPT_PASS(7, "验收通过"),
    ACCEPT_REFUSE(8, "验收不通过"),
    TESTING(9, "开发中(测试阶段)");

    private int status;

    private String desc;

    AuditStatusEnum(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
