package com.hk.library.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hk.library.common.constant.BookTypeEnum;
import com.hk.library.common.constant.ReturnCodeEnum;
import com.hk.library.common.constant.StateEnum;
import com.hk.library.common.utils.CalenderUtils;
import com.hk.library.dto.Result;
import com.hk.library.dto.query.ActivityBookQuery;
import com.hk.library.dto.query.PagesQuery;
import com.hk.library.entity.Activity;
import com.hk.library.mapping.ActivityMapper;
import com.hk.library.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2020-04-08
 */
@Service
@Slf4j
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private VenuesbookService venuesbookService;


    /**
     * 条件查询，无条件则返回全部分页数据
     * @param pagesQuery
     * @return
     */
    @Override
    public IPage<Activity> listByCondition(PagesQuery<Activity> pagesQuery) {
        QueryWrapper<Activity> wrapper = new QueryWrapper();
        Activity activity = pagesQuery.getData();
        if (StringUtils.isNotEmpty(activity.getActivityName())) {
            wrapper.like("activityName", "%" + activity.getActivityName() + "%");
        }

        if (StringUtils.isNotEmpty(activity.getDepot())) {
            wrapper.like("depot", "%" + activity.getDepot() + "%");
        }

        wrapper.eq("state", StateEnum.ENABLE.getState());
        wrapper.orderByDesc("stratTime");

        Page<Activity> page = new Page<>(pagesQuery.getCurrent(), pagesQuery.getPageSize());

        IPage<Activity> activityIPage = getBaseMapper().selectPage(page, wrapper);

        return activityIPage;
    }

    /**
     * 查询指定月份内的全部活动信息
     * @param time
     * @return
     */
    @Override
    public Map<String, List<Activity>> listByDate(Long time) {
        Long monthStartTime = CalenderUtils.getMonthStartTime(time, "GMT+8:00");
        Long monthEndTime = CalenderUtils.getMonthEndTime(time, "GMT+8:00");
        QueryWrapper<Activity> wrapper = new QueryWrapper();
        wrapper
                .select("TID", "stratTime", "activityName")
                .ge("stratTime", LocalDateTime.ofEpochSecond(monthStartTime, 0, ZoneOffset.ofHours(8)))
                .le("stratTime", LocalDateTime.ofEpochSecond(monthEndTime, 0, ZoneOffset.ofHours(8)));

        wrapper.eq("state", StateEnum.ENABLE.getState());

        List<Activity> activityIPage = getBaseMapper().selectList(wrapper);
        if (CollectionUtils.isEmpty(activityIPage)) {
            return null;
        }

        Map<String, List<Activity>> collect = activityIPage.stream().collect(Collectors.groupingBy(
                activity -> DateTimeFormatter.ofPattern("yyyy-MM-dd").format(activity.getStratTime())));

        return collect;
    }

    /**
     * 预约活动
     * @param activityBookQuery
     * @return
     */
    @Override
    public Result book(ActivityBookQuery activityBookQuery) {
        Result<Boolean> bookedRecordCheck = bookedRecordCheck(activityBookQuery);
        if (bookedRecordCheck.getCode() != ReturnCodeEnum.SUCCESS.getCode()) {
            return bookedRecordCheck;
        }

        Activity activity = getBaseMapper().selectById(activityBookQuery.getTid());
        if (activity == null) {
            return Result.error(ReturnCodeEnum.PARAM_ERROR, "活动不存在");
        }

        if (LocalDateTime.now().isAfter(activity.getEndTime())) {
            return Result.error(ReturnCodeEnum.STATE_ERROR, "活动已结束");
        }

        if (Integer.valueOf(activity.getBooked()) >= Integer.valueOf(activity.getOrders())) {
            return Result.error(ReturnCodeEnum.STATE_ERROR, "活动已约满");
        }

        if (StateEnum.ENABLE != StateEnum.getValue(activity.getState())) {
            return Result.error(ReturnCodeEnum.STATE_ERROR, "活动不可预约");
        }

        Result<Boolean> addBookRecord = addBookRecord(activityBookQuery, activity);
        if (addBookRecord.getCode() != ReturnCodeEnum.SUCCESS.getCode()) {
            return addBookRecord;
        }

        // TODO 发送短信

        log.info("activity booked success, tid={}, phone={}", activityBookQuery.getTid(), activityBookQuery.getMobile());

        return Result.success(true);
    }

    @Transactional
    protected Result addBookRecord(ActivityBookQuery activityBookQuery, Activity activity) {
        // 只有更新成功才能继续，避免并发问题
        if (!addBooked(activity.getTid(), activity.getBooked())) {
            log.error("update activity book record failed, tid={}, phone={}, booked={}",
                    activityBookQuery.getTid(), activityBookQuery.getMobile(), activity.getBooked());
            return Result.error(ReturnCodeEnum.FAILED, "预约失败");
        }

        Venuesbook venuesbook = new Venuesbook();
        venuesbook.setResponsiblePhone(activityBookQuery.getMobile());
        venuesbook.setResponsible(activityBookQuery.getName());
        venuesbook.setActivityName(activity.getActivityName());
        venuesbook.setActivityTid(activity.getTid());
        venuesbook.setVenuesTid("");
        venuesbook.setVenuesName("");
        venuesbook.setBooktime(activityBookQuery.getBookTime());
        venuesbook.setState(StateEnum.AUDITING.getState());
        venuesbook.setHourCost(activity.getOneCost());

        // todo 预约表是否需要添加唯一性索引
        boolean ret = venuesbookService.save(venuesbook);
        if (!ret) {
            log.error("add activity book record failed, tid={}, phone={}",
                    activityBookQuery.getTid(), activityBookQuery.getMobile());
            return Result.error(ReturnCodeEnum.FAILED, "预约失败");
        }

        Appointment newAppointment = new Appointment();
        newAppointment.setBookTid(venuesbook.getTid());
        newAppointment.setProcess(StateEnum.AUDITING.getState());
        newAppointment.setType(BookTypeEnum.ACTIVITY.getMsg());
        newAppointment.setMemberTid(activityBookQuery.getMemberTid());
        newAppointment.setDateTime(LocalDateTime.now());
        newAppointment.setDepot(activity.getDepot());
        newAppointment.setPhone(activityBookQuery.getMobile());
        newAppointment.setName(activityBookQuery.getName());
        newAppointment.setWay(activityBookQuery.getWay());

        ret = appointmentService.save(newAppointment);
        if (!ret) {
            log.error("add activity appointment record failed, tid={}, phone={}",
                    activityBookQuery.getTid(), activityBookQuery.getMobile());
            return Result.error(ReturnCodeEnum.FAILED, "预约失败");
        }

        return Result.success(true);
    }

    private Result<Boolean> bookedRecordCheck(ActivityBookQuery activityBookQuery) {
        Venuesbook appointment = venuesbookService.getOneByUserAndId(activityBookQuery.getMobile(),
                BookTypeEnum.ACTIVITY, activityBookQuery.getBookTime(), activityBookQuery.getTid());
        if (appointment != null) {
            return Result.error(ReturnCodeEnum.RECORD_EXISTS, "您已预约");
        }

        int activeRecordCount = venuesbookService.bookedActiveRecordCount(activityBookQuery.getMobile());

        if (activeRecordCount >= 10) {
            log.error("activity auditing count over limit, tid={}, phone={}", activityBookQuery.getTid(), activityBookQuery.getMobile());
            return Result.error(ReturnCodeEnum.OVER_LIMIT, "预约活动超过限制");
        }

        return Result.success(true);
    }

    /**
     * 添加单个预约人数
     * @param tid
     * @param booked
     * @return
     */
    public boolean addBooked(String tid, String booked) {
        // 当已预约人数与查询时一致才更新
        QueryWrapper<Activity> wrapper = new QueryWrapper();
        wrapper.eq("TID", tid);
        wrapper.eq("booked", booked);

        Activity activity = new Activity();
        activity.setBooked(String.valueOf(Integer.valueOf(booked) + 1));
        return getBaseMapper().update(activity, wrapper) == 1;
    }
}
