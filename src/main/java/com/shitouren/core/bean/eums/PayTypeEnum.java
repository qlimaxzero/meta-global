package com.shitouren.core.bean.eums;

public enum PayTypeEnum {

    // 微信服务商付款到零钱
    wxReplace,

    // 微信商户现金红包
    wxCashBonus,

    // 微信服务商现金红包
    weChatCashBonus,

    // 微信服务商付款到银行卡
    wxBank,

    // 支付宝单笔转账付款秘钥版
    alipayReplace,

    // 微信商户H5支付
    wxChatH5,

    // 微信服务商特约H5支付
    weChatHh5,

    // 微信服务商特约Jsapi支付
    weChatJs,

    // 微信商户Jsapi支付
    weChatJssh,

    // 微信小程序支付
    wxrequestPayment,

    // 微信服务商特约Native扫码
    weChatNative,

    // 微信商户Native扫码
    weChatNativesh,

    // 支付宝App转H5
    alipayApp,

    // 支付宝当面付
    alipayPrecreate,

    // 支付宝电脑网站支付
    alipayPcWebPay,

    // 支付宝手机网站支付
    alipayWapPay,
    //微信SF版Js转H5支付
    partnerJs;

}
