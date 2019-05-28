package com.imin.adminweb.service.impl.agent;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.imin.adminweb.consts.CommonConst;
import com.imin.adminweb.dto.request.agent.*;
import com.imin.adminweb.dto.response.agent.AppBookingListResDto;
import com.imin.adminweb.dto.response.agent.AppBookingResDto;
import com.imin.adminweb.enums.AuditStatusEnum;
import com.imin.adminweb.mapper.agent.AppBookingMapper;
import com.imin.adminweb.model.agent.AppBookingModel;
import com.imin.adminweb.model.user.SysUserModel;
import com.imin.adminweb.service.TokenService;
import com.imin.adminweb.service.agent.AppBookingNodeService;
import com.imin.adminweb.service.agent.AppBookingService;
import com.imin.adminweb.service.user.SysUserService;
import com.imin.adminweb.util.WechatUtil;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.exception.ServiceException;
import com.imin.infrastructure.common.redis.IRedisService;
import com.imin.infrastructure.common.utils.*;
import com.imin.user.dto.response.SysUserResDto;
import com.imin.user.dto.response.UserLoginResDto;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.imin.infrastructure.common.dto.PageInfo;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 应用定制服务
 * @date 2019-02-27 10:53:59
 **/
@Service
public class AppBookingServiceImpl implements AppBookingService {

    @Autowired
    private AppBookingMapper appBookingMapper;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private AppBookingNodeService appBookingNodeService;

    @Autowired
    private IRedisService redisService;

    private static final String ACCESS_TOKEN_KEY = "wechat:accessToken";

    /**
    * 列表
    */
    @Override
    public PageInfo<AppBookingListResDto> selectByApplicant(AppBookingQueryReqDto reqDto) {
        PageHelper.startPage(reqDto.getPageNumberByDefault(), reqDto.getPageSizeByDefault());
        Example example = new Example(AppBookingModel.class);
        example.orderBy("id").desc();
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("newFlag", 1);
        if (!StringUtil.isEmptyOrNull(reqDto.getSoftwareName())) {
            criteria.andLike("softwareName", "%" + reqDto.getSoftwareName() + "%");
        }
        if (null != reqDto.getAuditStatus()) {
            criteria.andEqualTo("auditStatus", reqDto.getAuditStatus());
        }
        if (null != reqDto.getAppType()) {
            criteria.andEqualTo("appType", reqDto.getAppType());
        }
        UserLoginResDto user = tokenService.getAndCheckCurrentUserInfo();
        criteria.andEqualTo("applicant", user.getId());
        List<AppBookingModel> models = appBookingMapper.selectByExample(example);
        return PageUtil.CopyPageList(models, AppBookingListResDto.class);
    }

    private boolean currentUserHasPermission(Long ... roleId) {
        UserLoginResDto user = tokenService.getAndCheckCurrentUserInfo();
        SysUserResDto userInfo = sysUserService.get(user.getId());
        if (null != userInfo && CollectionUtils.isNotEmpty(userInfo.getRoleIds())) {
            return userInfo.getRoleIds().removeAll(Lists.newArrayList(roleId));
        }
        return false;
    }

