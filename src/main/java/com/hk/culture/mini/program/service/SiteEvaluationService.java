package com.hk.culture.mini.program.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hk.culture.mini.program.dto.Result;
import com.hk.culture.mini.program.dto.query.PagesQuery;
import com.hk.culture.mini.program.entity.SiteEvaluation;

/**
 * <p>
 * 标注点评价表 服务类
 * </p>
 *
 * @author 
 * @since 2020-05-12
 */
public interface SiteEvaluationService extends IService<SiteEvaluation> {
    IPage<SiteEvaluation> listByCondition(PagesQuery<SiteEvaluation> pagesQuery);

    Result<Boolean> add(SiteEvaluation siteEvaluation);

    Result<Boolean> updateByTid(SiteEvaluation siteEvaluation);

    Result<Boolean> deleteByTid(String tid, String operator);
}
