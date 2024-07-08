package com.shitouren.core.events;

import com.shitouren.core.model.dto.PowerEventDTO;
import com.shitouren.core.service.GuildService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class PowerChangeListener {

    private final GuildService guildService;

    @Async
    @EventListener(PowerEventDTO.class)
    public void onApplicationEvent(PowerEventDTO dto) {
        try {
            log.info("{}", dto);
            guildService.refreshGuildRanking(dto);
        } catch (Exception e) {
            log.error("", e);
        }
    }

}
