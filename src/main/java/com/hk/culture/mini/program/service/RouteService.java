package com.hk.culture.mini.program.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hk.culture.mini.program.dto.Result;
import com.hk.culture.mini.program.dto.query.PagesQuery;
import com.hk.culture.mini.program.dto.vo.RouteVO;
import com.hk.culture.mini.program.entity.Route;

/**
 * <p>
 * 路线图表 服务类
 * </p>
 *
 * @author 
 * @since 2020-05-12
 */
public interface RouteService extends IService<Route> {

    Result<RouteVO> getByTid(String tid);

    IPage<RouteVO> listByCondition(PagesQuery<RouteVO> pagesQuery);

    Result<Boolean> add(RouteVO routeVO);

    Result<Boolean> updateByTid(RouteVO routeVO);

    Result<Boolean> deleteByTid(String tid, String operator);
}
