package com.hk.culture.mini.program.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hk.culture.mini.program.common.constant.ReturnCodeEnum;
import com.hk.culture.mini.program.common.utils.BeanUtil;
import com.hk.culture.mini.program.dto.Result;
import com.hk.culture.mini.program.dto.query.PagesQuery;
import com.hk.culture.mini.program.dto.vo.TalentVO;
import com.hk.culture.mini.program.entity.Talent;
import com.hk.culture.mini.program.service.TalentService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2020-04-08
 */
@RestController
@RequestMapping("/talent")
public class TalentController {

    @Autowired
    private TalentService talentService;

    @RequestMapping("/get/{id}")
    public Result<TalentVO> get(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return Result.error(ReturnCodeEnum.PARAM_ERROR);
        }

        Talent talent = talentService.getById(id);

        return Result.success(BeanUtil.convertToBean(talent, TalentVO.class));
    }

    @PostMapping("/list")
    public Result<Page<TalentVO>> list(@RequestBody PagesQuery<Talent> pagesQuery) {
        if (pagesQuery == null) {
            pagesQuery = new PagesQuery<>();
        }

        if (pagesQuery.getData() == null) {
            pagesQuery.setData(new Talent());
        }

        IPage<Talent> talentIPage = talentService.listByCondition(pagesQuery);
        if (talentIPage == null || CollectionUtils.isEmpty(talentIPage.getRecords())) {
            return Result.success(talentIPage);
        }

        IPage<TalentVO> activityVOIPage = talentIPage.convert(talent -> BeanUtil.convertToBean(talent, TalentVO.class));

        return Result.success(activityVOIPage);
    }


    @PostMapping("/upload")
    public Result<Boolean> upload(@RequestParam("file") MultipartFile file, HttpServletRequest req)
            throws IllegalStateException, IOException {
        if (file.isEmpty()) {
            return Result.error(ReturnCodeEnum.PARAM_ERROR, "上传内容为空");
        }

        // 获取文件存储路径（绝对路径）
        String path = "/Users/jiman/workspace/culture-mini-program/upload/img/";
        // 获取原文件名
        String fileName = file.getOriginalFilename();
        // 创建文件实例
        File filePath = new File(path + fileName);
        // 写入文件
        file.transferTo(filePath);
        return Result.success(true);
    }
}

