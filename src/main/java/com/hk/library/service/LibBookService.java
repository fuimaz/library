package com.hk.library.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hk.library.dto.Result;
import com.hk.library.dto.query.BookQuery;
import com.hk.library.dto.query.PagesQuery;
import com.hk.library.entity.LibBook;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 书籍 服务类
 * </p>
 *
 * @author 
 * @since 2020-08-22
 */
public interface LibBookService extends IService<LibBook> {

    IPage<LibBook> listByCondition(PagesQuery<BookQuery> query);

    boolean addBook(LibBook libBook);

    Result<Boolean> borrowCheck(int userId, int bookId);

    Result<Boolean> borrowBook(int userId, int bookId);

    Result<Boolean> returnBook(int userId, int bookId);

}
