package com.hk.culture.mini.program.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hk.culture.mini.program.dto.query.PagesQuery;
import com.hk.culture.mini.program.entity.MyCollection;
import com.hk.culture.mini.program.mapping.MyCollectionMapper;
import com.hk.culture.mini.program.service.MyCollectionService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2020-05-02
 */
@Service
public class MyCollectionServiceImpl extends ServiceImpl<MyCollectionMapper, MyCollection> implements MyCollectionService {

    /**
     * 条件查询，无条件则返回全部分页数据
     * @param pagesQuery
     * @return
     */
    @Override
    public IPage<MyCollection> listByCondition(PagesQuery<MyCollection> pagesQuery) {
        QueryWrapper<MyCollection> wrapper = new QueryWrapper();
        MyCollection activity = pagesQuery.getData();
        if (StringUtils.isNotEmpty(activity.getType())) {
            wrapper.eq("type", activity.getType());
        }

        wrapper.orderByDesc("createTime");

        Page<MyCollection> page = new Page<>(pagesQuery.getCurrent(), pagesQuery.getPageSize());

        IPage<MyCollection> activityIPage = getBaseMapper().selectPage(page, wrapper);

        return activityIPage;
    }

    /**
     * 通过点赞对象和点赞的会员查找点赞记录
     * @param targetTid
     * @param memberTid
     * @return
     */
    public MyCollection getByTargetIdAndMemberId(String targetTid, String memberTid) {
        QueryWrapper<MyCollection> wrapper = new QueryWrapper();

        wrapper.eq("targetTid", targetTid);
        wrapper.eq("memberTid", memberTid);

        return getBaseMapper().selectOne(wrapper);
    }

    /**
     * 收藏
     * @param type
     * @param targetTid
     * @param memberTid
     * @return
     */
    public boolean favorite(String type, String targetTid, String memberTid) {
        MyCollection collection = new MyCollection();
        collection.setCreateTime(LocalDateTime.now());
        collection.setUpdateTime(LocalDateTime.now());
        collection.setMemberTid(memberTid);
        collection.setTargetTid(targetTid);
        collection.setType(type);
        return getBaseMapper().insert(collection) == 1;
    }

    /**
     * 取消
     * @param targetTid
     * @param memberTid
     * @return
     */
    public boolean cancel(String targetTid, String memberTid) {
        QueryWrapper<MyCollection> wrapper = new QueryWrapper();

        wrapper.eq("targetTid", targetTid);
        wrapper.eq("memberTid", memberTid);

        return getBaseMapper().delete(wrapper) == 1;
    }
}
