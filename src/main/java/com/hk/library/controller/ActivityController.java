package com.hk.library.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author
 * @since 2020-04-08
 */
@RestController
@RequestMapping("/activity")
@ResponseBody
public class ActivityController {

    @RequestMapping("/home")
    public String productInfo() {
        return " admin home page ";
    }
}

