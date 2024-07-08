package com.shitouren.core.events;

import com.shitouren.core.common.Constants;
import com.shitouren.core.config.redis.CloudRedisTemplate;
import com.shitouren.core.model.dto.RedDotEventDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class RedDotListener {

    @Resource
    private CloudRedisTemplate cloudRedisTemplate;

    @Async
    @EventListener(RedDotEventDTO.class)
    public void onApplicationEvent(RedDotEventDTO dto) {
        try {
            log.info("{}", dto);
            cloudRedisTemplate.hset(Constants.RECORD_REDDOT_KEY + dto.getUid(), dto.getType().name(), 1);
        } catch (Exception e) {
            log.error("", e);
        }
    }

}
