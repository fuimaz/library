package com.hk.culture.mini.program.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hk.culture.mini.program.dto.Result;
import com.hk.culture.mini.program.dto.query.NewsQuery;
import com.hk.culture.mini.program.dto.query.PagesQuery;
import com.hk.culture.mini.program.entity.News;

/**
 * <p>
 * 新闻资讯表 服务类
 * </p>
 *
 * @author 
 * @since 2020-05-02
 */
public interface NewsService extends IService<News> {

    IPage<News> listByCondition(PagesQuery<NewsQuery> pagesQuery);

    Result getByTidAndMemberTid(String tid, String memberTid);
}
