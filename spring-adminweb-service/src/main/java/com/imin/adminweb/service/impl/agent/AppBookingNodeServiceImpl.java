package com.imin.adminweb.service.impl.agent;

import com.google.common.collect.Lists;
import com.imin.adminweb.feign.basic.DictApiServiceRemote;
import com.imin.adminweb.mapper.agent.AppBookingNodeMapper;
import com.imin.adminweb.model.agent.AppBookingNodeModel;
import com.imin.adminweb.service.agent.AppBookingNodeService;
import com.imin.basic.dto.request.DictListReqDto;
import com.imin.basic.dto.response.DictResDto;
import com.imin.infrastructure.common.exception.ServiceException;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.infrastructure.common.result.ResultDataUtil;
import com.imin.infrastructure.common.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Objects;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 应用定制审批流服务
 * @date 2019-03-01 11:11:07
 **/
@Slf4j
@Service
public class AppBookingNodeServiceImpl implements AppBookingNodeService {

    @Autowired
    private AppBookingNodeMapper appBookingNodeMapper;

    @Autowired
    private DictApiServiceRemote dictApiServiceRemote;


    /**
     * 通过流水号查数据
     *
     * @param seq
     * @return
     */
    @Override
    public List<AppBookingNodeModel> listBySeq(String seq) {
        return appBookingNodeMapper.select(AppBookingNodeModel.builder().seq(seq).build());
    }

    /**
     * 从当前节点开始创建流程
     * @param seq 流水号
     * @param node 当前节点
     * @param type 应用类型(0:OS;1:APK)
     * @param applicantId 申请人id
     * @param applicantName 申请人名称
     * @param remark 备注
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public void createWorkflow(String seq, String node, Integer type, Long applicantId, String applicantName, String remark) {
        List<AppBookingNodeModel> insertList = Lists.newArrayList();
        //查询配置的流程节点信息
        try {
            ResultData<List<DictResDto>> resultData = dictApiServiceRemote.list(DictListReqDto.builder()
                    .dictType("app_booking_node").dictCode(0 == type ? "os" : "apk").build());
            if (ResultDataUtil.checkCodeAndData(resultData)) {
                List<DictResDto> resDtos = resultData.getData();
                boolean isFirst = true;
                boolean isApplicant;
                for (DictResDto resDto : resDtos) {
                    if (resDto.getDictKey().compareTo(node) >= 0) {
                        AppBookingNodeModel model = AppBookingNodeModel.builder()
                                .seq(seq).auditFlag(isFirst ? 1 : 0).roleId(Long.valueOf(resDto.getDictValue()))
                                .node(resDto.getDictKey()).auditorId(0L).auditorName("").auditTime("0001-01-01 00:00:00").remark("")
                                .build();
                        // 0 代表申请人
                        isApplicant = Objects.equals("0", resDto.getDictValue());
                        if (isApplicant) {
                            model.setAuditorId(applicantId);
                            model.setAuditorName(applicantName);
                        }
                        if (isFirst) {
                            isFirst = false;

                        }
                        insertList.add(model);
                    }
                }
                appBookingNodeMapper.batchInsert(insertList);
            } else {
                throw new ServiceException("Please contact the administrator to configure the process approver");
            }
        } catch (Exception e) {
            log.error("调用基础服务异常, seq:{}", seq, e);
            throw new ServiceException("basic server error");
        }
    }

    /**
     * 进入下一流程节点
     * @param seq 流水号
     * @param auditorId 审核人id
     * @param auditorName 审核人名称
     * @param remark 备注
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public void nextWorkflow(String seq, Long auditorId, String auditorName, String remark) {
        List<AppBookingNodeModel> models = listBySeq(seq);
        if (CollectionUtils.isNotEmpty(models)) {
            //获取当前审核人的节点
            int auditNode = 0;
            for (int i = 0; i < models.size(); i++) {
                if (1 == models.get(i).getAuditFlag()) {
                    auditNode = i;
                    break;
                }
            }
            AppBookingNodeModel audit = models.get(auditNode);
            //设置审核人信息
            audit.setAuditorId(auditorId);
            audit.setAuditorName(auditorName);
            audit.setAuditFlag(0);
            audit.setAuditTime(DateUtil.getDateTime());
            audit.setRemark(remark);
            appBookingNodeMapper.updateByPrimaryKeySelective(audit);
            //将下一节点的审核人设置为当前审批人的状态
            if (auditNode < models.size() - 1) {
                AppBookingNodeModel next = models.get(auditNode + 1);
                next.setAuditFlag(1);
                appBookingNodeMapper.updateByPrimaryKeySelective(next);
            }
        }
    }

    /**
     * 从当前节点开始清除之后的节点
     * @param seq 流水号
     * @param node 当前节点
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public void clearStartNode(String seq, String node) {
        Example example = new Example(AppBookingNodeModel.class);
        example.createCriteria().andEqualTo("seq", seq).andGreaterThanOrEqualTo("node", node);
        appBookingNodeMapper.deleteByExample(example);
    }
}
