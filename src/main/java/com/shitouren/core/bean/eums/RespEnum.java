package com.shitouren.core.bean.eums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum RespEnum {

    /**
     * 成功------------------------------------------
     */
    SUCCESS(20000,"调用成功"),

    /**
     * 服务不可用-------------------------------------
     */
    BUSINESS_SYSTEM_ERROR(40100,"服务暂不可用"),
    GATEWAY_UN_KNOW_ERROR(40101,"服务暂不可用"),

    /**
     * 缺少必选参数------------------------------------
     */
    LESS_METHOD_ERROR(50100,"缺少方法名参数"),
    LESS_SIGN_ERROR(50101,"缺少签名参数"),
    LESS_OBJ_ENCRYPT_ERROR(50102,"缺少objEncrypt"),
    LESS_APPID_ERROR(50103,"缺少appId参数"),
    LESS_TIMESTAMP_ERROR(50104,"缺少时间戳参数"),

    /**
     * 非法的参数--------------------------------------
     */
    PARAM_USE_LESS_ERROR(50200,"参数无效"),
    METHOD_NOT_EXIST_ERROR(50201,"不存在的方法名"),
    STRUCTURE_USE_LESS_ERROR(50202,"无效的数据格式"),
    USE_LESS_SIGN_ERROR(50203,"无效签名"),
    DECODE_ERROR(50204,"解密异常"),
    USE_LESS_APP_ID_PARAM(50205,"无效的appId参数"),
    USE_LESS_TIMESTAMP_PARAM(50206,"非法的时间戳参数"),
    DECODE_STRUCTURE_ERROR(50207,"解密出错, 未配置加密密钥或加密密钥格式错误"),
    DECODE_UN_KNOW_ERROR(50208,"解密出错，未知异常"),
    CHECK_SIGN_ERROR(50209,"验签出错, 未配置对应签名算法的公钥或者证书"),
    SUSPICIOUS_ATTACK_REQUEST_ERROR(50210,"可疑的攻击请求"),
    FORBID_INTERFACE_ERROR(50211,"接口被禁用"),
    USE_LESS_KEY_ERROR(50212,"应用公钥证书已经不在有效期内"),
    INTERFACE_RESP_ERROR(50213,"接口响应失败"),
    NO_OBJ_ENCRYPT_ERROR(50214,"缺少ObjEncrypt"),
    NO_NONCE_ERROR(50215,"缺少nonce参数"),
    USE_LESS_NONCE_ERROR(50216,"nonce参数无效"),
    ENCODE_UN_KNOW_ERROR(50217,"加密未知异常"),
    SIGN_FAIL_UN_KNOW_ERROR(50218,"签名失败，未知异常"),
    ILLEGAL_PAGE_ERROR(50219,"非法的图片格式"),
    PARAM_NOT_RIGHT_ERROR(50220,"参数与接收参数不一致"),
    ENCODE_FAIL_ERROR(50221,"加密出错，未知异常"),

    /**
     * 用户验证----------------------------------------
     */
    USER_INFORMATION_ERROR(60100,"用户信息不匹配"),
    USE_LESS_NAME_PARAM_ERROR(60101,"无效的姓名参数"),
    USE_LESS_PHONE_ERROR(60102,"无效的手机号"),
    USER_STATUS_ERROR(60104,"用户状态异常"),
    SYNC_USER_ERROR(60103,"同步用户信息失败"),

    /**
     * 获取藏品----------------------------------------
     */
    USE_LESS_OWNER_PHONE(60200,"无效的藏品拥有者的手机号"),
    USE_LESS_WALLET_ERROR(60201,"无效的钱包地址"),
    GOODS_LIST_LOAD_ERROR(60202,"藏品列表获取失败"),

    /**
     * 发起转增-----------------------------------------
     */
    USE_LESS_SOURCE_PHONE_ERROR(60300,"无效的发起方手机号"),
    USE_LESS_SOURCE_WALLET_ERROR(60301,"无效的发起方钱包地址"),
    USE_LESS_TARGET_PHONE_ERROR(60302,"无效的接收方手机号"),
    USE_LESS_TARGET_WALLET_ERROR(60303,"无效的接收方钱包地址"),
    GOOD_NOT_EXIST_ERROR(60304,"不存在此藏品或藏品状态限制操作"),
    TRANSFER_NUMBER_ERROR(60305,"转赠数量异常"),
    USE_LESS_GOODS_HASH(60306,"无效的藏品hash"),
    USE_LESS_GOODS_CONTRACT(60307,"无效的合约地址"),
    TRANSFER_UN_KNOW_ERROR(60308,"转赠未知异常"),

    /**
     * 转赠结果查询----------------------------------------
     */
    USE_LESS_TRAN_NO_ERROR(60400,"无效的流水号"),
    TRANSFER_CONFIRM_UN_KNOW_ERROR(60401,"转赠确认未知异常"),
    REPEAT_TRAN_NO_ERROR(60402,"重复的流水号"),
    UN_SEARCHABLE_ERROR(60403,"未查询到此流水号，请发起转增重试"),

    /**
     * 验证用户---------------------------------------------
     */
    USE_LESS_TARGET_MOBILE_ERROR(60500,"无效的接收方手机号"),
    USE_LESS_WALLET_HASH_ERROR(60501,"无效的钱包地址"),
    GOODS_NOT_EXIST_ERROR(60502,"不存在此藏品"),
    USE_LESS_GOOD_HASH_ERROR(60503,"无效的藏品hash"),
    USE_LESS_CONTRACT_ERROR(60504,"无效的合约地址"),
    GOOD_COUNT_ERROR(60505,"藏品数量异常"),

    /**
     * 藏品档案----------------------------------------------
     */
    ARCHIVE_COUNT_LIMIT_ERROR(60600,"商品档案数量大于100条"),

    /**
     * 上下架------------------------------------------------
     */
    GOOD_STOCK_ERROR(60700,"藏品库存不足");



    private Integer k;
    private String v;


}
