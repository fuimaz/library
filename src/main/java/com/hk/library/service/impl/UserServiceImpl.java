package com.hk.library.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.hk.library.common.constant.RedisKeyConstants;
import com.hk.library.entity.User;
import com.hk.library.mapper.UserMapper;
import com.hk.library.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

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
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private Gson gson = new Gson();

    public User getByName(String name) {
        String userStr = (String)redisTemplate.opsForValue().get(String.format(RedisKeyConstants.USER_NAME_KEY, name));
        if (StringUtils.isNotEmpty(userStr)) {
            redisTemplate.delete(String.format(RedisKeyConstants.USER_NAME_KEY, name));
            return gson.fromJson(userStr, User.class);
        }

        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("name", name);
        User user = getBaseMapper().selectOne(wrapper);

        redisTemplate.opsForValue().set(String.format(RedisKeyConstants.USER_NAME_KEY, name), gson.toJson(user), 10, TimeUnit.SECONDS);
        return user;
    }
}
