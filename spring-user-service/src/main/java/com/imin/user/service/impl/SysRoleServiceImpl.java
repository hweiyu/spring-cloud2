package com.imin.user.service.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.imin.infrastructure.common.constants.ShareConstants;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.enums.DataStatusEnum;
import com.imin.infrastructure.common.utils.CopyDataUtil;
import com.imin.infrastructure.common.utils.LangUtils;
import com.imin.infrastructure.common.utils.StringUtil;
import com.imin.user.dto.reqeust.SysRoleInsertReqDto;
import com.imin.user.dto.reqeust.SysRoleQueryReqDto;
import com.imin.user.dto.reqeust.SysRoleUpdateReqDto;
import com.imin.user.dto.reqeust.SysRoleUpdateStatusReqDto;
import com.imin.user.dto.response.SysRoleListResDto;
import com.imin.user.model.SysUserRoleModel;
import com.imin.user.service.SysRoleResourceService;
import com.imin.user.service.SysUserRoleService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.imin.infrastructure.common.dto.PageInfo;
import com.imin.infrastructure.common.utils.PageUtil;

import com.imin.user.mapper.SysRoleMapper;
import com.imin.user.model.SysRoleModel;
import com.imin.user.service.SysRoleService;
import com.imin.user.dto.response.SysRoleResDto;
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
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleResourceService sysRoleResourceService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

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
        return PageUtil.CopyPageList(models, SysRoleListResDto.class);
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
        return CopyDataUtil.copyList(models, SysRoleListResDto.class);
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
