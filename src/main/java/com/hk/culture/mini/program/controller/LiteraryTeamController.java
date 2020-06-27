package com.hk.culture.mini.program.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hk.culture.mini.program.common.constant.ReturnCodeEnum;
import com.hk.culture.mini.program.dto.Result;
import com.hk.culture.mini.program.dto.query.PagesQuery;
import com.hk.culture.mini.program.dto.vo.LiteraryTeamVO;
import com.hk.culture.mini.program.entity.LiteraryTeam;
import com.hk.culture.mini.program.service.LiteraryTeamService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 文艺团队服务表 前端控制器
 * </p>
 *
 * @author 
 * @since 2020-06-07
 */
@RestController
@RequestMapping("/literaryTeam")
public class LiteraryTeamController {
    @Autowired
    private LiteraryTeamService literaryTeamService;

    @RequestMapping("/get/{id}")
    public Result<LiteraryTeamVO> get(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return Result.error(ReturnCodeEnum.PARAM_ERROR);
        }

        return literaryTeamService.getByTid(id);
    }

    @PostMapping("/list")
    public Result<Page<LiteraryTeamVO>> list(@RequestBody PagesQuery<LiteraryTeam> pagesQuery) {
        if (pagesQuery == null) {
            pagesQuery = new PagesQuery<>();
        }

        if (pagesQuery.getData() == null) {
            pagesQuery.setData(new LiteraryTeam());
        }

        IPage<LiteraryTeamVO> siteIPage = literaryTeamService.listByCondition(pagesQuery);
        if (siteIPage == null || CollectionUtils.isEmpty(siteIPage.getRecords())) {
            return Result.success(siteIPage);
        }


        return Result.success(siteIPage);
    }

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody LiteraryTeamVO literaryTeamVO) {
        if (literaryTeamVO == null) {
            return Result.error(ReturnCodeEnum.PARAM_ERROR);
        }

        return Result.success(literaryTeamService.add(literaryTeamVO));
    }

    @PostMapping("/remove")
    public Result remove(@NonNull @RequestParam(value = "tid") String tid,
                         @NonNull @RequestParam(value = "memberTid") String memberTid) {

        return Result.success(literaryTeamService.deleteByTid(tid, memberTid));
    }

    @PostMapping("/update")
    public Result update(@RequestBody LiteraryTeamVO literaryTeamVO) {
        if (literaryTeamVO == null) {
            return Result.error(ReturnCodeEnum.PARAM_ERROR);
        }

        return literaryTeamService.updateByTid(literaryTeamVO);
    }
}

