package com.hk.culture.mini.program.cron;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hk.culture.mini.program.common.constant.StateEnum;
import com.hk.culture.mini.program.entity.Message;
import com.hk.culture.mini.program.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 定时扫描发送短信
 */
@Component
@Slf4j
public class MsgSenderCronTask {
    @Autowired
    private MessageService messageService;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void job() {

        List<Message> messages = messageService.listWaitingMsg();
        if (CollectionUtils.isEmpty(messages)) {
            return;
        }

        for (Message message : messages) {
            try {
                message.setState(StateEnum.PROCESSING.getStateCode());
                if (!updateMsg(message, StateEnum.PROCESSING)) {
                    log.error("update to processing state failed, tid={}", message.getTid());
                    continue;
                }

                // todo 根据情况发送短信或者推送
                if (!updateMsg(message, StateEnum.ENABLE)) {
                    log.error("update to enable state failed, tid={}", message.getTid());
                    continue;
                }
            } catch (Exception e) {
                log.error("", e);
            }
        }


    }




    private boolean updateMsg(Message message, StateEnum update) {
        // 状态一致时才更新
        QueryWrapper<Message> wrapper = new QueryWrapper();
        wrapper.eq("TID", message.getTid());
        wrapper.eq("state", message.getState());

        Message updateMsg = new Message();
        message.setState(update.getStateCode());
        return messageService.getBaseMapper().update(updateMsg, wrapper) == 1;
    }
}
