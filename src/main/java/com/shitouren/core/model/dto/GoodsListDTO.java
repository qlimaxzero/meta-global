package com.shitouren.core.model.dto;

import lombok.Data;


@Data
public class GoodsListDTO {

    private String phone;

    private String walletHash;

    private String keyword;

    private String archiveId;

    private String page;

    private String pageSize;

}
