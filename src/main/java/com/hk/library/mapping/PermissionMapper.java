package com.hk.library.mapping;

import com.hk.library.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2020-08-20
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> findByAdminUserId(long userId);
}
