package com.hk.library.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hk.library.common.constant.ReturnCodeEnum;
import com.hk.library.common.constant.StateEnum;
import com.hk.library.dto.Result;
import com.hk.library.dto.query.PagesQuery;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2020-04-11
 */
@Service
public class OnlineshowServiceImpl extends ServiceImpl<OnlineshowMapper, Onlineshow> implements OnlineshowService {

    private ExecutorService pool = Executors.newFixedThreadPool(10);

    /**
     * 获取详情
     * @param tid
     * @return
     */
    @Override
    public Result getByTid(@NonNull String tid) {
        Onlineshow onlineshow = getBaseMapper().selectById(tid);

        if (onlineshow == null) {
            return Result.error(ReturnCodeEnum.RECORD_NOT_EXISTS, "文章不存在");
        }

        pool.submit(() -> addReadCnt(onlineshow.getTid(), onlineshow.getReadCount()));

        return Result.success(onlineshow);
    }

    /**
     * 条件查询，无条件则返回全部分页数据
     * @param pagesQuery
     * @return
     */
    @Override
    public IPage<Onlineshow> listByCondition(PagesQuery<Onlineshow> pagesQuery) {
        QueryWrapper<Onlineshow> wrapper = new QueryWrapper();

        wrapper.select("TID", "startTime", "insertTime", "showName", "title", "state", "endTime");

        wrapper.ge("startTime", LocalDateTime.now());
        wrapper.lt("endTime", LocalDateTime.now());

        wrapper.eq("state", StateEnum.ENABLE.getState());

        wrapper.orderByDesc("insertTime");

        Page<Onlineshow> page = new Page<>(pagesQuery.getCurrent(), pagesQuery.getPageSize());

        IPage<Onlineshow> activityIPage = getBaseMapper().selectPage(page, wrapper);

        return activityIPage;
    }


    /**
     * 添加阅读人数
     * @param tid
     * @param readCount
     * @return
     */
    private boolean addReadCnt(String tid, String readCount) {
        // 当已预约人数与查询时一致才更新
        QueryWrapper<Onlineshow> wrapper = new QueryWrapper();
        wrapper.eq("TID", tid);
        wrapper.eq("readCount", readCount);

        Onlineshow onlineshow = new Onlineshow();
        onlineshow.setReadCount(String.valueOf(Integer.valueOf(readCount) + 1));
        return getBaseMapper().update(onlineshow, wrapper) == 1;
    }
}
