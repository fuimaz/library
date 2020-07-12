package com.hk.culture.mini.program.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hk.culture.mini.program.dto.Result;
import com.hk.culture.mini.program.dto.query.PagesQuery;
import com.hk.culture.mini.program.dto.query.VenuesBookQuery;
import com.hk.culture.mini.program.entity.Venues;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2020-04-08
 */
public interface VenuesService extends IService<Venues> {


    IPage<Venues> listByCondition(PagesQuery<Venues> pagesQuery);

    Result book(VenuesBookQuery venuesBookQuery);

    /**
     * 获取场馆预约状态
     *
     */
    List<JSONObject> listBookState(String tid, String date, List<String> intervals);

    List<Venues> listCanBookByDate(String date);

}
