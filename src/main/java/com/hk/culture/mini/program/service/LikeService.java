package com.hk.culture.mini.program.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hk.culture.mini.program.entity.Like;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2020-05-02
 */
public interface LikeService extends IService<Like> {

    Like getByTargetIdAndMemberId(String targetTid, String memberTid);

    int getCountByTargetId(String targetTid);

    boolean like(String type, String targetTid, String memberTid);

    boolean cancel(String targetTid, String memberTid);
}
