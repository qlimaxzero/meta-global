package com.shitouren.core.model.dto;

import lombok.Data;


@Data
public class CommonReq {

    /**
     * appId
     */
    private String appId;

    /**
     * uuid
     */
    private String nonce;

    /**
     * 当前时间戳
     */
    private Long timestamp;

    /**
     * 版本号
     */
    private String version;

    /**
     * 一级平台对应方法名
     */
    private String method;

    /**
     * 签名
     */
    private String sign;

    /**
     * 加密信息
     */
    private String objEncrypt;

}
