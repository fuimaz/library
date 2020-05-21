package com.hk.culture.mini.program.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hk.culture.mini.program.common.constant.StateEnum;
import com.hk.culture.mini.program.dto.query.PagesQuery;
import com.hk.culture.mini.program.entity.Message;
import com.hk.culture.mini.program.mapping.MessageMapper;
import com.hk.culture.mini.program.service.MessageService;
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
     * 条件查询，无条件则返回全部分页数据
     * @param memberTid
     * @return
     */
    @Override
    public List<Message> listByMemberId(String memberTid) {
        QueryWrapper<Message> wrapper = new QueryWrapper();
        wrapper.eq("memberTid", "memberTid");

        wrapper.orderByDesc("createTime");
        wrapper.eq("state", StateEnum.ENABLE.getState());

        List<Message> activityIPage = getBaseMapper().selectList(wrapper);

        return activityIPage;
    }

    /**
     * 条件查询，无条件则返回全部分页数据
     * @param pagesQuery
     * @return
     */
    @Override
    public IPage<Message> listByCondition(PagesQuery<String> pagesQuery) {
        QueryWrapper<Message> wrapper = new QueryWrapper();

        wrapper.eq("memberTid", "memberTid");
        wrapper.eq("state", StateEnum.ENABLE.getState());
        wrapper.orderByDesc("createTime");

        Page<Message> page = new Page<>(pagesQuery.getCurrent(), pagesQuery.getPageSize());

        IPage<Message> messageIPage = getBaseMapper().selectPage(page, wrapper);

        return messageIPage;
    }

    @Override
    public boolean add(String type, String relateTid, String memberTid, String content, String phone) {
        Message message = new Message();
        message.setCreateTime(LocalDateTime.now());
        message.setUpdateTime(LocalDateTime.now());
        message.setMemberTid(memberTid);
        message.setRelateTid(relateTid);
        message.setType(type);
        message.setContent(content);
        message.setState(StateEnum.ENABLE.getStateCode());
        message.setPhone()
        return getBaseMapper().insert(message) == 1;
    }
}
