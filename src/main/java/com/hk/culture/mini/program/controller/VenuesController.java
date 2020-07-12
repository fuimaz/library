package com.hk.culture.mini.program.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hk.culture.mini.program.common.constant.ReturnCodeEnum;
import com.hk.culture.mini.program.common.utils.BeanUtil;
import com.hk.culture.mini.program.dto.Result;
import com.hk.culture.mini.program.dto.query.PagesQuery;
import com.hk.culture.mini.program.dto.query.VenuesBookQuery;
import com.hk.culture.mini.program.dto.vo.VenuesVO;
import com.hk.culture.mini.program.entity.Venues;
import com.hk.culture.mini.program.service.VenuesService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2020-04-08
 */
@RestController
@RequestMapping("/venues")
public class VenuesController {

    @Autowired
    private VenuesService venuesService;


    @PostMapping("/list")
    public Result<Page<VenuesVO>> list(@RequestBody PagesQuery<VenuesVO> pagesQuery) {
        if (pagesQuery == null) {
            pagesQuery = new PagesQuery<>();
        }

        if (pagesQuery.getData() == null) {
            pagesQuery.setData(new VenuesVO());
        }

        Page<Venues> page = new Page<>(pagesQuery.getCurrent(), pagesQuery.getPageSize());

        IPage<Venues> venuesIPage = venuesService.page(page);
        if (venuesIPage == null || CollectionUtils.isEmpty(venuesIPage.getRecords())) {
            return Result.success(venuesIPage);
        }

        IPage<VenuesVO> venuesVOIPage = venuesIPage.convert(activity -> BeanUtil.convertToBean(activity, VenuesVO.class));

        return Result.success(venuesVOIPage);
    }

    @RequestMapping("/get/{id}")
    public Result<VenuesVO> get(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return Result.error(ReturnCodeEnum.PARAM_ERROR);
        }

        Venues venues = venuesService.getById(id);

        return Result.success(BeanUtil.convertToBean(venues, VenuesVO.class));
    }

    @PostMapping("/book")
    public Result book(@RequestBody VenuesBookQuery venuesBookQuery) {
        if (venuesBookQuery == null) {
            return Result.error(ReturnCodeEnum.PARAM_ERROR);
        }

        return venuesService.book(venuesBookQuery);
    }

    @PostMapping("/listBookState")
    public Result<List<JSONObject>> listBookState(@RequestParam("tid") String tid,
                                                  @RequestParam("bookDate") String bookDate,
                                                  @RequestParam("intervals") List<String> intervals) {
        if (StringUtils.isEmpty(bookDate) || StringUtils.isEmpty(tid)) {
            return Result.error(ReturnCodeEnum.PARAM_ERROR);
        }

        return Result.success(venuesService.listBookState(tid, bookDate, intervals));
    }

    @PostMapping("/listValidByDate")
    public Result<List<JSONObject>> listValidByDate(@RequestParam("bookDate") String bookDate) {
        if (StringUtils.isEmpty(bookDate)) {
            return Result.error(ReturnCodeEnum.PARAM_ERROR);
        }

        return Result.success(venuesService.listCanBookByDate(bookDate));
    }
}

