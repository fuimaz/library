package com.hk.culture.mini.program.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hk.culture.mini.program.common.constant.ReturnCodeEnum;
import com.hk.culture.mini.program.dto.Result;
import com.hk.culture.mini.program.dto.query.PagesQuery;
import com.hk.culture.mini.program.entity.Site;
import com.hk.culture.mini.program.service.SiteService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 标注点表 前端控制器
 * </p>
 *
 * @author 
 * @since 2020-05-12
 */
@Controller
@RequestMapping("/site")
public class SiteController {
    @Autowired
    private SiteService siteService;

    @RequestMapping("/get/{id}")
    public Result<Site> get(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return Result.error(ReturnCodeEnum.PARAM_ERROR);
        }

        Site site = siteService.getById(id);

        return Result.success(site);
    }

    @PostMapping("/list")
    public Result<Page<Site>> list(@RequestBody PagesQuery<Site> pagesQuery) {
        if (pagesQuery == null) {
            pagesQuery = new PagesQuery<>();
        }

        if (pagesQuery.getData() == null) {
            pagesQuery.setData(new Site());
        }

        IPage<Site> siteIPage = siteService.listByCondition(pagesQuery);
        if (siteIPage == null || CollectionUtils.isEmpty(siteIPage.getRecords())) {
            return Result.success(siteIPage);
        }


        return Result.success(siteIPage);
    }

    @PostMapping("/add")
    public Result<Boolean> like(@RequestBody Site site) {
        if (site == null) {
            return Result.error(ReturnCodeEnum.PARAM_ERROR);
        }

        return Result.success(siteService.add(site));
    }

    @PostMapping("/remove")
    public Result cancel(@NonNull @RequestParam(value = "tid") String tid,
                         @NonNull @RequestParam(value = "memberTid") String memberTid) {

        return Result.success(siteService.deleteByTid(tid, memberTid));
    }


    @PostMapping("/update")
    public Result update(@RequestBody Site site) {
        if (site == null) {
            return Result.error(ReturnCodeEnum.PARAM_ERROR);
        }

        return siteService.updateByTid(site);
    }
}

