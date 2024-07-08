package com.shitouren.core.model.dto;

import lombok.Data;

import java.util.List;


@Data
public class GoodsVerifyDTO {

    private String phone;

    private String walletHash;

    private List<GoodsVerifyList> goodsList;


    @Data
    public static class GoodsVerifyList{

        private String goodsId;

        private String goodsHash;

        private String goodsContract;

        private String verifyCount;

    }


}
