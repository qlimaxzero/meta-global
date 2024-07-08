
package com.shitouren.core.constant;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;


public final class EncodeConstants {

    public static final String SIGN_FIELD           = "sign";
    public static final String SIGN_TYPE_FIELD      = "sign_type";


    /**
     * 默认字符集编码，EasySDK统一固定使用UTF-8编码，无需用户感知编码，用户面对的总是String而不是bytes
     */
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;


    /**
     * RSA2对应的真实签名算法名称
     */
    public static final String SHA_256_WITH_RSA = "SHA256WithRSA";

    /**
     * RSA2对应的真实非对称加密算法名称
     */
    public static final String RSA = "RSA";

}