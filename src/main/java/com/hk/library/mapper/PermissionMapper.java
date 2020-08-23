package com.hk.library.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hk.library.entity.Permission;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2020-08-22
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> getByAdminUserId(long userId);
}
