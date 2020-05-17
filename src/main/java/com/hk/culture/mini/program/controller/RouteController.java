package com.hk.culture.mini.program.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hk.culture.mini.program.common.constant.ReturnCodeEnum;
import com.hk.culture.mini.program.dto.Result;
import com.hk.culture.mini.program.dto.query.PagesQuery;
import com.hk.culture.mini.program.dto.vo.RouteVO;
import com.hk.culture.mini.program.service.RouteService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 路线图表 前端控制器
 * </p>
 *
 * @author 
 * @since 2020-05-12
 */
@Controller
@RequestMapping("/route")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @RequestMapping("/get/{id}")
    public Result<RouteVO> get(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return Result.error(ReturnCodeEnum.PARAM_ERROR);
        }

        return routeService.getByTid(id);
    }

    @PostMapping("/list")
    public Result<Page<RouteVO>> list(@RequestBody PagesQuery<RouteVO> pagesQuery) {
        if (pagesQuery == null) {
            pagesQuery = new PagesQuery<>();
        }

        if (pagesQuery.getData() == null) {
            pagesQuery.setData(new RouteVO());
        }

        IPage<RouteVO> siteIPage = routeService.listByCondition(pagesQuery);
        if (siteIPage == null || CollectionUtils.isEmpty(siteIPage.getRecords())) {
            return Result.success(siteIPage);
        }


        return Result.success(siteIPage);
    }

    @PostMapping("/add")
    public Result<Boolean> like(@RequestBody RouteVO routeVO) {
        if (routeVO == null) {
            return Result.error(ReturnCodeEnum.PARAM_ERROR);
        }

        return Result.success(routeService.add(routeVO));
    }

    @PostMapping("/remove")
    public Result cancel(@NonNull @RequestParam(value = "tid") String tid,
                         @NonNull @RequestParam(value = "memberTid") String memberTid) {

        return Result.success(routeService.deleteByTid(tid, memberTid));
    }

    @PostMapping("/update")
    public Result update(@RequestBody RouteVO routeVO) {
        if (routeVO == null) {
            return Result.error(ReturnCodeEnum.PARAM_ERROR);
        }

        return routeService.updateByTid(routeVO);
    }
}

