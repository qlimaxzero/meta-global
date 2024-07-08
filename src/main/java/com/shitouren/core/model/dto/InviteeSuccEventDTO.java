package com.shitouren.core.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class InviteeSuccEventDTO {

    private Integer inviterId;
    private Integer inviteeId;

}
