package com.hk.culture.mini.program.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hk.culture.mini.program.common.constant.ReturnCodeEnum;
import com.hk.culture.mini.program.common.constant.StateEnum;
import com.hk.culture.mini.program.common.utils.BeanUtil;
import com.hk.culture.mini.program.dto.Result;
import com.hk.culture.mini.program.dto.query.PagesQuery;
import com.hk.culture.mini.program.dto.vo.LiteraryTeamVO;
import com.hk.culture.mini.program.entity.LitTeamMember;
import com.hk.culture.mini.program.entity.LiteraryTeam;
import com.hk.culture.mini.program.mapping.LiteraryTeamMapper;
import com.hk.culture.mini.program.service.LitTeamMemberService;
import com.hk.culture.mini.program.service.LiteraryTeamService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 文艺团队服务表 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-06-07
 */
@Service
@Slf4j
public class LiteraryTeamServiceImpl extends ServiceImpl<LiteraryTeamMapper, LiteraryTeam> implements LiteraryTeamService {
    @Autowired
    private LitTeamMemberService litTeamMemberService;

    /**
     * 获取详情
     * @param tid
     * @return
     */
    @Override
    public Result<LiteraryTeamVO> getByTid(String tid) {
        LiteraryTeam literaryTeam = getBaseMapper().selectById(tid);

        if (literaryTeam == null) {
            return Result.error(ReturnCodeEnum.RECORD_NOT_EXISTS, "团队不存在");
        }

        LiteraryTeamVO literaryTeamVO = BeanUtil.convertToBean(literaryTeam, LiteraryTeamVO.class);
        List<LitTeamMember> teamMembers = litTeamMemberService.listByTeamTid(literaryTeamVO.getTid());
        if (CollectionUtils.isEmpty(teamMembers)) {
            return Result.success(literaryTeam);
        }

        literaryTeamVO.setTeamMemberList(teamMembers);

        return Result.success(literaryTeamVO);
    }


    /**
     * 条件查询，无条件则返回全部分页数据
     *
     * @param pagesQuery
     * @return
     */
    @Override
    public IPage<LiteraryTeamVO> listByCondition(PagesQuery<LiteraryTeam> pagesQuery) {
        QueryWrapper<LiteraryTeam> wrapper = new QueryWrapper();
        LiteraryTeam literaryTeam = BeanUtil.convertToBean(pagesQuery.getData(), LiteraryTeam.class);
        if (StringUtils.isNotEmpty(literaryTeam.getName())) {
            wrapper.like("name", literaryTeam.getName() + "%");
        }

        // 默认只搜可见的
        if (literaryTeam.getState() == null) {
            wrapper.eq("`state`", StateEnum.ENABLE.getStateCode());
        } else {
            wrapper.eq("state", literaryTeam.getState());
        }

        if (pagesQuery.isOrderByDesc()) {
            wrapper.orderByDesc("createTime");
        } else {
            wrapper.orderByAsc("createTime");
        }

        Page<LiteraryTeam> page = new Page<>(pagesQuery.getCurrent(), pagesQuery.getPageSize());
        IPage<LiteraryTeam> literaryTeamIPage = getBaseMapper().selectPage(page, wrapper);
        if (CollectionUtils.isEmpty(literaryTeamIPage.getRecords())) {
            return literaryTeamIPage.convert(rawRoute -> BeanUtil.convertToBean(rawRoute, LiteraryTeamVO.class));
        }

        // 获取成员
        IPage<LiteraryTeamVO> literaryTeamVOIPage = literaryTeamIPage.convert(team -> {
            LiteraryTeamVO literaryTeamVO = BeanUtil.convertToBean(team, LiteraryTeamVO.class);
            List<LitTeamMember> teamMembers = litTeamMemberService.listByTeamTid(literaryTeamVO.getTid());
            if (CollectionUtils.isEmpty(teamMembers)) {
                return literaryTeamVO;
            }

            literaryTeamVO.setTeamMemberList(teamMembers);
            return literaryTeamVO;
        });

        return literaryTeamVOIPage;
    }

