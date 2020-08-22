package com.hk.library.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hk.library.common.BixException;
import com.hk.library.common.constant.ReturnCodeEnum;
import com.hk.library.common.constant.StateEnum;
import com.hk.library.dto.Result;
import com.hk.library.dto.query.BookQuery;
import com.hk.library.dto.query.PagesQuery;
import com.hk.library.entity.LibBook;
import com.hk.library.entity.LibBookBorrow;
import com.hk.library.mapper.LibBookMapper;
import com.hk.library.service.LibBookBorrowService;
import com.hk.library.service.LibBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * <p>
 * 书籍 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-08-22
 */
@Service
@Slf4j
public class LibBookServiceImpl extends ServiceImpl<LibBookMapper, LibBook> implements LibBookService {
    @Autowired
    private LibBookBorrowService libBookBorrowService;

    @Override
    public IPage<LibBook> listByCondition(PagesQuery<BookQuery> pagesQuery) {
        QueryWrapper<LibBook> wrapper = new QueryWrapper();
        BookQuery queryData = pagesQuery.getData();
        if (StringUtils.isNotEmpty(queryData.getBookName())) {
            wrapper.like("book_name", queryData.getBookName() + "%");
        }

        if (StringUtils.isNotEmpty(queryData.getAuthor())) {
            wrapper.like("author", queryData.getAuthor() + "%");
        }

        wrapper.orderByDesc("create_time");

        Page<LibBook> page = new Page<>(pagesQuery.getCurrent(), pagesQuery.getPageSize());

        IPage<LibBook> libBookIPage = getBaseMapper().selectPage(page, wrapper);

        return libBookIPage;
    }

    @Override
    public boolean addBook(LibBook libBook) {
        libBook.setStatus(StateEnum.ENABLE.getStateCode());
        libBook.setCreateTime(LocalDateTime.now());
        libBook.setUpdateTime(LocalDateTime.now());
        return getBaseMapper().insert(libBook) == 1;
    }

    @Override
    @Transactional
    public Result<Boolean> borrowBook(int userId, int bookId) {
        LibBookBorrow activeByUserIdAndBookId = libBookBorrowService.getActiveByUserIdAndBookId(userId, bookId);
        if (activeByUserIdAndBookId != null) {
            log.warn("exist borrowing the same book, {}", activeByUserIdAndBookId);
            return Result.error(ReturnCodeEnum.FAILED);
        }

        boolean addBorrowRecord = libBookBorrowService.addBorrowRecord(userId, bookId);
        if (!addBorrowRecord) {
            log.error("add book record failed, userId={}, bookId={}", userId, bookId);
            throw new BixException("添加预约记录失败");
        }


        int repertoryCnt = getBaseMapper().updateRepertoryCnt(-1, bookId);
        if (repertoryCnt != 1) {
            log.error("update repertory count failed, userId={}, bookId={}", userId, bookId);
            throw new BixException("添加预约记录失败");
        }

        return Result.success(true);
    }

    @Override
    @Transactional
    public Result<Boolean> returnBook(int userId, int bookId) {
        LibBookBorrow activeByUserIdAndBookId = libBookBorrowService.getActiveByUserIdAndBookId(userId, bookId);
        if (activeByUserIdAndBookId == null) {
            log.warn("no exist borrowing book, userId={}, bookId={}", userId, bookId);
            return Result.error(ReturnCodeEnum.FAILED);
        }

        boolean returnBorrowRecord = libBookBorrowService.updateReturnBorrowRecord(userId, bookId);
        if (!returnBorrowRecord) {
            log.error("update book record status failed, userId={}, bookId={}", userId, bookId);
            throw new BixException("归还图书操作失败");
        }


        int repertoryCnt = getBaseMapper().updateRepertoryCnt(1, bookId);
        if (repertoryCnt != 1) {
            log.error("update repertory count failed, userId={}, bookId={}", userId, bookId);
            throw new BixException("归还图书操作失败");
        }

        return Result.success(true);
    }
}
