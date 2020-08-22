package com.hk.library.service;

import com.hk.library.entity.LibBookBorrow;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 书的借出归还记录 服务类
 * </p>
 *
 * @author 
 * @since 2020-08-22
 */
public interface LibBookBorrowService extends IService<LibBookBorrow> {

    LibBookBorrow getActiveByUserIdAndBookId(int userId, int bookId);

    List<LibBookBorrow> listByUserIdAndBookId(int userId, int bookId);

    List<LibBookBorrow> listByUserId(int userId);

    boolean addBorrowRecord(int userId, int bookId);

    boolean updateReturnBorrowRecord(int userId, int bookId);

}