    /**
     * 插入
     *
     * @param literaryTeamVO
     * @return
     */
    @Override
    public Result<Boolean> add(LiteraryTeamVO literaryTeamVO) {
        LiteraryTeam literaryTeam = BeanUtil.convertToBean(literaryTeamVO, LiteraryTeam.class);

        literaryTeam.setUpdateTime(LocalDateTime.now());
        literaryTeam.setCreateTime(LocalDateTime.now());
        literaryTeam.setAuditing(StateEnum.DISABLE.getState());

        if (getBaseMapper().insert(literaryTeam) != 1) {
            return Result.error(ReturnCodeEnum.FAILED, "团队添加失败");
        }

        if (CollectionUtils.isEmpty(literaryTeamVO.getTeamMemberList())) {
            return Result.success(true);
        }

        // 插入队员信息
        for (LitTeamMember litTeamMember : literaryTeamVO.getTeamMemberList()) {
            saveTeamMember(literaryTeam.getTid(), litTeamMember);
        }


        return Result.result(true);
    }

    /**
     * 根据Tid更新
     *
     * @param teamVO
     * @return
     */
    @Override
    public Result<Boolean> updateByTid(LiteraryTeamVO teamVO) {
        LiteraryTeam literaryTeam = BeanUtil.convertToBean(teamVO, LiteraryTeam.class);
        literaryTeam.setUpdateTime(LocalDateTime.now());

        // 不能修改
        literaryTeam.setCreateTime(null);

        getBaseMapper().updateById(literaryTeam);

        if (CollectionUtils.isEmpty(teamVO.getTeamMemberList())) {
            litTeamMemberService.deleteByTeamTid(teamVO.getTid());
            return Result.result(true);
        }

        // 关联表改操作
        litTeamMemberService.updateBatchById(teamVO.getTeamMemberList()
                .stream().map(litTeamMember -> {
                    litTeamMember.setUpdateTime(LocalDateTime.now());
                    litTeamMember.setCreateTime(null);
                    return litTeamMember;
                })
                .collect(Collectors.toList())
        );


        List<LitTeamMember> teamMembers = litTeamMemberService.listByTeamTid(teamVO.getTid());
        List<String> existMemberTids = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(teamMembers)) {
            teamMembers.stream()
                    .forEach(litTeamMember -> {
                        existMemberTids.add(litTeamMember.getTid());
                    });
        }

        List<String> updateMemberTids = new ArrayList<>();
        teamVO.getTeamMemberList().stream()
                .forEach(litTeamMember -> {
                    updateMemberTids.add(litTeamMember.getTid());
                });

        // 关联表删操作，更新列表里没有的就删除
        existMemberTids.remove(updateMemberTids);
        if (CollectionUtils.isNotEmpty(existMemberTids)) {
            litTeamMemberService.removeByIds(existMemberTids);
        }

        // 关联表增操作，已存在列表里没有的就增加
        updateMemberTids.remove(existMemberTids);
        if (CollectionUtils.isNotEmpty(updateMemberTids)) {
            for (LitTeamMember litTeamMember : teamVO.getTeamMemberList()) {
                if (!updateMemberTids.contains(litTeamMember.getTid())) {
                    continue;
                }
                saveTeamMember(teamVO.getTid(), litTeamMember);
            }
//            litTeamMemberService.removeByIds(existTids);
        }


        return Result.result(true);
    }

    /**
     * 根据Tid删除
     *
     * @param tid
     * @param tid
     * @return
     */
    @Override
    public Result<Boolean> deleteByTid(String tid, String operator) {
        List<LitTeamMember> routeRelates = litTeamMemberService.listByTeamTid(tid);
        List<String> existTids = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(routeRelates)) {
            routeRelates.stream()
                    .forEach(routeRelate -> {
                        existTids.add(routeRelate.getTid());
                    });
        }

        // 关联表删操作
        if (CollectionUtils.isNotEmpty(existTids)) {
            litTeamMemberService.removeByIds(existTids);
        }

        log.warn("{} delete tid={} record", operator);
        return Result.result(getBaseMapper().deleteById(tid) == 1);
    }

    private void saveTeamMember(String teamTid, LitTeamMember litTeamMember) {
        litTeamMember.setCreateTime(LocalDateTime.now());
        litTeamMember.setUpdateTime(LocalDateTime.now());
        litTeamMember.setTeamTID(teamTid);

        try {
            litTeamMemberService.save(litTeamMember);
        } catch (Exception e) {
            log.error("", e);
        }
    }
}
