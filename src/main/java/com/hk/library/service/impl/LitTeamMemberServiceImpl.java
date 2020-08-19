package com.hk.library.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hk.library.common.constant.ReturnCodeEnum;
import com.hk.library.dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 文艺团队成员表 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-06-07
 */
@Service
@Slf4j
public class LitTeamMemberServiceImpl extends ServiceImpl<LitTeamMemberMapper, LitTeamMember> implements LitTeamMemberService {

    /**
     * 根据routeTid查询
     * @param teamTid
     * @return
     */
    @Override
    public List<LitTeamMember> listByTeamTid(String teamTid) {
        QueryWrapper<LitTeamMember> wrapper = new QueryWrapper();

        wrapper.eq("teamTID", teamTid);

        wrapper.orderByAsc("createTime");

        List<LitTeamMember> teamMembers = getBaseMapper().selectList(wrapper);

        return teamMembers;
    }

    @Override
    public Result<Boolean> deleteByTeamTid(String teamTid) {
        if (StringUtils.isEmpty(teamTid)) {
            return Result.error(ReturnCodeEnum.PARAM_ERROR, "删除成员失败");
        }

        QueryWrapper<LitTeamMember> wrapper = new QueryWrapper();
        wrapper.eq("teamTID", teamTid);
        getBaseMapper().delete(wrapper);
        return Result.result(getBaseMapper().delete(wrapper) > 0);
    }

    /**
     * 插入
     * @param litTeamMember
     * @return
     */
    @Override
    public Result<Boolean> add(LitTeamMember litTeamMember) {

        litTeamMember.setUpdateTime(LocalDateTime.now());
        litTeamMember.setCreateTime(LocalDateTime.now());

        return Result.result(getBaseMapper().insert(litTeamMember) == 1);
    }

    /**
     * 根据Tid更新
     * @param litTeamMember
     * @return
     */
    @Override
    public Result<Boolean> updateByTid(LitTeamMember litTeamMember) {
        litTeamMember.setUpdateTime(LocalDateTime.now());

        // 不能修改
        litTeamMember.setCreateTime(null);

        return Result.result(getBaseMapper().updateById(litTeamMember) == 1);
    }

    /**
     * 根据Tid删除
     * @param tid
     * @param tid
     * @return
     */
    @Override
    public Result<Boolean> deleteByTid(String tid, String operator) {

        log.warn("{} delete tid={} record", operator);
        return Result.result(getBaseMapper().deleteById(tid) == 1);
    }
}
