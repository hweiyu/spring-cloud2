package com.imin.adminweb.service.agent;

import com.imin.adminweb.model.agent.AppBookingNodeModel;

import java.util.List;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 应用定制审批流接口
 * @date 2019-03-01 11:11:07
 **/
public interface AppBookingNodeService {

    /**
     * 通过流水号查数据
     * @param seq
     * @return
     */
    List<AppBookingNodeModel> listBySeq(String seq);

    /**
     * 从当前节点开始创建流程
     * @param seq 流水号
     * @param node 当前节点
     * @param type 应用类型(0:OS;1:APK)
     * @param applicantId 申请人id
     * @param applicantName 申请人名称
     * @param remark 备注
     */
    void createWorkflow(String seq, String node, Integer type, Long applicantId, String applicantName, String remark);

    /**
     * 进入下一流程节点
     * @param seq 流水号
     * @param auditorId 审核人id
     * @param auditorName 审核人名称
     * @param remark 备注
     */
    void nextWorkflow(String seq, Long auditorId, String auditorName, String remark);

    /**
     * 从当前节点开始清除之后的节点
     * @param seq 流水号
     * @param node 当前节点
     */
    void clearStartNode(String seq, String node);
}

