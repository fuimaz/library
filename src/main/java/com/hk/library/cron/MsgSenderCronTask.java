package com.hk.library.cron;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hk.library.common.constant.StateEnum;
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

}
