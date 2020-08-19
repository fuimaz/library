package com.hk.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.hk.library.common.constant.ReturnCodeEnum;
import com.hk.library.common.constant.StateEnum;
import com.hk.library.dto.Result;
import com.hk.library.dto.query.VoteQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2020-04-11
 */
@Service
public class VotetableServiceImpl extends ServiceImpl<VotetableMapper, Votetable> implements VotetableService {
    @Autowired
    private VotestoreService votestoreService;
    private Cache<String, Optional<Boolean>> voteCache = CacheBuilder.newBuilder()
            .maximumSize(100000)
            .expireAfterWrite(1, TimeUnit.HOURS)
            .concurrencyLevel(10)
            .recordStats()
            .build();

    public Result vote(VoteQuery voteQuery) {
        Optional<Boolean> existRet;
        try {
            existRet = voteCache.get(String.format("%s-%s", voteQuery.getUserTid(), voteQuery.getQuestionTid()), () -> Optional.ofNullable(null));
        } catch (ExecutionException e) {
            // 忽略异常，继续执行
            existRet = Optional.ofNullable(null);
        }

        if (existRet.isPresent() && existRet.get()) {
            return Result.error(ReturnCodeEnum.RECORD_EXISTS, "你已投票");
        }

        Votestore votestore = votestoreService.getBaseMapper().selectById(voteQuery.getQuestionTid());
        if (votestore == null) {
            return Result.error(ReturnCodeEnum.PARAM_ERROR, "投票活动不存在");
        }

        if (!StateEnum.ENABLE.getState().equals(votestore.getState())) {
            return Result.error(ReturnCodeEnum.STATE_ERROR, "投票已结束");
        }

        // todo 投票表增加唯一性索引约束, 增加了就不需要先判断一遍是否已投票
        Votetable votetable = new Votetable();
        votetable.setAnswer(voteQuery.getAnswer());
        votetable.setQuestionTid(voteQuery.getQuestionTid());
        votetable.setUserName(voteQuery.getUserName());
        votetable.setUserTid(voteQuery.getUserTid());
        int insert = getBaseMapper().insert(votetable);
        if (insert == 1) {
            voteCache.put(String.format("%s-%s", voteQuery.getUserTid(), voteQuery.getQuestionTid()), Optional.of(true));
            return Result.success(true);
        }

        return Result.error(ReturnCodeEnum.FAILED, "投票失败");
    }
}
