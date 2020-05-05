package com.hk.culture.mini.program.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hk.culture.mini.program.common.utils.BeanUtil;
import com.hk.culture.mini.program.dto.Result;
import com.hk.culture.mini.program.dto.query.NewsQuery;
import com.hk.culture.mini.program.dto.query.PagesQuery;
import com.hk.culture.mini.program.dto.vo.NewsVO;
import com.hk.culture.mini.program.entity.News;
import com.hk.culture.mini.program.service.NewsService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 新闻资讯表 前端控制器
 * </p>
 *
 * @author 
 * @since 2020-05-02
 */
@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @RequestMapping("/getOne")
    public Result<News> get(@NonNull @RequestParam(value = "tid") String tid,
                            @NonNull @RequestParam(value = "memberTid") String memberTid) {

        return newsService.getByTidAndMemberTid(tid, memberTid);
    }

    @PostMapping("/list")
    public Result<Page<NewsVO>> list(@RequestBody PagesQuery<NewsQuery> pagesQuery) {
        if (pagesQuery == null) {
            pagesQuery = new PagesQuery<>();
        }

        if (pagesQuery.getData() == null) {
            pagesQuery.setData(new NewsQuery());
        }

        IPage<News> newsIPage = newsService.listByCondition(pagesQuery);
        if (newsIPage == null || CollectionUtils.isEmpty(newsIPage.getRecords())) {
            return Result.success(newsIPage);
        }

        IPage<NewsVO> newsVOIPage = newsIPage.convert(news -> BeanUtil.convertToBean(news, NewsVO.class));

        return Result.success(newsVOIPage);
    }

}

