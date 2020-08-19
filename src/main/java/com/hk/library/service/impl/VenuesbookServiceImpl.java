package com.hk.library.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hk.library.common.constant.BookTypeEnum;
import com.hk.library.common.constant.StateEnum;
import com.hk.library.dto.query.PagesQuery;
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
public class VenuesbookServiceImpl extends ServiceImpl<VenuesbookMapper, Venuesbook> implements VenuesbookService {

    /**
     * 查询指定用户已通过预约数
     * @param phone
     * @return
     */
    public int bookedActiveRecordCount(String phone) {
        QueryWrapper<Venuesbook> wrapper = new QueryWrapper();
        Venuesbook venuesbook = new Venuesbook();
        venuesbook.setResponsiblePhone(phone);
        // 限定已通过、审核中状态
        wrapper.in("state", StateEnum.ENABLE.getState(), StateEnum.AUDITING.getState());
        wrapper.setEntity(venuesbook);

        Integer selectCount = getBaseMapper().selectCount(wrapper);

        return selectCount == null ? 0 : selectCount;
    }

    /**
     * 根据tid查预约记录
     * @param tid
     * @return
     */
    public List<Venuesbook> listByTidAndDate(String tid, LocalDateTime dateTime, BookTypeEnum bookTypeEnum) {
        QueryWrapper<Venuesbook> wrapper = new QueryWrapper();

        wrapper.select("venuesTid", "startTime", "endTime");
        Venuesbook venuesbook = new Venuesbook();
        if (bookTypeEnum == BookTypeEnum.VENUES) {
            venuesbook.setVenuesTid(tid);
        } else {
            venuesbook.setActivityTid(tid);
        }

        // 限定已通过、审核中状态
        wrapper.in("`state`", StateEnum.ENABLE.getState(), StateEnum.AUDITING.getState());
        wrapper.between("startTime", dateTime.minusDays(1), dateTime.plusDays(1));
        wrapper.setEntity(venuesbook);

        return getBaseMapper().selectList(wrapper);
    }

    /**
     * 根据tid查预约记录数
     * @param tid
     * @return
     */
    public int getCountByTidAndDate(String tid, LocalDateTime dateTime, BookTypeEnum bookTypeEnum) {
        QueryWrapper<Venuesbook> wrapper = new QueryWrapper();

        wrapper.select("TID");
        Venuesbook venuesbook = new Venuesbook();
        if (bookTypeEnum == BookTypeEnum.VENUES) {
            venuesbook.setVenuesTid(tid);
        } else {
            venuesbook.setActivityTid(tid);
        }

        // 限定已通过、审核中状态
        wrapper.in("`state`", StateEnum.ENABLE.getState(), StateEnum.AUDITING.getState());
        wrapper.between("startTime", dateTime.minusDays(1), dateTime.plusDays(1));
        wrapper.setEntity(venuesbook);

        return getBaseMapper().selectCount(wrapper);
    }

    /**
     * 查询用户特定场景的预约记录
     * @param phone
     * @param bookTypeEnum
     * @param sceneId
     * @return
     */
    public Venuesbook getOneByUserAndId(String phone, BookTypeEnum bookTypeEnum, LocalDateTime bookTime, String sceneId) {
        QueryWrapper<Venuesbook> wrapper = new QueryWrapper();
        Venuesbook venuesbook = new Venuesbook();
        venuesbook.setResponsiblePhone(phone);
        venuesbook.setBooktime(bookTime);
        if (BookTypeEnum.ACTIVITY == bookTypeEnum)  {
            venuesbook.setActivityTid(sceneId);
        } else if (BookTypeEnum.VENUES == bookTypeEnum) {
            venuesbook.setVenuesTid(sceneId);
        }

        wrapper.in("state", StateEnum.ENABLE.getState(), StateEnum.AUDITING.getState());
        wrapper.setEntity(venuesbook);
        wrapper.orderByDesc("bookTime");

        Venuesbook res = getBaseMapper().selectOne(wrapper);

        return res;
    }

    /**
     * 根据手机号查询指定用户预约记录
     * @param memberTidPagesQuery
     * @return
     */
    public IPage<Venuesbook> listByMemberId(PagesQuery<String> memberTidPagesQuery) {
        QueryWrapper<Venuesbook> wrapper = new QueryWrapper();

        Venuesbook venuesbook = new Venuesbook();
        venuesbook.setResponsiblePhone(memberTidPagesQuery.getData());
        wrapper.setEntity(venuesbook);
        wrapper.orderByAsc("dateTime");

        Page<Venuesbook> page = new Page<>(memberTidPagesQuery.getCurrent(), memberTidPagesQuery.getPageSize());

        IPage<Venuesbook> venuesbookIPage = getBaseMapper().selectPage(page, wrapper);

        return venuesbookIPage;
    }

    /**
     * 取消预约记录
     * @param tid
     * @return
     */
    public boolean cancleByTid(String tid) {
        Venuesbook venuesbook = new Venuesbook();
        venuesbook.setTid(tid);
        venuesbook.setState(StateEnum.CANCEL.getState());

        return getBaseMapper().updateById(venuesbook) == 1;
    }
}
