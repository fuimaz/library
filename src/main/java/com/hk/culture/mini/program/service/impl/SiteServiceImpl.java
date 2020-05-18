package com.hk.culture.mini.program.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hk.culture.mini.program.common.constant.StateEnum;
import com.hk.culture.mini.program.dto.Result;
import com.hk.culture.mini.program.dto.query.PagesQuery;
import com.hk.culture.mini.program.entity.Site;
import com.hk.culture.mini.program.mapping.SiteMapper;
import com.hk.culture.mini.program.service.SiteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 标注点表 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-05-12
 */
@Service
@Slf4j
public class SiteServiceImpl extends ServiceImpl<SiteMapper, Site> implements SiteService {

    /**
     * 条件查询，无条件则返回全部分页数据
     * @param pagesQuery
     * @return
     */
    @Override
    public IPage<Site> listByCondition(PagesQuery<Site> pagesQuery) {
        QueryWrapper<Site> wrapper = new QueryWrapper();
        Site site = pagesQuery.getData();
        if (StringUtils.isNotEmpty(site.getName())) {
            wrapper.like("name", site.getName() + "%");
        }

        // 默认只搜可见的
        if (StringUtils.isEmpty(site.getShow())) {
            wrapper.eq("`show`", StateEnum.ENABLE.getState());
        }

        wrapper.orderByDesc("`order`");

        Page<Site> page = new Page<>(pagesQuery.getCurrent(), pagesQuery.getPageSize());

        IPage<Site> siteIPage = getBaseMapper().selectPage(page, wrapper);

        return siteIPage;
    }

    /**
     * 插入
     * @param site
     * @return
     */
    @Override
    public Result<Boolean> add(Site site) {

        site.setUpdateTime(LocalDateTime.now());
        site.setCreateTime(LocalDateTime.now());

        return Result.result(getBaseMapper().insert(site) == 1);
    }

    /**
     * 根据Tid更新
     * @param site
     * @return
     */
    @Override
    public Result<Boolean> updateByTid(Site site) {
        site.setUpdateTime(LocalDateTime.now());

        // 不能修改
        site.setCreateTime(null);

        return Result.result(getBaseMapper().updateById(site) == 1);
    }

    /**
     * 根据Tid删除
     * @param tid
     * @param tid
     * @return
     */
    @Override
    public Result<Boolean> deleteByTid(String tid, String operator) {

        log.warn("{} delete tid={} record", operator);
        return Result.result(getBaseMapper().deleteById(tid) == 1);
    }
}
