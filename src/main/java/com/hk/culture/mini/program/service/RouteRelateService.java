package com.hk.culture.mini.program.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hk.culture.mini.program.dto.Result;
import com.hk.culture.mini.program.entity.RouteRelate;

import java.util.List;

/**
 * <p>
 * 路线标注点关联表 服务类
 * </p>
 *
 * @author 
 * @since 2020-05-12
 */
public interface RouteRelateService extends IService<RouteRelate> {

    List<RouteRelate> listByRouteTid(String routeTid);

    Result<Boolean> deleteByRouteTid(String routeTid);

    Result<Boolean> add(RouteRelate routeRelate);

    Result<Boolean> updateByTid(RouteRelate routeRelate);

    Result<Boolean> deleteByTid(String tid, String operator);
}
