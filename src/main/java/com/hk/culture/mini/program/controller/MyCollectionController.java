package com.hk.culture.mini.program.controller;


import com.hk.culture.mini.program.dto.Result;
import com.hk.culture.mini.program.service.MyCollectionService;
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
@RequestMapping("/myCollection")
public class MyCollectionController {
    @Autowired
    private MyCollectionService myCollectionService;

    @PostMapping("/favorite")
    public Result<Boolean> like(@NonNull @RequestParam(value = "tid") String tid,
                                @NonNull @RequestParam(value = "type") String type,
                                @NonNull @RequestParam(value = "memberTid") String memberTid) {

        return Result.success(myCollectionService.favorite(type, tid, memberTid));
    }

    @PostMapping("/cancel")
    public Result cancel(@NonNull @RequestParam(value = "tid") String tid,
                         @NonNull @RequestParam(value = "memberTid") String memberTid) {

        return Result.success(myCollectionService.cancel(tid, memberTid));
    }

}

