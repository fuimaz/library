package com.hk.culture.mini.program.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hk.culture.mini.program.entity.Like;
import com.hk.culture.mini.program.mapping.LikeMapper;
import com.hk.culture.mini.program.service.LikeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2020-05-02
 */
@Service
public class LikeServiceImpl extends ServiceImpl<LikeMapper, Like> implements LikeService {


    /**
     * 通过点赞对象和点赞的会员查找点赞记录
     * @param targetTid
     * @param memberTid
     * @return
     */
    public Like getByTargetIdAndMemberId(String targetTid, String memberTid) {
        QueryWrapper<Like> wrapper = new QueryWrapper();

        wrapper.eq("targetTid", targetTid);
        wrapper.eq("memberTid", memberTid);

        return getBaseMapper().selectOne(wrapper);
    }

    /**
     * 点赞
     * @param targetTid
     * @param memberTid
     * @return
     */
    public boolean like(String type, String targetTid, String memberTid) {
        Like like = new Like();
        like.setCreateTime(LocalDateTime.now());
        like.setUpdateTime(LocalDateTime.now());
        like.setMemberTid(memberTid);
        like.setTargetTid(targetTid);
        like.setType(type);
        return getBaseMapper().insert(like) == 1;
    }

    /**
     * 取消
     * @param targetTid
     * @param memberTid
     * @return
     */
    public boolean cancel(String targetTid, String memberTid) {
        QueryWrapper<Like> wrapper = new QueryWrapper();

        wrapper.eq("targetTid", targetTid);
        wrapper.eq("memberTid", memberTid);

        return getBaseMapper().delete(wrapper) == 1;
    }
}
