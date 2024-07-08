package com.shitouren.core.model.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @description: 商户平台商品描述
 * @author: Donugh
 * @create: 2023-05-09 22:31
 **/

@Data
@Builder
public class GoodsInfo {
    //商品名称
    private String goods_name;

    //商品描述
    private String goods_desc;

    //商品数量
    private String goods_num;
}