package com.hk.culture.mini.program.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hk.culture.mini.program.dto.query.PagesQuery;
import com.hk.culture.mini.program.entity.Activity;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2020-04-08
 */
public interface ActivityService extends IService<Activity> {

    public IPage<Activity> listByCondition(PagesQuery<Activity> pagesQuery);
}
