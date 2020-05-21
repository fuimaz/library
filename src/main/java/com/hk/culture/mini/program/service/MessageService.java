package com.hk.culture.mini.program.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hk.culture.mini.program.dto.query.PagesQuery;
import com.hk.culture.mini.program.entity.Message;

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

    List<Message> listByMemberId(String memberTid);

    IPage<Message> listByCondition(PagesQuery<String> pagesQuery);

    boolean add(String type, String relateTid, String memberTid, String content, String phone);

}
