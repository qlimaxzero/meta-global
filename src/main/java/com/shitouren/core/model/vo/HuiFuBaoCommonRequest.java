package com.shitouren.core.model.vo;

import lombok.Data;

/**
 * @description: 汇付宝公共请求参数
 * @author: Donugh
 * @create: 2023-05-09 21:50
 **/

@Data
public class HuiFuBaoCommonRequest {

    private String method;

    private String version;

    private String merch_id;

    private String biz_content;

    private String timestamp;

    private String sign_type;

    private String sign;

}