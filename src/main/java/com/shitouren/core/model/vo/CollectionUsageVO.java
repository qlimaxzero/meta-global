package com.shitouren.core.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "collectionUsageVO")
public class CollectionUsageVO {
    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private Integer userId;

    /**
     * 元宇宙土地id
     */
    @NotNull(message = "场景id不能为空")
    private Integer mapId;

    /**
     * 店铺id
     */
    @NotNull(message = "店铺id不能为空")
    private Integer shopId;

    /**
     * 藏品id
     */
    @NotNull(message = "藏品id不能为空")
    private Integer collectionId;

    /**
     * 用户藏品id(user_grant_id)
     */
    @NotNull(message = "用户藏品id不能为空")
    private Integer userCollectionId;

    /**
     * 是否使用(0-未使用;1-已使用)
     */
    private Byte usage = 1;

    /**
     * 藏品在店铺中的摆放位置编号
     */
    @NotNull(message = "摆放位置不能为空")
    private Byte position;

}