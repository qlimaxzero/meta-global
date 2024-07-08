package com.shitouren.core.model.dto;

import lombok.Data;

import java.util.List;


@Data
public class GoodsPutAwayDTO {

    private String walletHash;

    private List<GoodsPutAwayList> goodsList;

    @Data
    public static class GoodsPutAwayList{

        private String goodsId;

        private String goodsHash;

//        private String goodsNo;

        private String goodsPrice;

        private String goodsContract;

        private Integer status;


    }




}
