package com.shitouren.core.model.dto;

import lombok.Data;

import java.util.List;


@Data
public class GoodsTransferDTO {

    private String sourcePhone;

    private String targetPhone;

    private String tranNo;

    private String sourceWalletHash;

    private String targetWalletHash;

    private List<GoodsTransferList> goodsList;

    @Data
    public static class GoodsTransferList{

        private String goodsId;

        private String goodsHash;

        private String goodsContract;

        private String goodsNo;

        private String goodsPrice;

        private String reduceCost;

        private String rebate;

    }


}
