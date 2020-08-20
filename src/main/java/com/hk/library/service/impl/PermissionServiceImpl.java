package com.hk.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hk.library.entity.Permission;
import com.hk.library.mapping.PermissionMapper;
import com.hk.library.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author
 * @since 2020-08-20
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    public List<Permission> getByAdminUserId(long userId) {
        return getBaseMapper().findByAdminUserId(userId);
    }
}
