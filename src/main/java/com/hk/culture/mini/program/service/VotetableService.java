package com.hk.culture.mini.program.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hk.culture.mini.program.dto.Result;
import com.hk.culture.mini.program.dto.query.VoteQuery;
import com.hk.culture.mini.program.entity.Votetable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2020-04-08
 */
public interface VotetableService extends IService<Votetable> {

    Result vote(VoteQuery voteQuery);
}
