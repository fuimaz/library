package com.hk.culture.mini.program.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hk.culture.mini.program.dto.Result;
import com.hk.culture.mini.program.dto.query.PagesQuery;
import com.hk.culture.mini.program.entity.Site;

/**
 * <p>
 * 标注点表 服务类
 * </p>
 *
 * @author 
 * @since 2020-05-12
 */
public interface SiteService extends IService<Site> {

    IPage<Site> listByCondition(PagesQuery<Site> pagesQuery);

    Result<Boolean> add(Site site);

    Result<Boolean> updateByTid(Site site);

    Result<Boolean> deleteByTid(String tid, String operator);

}
