package com.shitouren.core.model.vo;

import lombok.Data;

import java.util.List;


@Data
public class GoodsPutAwayVO {

    private String walletHash;

    private List<GoodsPutAwayVOList> goodsList;

    @Data
    public static class GoodsPutAwayVOList{

        private Integer status;

        private String goodsId;

    }

}
