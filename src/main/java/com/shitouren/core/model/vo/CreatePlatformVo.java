package com.shitouren.core.model.vo;

import lombok.Data;


@Data
public class CreatePlatformVo {

    /**
     * 平台名
     */
    private String platformName;

    /**
     * 唯一标识
     */
    private String appId;

    /**
     * 平台秘钥（用于解密平台发送的请求的参数）
     */
    private String platformSecretKey;

    /**
     * 平台公钥
     */
    private String platformPublicKey;




}
