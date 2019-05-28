package com.imin.adminweb.service.agent;

import com.imin.adminweb.dto.request.agent.*;
import com.imin.adminweb.dto.response.agent.AppBookingListResDto;
import com.imin.adminweb.dto.response.agent.AppBookingResDto;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 应用定制接口
 * @date 2019-02-27 10:53:59
 **/
public interface AppBookingService {

    /**
    * 列表
    */
    PageInfo<AppBookingListResDto> selectByApplicant(AppBookingQueryReqDto reqDto);

    /**
     * 列表
     */
    PageInfo<AppBookingListResDto> selectByAuditor(AppBookingQueryReqDto reqDto);

    /**
    * 查询
    */
    AppBookingResDto get(Long id);

    /**
     * 保存(存草稿、存开发计划，存测试计划，存打包部署)
     * @param reqDto
     * @return
     */
    IdDto save(AppBookingSaveReqDto reqDto);

    /**
     * 提交申请
     * @param reqDto
     * @return
     */
    IdDto submit(AppBookingSubmitReqDto reqDto);

    /**
     * 撤回
     * @param id
     * @return
     */
    IdDto revoke(Long id);

    /**
     * 审核
     * @param reqDto
     * @return
     */
    IdDto audit(AppBookingAuditReqDto reqDto);

    /**
     * 开发中
     * @param reqDto
     * @return
     */
    IdDto develop(AppBookingDevelopReqDto reqDto);

    /**
     * 开发完成，测试通过并打包部署
     * @param reqDto
     * @return
     */
    IdDto deploy(AppBookingDeployReqDto reqDto);

    /**
     * 验收
     * @param reqDto
     * @return
     */
    IdDto accept(AppBookingAcceptReqDto reqDto);

}

