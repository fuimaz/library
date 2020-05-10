package com.hk.culture.mini.program.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hk.culture.mini.program.common.constant.ReturnCodeEnum;
import com.hk.culture.mini.program.dto.Result;
import com.hk.culture.mini.program.dto.query.PagesQuery;
import com.hk.culture.mini.program.entity.Onlineshow;
import com.hk.culture.mini.program.service.OnlineshowService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2020-04-08
 */
@Controller
@RequestMapping("/onlineshow")
public class OnlineshowController {

    @Autowired
    private OnlineshowService onlineshowService;

    @RequestMapping("/get/{id}")
    public Result<Onlineshow> get(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return Result.error(ReturnCodeEnum.PARAM_ERROR);
        }

        return Result.success(onlineshowService.getByTid(id));
    }

    @PostMapping("/list")
    public Result<Page<Onlineshow>> list(@RequestBody PagesQuery<Onlineshow> pagesQuery) {
        if (pagesQuery == null) {
            pagesQuery = new PagesQuery<>();
        }

        if (pagesQuery.getData() == null) {
            pagesQuery.setData(new Onlineshow());
        }

        IPage<Onlineshow> activityIPage = onlineshowService.listByCondition(pagesQuery);
        if (activityIPage == null || CollectionUtils.isEmpty(activityIPage.getRecords())) {
            return Result.success(activityIPage);
        }

        return Result.success(activityIPage);
    }
}

