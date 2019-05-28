package com.imin.user.service.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.imin.infrastructure.common.constants.ShareConstants;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.enums.DataStatusEnum;
import com.imin.infrastructure.common.utils.CopyDataUtil;
import com.imin.infrastructure.common.utils.LangUtils;
import com.imin.infrastructure.common.utils.StringUtil;
import com.imin.user.dto.reqeust.SysResourceInsertReqDto;
import com.imin.user.dto.reqeust.SysResourceQueryReqDto;
import com.imin.user.dto.reqeust.SysResourceUpdateReqDto;
import com.imin.user.dto.response.SysResourceListResDto;
import com.imin.user.exception.UserException;
import com.imin.user.service.SysRoleResourceService;
import com.imin.user.service.SysRoleService;
import com.imin.user.service.SysUserRoleService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imin.user.mapper.SysResourceMapper;
import com.imin.user.model.SysResourceModel;
import com.imin.user.service.SysResourceService;
import com.imin.user.dto.response.SysResourceResDto;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title: 描述
 * @Description: 系统菜单权限资源服务
 * @date 2018-11-27 18:19:55
 **/
@Service
public class SysResourceServiceImpl implements SysResourceService {

    @Autowired
    private SysResourceMapper sysResourceMapper;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysRoleResourceService sysRoleResourceService;

    @Autowired
    private SysRoleService sysRoleService;

