package com.hk.library.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2020-04-11
 */
@Service
public class AppointmentServiceImpl extends ServiceImpl<AppointmentMapper, Appointment> implements IService<Appointment> {


//    /**
//     * 查询指定用户已通过预约数
//     * @param phone
//     * @param type
//     * @param memberTid
//     * @return
//     */
//    public int bookedActiveRecordCount(String phone, String type, String memberTid) {
//        QueryWrapper<Appointment> wrapper = new QueryWrapper();
//        Appointment appointment = new Appointment();
//        appointment.setMemberTid(memberTid);
//        appointment.setPhone(phone);
//        appointment.setType(type);
//        // 限定已通过状态
//        appointment.setProcess(StateEnum.ENABLE.getState());
//        wrapper.setEntity(appointment);
//
//        Integer selectCount = getBaseMapper().selectCount(wrapper);
//
//        return selectCount == null ? 0 : selectCount;
//    }
//
//    /**
//     * 查询用户特定场景的预约记录
//     * @param phone
//     * @param sceneId
//     * @param memberTid
//     * @return
//     */
//    public Appointment getOneByUserAndId(String phone, String sceneId, String memberTid) {
//        QueryWrapper<Appointment> wrapper = new QueryWrapper();
//        Appointment appointment = new Appointment();
//        appointment.setMemberTid(memberTid);
//        appointment.setPhone(phone);
//        appointment.setBookTid(sceneId);
//        appointment.setProcess(StateEnum.ENABLE.getState());
//        wrapper.setEntity(appointment);
//        wrapper.orderByDesc("dateTime");
//
//        Appointment res = getBaseMapper().selectOne(wrapper);
//
//        return res;
//    }
//
//    /**
//     * 查询指定用户预约记录
//     * @param memberTidPagesQuery
//     * @return
//     */
//    public IPage<Appointment> listByMemberId(PagesQuery<String> memberTidPagesQuery) {
//        QueryWrapper<Appointment> wrapper = new QueryWrapper();
//
//        Appointment appointment = new Appointment();
//        appointment.setMemberTid(memberTidPagesQuery.getData());
//        wrapper.setEntity(appointment);
//        wrapper.orderByAsc("dateTime");
//        // Appointment是不是可以加个预约项目名，然后点详情再获取项目详情
//
//        Page<Appointment> page = new Page<>(memberTidPagesQuery.getCurrent(), memberTidPagesQuery.getPageSize());
//
//        IPage<Appointment> activityIPage = getBaseMapper().selectPage(page, wrapper);
//
//        return activityIPage;
//    }
//
//    /**
//     * 查询指定用户预约记录
//     * @param tid
//     * @return
//     */
//    public boolean cancleByTid(String tid) {
//
//        Appointment appointment = new Appointment();
//        appointment.setTid(tid);
//        appointment.setProcess(StateEnum.CANCEL.getState());
//
//        return getBaseMapper().updateById(appointment) == 1;
//    }

}
