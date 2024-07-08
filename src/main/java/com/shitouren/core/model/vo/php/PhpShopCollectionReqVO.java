package com.shitouren.core.model.vo.php;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "phpShopCollectionReqVO")
public class PhpShopCollectionReqVO {

    /**
     * 元宇宙土地id
     */
    @NotNull(message = "土地id不能为空")
    private Integer mapId;

    /**
     * 店铺id
     */
    private String shopIds;

    /**
     * 是否使用(0-未使用;1-已使用)
     */
    private Byte usage = 1;

}