package com.shitouren.core.model.vo;

import lombok.Data;


@Data
public class CommonResp {

   private String appId;

   private String method;

   private Integer code;

   private String msg;

   private String nonce;

   private Long timestamp;

   private String sign;

   private String objEncrypt;

   private String version;

}
