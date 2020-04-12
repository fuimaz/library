package com.hk.culture.mini.program.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hk.culture.mini.program.dto.query.ActivityBookQuery;
import com.hk.culture.mini.program.dto.query.PagesQuery;
import com.hk.culture.mini.program.dto.Result;
import com.hk.culture.mini.program.entity.Activity;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2020-04-08
 */
public interface ActivityService extends IService<Activity> {

    IPage<Activity> listByCondition(PagesQuery<Activity> pagesQuery);

    Result<Boolean> book(ActivityBookQuery activityBookQuery);

    Map<String, List<Activity>> listByDate(PagesQuery<Long> pagesQuery);
}
