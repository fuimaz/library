package com.hk.culture.mini.program.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hk.culture.mini.program.common.constant.MsgTypeEnum;
import com.hk.culture.mini.program.dto.query.PagesQuery;
import com.hk.culture.mini.program.entity.Message;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2020-05-21
 */
public interface MessageService extends IService<Message> {

    List<Message> listWaitingMsg();

    List<Message> listByMemberId(String memberTid);

    IPage<Message> listByCondition(PagesQuery<Message> pagesQuery);

    boolean add(MsgTypeEnum type, String relateTid, String memberTid, String content, String phone);

    boolean addTimingMsg(MsgTypeEnum type, String relateTid, String memberTid, String content,
                         String phone, LocalDateTime notifyTime);

}
