package com.hk.culture.mini.program.controller;


import com.hk.culture.mini.program.dto.Result;
import com.hk.culture.mini.program.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2020-05-02
 */
@Controller
@RequestMapping("/like")
public class LikeController {
    @Autowired
    private LikeService likeService;


    @PostMapping("/like")
    public Result<Boolean> like(@NonNull @RequestParam(value = "tid") String tid,
                                @NonNull @RequestParam(value = "type") String type,
                                @NonNull @RequestParam(value = "memberTid") String memberTid) {

        return Result.success(likeService.like(type, tid, memberTid));
    }

    @PostMapping("/cancel")
    public Result cancel(@NonNull @RequestParam(value = "tid") String tid,
                         @NonNull @RequestParam(value = "memberTid") String memberTid) {

        return Result.success(likeService.cancel(tid, memberTid));
    }
}

