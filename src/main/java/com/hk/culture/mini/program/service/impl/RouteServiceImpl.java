package com.hk.culture.mini.program.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hk.culture.mini.program.common.constant.ReturnCodeEnum;
import com.hk.culture.mini.program.common.constant.StateEnum;
import com.hk.culture.mini.program.common.utils.BeanUtil;
import com.hk.culture.mini.program.dto.Result;
import com.hk.culture.mini.program.dto.query.PagesQuery;
import com.hk.culture.mini.program.dto.vo.RouteSiteVO;
import com.hk.culture.mini.program.dto.vo.RouteVO;
import com.hk.culture.mini.program.entity.Route;
import com.hk.culture.mini.program.entity.RouteRelate;
import com.hk.culture.mini.program.entity.Site;
import com.hk.culture.mini.program.mapping.RouteMapper;
import com.hk.culture.mini.program.service.RouteRelateService;
import com.hk.culture.mini.program.service.RouteService;
import com.hk.culture.mini.program.service.SiteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 路线图表 服务实现类
 * </p>
 *
 * @author
 * @since 2020-05-12
 */
@Service
@Slf4j
public class RouteServiceImpl extends ServiceImpl<RouteMapper, Route> implements RouteService {

    @Autowired
    private RouteRelateService routeRelateService;
    @Autowired
    private SiteService siteService;

    /**
     * 获取详情
     * @param tid
     * @return
     */
    @Override
    public Result<RouteVO> getByTid(String tid) {
        Route route = getBaseMapper().selectById(tid);

        if (route == null) {
            return Result.error(ReturnCodeEnum.RECORD_NOT_EXISTS, "路线不存在");
        }

        RouteVO routeVO = BeanUtil.convertToBean(route, RouteVO.class);
        List<RouteRelate> routeRelates = routeRelateService.listByRouteTid(routeVO.getTid());
        if (CollectionUtils.isEmpty(routeRelates)) {
            return Result.success(route);
        }

        // 获取坐标点信息
        List<Site> sites = (List<Site>) siteService.listByIds(
                routeRelates.stream()
                        .map(RouteRelate::getSiteTid)
                        .collect(Collectors.toList()));

        // 坐标点id与关联信息map
        Map<String, RouteRelate> siteOrderMap = routeRelates.stream()
                .collect(Collectors.toMap(RouteRelate::getSiteTid, routeRelate -> routeRelate));

        // 取路线图中的标注点排序
        List<RouteSiteVO> routeSiteVOS = sites.stream()
                .map(rawSite -> {
                            RouteSiteVO routeSiteVO = BeanUtil.convertToBean(rawSite, RouteSiteVO.class);
                            RouteRelate routeRelate = siteOrderMap.get(routeSiteVO.getTid());
                            routeSiteVO.setRelateTid(routeRelate == null ? null : routeRelate.getTid());
                            routeSiteVO.setOrder(routeRelate == null ? 0 : routeRelate.getOrder());
                            return routeSiteVO;
                        }
                )
                .collect(Collectors.toList());

        routeVO.setSiteList(routeSiteVOS);

        return Result.success(route);
    }


    /**
     * 条件查询，无条件则返回全部分页数据
     *
     * @param pagesQuery
     * @return
     */
    @Override
    public IPage<RouteVO> listByCondition(PagesQuery<RouteVO> pagesQuery) {
        QueryWrapper<Route> wrapper = new QueryWrapper();
        Route route = BeanUtil.convertToBean(pagesQuery.getData(), Route.class);
        if (StringUtils.isNotEmpty(route.getName())) {
            wrapper.like("name", route.getName() + "%");
        }

        // 默认只搜可见的
        if (StringUtils.isEmpty(route.getShow())) {
            wrapper.eq("show", StateEnum.ENABLE.getState());
        }

        if (pagesQuery.isOrderByDesc()) {
            wrapper.orderByDesc("createTime");
        } else {
            wrapper.orderByAsc("createTime");
        }

        Page<Route> page = new Page<>(pagesQuery.getCurrent(), pagesQuery.getPageSize());

        // 获取路线记录
        IPage<Route> routeIPage = getBaseMapper().selectPage(page, wrapper);
        if (CollectionUtils.isEmpty(routeIPage.getRecords())) {
            return routeIPage.convert(rawRoute -> BeanUtil.convertToBean(rawRoute, RouteVO.class));
        }

        // 获取路线关联的坐标点信息
        IPage<RouteVO> routeVOIPage = routeIPage.convert(rawRoute -> {
            RouteVO routeVO = BeanUtil.convertToBean(rawRoute, RouteVO.class);
            // 获取跟路线关联的坐标点信息
            List<RouteRelate> routeRelates = routeRelateService.listByRouteTid(routeVO.getTid());
            if (CollectionUtils.isEmpty(routeRelates)) {
                return routeVO;
            }
            // 获取坐标点信息
            List<Site> sites = (List<Site>) siteService.listByIds(
                    routeRelates.stream()
                            .map(RouteRelate::getSiteTid)
                            .collect(Collectors.toList()));

            // 坐标点id与关联信息map
            Map<String, RouteRelate> siteOrderMap = routeRelates.stream()
                    .collect(Collectors.toMap(RouteRelate::getSiteTid, routeRelate -> routeRelate));

            // 取路线图中的标注点排序
            List<RouteSiteVO> routeSiteVOS = sites.stream()
                    .map(rawSite -> {
                                RouteSiteVO routeSiteVO = BeanUtil.convertToBean(rawSite, RouteSiteVO.class);
                                RouteRelate routeRelate = siteOrderMap.get(routeSiteVO.getTid());
                                routeSiteVO.setOrder(routeRelate == null ? 0 : routeRelate.getOrder());
                                routeSiteVO.setRelateTid(routeRelate == null ? null : routeRelate.getTid());
                                return routeSiteVO;
                            }
                    )
                    .collect(Collectors.toList());

            routeVO.setSiteList(routeSiteVOS);
            return routeVO;
        });

        return routeVOIPage;
    }

