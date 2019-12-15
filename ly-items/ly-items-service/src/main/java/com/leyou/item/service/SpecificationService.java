package com.leyou.item.service;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class SpecificationService {

    @Autowired
    private SpecParamMapper specParamMapper;

    @Autowired
    private SpecGroupMapper specGroupMapper;

    public List<SpecParam> querySpecParams(Long gid, Long cid, Boolean searching, Boolean generic) {
        SpecParam param = new SpecParam();
        param.setGroupId(gid);
        param.setCid(cid);
        param.setSearching(searching);
        param.setGeneric(generic);
        return this.specParamMapper.select(param);
    }

    public List<SpecGroup> querySpecsByCid(Long cid) {
        // 查询规格组
        List<SpecGroup> groups = this.querySpecGroups(cid);
        SpecParam param = new SpecParam();
        groups.forEach(g -> {
            // 查询组内参数
            g.setParams(this.querySpecParams(g.getId(), null, null, null));
        });
        return groups;
    }

    public List<SpecGroup> querySpecGroups(Long cid) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        List<SpecGroup> specGroupList = specGroupMapper.select(specGroup);
        return specGroupList;
    }

    public List<SpecGroup> querySpecGroupByCid(Long cid) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        List<SpecGroup> specGroupList = specGroupMapper.select(specGroup);
        if (CollectionUtils.isEmpty(specGroupList)) {
            throw new LyException(ExceptionEnum.SPEC_GROUP_NOT_FOUND);
        }
        return specGroupList;
    }
}
