package com.shitouren.core.model.vo;

import lombok.Data;

import java.util.List;


@Data
public class GoodsTransferConfirmVO {

    private Integer status;

    private String tranNo;

    private List<GoodsTransferConfirmList> goodsList;


    @Data
    public static class GoodsTransferConfirmList{

        private String goodsId;

        private Integer goodsStatus;

    }



}
