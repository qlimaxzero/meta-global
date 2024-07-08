package com.shitouren.core.model.dto;

import com.shitouren.core.bean.eums.UserBuyEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BuyEventDTO {

    private Integer uid;
    private Integer nftId;
    private UserBuyEnum type;

}
