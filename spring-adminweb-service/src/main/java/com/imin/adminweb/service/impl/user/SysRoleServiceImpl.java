package com.imin.adminweb.service.impl.user;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.imin.adminweb.feign.basic.DictApiServiceRemote;
import com.imin.adminweb.mapper.user.SysRoleMapper;
import com.imin.adminweb.model.user.SysRoleModel;
import com.imin.adminweb.model.user.SysUserRoleModel;
import com.imin.adminweb.service.user.SysRoleResourceService;
import com.imin.adminweb.service.user.SysRoleService;
import com.imin.adminweb.service.user.SysUserRoleService;
import com.imin.basic.dto.request.DictListReqDto;
import com.imin.basic.dto.response.DictResDto;
import com.imin.infrastructure.common.constants.ShareConstants;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.enums.DataStatusEnum;
import com.imin.infrastructure.common.result.ResultData;
import com.imin.infrastructure.common.result.ResultDataUtil;
import com.imin.infrastructure.common.utils.*;
import com.imin.user.dto.reqeust.SysRoleInsertReqDto;
import com.imin.user.dto.reqeust.SysRoleQueryReqDto;
import com.imin.user.dto.reqeust.SysRoleUpdateReqDto;
import com.imin.user.dto.reqeust.SysRoleUpdateStatusReqDto;
import com.imin.user.dto.response.SysRoleListResDto;
import com.imin.user.dto.response.SysRoleResDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 系统角色服务
 * @date 2018-11-27 18:19:55
 **/
