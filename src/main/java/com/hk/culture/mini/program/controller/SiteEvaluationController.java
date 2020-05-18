package com.hk.culture.mini.program.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hk.culture.mini.program.common.constant.ReturnCodeEnum;
import com.hk.culture.mini.program.dto.Result;
import com.hk.culture.mini.program.dto.query.PagesQuery;
import com.hk.culture.mini.program.entity.SiteEvaluation;
import com.hk.culture.mini.program.service.SiteEvaluationService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 标注点评价表 前端控制器
 * </p>
 *
 * @author 
 * @since 2020-05-12
 */
@RestController
@RequestMapping("/siteEvaluation")
public class SiteEvaluationController {

    @Autowired
    private SiteEvaluationService siteEvaluationService;

    @RequestMapping("/get/{id}")
    public Result<SiteEvaluation> get(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return Result.error(ReturnCodeEnum.PARAM_ERROR);
        }

        SiteEvaluation siteEvaluation = siteEvaluationService.getById(id);

        return Result.success(siteEvaluation);
    }

    @PostMapping("/list")
    public Result<Page<SiteEvaluation>> list(@RequestBody PagesQuery<SiteEvaluation> pagesQuery) {
        if (pagesQuery == null) {
            pagesQuery = new PagesQuery<>();
        }

        if (pagesQuery.getData() == null) {
            pagesQuery.setData(new SiteEvaluation());
        }

        IPage<SiteEvaluation> siteIPage = siteEvaluationService.listByCondition(pagesQuery);
        if (siteIPage == null || CollectionUtils.isEmpty(siteIPage.getRecords())) {
            return Result.success(siteIPage);
        }


        return Result.success(siteIPage);
    }

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SiteEvaluation site) {
        if (site == null) {
            return Result.error(ReturnCodeEnum.PARAM_ERROR);
        }

        return Result.success(siteEvaluationService.add(site));
    }

    @PostMapping("/remove")
    public Result remove(@NonNull @RequestParam(value = "tid") String tid,
                         @NonNull @RequestParam(value = "memberTid") String memberTid) {

        return Result.success(siteEvaluationService.deleteByTid(tid, memberTid));
    }

    @PostMapping("/update")
    public Result update(@RequestBody SiteEvaluation site) {
        if (site == null) {
            return Result.error(ReturnCodeEnum.PARAM_ERROR);
        }

        return siteEvaluationService.updateByTid(site);
    }
}

