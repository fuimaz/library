package com.hk.culture.mini.program.controller;


import com.hk.culture.mini.program.entity.Activity;
import com.hk.culture.mini.program.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author
 * @since 2020-04-08
 */
@Controller
@RequestMapping("/activity")
@ResponseBody
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @RequestMapping("/add")
    public void add() {
        Activity activity = new Activity();
        activity.setActivityName("1");
        activity.setAsstCompany("1");
        activityService.save(activity);
    }

    @RequestMapping("/get")
    public Object get() {

        return activityService.getById("1");
    }

    @RequestMapping("/hello")
    public Object hello() {

        return "one";
    }
}

