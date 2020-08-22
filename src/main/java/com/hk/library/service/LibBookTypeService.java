package com.hk.library.service;

import com.hk.library.dto.Result;
import com.hk.library.entity.LibBookType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 书籍类型 服务类
 * </p>
 *
 * @author 
 * @since 2020-08-22
 */
public interface LibBookTypeService extends IService<LibBookType> {

    List<LibBookType> listAll();

    boolean addBookType(String typeName, String desc);

}
