package com.hk.culture.mini.program.cron;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hk.culture.mini.program.entity.Talent;
import com.hk.culture.mini.program.service.LikeService;
import com.hk.culture.mini.program.service.TalentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 定时扫描发送短信
 */
@Component
@Slf4j
public class TalentOrderCronTask {
    @Autowired
    private TalentService talentService;
    @Autowired
    private LikeService likeService;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void job() {

        List<Talent> talentList = talentService.listAllActive();
        if (CollectionUtils.isEmpty(talentList)) {
            return;
        }

        List<String> tids = talentList.stream().map(Talent::getTid).collect(Collectors.toList());

        for (String tid : tids) {
            try {
                int countByTargetId = likeService.getCountByTargetId(tid);
                if (!updateOrder(tid, countByTargetId)) {
                    log.error("update  vto processing state failed, tid={}", tid);
                    continue;
                }
            } catch (Exception e) {
                log.error("", e);
            }
        }
    }

    private boolean updateOrder(String tid, int count) {
        QueryWrapper<Talent> wrapper = new QueryWrapper();
        wrapper.eq("TID", tid);

        Talent updateMsg = new Talent();
        updateMsg.setOrder(count);
        return talentService.getBaseMapper().update(updateMsg, wrapper) == 1;
    }
}
