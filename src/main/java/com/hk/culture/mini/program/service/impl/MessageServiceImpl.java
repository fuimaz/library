package com.hk.culture.mini.program.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hk.culture.mini.program.common.constant.MsgTypeEnum;
import com.hk.culture.mini.program.common.constant.StateEnum;
import com.hk.culture.mini.program.dto.query.PagesQuery;
import com.hk.culture.mini.program.entity.Message;
import com.hk.culture.mini.program.mapping.MessageMapper;
import com.hk.culture.mini.program.service.MessageService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2020-05-21
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    /**
     * 根据会员id查询
     * @param memberTid
     * @return
     */
    @Override
    public List<Message> listByMemberId(String memberTid) {
        QueryWrapper<Message> wrapper = new QueryWrapper();
        wrapper.eq("memberTid", memberTid);

        wrapper.orderByDesc("updateTime");
        wrapper.eq("state", StateEnum.ENABLE.getState());

        List<Message> activityIPage = getBaseMapper().selectList(wrapper);

        return activityIPage;
    }

    /**
     * 根据消息类型查询
     * @param msgTypeEnum
     * @return
     */
    @Override
    public List<Message> listWaitingMsg() {
        QueryWrapper<Message> wrapper = new QueryWrapper();
        wrapper.le("unix_timestamp(notifyTime)", System.currentTimeMillis() / 1000);
        wrapper.eq("state", StateEnum.WAITING.getState());

        List<Message> activityIPage = getBaseMapper().selectList(wrapper);

        return activityIPage;
    }

    /**
     * 条件查询，无条件则返回全部分页数据
     * @param pagesQuery
     * @return
     */
    @Override
    public IPage<Message> listByCondition(PagesQuery<Message> pagesQuery) {
        QueryWrapper<Message> wrapper = new QueryWrapper();
        Message message = pagesQuery.getData();
        wrapper.eq("memberTid", message.getMemberTid());
        wrapper.eq("state", StateEnum.ENABLE.getState());
        wrapper.orderByDesc("createTime");

        if (StringUtils.isNotEmpty(message.getType())) {
            wrapper.eq("`type`", message.getType());
        }

        Page<Message> page = new Page<>(pagesQuery.getCurrent(), pagesQuery.getPageSize());

        IPage<Message> messageIPage = getBaseMapper().selectPage(page, wrapper);

        return messageIPage;
    }

    @Override
    public boolean add(MsgTypeEnum type, String relateTid, String memberTid, String content, String phone) {
        Message message = new Message();
        message.setCreateTime(LocalDateTime.now());
        message.setUpdateTime(LocalDateTime.now());
        message.setMemberTid(memberTid);
        message.setRelateTid(relateTid);
        message.setType(type.getType());
        message.setContent(content);
        message.setState(StateEnum.ENABLE.getStateCode());
        message.setPhone(phone);
        return getBaseMapper().insert(message) == 1;
    }

    /**
     * 指定时间生效的消息
     * @param type
     * @param relateTid
     * @param memberTid
     * @param content
     * @param phone
     * @param notifyTime
     * @return
     */
    @Override
    public boolean addTimingMsg(MsgTypeEnum type, String relateTid, String memberTid, String content,
                                String phone, LocalDateTime notifyTime) {
        Message message = new Message();
        message.setCreateTime(LocalDateTime.now());
        message.setUpdateTime(LocalDateTime.now());
        message.setMemberTid(memberTid);
        message.setRelateTid(relateTid);
        message.setType(type.getType());
        message.setContent(content);
        message.setState(StateEnum.WAITING.getStateCode());
        message.setNotifyTime(notifyTime);
        message.setPhone(phone);
        return getBaseMapper().insert(message) == 1;
    }
}
