package com.hk.library.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hk.library.common.constant.ReturnCodeEnum;
import com.hk.library.common.utils.BeanUtil;
import com.hk.library.dto.Result;
import com.hk.library.dto.query.ActivityBookQuery;
import com.hk.library.dto.query.PagesQuery;
import com.hk.library.dto.vo.ActivityVO;
import com.hk.library.entity.Activity;
import com.hk.library.service.ActivityService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author
 * @since 2020-04-08
 */
@RestController
@RequestMapping("/activity")
@ResponseBody
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @RequestMapping("/get/{id}")
    public Result<ActivityVO> get(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return Result.error(ReturnCodeEnum.PARAM_ERROR);
        }

        Activity activity = activityService.getById(id);

        return Result.success(BeanUtil.convertToBean(activity, ActivityVO.class));
    }

    @PostMapping("/list")
    public Result<Page<ActivityVO>> list(@RequestBody PagesQuery<Activity> pagesQuery) {
        if (pagesQuery == null) {
            pagesQuery = new PagesQuery<>();
        }

        if (pagesQuery.getData() == null) {
            pagesQuery.setData(new Activity());
        }

        IPage<Activity> activityIPage = activityService.listByCondition(pagesQuery);
        if (activityIPage == null || CollectionUtils.isEmpty(activityIPage.getRecords())) {
            return Result.success(activityIPage);
        }

        IPage<ActivityVO> activityVOIPage = activityIPage.convert(activity -> BeanUtil.convertToBean(activity, ActivityVO.class));

        return Result.success(activityVOIPage);
    }

    @GetMapping("/listByMonth")
    public Result listByMonth(Long time) {
        if (time == null) {
            time = System.currentTimeMillis();
        }

        return Result.success(activityService.listByDate(time));
    }


    @PostMapping("/book")
    public Result book(@RequestBody ActivityBookQuery activityBookQuery) {
        if (activityBookQuery == null) {
            return Result.error(ReturnCodeEnum.PARAM_ERROR);
        }

        return activityService.book(activityBookQuery);
    }
}