    /**
    * 列表
    */
    @Override
    public List<SysResourceListResDto> select(SysResourceQueryReqDto reqDto) {
        List<SysResourceModel> models = sysResourceMapper.select(
                SysResourceModel.builder().platformType(reqDto.getPlatformType()).build());
        List<SysResourceListResDto> tempList = wrapTree(models);
        List<SysResourceListResDto> result = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(tempList)) {
            for (SysResourceListResDto cur : tempList) {
                getOriginList(cur, result);
            }
        }
        return result;
    }

    private void getOriginList(SysResourceListResDto cur, List<SysResourceListResDto> result) {
        if (null != cur) {
            result.add(cur);
            List<SysResourceListResDto> children = cur.getChildren();
            if (CollectionUtils.isNotEmpty(children)) {
                for (SysResourceListResDto child : children) {
                    getOriginList(child, result);
                }
            }
            cur.setChildren(null);
        }
    }

    private List<SysResourceListResDto> wrapTree(List<SysResourceModel> models) {
        List<SysResourceListResDto> result = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(models)) {
            Multimap<Long, SysResourceModel> resourceMap = getResourceMap(models);
            int serialNum = 1;
            for (SysResourceModel model : models) {
                if (isRootResource(model)) {
                    SysResourceListResDto curDto = CopyDataUtil.copyObject(model, SysResourceListResDto.class);
                    curDto.setSerialNum(String.valueOf(serialNum++));
                    setChildrenResource(curDto, resourceMap);
                    result.add(curDto);
                }
            }
        }
        return result;
    }

    /**
     * 通过用户id查资源列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysResourceListResDto> listByUserId(Long userId) {
        List<SysResourceModel> models = listModelsByUserId(userId);
        return wrapTree(models);
    }

    private boolean isRootResource(SysResourceModel model) {
        return model.getParentId() <= 0;
    }

    private void setChildrenResource(SysResourceListResDto curDto, Multimap<Long, SysResourceModel> resourceMap) {
        List<SysResourceModel> originChildren = (List<SysResourceModel>) resourceMap.get(curDto.getId());
        if (CollectionUtils.isNotEmpty(originChildren)) {
            List<SysResourceListResDto> children = Lists.newArrayList();
            int serialNum = 1;
            for (SysResourceModel originChild : originChildren) {
                SysResourceListResDto child = CopyDataUtil.copyObject(originChild, SysResourceListResDto.class);
                child.setSerialNum(curDto.getSerialNum() + "." + serialNum++);
                setChildrenResource(child, resourceMap);
                children.add(child);
            }
            curDto.setChildren(children);
        }
    }

    /**
    * 查询
    */
    @Override
    public SysResourceResDto get(Long id) {
        SysResourceModel model = getById(id);
        return CopyDataUtil.copyObject(model, SysResourceResDto.class);
    }

    /**
    * 添加
    */
    @Override
    public IdDto insert(SysResourceInsertReqDto reqDto) {
        SysResourceModel insertObj = CopyDataUtil.copyObject(reqDto, SysResourceModel.class);
        setResourceCode(insertObj);
        sysResourceMapper.insertSelective(insertObj);
        return new IdDto(insertObj.getId());
    }

    private void setResourceCode(SysResourceModel model) {
        model.setResourceCode(UUID.randomUUID().toString().replace("-", ""));
    }

    /**
    * 修改
    */
    @Override
    public IdDto update(SysResourceUpdateReqDto reqDto) {
        SysResourceModel updateObj = CopyDataUtil.copyObject(reqDto, SysResourceModel.class);
        checkStatus(updateObj);
        sysResourceMapper.updateByPrimaryKeySelective(updateObj);
        return new IdDto(reqDto.getId());
    }

    /**
     * 修改状态时，校验是否能修改
     * @param model
     */
    private void checkStatus(SysResourceModel model) {
        if (null == model.getDataStatus()) {
            return;
        }
        SysResourceModel origin = sysResourceMapper.selectOne(SysResourceModel.builder().id(model.getId()).build());
        //状态不变，直接返回
        if (origin.getDataStatus().equals(model.getDataStatus())) {
            return;
        }
        //如果修改为启用状态
        if (DataStatusEnum.ENABLE.isSame(model.getDataStatus())) {
            if (origin.getParentId() > 0) {
                SysResourceModel parent = getById(origin.getParentId());
                //判断父节点的状态是否为忆禁用，如果禁用，请先启用父节点
                if (null != parent && DataStatusEnum.FORBIDDEN.isSame(parent.getDataStatus())) {
                    throw new UserException("the parent node is forbidden");
                }
            }
        }
        //如果修改状态为禁用
        else if (DataStatusEnum.FORBIDDEN.isSame(model.getDataStatus())) {
            List<SysResourceModel> children = listChildren(model.getId());
            if (CollectionUtils.isNotEmpty(children)) {
                for (SysResourceModel child : children) {
                    //判断是否有启用状态的子节点，如果有，请先禁用子节点
                    if (DataStatusEnum.ENABLE.isSame(child.getDataStatus())) {
                        throw new UserException("the child node is enable");
                    }
                }
            }
        }
        //修改状态为已删除
        else if (DataStatusEnum.DELETED.isSame(model.getDataStatus())) {
            List<SysResourceModel> children = listChildren(model.getId());
            //如果存在子节点，请先删除子节点
            if (CollectionUtils.isNotEmpty(children)) {
                throw new UserException("has children nodes");
            }
        }
    }

    private SysResourceModel getById(Long id) {
        Example example = new Example(SysResourceModel.class);
        example.createCriteria()
                .andEqualTo("id", id)
                .andNotEqualTo("dataStatus", DataStatusEnum.DELETED.getStatus());
        List<SysResourceModel> models = sysResourceMapper.selectByExample(example);
        return CollectionUtils.isNotEmpty(models) ? models.get(0) : null;
    }

    private List<SysResourceModel> listChildren(Long parentId) {
        Example example = new Example(SysResourceModel.class);
        example.createCriteria()
                .andEqualTo("parentId", parentId)
                .andNotEqualTo("dataStatus", DataStatusEnum.DELETED.getStatus());
        return sysResourceMapper.selectByExample(example);
    }

    /**
     * 封装资源列表
     * key:父节点id, value:资源信息
     *
     * @param models
     * @return
     */
    @Override
    public Multimap<Long, SysResourceModel> getResourceMap(List<SysResourceModel> models) {
        Multimap<Long, SysResourceModel> resourceMap = ArrayListMultimap.create();
        for (SysResourceModel model : models) {
            resourceMap.put(model.getParentId(), model);
        }
        return resourceMap;
    }

    /**
     * 通过用户id和平台类型查资源列表
     * @param userId
     * @return
     */
    @Override
    public List<SysResourceModel> listModelsByUserId(Long userId) {
        Example example = new Example(SysResourceModel.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotEqualTo("dataStatus", DataStatusEnum.DELETED.getStatus());
        if (!ShareConstants.SUPPER_ADMIN_ID.equals(userId)) {
            List<Long> roleIds = sysUserRoleService.listByUserId(userId);
            if (CollectionUtils.isEmpty(roleIds)) {
                return Lists.newArrayList();
            }
            List<Long> resourceIds = sysRoleResourceService.listByRoleIds(roleIds);
            if (CollectionUtils.isEmpty(resourceIds)) {
                return Lists.newArrayList();
            }
            criteria.andIn("id", resourceIds);
        }
        return sysResourceMapper.selectByExample(example);
    }

    /**
     * 通过用户id和平台类型查可用状态的资源列表
     *
     * @param userId
     * @param platformType
     * @return
     */
    @Override
    public List<SysResourceModel> listEnableByUserIdAndPlatformType(Long userId, Integer platformType) {
        Example example = new Example(SysResourceModel.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("dataStatus", DataStatusEnum.ENABLE.getStatus())
                .andEqualTo("platformType", platformType);
        if (!ShareConstants.SUPPER_ADMIN_ID.equals(userId)) {
            List<Long> roleIds = sysUserRoleService.listByUserId(userId);
            if (CollectionUtils.isEmpty(roleIds)) {
                return Lists.newArrayList();
            }
            List<Long> filterRoleIds = sysRoleService.filterEnableIds(roleIds);
            List<Long> resourceIds = sysRoleResourceService.listByRoleIds(filterRoleIds);
            if (CollectionUtils.isEmpty(resourceIds)) {
                return Lists.newArrayList();
            }
            criteria.andIn("id", resourceIds);
        }
        return sysResourceMapper.selectByExample(example);
    }

}
