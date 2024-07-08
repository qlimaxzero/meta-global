package com.shitouren.core.model.vo;

import lombok.Data;

import java.util.List;


@Data
public class GoodsListVO {

    private Integer total;

    private List<GoodsListVOList> goodsList;

    @Data
    public static class GoodsListVOList{

        private List<String> goodsImage;

        private String archiveId;

        private String goodsNo;

        private String goodsId;

        private String goodsName;

        private String goodsDescription;

        private Integer canTransfer;

        private String canTransferTime;

        private String goodsHash;

        private String goodsContract;
    }
}