    /**
     * 列表
     *
     * @param reqDto
     */
    @Override
    public PageInfo<AppBookingListResDto> selectByAuditor(AppBookingQueryReqDto reqDto) {
        if (!currentUserHasPermission(CommonConst.AUDIT_ROLE)) {
            return new PageInfo<>(Lists.newArrayList());
        }
        Example example = new Example(AppBookingModel.class);
        example.orderBy("id").desc();
        Example.Criteria criteria = example.createCriteria();
        //查最新的记录
        criteria.andEqualTo("newFlag", 1);
        //状态为非草稿，撤回，审核不通过的数据
        criteria.andNotEqualTo("auditStatus", AuditStatusEnum.DRAFT.getStatus());
        if (!StringUtil.isEmptyOrNull(reqDto.getSoftwareName())) {
            criteria.andLike("softwareName", "%" + reqDto.getSoftwareName() + "%");
        }
        if (null != reqDto.getAuditStatus()) {
            criteria.andEqualTo("auditStatus", reqDto.getAuditStatus());
        }
        if (null != reqDto.getAppType()) {
            criteria.andEqualTo("appType", reqDto.getAppType());
        }
        List<AppBookingListResDto> result = Lists.newArrayList();
        PageHelper.startPage(reqDto.getPageNumberByDefault(), reqDto.getPageSizeByDefault());
        List<AppBookingModel> models = appBookingMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(models)) {
            //获取所有的申请人账户id
            List<Long> userIds = Lists.newArrayList();
            for (AppBookingModel model : models) {
                userIds.add(model.getApplicant());
            }
            Map<Long, String> accountMap = Maps.newHashMap();
            if (CollectionUtils.isNotEmpty(userIds)) {
                List<SysUserModel> userModels = sysUserService.listByIds(userIds);
                if (CollectionUtils.isNotEmpty(userModels)) {
                    for (SysUserModel userModel : userModels) {
                        accountMap.put(userModel.getId(), userModel.getAccount());
                    }
                }
            }
            //遍历设备申请人名称
            for (AppBookingModel model : models) {
                AppBookingListResDto cur = CopyDataUtil.copyObject(model, AppBookingListResDto.class);
                cur.setApplicantName(accountMap.get(model.getApplicant()));
                result.add(cur);
            }
        }
        return PageUtil.getPageList(result, PageUtil.getPaginationInfo(models));
    }

    /**
    * 查询
    */
    @Override
    public AppBookingResDto get(Long id) {
        AppBookingModel model = appBookingMapper.selectOne(AppBookingModel.builder().id(id).newFlag(1).build());
        return CopyDataUtil.copyObject(model, AppBookingResDto.class);
    }

    /**
     * 保存(存草稿、存开发计划，存测试计划，存打包部署)
     *
     * @param reqDto
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public IdDto save(AppBookingSaveReqDto reqDto) {
        AppBookingModel model = CopyDataUtil.copyObject(reqDto, AppBookingModel.class);
        if (null == model.getId()) {
            UserLoginResDto user = tokenService.getAndCheckCurrentUserInfo();
            model.setApplicant(user.getId());
            appBookingMapper.insertSelective(model);
        } else {
            appBookingMapper.updateByPrimaryKeySelective(model);
        }
        return new IdDto(model.getId());
    }

    /**
     * 提交申请
     *
     * @param reqDto
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public IdDto submit(AppBookingSubmitReqDto reqDto) {
        AppBookingModel model = CopyDataUtil.copyObject(reqDto, AppBookingModel.class);
        UserLoginResDto user = tokenService.getAndCheckCurrentUserInfo();
        //设置申请人信息
        model.setApplicant(user.getId());
        model.setApplicantTime(DateUtil.getDateTime());
        //将状态设置为审核中
        model.setAuditStatus(AuditStatusEnum.AUDITING.getStatus());
        //清除单据信息
        clear(model);
        if (null == model.getId()) {
            //生成流水号
            model.setSeq(String.valueOf(new SnowFlake().nextId()));
            appBookingMapper.insertSelective(model);
        } else {
            //判断流水号是否存在，不存在则生成一个
            AppBookingModel dbModel = appBookingMapper.selectOne(AppBookingModel.builder().id(model.getId()).newFlag(1).build());
            model.setSeq(StringUtil.isEmptyOrNull(dbModel.getSeq()) ? String.valueOf(new SnowFlake().nextId()) : dbModel.getSeq());
            appBookingMapper.updateByPrimaryKeySelective(model);
        }
        //创建流程节点
        appBookingNodeService.createWorkflow(model.getSeq(),
                CommonConst.WORK_FLOW_NODE_1, model.getAppType(), user.getId(), user.getAccount(), "申请人提交定制申请");

        //给审核人的微信号发提醒消息
        List<SysUserModel> userModels = sysUserService.listByRoleId(CommonConst.AUDIT_ROLE);
        if (CollectionUtils.isNotEmpty(userModels)) {
            List<String> openIds = Lists.newArrayList();
            for (SysUserModel userModel : userModels) {
                if (!StringUtil.isEmptyOrNull(userModel.getOpenId())) {
                    openIds.add(userModel.getOpenId());
                }
            }
            if (CollectionUtils.isNotEmpty(openIds)) {
                sendMessage(user.getAccount(), openIds);
            }
        }

        return new IdDto(model.getId());
    }

    private void sendMessage(String name, List<String> openIds) {
        String accessToken = redisService.get(ACCESS_TOKEN_KEY);
        if (StringUtil.isEmptyOrNull(accessToken)) {
            accessToken = WechatUtil.getAccessToken();
            if (null != accessToken) {
                redisService.set(ACCESS_TOKEN_KEY, accessToken, 10800);
            }
        }
        if (CollectionUtils.isNotEmpty(openIds) && !StringUtil.isEmptyOrNull(accessToken)) {
            for (String openId : openIds) {
                WechatUtil.sendMessageByTemplate(openId, name, accessToken, "93DJS6O63vVPf_VsxkajEwT6t2i1kShNTYKiE_iRKPA");
            }
        }
    }

    /**
     * 撤回
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public IdDto revoke(Long id) {
        AppBookingModel model = appBookingMapper.selectOne(AppBookingModel.builder().id(id).newFlag(1).build());
        UserLoginResDto user = tokenService.getAndCheckCurrentUserInfo();
        if (!Objects.equals(user.getId(), model.getApplicant())) {
            throw new ServiceException("请勿操作别人的单据");
        }
        appBookingMapper.updateByPrimaryKeySelective(AppBookingModel.builder()
                .id(id)
                .auditStatus(AuditStatusEnum.REVOKE.getStatus())
                .applicantTime("0001-01-01 00:00:00")
                .build());
        //清除流程节点
        appBookingNodeService.clearStartNode(model.getSeq(), CommonConst.WORK_FLOW_NODE_1);
        return new IdDto(id);
    }

    /**
     * 清除表单数据
     * @param model
     */
    private void clear(AppBookingModel model) {
        final String datetime = "0001-01-01 00:00:00";
        model.setDevPeriod("");
        model.setDevStartTime(datetime);
        model.setDevEndTime(datetime);
        model.setTestPeriod("");
        model.setTestStartTime(datetime);
        model.setTestEndTime(datetime);
        model.setAuditTime(datetime);
        model.setAuditFlag(0);
        model.setAuditSuggest("");
        model.setAcceptTime(datetime);
        model.setAcceptFlag(0);
        model.setAcceptSuggest("");
    }

    /**
     * 审核
     *
     * @param reqDto
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public IdDto audit(AppBookingAuditReqDto reqDto) {
        if (!currentUserHasPermission(CommonConst.AUDIT_ROLE)) {
            throw new ServiceException("您没有操作权限");
        }
        AppBookingModel dbModel = appBookingMapper.selectOne(AppBookingModel.builder().id(reqDto.getId()).newFlag(1).build());
        AppBookingModel model = CopyDataUtil.copyObject(reqDto, AppBookingModel.class);
        //设置审批状态
        model.setAuditStatus(CommonConst.PASS_FLAG == model.getAuditFlag() ?
                AuditStatusEnum.AUDIT_PASS.getStatus() : AuditStatusEnum.AUDIT_REFUSE.getStatus());
        if (CommonConst.PASS_FLAG == model.getAuditFlag()) {
            UserLoginResDto user = tokenService.getAndCheckCurrentUserInfo();
            appBookingNodeService.nextWorkflow(dbModel.getSeq(), user.getId(), user.getAccount(), "审核通过");
        } else {
            appBookingNodeService.clearStartNode(dbModel.getSeq(), CommonConst.WORK_FLOW_NODE_1);
        }
        model.setAuditTime(DateUtil.getDateTime());
        appBookingMapper.updateByPrimaryKeySelective(model);
        return new IdDto(model.getId());
    }

    /**
     * 开发中
     *
     * @param reqDto
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public IdDto develop(AppBookingDevelopReqDto reqDto) {
        if (!currentUserHasPermission(CommonConst.AUDIT_ROLE)) {
            throw new ServiceException("您没有操作权限");
        }
        AppBookingModel dbModel = appBookingMapper.selectOne(AppBookingModel.builder().id(reqDto.getId()).newFlag(1).build());
        Long id = reqDto.getId();
        AppBookingModel model = CopyDataUtil.copyObject(reqDto, AppBookingModel.class);
        //设置状态为待验收
        model.setAuditStatus(AuditStatusEnum.DEVELOP.getStatus());
        //如果是验收不通过，则保留原数据，并生成一条新的流程数据
        if (AuditStatusEnum.ACCEPT_REFUSE.getStatus() == dbModel.getAuditStatus()) {
            appBookingMapper.updateByPrimaryKeySelective(AppBookingModel.builder().id(reqDto.getId()).newFlag(0).build());
            //创建一条新的记录
            copy(model, dbModel);
            appBookingMapper.insertSelective(model);
            id = model.getId();
        } else {
            appBookingMapper.updateByPrimaryKeySelective(model);
        }
        UserLoginResDto user = tokenService.getAndCheckCurrentUserInfo();
        //跳转下一流程
        appBookingNodeService.nextWorkflow(dbModel.getSeq(), user.getId(), user.getAccount(), "开发中");
        return new IdDto(id);
    }

    private void copy(AppBookingModel target, AppBookingModel source) {
        target.setId(null);
        //填基础定制信息
        target.setSeq(source.getSeq());
        target.setSoftwareName(source.getSoftwareName());
        target.setAppType(source.getAppType());
        target.setContentType(source.getContentType());
        target.setBookingReason(source.getBookingReason());
        target.setUpgradeStrategy(source.getUpgradeStrategy());
        //填审核信息
        target.setAuditFlag(source.getAuditFlag());
        target.setAuditTime(source.getAuditTime());
        //填申请人
        target.setApplicant(source.getApplicant());
        target.setApplicantTime(source.getApplicantTime());
    }

    /**
     * 开发完成，测试通过并打包部署
     *
     * @param reqDto
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public IdDto deploy(AppBookingDeployReqDto reqDto) {
        if (!currentUserHasPermission(CommonConst.AUDIT_ROLE)) {
            throw new ServiceException("您没有操作权限");
        }
        AppBookingModel dbModel = appBookingMapper.selectOne(AppBookingModel.builder().id(reqDto.getId()).newFlag(1).build());
        AppBookingModel model = CopyDataUtil.copyObject(reqDto, AppBookingModel.class);
        //设置状态为待验收
        model.setAuditStatus(AuditStatusEnum.ACCEPTING.getStatus());
        appBookingMapper.updateByPrimaryKeySelective(model);
        UserLoginResDto user = tokenService.getAndCheckCurrentUserInfo();
        //跳转下一流程
        appBookingNodeService.nextWorkflow(dbModel.getSeq(), user.getId(), user.getAccount(), "测试完成并打包部署");
        return new IdDto(reqDto.getId());
    }

    /**
     * 验收
     *
     * @param reqDto
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public IdDto accept(AppBookingAcceptReqDto reqDto) {
        //判断是否权限操作
        AppBookingModel dbModel = appBookingMapper.selectOne(AppBookingModel.builder().id(reqDto.getId()).newFlag(1).build());
        UserLoginResDto user = tokenService.getAndCheckCurrentUserInfo();
        if (!Objects.equals(user.getId(), dbModel.getApplicant())) {
            throw new ServiceException("您没有操作权限");
        }
        AppBookingModel model = CopyDataUtil.copyObject(reqDto, AppBookingModel.class);
        model.setAuditStatus(CommonConst.PASS_FLAG == reqDto.getAcceptFlag()
                ? AuditStatusEnum.ACCEPT_PASS.getStatus() : AuditStatusEnum.ACCEPT_REFUSE.getStatus());
        //如果验收通过，则进入下一流程
        if (CommonConst.PASS_FLAG == reqDto.getAcceptFlag()) {
            appBookingNodeService.nextWorkflow(dbModel.getSeq(), user.getId(), user.getAccount(), "验收通过");
        }
        //验收不通过，跳回到开发中节点
        else {
            appBookingNodeService.clearStartNode(dbModel.getSeq(), CommonConst.WORK_FLOW_NODE_2);
            appBookingNodeService.createWorkflow(dbModel.getSeq(), CommonConst.WORK_FLOW_NODE_2, dbModel.getAppType(),
                    dbModel.getApplicant(), "", "验收不通过");
        }
        model.setAcceptTime(DateUtil.getDateTime());
        appBookingMapper.updateByPrimaryKeySelective(model);
        return new IdDto(model.getId());
    }

}
