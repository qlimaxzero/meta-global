package com.shitouren.core.events;

import com.shitouren.core.model.dto.InviteeSuccEventDTO;
import com.shitouren.core.service.InviteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

//@Component
@Slf4j
@AllArgsConstructor
public class InviteeSuccListener {

    private final InviteService inviteService;

    @Async
    @EventListener(InviteeSuccEventDTO.class)
    public void onApplicationEvent(InviteeSuccEventDTO dto) {
        try {
            log.info("{}", dto);
            inviteService.invitationRewardGrant(dto.getInviterId(), dto.getInviteeId());
        } catch (Exception e) {
            log.error("", e);
        }
    }

}
