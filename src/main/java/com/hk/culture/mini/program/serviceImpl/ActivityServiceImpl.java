package com.hk.culture.mini.program.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hk.culture.mini.program.common.constant.StateEnum;
import com.hk.culture.mini.program.dto.query.PagesQuery;
import com.hk.culture.mini.program.entity.Activity;
import com.hk.culture.mini.program.mapping.ActivityMapper;
import com.hk.culture.mini.program.service.ActivityService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2020-04-08
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {


    public IPage<Activity> listByCondition(PagesQuery<Activity> pagesQuery) {
        QueryWrapper<Activity> wrapper = new QueryWrapper();
        Activity activity = pagesQuery.getData();
        if (StringUtils.isNotEmpty(activity.getActivityName())) {
            wrapper.like("activityName", activity.getActivityName());
        }

        if (StringUtils.isNotEmpty(activity.getActivityName())) {
            wrapper.like("depot", activity.getDepot());
        }

        wrapper.eq("state", StateEnum.ENABLE.getState());

        Page<Activity> page = new Page<>(pagesQuery.getCurrent(), pagesQuery.getPageSize());

        IPage<Activity> activityIPage = getBaseMapper().selectPage(page, wrapper);

        List<Activity> records = activityIPage.getRecords();

        return activityIPage;
    }
}