    /**
     * 插入
     *
     * @param routeVO
     * @return
     */
    @Override
    public Result<Boolean> add(RouteVO routeVO) {
        Route route = BeanUtil.convertToBean(routeVO, Route.class);
        route.setUpdateTime(LocalDateTime.now());

        route.setUpdateTime(LocalDateTime.now());
        route.setCreateTime(LocalDateTime.now());
        route.setAuditing(StateEnum.DISABLE.getState());

        if (getBaseMapper().insert(route) != 1) {
            return Result.error(ReturnCodeEnum.FAILED, "路线图添加失败");
        }

        if (CollectionUtils.isEmpty(routeVO.getSiteList())) {
            return Result.success(true);
        }

        // 插入关联坐标点信息
        for (RouteSiteVO site : routeVO.getSiteList()) {
            saveRouteRelate(routeVO.getTid(), site);
        }


        return Result.result(true);
    }

    /**
     * 根据Tid更新
     *
     * @param routeVO
     * @return
     */
    @Override
    public Result<Boolean> updateByTid(RouteVO routeVO) {
        Route route = BeanUtil.convertToBean(routeVO, Route.class);
        route.setUpdateTime(LocalDateTime.now());

        // 不能修改
        route.setCreateTime(null);

        getBaseMapper().updateById(route);

        if (CollectionUtils.isEmpty(routeVO.getSiteList())) {
            routeRelateService.deleteByRouteTid(routeVO.getTid());
            return Result.result(true);
        }

        // 关联表改操作
        routeRelateService.updateBatchById(routeVO.getSiteList()
                .stream().map(routeSiteVO -> {
                    RouteRelate routeRelate = new RouteRelate();
                    routeRelate.setUpdateTime(LocalDateTime.now());
                    routeRelate.setOrder(routeSiteVO.getOrder());
                    routeRelate.setTid(routeSiteVO.getRelateTid());
                    return routeRelate;
                })
                .collect(Collectors.toList())
        );


        List<RouteRelate> routeRelates = routeRelateService.listByRouteTid(routeVO.getTid());
        List<String> existTids = new ArrayList<>();
        List<String> existSiteTids = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(routeRelates)) {
            routeRelates.stream()
                    .forEach(routeRelate -> {
                        existTids.add(routeRelate.getTid());
                        existSiteTids.add(routeRelate.getSiteTid());
                    });
        }

        List<String> updateTids = new ArrayList<>();
        List<String> updateSiteTids = new ArrayList<>();
        routeVO.getSiteList().stream()
                .forEach(routeSiteVO -> {
                    updateTids.add(routeSiteVO.getRelateTid());
                    updateSiteTids.add(routeSiteVO.getTid());
                });

        // 关联表删操作，更新列表里没有的就删除
        existTids.remove(updateTids);
        if (CollectionUtils.isNotEmpty(existTids)) {
            routeRelateService.removeByIds(existTids);
        }

        // 关联表增操作，已存在列表里没有的就增加
        updateSiteTids.remove(existSiteTids);
        if (CollectionUtils.isNotEmpty(updateSiteTids)) {
            for (RouteSiteVO site : routeVO.getSiteList()) {
                if (!updateSiteTids.contains(site.getTid())) {
                    continue;
                }
                saveRouteRelate(routeVO.getTid(), site);
            }
            routeRelateService.removeByIds(existTids);
        }


        return Result.result(true);
    }

    /**
     * 根据Tid删除
     *
     * @param tid
     * @param tid
     * @return
     */
    @Override
    public Result<Boolean> deleteByTid(String tid, String operator) {
        List<RouteRelate> routeRelates = routeRelateService.listByRouteTid(tid);
        List<String> existTids = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(routeRelates)) {
            routeRelates.stream()
                    .forEach(routeRelate -> {
                        existTids.add(routeRelate.getTid());
                    });
        }

        // 关联表删操作
        if (CollectionUtils.isNotEmpty(existTids)) {
            routeRelateService.removeByIds(existTids);
        }

        log.warn("{} delete tid={} record", operator);
        return Result.result(getBaseMapper().deleteById(tid) == 1);
    }


    private void saveRouteRelate(String routeTid, RouteSiteVO siteVO) {
        RouteRelate routeRelate = new RouteRelate();
        routeRelate.setCreateTime(LocalDateTime.now());
        routeRelate.setUpdateTime(LocalDateTime.now());
        routeRelate.setOrder(siteVO.getOrder());
        routeRelate.setRouteTid(routeTid);
        routeRelate.setSiteTid(siteVO.getTid());

        try {
            routeRelateService.save(routeRelate);
        } catch (Exception e) {
            log.error("", e);
        }
    }
}
