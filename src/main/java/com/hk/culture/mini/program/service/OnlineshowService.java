package com.hk.culture.mini.program.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hk.culture.mini.program.dto.Result;
import com.hk.culture.mini.program.dto.query.PagesQuery;
import com.hk.culture.mini.program.entity.Onlineshow;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2020-04-08
 */
public interface OnlineshowService extends IService<Onlineshow> {

    Result getByTid(String tid);

    IPage<Onlineshow> listByCondition(PagesQuery<Onlineshow> pagesQuery);

}
