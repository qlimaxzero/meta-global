package com.shitouren.core.bean.eums;

/**
 * @author c
 * @desc 配置文件信息，实际使用根据实际需求配置相应位置
 */
public class CompanyConfig {

    /**
     * 商户APPID
     */
    public static final String APPID = "1036470";

    /**
     * 商户密钥
     */
    public static final String PRIVATE_SIGN = "JJWLuxMLdzQalcYvujHu7UbrG0q3K5Yv";

    /**
     * 支付地址  返回请求结果参数，需要自己处理跳转
     */
    public static final String PAY_RETURN_JSON_URL = "https://api2.payunk.com/index/unifiedorder?format=json";

    /**
     * 支付地址  直接跳转支付平台收银页面
     */
    public static final String PAY_RETURN_HTNL_URL = "https://api2.payunk.com/index/unifiedorder";

    /**
     * 支付查询地址  返回请求结果参数，需要自己处理跳转
     */
    public static final String PAY_SELECT_RETURN_JSON_URL = "https://api2.payunk.com/index/getorder?format=json";

    /**
     * 支付查询地址  直接跳转支付平台收银页面
     */
    public static final String PAY_SELECT_RETURN_HTNL_URL = "https://api2.payunk.com/index/getorder";

    /**
     * 支付平台版本号
     */
    public static final String PAY_VERSION = "v1.0";

    /**
     * 支付结果通知地址
     */
    public static final String PAY_CALLBACK_URL = "http://www.rocknft.top:8200/pay/notify/";
    /**
     * 支付成功页面跳转地址
     */
    public static final String PAY_SUCCESS_WEB_URL = "";
    /**
     * 支付失败页面跳转地址
     */
    public static final String PAY_ERROR_WEB_URL = "";

}
