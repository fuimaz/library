package com.hk.culture.mini.program.service.impl;

import com.hk.culture.mini.program.entity.Message;
import com.hk.culture.mini.program.mapping.MessageMapper;
import com.hk.culture.mini.program.service.MessageService;
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
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

}
