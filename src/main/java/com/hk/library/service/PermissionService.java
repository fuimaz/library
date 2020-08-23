package com.hk.library.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hk.library.entity.Permission;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author
 * @since 2020-08-20
 */
public interface PermissionService extends IService<Permission> {

    List<Permission> getByAdminUserId(long userId);
}
