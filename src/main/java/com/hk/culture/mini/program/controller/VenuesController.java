package com.hk.culture.mini.program.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hk.culture.mini.program.common.utils.BeanUtil;
import com.hk.culture.mini.program.dto.Result;
import com.hk.culture.mini.program.dto.query.PagesQuery;
import com.hk.culture.mini.program.dto.vo.VenuesVO;
import com.hk.culture.mini.program.entity.Venues;
import com.hk.culture.mini.program.service.VenuesService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Result<Page<VenuesVO>> list(@RequestBody PagesQuery pagesQuery) {
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
}

