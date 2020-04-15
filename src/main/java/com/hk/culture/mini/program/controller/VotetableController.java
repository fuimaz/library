package com.hk.culture.mini.program.controller;


import com.hk.culture.mini.program.common.constant.ReturnCodeEnum;
import com.hk.culture.mini.program.dto.Result;
import com.hk.culture.mini.program.dto.query.VoteQuery;
import com.hk.culture.mini.program.service.VotetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2020-04-08
 */
@RestController
@RequestMapping("/votetable")
public class VotetableController {

    @Autowired
    private VotetableService votetableService;

    @PostMapping("/vote")
    public Result<Boolean> vote(@RequestBody VoteQuery voteQuery) {
        if (voteQuery == null) {
            return Result.error(ReturnCodeEnum.PARAM_ERROR);
        }

        return votetableService.vote(voteQuery);
    }

}

