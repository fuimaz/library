package com.hk.library.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hk.library.entity.User;
import com.hk.library.mapping.UserMapper;
import com.hk.library.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author
 * @since 2020-08-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    public User getByName(String name) {
        QueryWrapper<User> wrapper = new QueryWrapper();

        wrapper.eq("name", name);

        return getBaseMapper().selectOne(wrapper);
    }
}
