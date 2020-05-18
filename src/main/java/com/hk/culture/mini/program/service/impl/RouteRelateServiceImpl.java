package com.hk.culture.mini.program.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hk.culture.mini.program.common.constant.ReturnCodeEnum;
import com.hk.culture.mini.program.dto.Result;
import com.hk.culture.mini.program.entity.RouteRelate;
import com.hk.culture.mini.program.mapping.RouteRelateMapper;
import com.hk.culture.mini.program.service.RouteRelateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 路线标注点关联表 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-05-12
 */
@Service
@Slf4j
public class RouteRelateServiceImpl extends ServiceImpl<RouteRelateMapper, RouteRelate> implements RouteRelateService {

    /**
     * 根据routeTid查询
     * @param routeTid
     * @return
     */
    @Override
    public List<RouteRelate> listByRouteTid(String routeTid) {
        QueryWrapper<RouteRelate> wrapper = new QueryWrapper();

        wrapper.eq("routeTid", routeTid);

        wrapper.orderByAsc("`order`");

        List<RouteRelate> routeRelates = getBaseMapper().selectList(wrapper);

        return routeRelates;
    }

    @Override
    public Result<Boolean> deleteByRouteTid(String routeTid) {
        if (StringUtils.isEmpty(routeTid)) {
            return Result.error(ReturnCodeEnum.PARAM_ERROR, "删除关联坐标点失败");
        }

        QueryWrapper<RouteRelate> wrapper = new QueryWrapper();
        wrapper.eq("routeTid", routeTid);
        getBaseMapper().delete(wrapper);
        return Result.result(getBaseMapper().delete(wrapper) > 0);
    }

    /**
     * 插入
     * @param routeRelate
     * @return
     */
    @Override
    public Result<Boolean> add(RouteRelate routeRelate) {

        routeRelate.setUpdateTime(LocalDateTime.now());
        routeRelate.setCreateTime(LocalDateTime.now());

        return Result.result(getBaseMapper().insert(routeRelate) == 1);
    }

    /**
     * 根据Tid更新
     * @param routeRelate
     * @return
     */
    @Override
    public Result<Boolean> updateByTid(RouteRelate routeRelate) {
        routeRelate.setUpdateTime(LocalDateTime.now());

        // 不能修改
        routeRelate.setCreateTime(null);

        return Result.result(getBaseMapper().updateById(routeRelate) == 1);
    }

    /**
     * 根据Tid删除
     * @param tid
     * @param tid
     * @return
     */
    @Override
    public Result<Boolean> deleteByTid(String tid, String operator) {

        log.warn("{} delete tid={} record", operator);
        return Result.result(getBaseMapper().deleteById(tid) == 1);
    }

}
