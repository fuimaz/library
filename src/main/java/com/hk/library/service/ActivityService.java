package com.hk.library.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hk.library.dto.query.ActivityBookQuery;
import com.hk.library.dto.query.PagesQuery;
import com.hk.library.dto.Result;
import com.hk.library.entity.Activity;

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

    Map<String, List<Activity>> listByDate(Long time);
}
