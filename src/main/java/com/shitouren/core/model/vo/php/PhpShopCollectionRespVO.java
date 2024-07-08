package com.shitouren.core.model.vo.php;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "phpShopCollectionRespVO")
public class PhpShopCollectionRespVO {

    /**
     * 店铺id
     */
    private Integer shopId;

    private List<Collections> collections;

    @Data
    public static class Collections {
        /**
         * 藏品id
         */
        private Integer id;

        /**
         * 用户藏品id(user_grant_id)
         */
        private Integer userCollectionId;

        /**
         * 是否使用(0-未使用;1-已使用)
         */
        private Byte usage = 1;

        /**
         * 藏品在店铺中的摆放位置编号
         */
        private Byte position;
    }

}