package com.hk.culture.mini.program.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hk.culture.mini.program.common.constant.ReturnCodeEnum;
import com.hk.culture.mini.program.common.constant.StateEnum;
import com.hk.culture.mini.program.dto.Result;
import com.hk.culture.mini.program.dto.query.PagesQuery;
import com.hk.culture.mini.program.entity.Talent;
import com.hk.culture.mini.program.mapping.TalentMapper;
import com.hk.culture.mini.program.service.TalentService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2020-04-11
 */
@Service
@Slf4j
public class TalentServiceImpl extends ServiceImpl<TalentMapper, Talent> implements TalentService {

    /**
     * 获取详情
     * @param tid
     * @return
     */
    @Override
    public Result getByTid(@NonNull String tid) {
        Talent talent = getBaseMapper().selectById(tid);

        if (talent == null) {
            return Result.error(ReturnCodeEnum.RECORD_NOT_EXISTS, "文艺人才不存在");
        }

        return Result.success(talent);
    }


    /**
     * 条件查询，无条件则返回全部分页数据
     * @param pagesQuery
     * @return
     */
    @Override
    public IPage<Talent> listByCondition(PagesQuery<Talent> pagesQuery) {
        QueryWrapper<Talent> wrapper = new QueryWrapper();

        Talent talent = pagesQuery.getData();

        // 默认只搜可见的
        if (talent.getState() == null) {
            wrapper.eq("`state`", StateEnum.ENABLE.getStateCode());
        } else {
            wrapper.eq("`state`", talent.getState());
        }

        // 默认只搜可见的
        if (talent.getAuditing() == null) {
            wrapper.eq("auditing", StateEnum.ENABLE.getStateCode());
        } else {
            wrapper.eq("auditing", talent.getAuditing());
        }

        // 对人才按点赞数进行排名展示
        wrapper.orderByDesc("`order`");

        Page<Talent> page = new Page<>(pagesQuery.getCurrent(), pagesQuery.getPageSize());

        IPage<Talent> talentIPage = getBaseMapper().selectPage(page, wrapper);

        return talentIPage;
    }

    /**
     * 插入
     *
     * @param talent
     * @return
     */
    @Override
    public Result<Boolean> add(Talent talent) {
        QueryWrapper<Talent> wrapper = new QueryWrapper();

        wrapper.eq("idNo", talent.getIdNo());
        Talent one = getBaseMapper().selectOne(wrapper);
        if (one != null) {
            return Result.error(ReturnCodeEnum.RECORD_EXISTS, "您已提交过");
        }

        talent.setUpdateTime(LocalDateTime.now());
        talent.setCreateTime(LocalDateTime.now());
        talent.setAuditing(StateEnum.DISABLE.getState());
        talent.setState(StateEnum.DISABLE.getStateCode());

        return Result.result(getBaseMapper().insert(talent) == 1);
    }

    /**
     * 根据Tid更新
     * @param talent
     * @return
     */
    @Override
    public Result<Boolean> updateByTid(Talent talent) {
        talent.setUpdateTime(LocalDateTime.now());

        // 不能修改
        talent.setCreateTime(null);
        talent.setIdNo(null);

        return Result.result(getBaseMapper().updateById(talent) == 1);
    }

    /**
     * 根据Tid更新
     * @return
     */
    @Override
    public List<Talent> listAllActive() {
        QueryWrapper<Talent> wrapper = new QueryWrapper();
        wrapper.eq("state", StateEnum.ENABLE.getStateCode());
        wrapper.eq("auditing", StateEnum.ENABLE.getState());

        List<Talent> talentList = getBaseMapper().selectList(wrapper);

        return talentList;
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