@Service
@Slf4j
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleResourceService sysRoleResourceService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private DictApiServiceRemote dictApiServiceRemote;

    /**
    * 列表
    */
    @Override
    public PageInfo<SysRoleListResDto> select(SysRoleQueryReqDto reqDto) {
        PageHelper.startPage(reqDto.getPageNumberByDefault(), reqDto.getPageSizeByDefault());
        Example example = new Example(SysRoleModel.class);
        Example.Criteria criteria = example.createCriteria();
        //todo 不过滤user id
//        criteria.andEqualTo("userId", reqDto.getUserId());
        criteria.andNotEqualTo("id", ShareConstants.SUPPER_ROLE_ID);
        if (!StringUtil.isEmptyOrNull(reqDto.getRoleName())) {
            criteria.andLike("roleName", "%" + reqDto.getRoleName() + "%");
        }
        if (null != reqDto.getDataStatus()) {
            criteria.andEqualTo("dataStatus", reqDto.getDataStatus());
        } else {
            criteria.andNotEqualTo("dataStatus", DataStatusEnum.DELETED.getStatus());
        }
        List<SysRoleModel> models = sysRoleMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(models)) {
            Map<String, String> roleMap = getRoleMap();
            for (SysRoleModel model : models) {
                resetRoleName(model, roleMap);
            }
        }
        return PageUtil.CopyPageList(models, SysRoleListResDto.class);
    }

    private void resetRoleName(SysRoleModel model, Map<String, String> roleMap) {
        String roleName = roleMap.get(model.getRoleCode());
        if (null != roleName) {
            model.setRoleName(roleName);
        }
    }

    /**
     * 通过用户id查角色列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysRoleListResDto> listByUserId(Long userId) {
        Example example = new Example(SysRoleModel.class);
        example.createCriteria()
                //todo 暂时不过滤用户
//                .andEqualTo("userId", userId)
                .andNotEqualTo("id", ShareConstants.SUPPER_ROLE_ID)
                .andNotEqualTo("dataStatus", DataStatusEnum.DELETED.getStatus());
        List<SysRoleModel> models = sysRoleMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(models)) {
            Map<String, String> roleMap = getRoleMap();
            for (SysRoleModel model : models) {
                resetRoleName(model, roleMap);
            }
        }
        return CopyDataUtil.copyList(models, SysRoleListResDto.class);
    }

    /**
     * key:角色编码, value:角色名称
     * @return
     */
    @Override
    public Map<String, String> getRoleMap() {
        Map<String, String> map = Maps.newHashMap();
        try {
            ResultData<List<DictResDto>> resultData = dictApiServiceRemote.list(DictListReqDto.builder()
                    .dictType("sys_role").dictKey(Il8nSupportUtil.getSupportLanguageFromHttpRequest()).build());
            if (ResultDataUtil.checkCodeAndData(resultData)) {
                for (DictResDto data : resultData.getData()) {
                    map.put(data.getDictCode(), data.getDictValue());
                }
            }
        } catch (Exception e) {
            log.error("查角色国际化名称异常language:{}", Il8nSupportUtil.getSupportLanguageFromHttpRequest(), e);
        }
        return map;
    }

    /**
    * 查询
    */
    @Override
    public SysRoleResDto get(Long id) {
        Example example = new Example(SysRoleModel.class);
        example.createCriteria()
                .andEqualTo("id", id)
                .andNotEqualTo("dataStatus", DataStatusEnum.DELETED.getStatus());
        List<SysRoleModel> models = sysRoleMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(models)) {
            SysRoleResDto resDto = CopyDataUtil.copyObject(models.get(0), SysRoleResDto.class);
            setResources(resDto);
            return resDto;
        }
        return null;
    }

    private void setResources(SysRoleResDto resDto) {
        resDto.setResourceIds(sysRoleResourceService.listByRoleId(resDto.getId()));
    }

    /**
    * 添加
    */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public IdDto insert(SysRoleInsertReqDto reqDto) {
        SysRoleModel insertObj = CopyDataUtil.copyObject(reqDto, SysRoleModel.class);
        sysRoleMapper.insertSelective(insertObj);
        sysRoleResourceService.batchOperate(insertObj.getId(), reqDto.getResourceIds());
        return new IdDto(insertObj.getId());
    }

    /**
    * 修改
    */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {RuntimeException.class, Exception.class})
    public IdDto update(SysRoleUpdateReqDto reqDto) {
        SysRoleModel updateObj = CopyDataUtil.copyObject(reqDto, SysRoleModel.class);
        sysRoleMapper.updateByPrimaryKeySelective(updateObj);
        sysRoleResourceService.batchOperate(reqDto.getId(), reqDto.getResourceIds());
        return new IdDto(reqDto.getId());
    }

    /**
     * 修改状态
     *
     * @param reqDto
     */
    @Override
    public IdDto updateStatus(SysRoleUpdateStatusReqDto reqDto) {
        SysRoleModel updateObj = CopyDataUtil.copyObject(reqDto, SysRoleModel.class);
        sysRoleMapper.updateByPrimaryKeySelective(updateObj);
        return new IdDto(reqDto.getId());
    }

    /**
     * 过滤出可用状态的角色id列表
     *
     * @param ids
     * @return
     */
    @Override
    public List<Long> filterEnableIds(List<Long> ids) {
        Example example = new Example(SysRoleModel.class);
        example.createCriteria()
                .andIn("id", ids)
                .andEqualTo("dataStatus", DataStatusEnum.ENABLE.getStatus());
        List<SysRoleModel> models = sysRoleMapper.selectByExample(example);
        return LangUtils.transform(models, new Function<SysRoleModel, Long>() {
            @Override
            public Long apply(SysRoleModel sysRoleModel) {
                return sysRoleModel.getId();
            }
        });
    }

    /**
     * 通过用户id查角色信息
     * key: 用户id, value:角色列表
     *
     * @param userIds
     * @return
     */
    @Override
    public Multimap<Long, SysRoleModel> getMapByUserIds(List<Long> userIds) {
        Multimap<Long, SysRoleModel> resultMap = ArrayListMultimap.create();
        List<SysUserRoleModel> models = sysUserRoleService.listByUserIds(userIds);
        if (CollectionUtils.isNotEmpty(models)) {
            //key:用户id, value:角色id
            Multimap<Long, Long> userRoleMap = ArrayListMultimap.create();
            for (SysUserRoleModel model : models) {
                userRoleMap.put(model.getUserId(), model.getRoleId());
            }
            Example example = new Example(SysRoleModel.class);
            //用set去重
            example.createCriteria().andIn("id", Sets.newHashSet(userRoleMap.values()));
            List<SysRoleModel> roleModels = sysRoleMapper.selectByExample(example);
            //key:角色id, value:角色实体对象
            Map<Long, SysRoleModel> roleMap = Maps.newHashMap();
            if (CollectionUtils.isNotEmpty(roleModels)) {
                for (SysRoleModel roleModel : roleModels) {
                    roleMap.put(roleModel.getId(), roleModel);
                }
            }
            Collection<Long> curRoleIds;
            SysRoleModel curRoleModel;
            //封装每个用户id对应的多个角色id数据
            for (Long userId : userIds) {
                curRoleIds = userRoleMap.get(userId);
                if (CollectionUtils.isNotEmpty(curRoleIds)) {
                    for (Long curRoleId : curRoleIds) {
                        curRoleModel = roleMap.get(curRoleId);
                        if (null != curRoleModel) {
                            resultMap.put(userId, curRoleModel);
                        }
                    }
                }
            }
        }
        return resultMap;
    }

}
