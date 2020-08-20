package com.hk.library.service;

import com.hk.library.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2020-08-20
 */
public interface UserService extends IService<User> {

    User getByName(String name);
}
