package com.shitouren.core.events;

import com.shitouren.core.common.Constants;
import com.shitouren.core.model.dto.BuyEventDTO;
import com.shitouren.core.service.UserGrantService;
import com.shitouren.core.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class UserBuyNFTListener {

    @Resource
    private UserService userService;;

    @Resource
    private UserGrantService userGrantService;;

    @Async
    @EventListener(BuyEventDTO.class)
    public void onApplicationEvent(BuyEventDTO dto) {
        try {
            log.info("{}", dto);
            switch (dto.getType()) {
                case MARKET:
                    userGrantService.updateHistoryDialogueRecord(dto.getUid(), dto.getNftId());
                case BLIND_BOX:
                default:
                    userService.updateValidById(dto.getUid(), Constants.USER_VALID);
            }
        } catch (Exception e) {
            log.error("", e);
        }
    }

}
