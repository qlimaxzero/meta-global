package com.shitouren.core.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MiningDTO {

    private BigDecimal miningAIB;

    private BigDecimal miningAIC;

    private BigDecimal powerTotal;

    private BigDecimal powerNFT;

    private BigDecimal powerInvitation;

    private BigDecimal powerGuild;

    private BigDecimal powerActivity;

    private BigDecimal miningLeaderRewardAIB;//会长额外奖励

}
