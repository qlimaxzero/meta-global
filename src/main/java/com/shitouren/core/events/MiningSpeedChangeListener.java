package com.shitouren.core.events;

import com.shitouren.core.model.dto.MiningSpeedChangeEventDTO;
import com.shitouren.core.service.MiningService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

//@Component
@Slf4j
@AllArgsConstructor
public class MiningSpeedChangeListener {

    private final MiningService miningService;

    @Async
    @TransactionalEventListener(classes = MiningSpeedChangeEventDTO.class, phase = TransactionPhase.AFTER_COMMIT)
    public void onApplicationEvent(MiningSpeedChangeEventDTO dto) {
        try {
            log.info("{}", dto);
            miningService.checkAndAddMiningSpeedRecord(dto.getUserid());
        } catch (Exception e) {
            log.error("", e);
        }
    }

}
