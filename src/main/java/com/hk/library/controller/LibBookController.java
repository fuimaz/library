package com.hk.library.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hk.library.common.constant.ReturnCodeEnum;
import com.hk.library.common.utils.BeanUtil;
import com.hk.library.dto.Result;
import com.hk.library.dto.query.BookQuery;
import com.hk.library.dto.query.PagesQuery;
import com.hk.library.dto.vo.LibBookVO;
import com.hk.library.entity.LibBook;
import com.hk.library.service.LibBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 书籍 前端控制器
 * </p>
 *
 * @author 
 * @since 2020-08-22
 */
@Api(tags = "图书管理")
@RestController
@RequestMapping("/libBook")
public class LibBookController {
    @Autowired
    private LibBookService libBookService;

    @ApiOperation("分页查询图书信息")
    @PostMapping("/list")
    public Result<Page<LibBook>> list(@RequestBody PagesQuery<BookQuery> pagesQuery) {
        if (pagesQuery == null) {
            pagesQuery = new PagesQuery<>();
        }

        if (pagesQuery.getData() == null) {
            pagesQuery.setData(new BookQuery());
        }

        IPage<LibBook> libBookIPage = libBookService.listByCondition(pagesQuery);
        if (libBookIPage == null || CollectionUtils.isEmpty(libBookIPage.getRecords())) {
            return Result.success(libBookIPage);
        }

        IPage<LibBookVO> libBookVOIPage = libBookIPage.convert(activity -> BeanUtil.convertToBean(activity, LibBookVO.class));

        return Result.success(libBookVOIPage);
    }

    @ApiOperation("添加书籍")
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody LibBook libBook) {
        if (libBook == null) {
            return Result.error(ReturnCodeEnum.PARAM_ERROR);
        }

        return Result.success(libBookService.addBook(libBook));
    }

    @ApiOperation("借书")
    @GetMapping("/borrow")
    public Result<Boolean> borrow(int userId, int bookId) {
        Result<Boolean> result = libBookService.borrowCheck(userId, bookId);
        if (result.getData() == null || !result.getData()) {
            return result;
        }

        return Result.success(libBookService.borrowBook(userId, bookId));
    }

    @ApiOperation("还书")
    @GetMapping("/returnBook")
    public Result<Boolean> returnBook(int userId, int bookId) {
        Result<Boolean> result = libBookService.borrowBook(userId, bookId);
        if (result.getData() == null || !result.getData()) {
            return result;
        }

        return Result.success(libBookService.returnBook(userId, bookId));
    }
}

