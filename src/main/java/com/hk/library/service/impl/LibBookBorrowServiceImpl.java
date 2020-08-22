package com.hk.library.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hk.library.common.constant.StateEnum;
import com.hk.library.dto.query.BookQuery;
import com.hk.library.entity.LibBook;
import com.hk.library.entity.LibBookBorrow;
import com.hk.library.entity.LibBookType;
import com.hk.library.mapper.LibBookBorrowMapper;
import com.hk.library.service.LibBookBorrowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 书的借出归还记录 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-08-22
 */
@Service
public class LibBookBorrowServiceImpl extends ServiceImpl<LibBookBorrowMapper, LibBookBorrow> implements LibBookBorrowService {


    @Override
    public LibBookBorrow getActiveByUserIdAndBookId(int userId, int bookId) {
        QueryWrapper<LibBookBorrow> wrapper = new QueryWrapper();
        wrapper.eq("user_id", userId);
        wrapper.eq("book_id", bookId);
        wrapper.eq("status", StateEnum.BORROWING.getStateCode());

        LibBookBorrow libBookBorrow = getBaseMapper().selectOne(wrapper);

        return libBookBorrow;
    }

    @Override
    public List<LibBookBorrow> listByUserIdAndBookId(int userId, int bookId) {
        QueryWrapper<LibBookBorrow> wrapper = new QueryWrapper();
        wrapper.eq("user_id", userId);
        wrapper.eq("book_id", bookId);

        List<LibBookBorrow> libBookBorrows = getBaseMapper().selectList(wrapper);

        return libBookBorrows;
    }

    @Override
    public List<LibBookBorrow> listByUserId(int userId) {
        QueryWrapper<LibBookBorrow> wrapper = new QueryWrapper();
        wrapper.eq("user_id", userId);

        List<LibBookBorrow> libBookBorrows = getBaseMapper().selectList(wrapper);

        return libBookBorrows;
    }

    @Override
    public boolean addBorrowRecord(int userId, int bookId) {
        LibBookBorrow libBookBorrow = new LibBookBorrow();
        libBookBorrow.setBookId(bookId);
        libBookBorrow.setUserId(userId);
        libBookBorrow.setCreateTime(LocalDateTime.now());
        libBookBorrow.setUpdateTime(LocalDateTime.now());
        libBookBorrow.setStatus(StateEnum.BORROWING.getStateCode());

        return getBaseMapper().insert(libBookBorrow) == 1;
    }

    @Override
    public boolean updateReturnBorrowRecord(int userId, int bookId) {
        LibBookBorrow libBookBorrow = new LibBookBorrow();
        libBookBorrow.setStatus(StateEnum.BORROWED.getStateCode());
        libBookBorrow.setUpdateTime(LocalDateTime.now());

        QueryWrapper<LibBookBorrow> wrapper = new QueryWrapper();
        wrapper.eq("user_id", userId);
        wrapper.eq("book_id", bookId);
        wrapper.eq("status", StateEnum.BORROWING.getStateCode());

        return getBaseMapper().update(libBookBorrow, wrapper) == 1;
    }
}
