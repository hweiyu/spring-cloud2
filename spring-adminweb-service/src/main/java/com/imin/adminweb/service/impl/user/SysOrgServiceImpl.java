package com.imin.adminweb.service.impl.user;

import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.imin.adminweb.exception.UserException;
import com.imin.adminweb.mapper.user.SysOrgMapper;
import com.imin.adminweb.model.user.SysOrgModel;
import com.imin.adminweb.service.user.SysOrgService;
import com.imin.infrastructure.common.dto.IdDto;
import com.imin.infrastructure.common.utils.CopyDataUtil;
import com.imin.infrastructure.common.utils.LangUtils;
import com.imin.infrastructure.common.utils.MapUtil;
import com.imin.infrastructure.common.utils.StringUtil;
import com.imin.user.dto.reqeust.SysOrgInsertReqDto;
import com.imin.user.dto.reqeust.SysOrgQueryReqDto;
import com.imin.user.dto.reqeust.SysOrgUpdateReqDto;
import com.imin.user.dto.response.SysOrgListResDto;
import com.imin.user.dto.response.SysOrgResDto;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 * @author huangweiyu
 * @version V1.0
 * @Title 描述
 * @Description 描述
 * @date 2019/5/24 11:48
 **/
@Service
public class SysOrgServiceImpl implements SysOrgService {

    @Autowired
    private SysOrgMapper sysOrgMapper;

    /**
     * 列表
     *
     * @param reqDto
     */
    @Override
    public List<SysOrgListResDto> select(SysOrgQueryReqDto reqDto) {
        List<SysOrgListResDto> result = Lists.newArrayList();
        List<SysOrgModel> models = sysOrgMapper.select(SysOrgModel.builder().orgType(reqDto.getOrgType()).build());
        if (CollectionUtils.isNotEmpty(models)) {
            Multimap<Long, SysOrgModel> multimap = MapUtil.listToMultimap("parentId", models);
            for (SysOrgModel model : models) {
                if (isRoot(model)) {
                    SysOrgListResDto dto = CopyDataUtil.copyObject(model, SysOrgListResDto.class);
                    setChildren(dto, multimap);
                    result.add(dto);
                }
            }
        }
        sort(result);
        return result;
    }

    private boolean isRoot(SysOrgModel model) {
        return null != model && model.getParentId() <= 0;
    }

    private void setChildren(SysOrgListResDto dto, Multimap<Long, SysOrgModel> multimap) {
        List<SysOrgModel> models = (List<SysOrgModel>) multimap.get(dto.getId());
        List<SysOrgListResDto> children = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(models)) {
            for (SysOrgModel model : models) {
                SysOrgListResDto child = CopyDataUtil.copyObject(model, SysOrgListResDto.class);
                setChildren(child, multimap);
                children.add(child);
            }
        }
        sort(children);
        dto.setChildren(children);
    }

    private void sort(List<SysOrgListResDto> dtos) {
        if (CollectionUtils.isNotEmpty(dtos)) {
            dtos.sort(new Comparator<SysOrgListResDto>() {
                @Override
                public int compare(SysOrgListResDto o1, SysOrgListResDto o2) {
                    return o1.getSortBy().compareTo(o2.getSortBy());
                }
            });
        }
    }

    /**
     * 查询
     *
     * @param id
     */
    @Override
    public SysOrgResDto get(Long id) {
        SysOrgModel model = sysOrgMapper.selectByPrimaryKey(id);
        return CopyDataUtil.copyObject(model, SysOrgResDto.class);
    }

    /**
     * 添加
     *
     * @param reqDto
     */
    @Override
    public IdDto insert(SysOrgInsertReqDto reqDto) {
        SysOrgModel model = CopyDataUtil.copyObject(reqDto, SysOrgModel.class);
        createOrgQueue(reqDto.getOrgType(), model);
        sysOrgMapper.insert(model);
        return new IdDto(model.getId());
    }

    private void createOrgQueue(Integer orgType, SysOrgModel model) {
        String queuePrefix = "";
        if (model.getParentId() > 0) {
            SysOrgModel parent = sysOrgMapper.selectByPrimaryKey(model.getParentId());
            if (!orgType.equals(parent.getOrgType())) {
                throw new UserException("组织类型与父节点的不一致");
            }
            queuePrefix = parent.getOrgQueue();
            model.setParentIds(parent.getParentIds() + "," + parent.getId());
        } else {
            model.setParentIds("0");
        }
        String maxQueue = sysOrgMapper.getMaxQueue(model.getParentId());
        String queue;
        if (!StringUtil.isEmptyOrNull(maxQueue)) {
            String[] arr = maxQueue.split("[|]");
            String raw = "0000" + (Integer.valueOf(arr[arr.length - 1]) + 1) ;
            queue = queuePrefix + "|" + raw.substring(raw.length() - 4);
        } else {
            queue = queuePrefix + "|0001";
        }
        model.setOrgQueue(queue);
    }

    public static void main(String[] args) {
        String d = "123456789";
        System.out.println(d.substring(d.length() - 4));
    }

    /**
     * 修改
     *
     * @param reqDto
     */
    @Override
    public IdDto update(SysOrgUpdateReqDto reqDto) {
        SysOrgModel model = CopyDataUtil.copyObject(reqDto, SysOrgModel.class);
        sysOrgMapper.updateByPrimaryKeySelective(model);
        return new IdDto(model.getId());
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public IdDto delete(Long id) {
        SysOrgModel model = sysOrgMapper.selectByPrimaryKey(id);
        if (null != model) {
            Example example = new Example(SysOrgModel.class);
            example.createCriteria().andLike("orgQueue", model.getOrgQueue() + "%");
            List<SysOrgModel> models = sysOrgMapper.selectByExample(example);
            if (CollectionUtils.isNotEmpty(models)) {
                List<Long> ids = LangUtils.transform(models, new Function<SysOrgModel, Long>() {
                    @Override
                    public Long apply(SysOrgModel sysOrgModel) {
                        return sysOrgModel.getId();
                    }
                });
                Example deleteExample = new Example(SysOrgModel.class);
                deleteExample.createCriteria().andIn("id", ids);
                sysOrgMapper.deleteByExample(deleteExample);
            }
        }
        return new IdDto(id);
    }
}
