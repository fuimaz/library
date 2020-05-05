package com.hk.culture.mini.program.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hk.culture.mini.program.dto.query.PagesQuery;
import com.hk.culture.mini.program.entity.MyCollection;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2020-05-02
 */
public interface MyCollectionService extends IService<MyCollection> {

    IPage<MyCollection> listByCondition(PagesQuery<MyCollection> pagesQuery);

    MyCollection getByTargetIdAndMemberId(String targetTid, String memberTid);

    boolean favorite(String type, String targetTid, String memberTid);

    boolean cancel(String targetTid, String memberTid);

}
