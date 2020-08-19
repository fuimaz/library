package com.hk.library.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hk.library.common.constant.StateEnum;
import com.hk.library.dto.Result;
import com.hk.library.dto.query.PagesQuery;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 标注点评价表 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-05-12
 */
@Service
@Slf4j
public class SiteEvaluationServiceImpl extends ServiceImpl<SiteEvaluationMapper, SiteEvaluation> implements SiteEvaluationService {

    /**
     * 条件查询，无条件则返回全部分页数据
     * @param pagesQuery
     * @return
     */
    @Override
    public IPage<SiteEvaluation> listByCondition(PagesQuery<SiteEvaluation> pagesQuery) {
        QueryWrapper<SiteEvaluation> wrapper = new QueryWrapper();
        SiteEvaluation siteEvaluation = pagesQuery.getData();
        if (StringUtils.isNotEmpty(siteEvaluation.getSiteTid())) {
            wrapper.eq("siteTid", siteEvaluation.getSiteTid());
        }

        // 默认只搜可见的
        if (StringUtils.isEmpty(siteEvaluation.getShow())) {
            wrapper.eq("`show`", StateEnum.ENABLE.getState());
        }

        if (pagesQuery.isOrderByDesc()) {
            wrapper.orderByDesc("createTime");
        } else {
            wrapper.orderByAsc("createTime");
        }

        Page<SiteEvaluation> page = new Page<>(pagesQuery.getCurrent(), pagesQuery.getPageSize());

        IPage<SiteEvaluation> siteIPage = getBaseMapper().selectPage(page, wrapper);

        return siteIPage;
    }

    /**
     * 插入
     * @param siteEvaluation
     * @return
     */
    @Override
    public Result<Boolean> add(SiteEvaluation siteEvaluation) {

        siteEvaluation.setUpdateTime(LocalDateTime.now());
        siteEvaluation.setCreateTime(LocalDateTime.now());
        siteEvaluation.setAuditing(StateEnum.DISABLE.getState());

        return Result.result(getBaseMapper().insert(siteEvaluation) == 1);
    }

    /**
     * 根据Tid更新
     * @param siteEvaluation
     * @return
     */
    @Override
    public Result<Boolean> updateByTid(SiteEvaluation siteEvaluation) {
        siteEvaluation.setUpdateTime(LocalDateTime.now());

        // 不能修改
        siteEvaluation.setCreateTime(null);

        return Result.result(getBaseMapper().updateById(siteEvaluation) == 1);
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
