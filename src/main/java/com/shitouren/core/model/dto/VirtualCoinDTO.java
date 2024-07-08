package com.shitouren.core.model.dto;

import lombok.Data;

@Data
public class VirtualCoinDTO {

    private String txid;//链上交易id

    private String fromAddr;//支付账户钱包地址

}
