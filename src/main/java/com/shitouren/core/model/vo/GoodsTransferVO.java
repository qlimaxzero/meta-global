package com.shitouren.core.model.vo;

import lombok.Data;

import java.util.List;


@Data
public class GoodsTransferVO {

    private Integer status;

    private String tranNo;

    private List<GoodsTransferVOList> goodsList;

    @Data
    public static class GoodsTransferVOList{

        private String goodsId;

        private String goodsHash;

        private String goodsContract;

        private Integer goodsStatus;


    }

}
