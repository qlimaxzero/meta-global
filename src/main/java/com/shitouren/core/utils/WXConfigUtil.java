package com.shitouren.core.utils;

import com.github.wxpay.sdk.WXPayConfig;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 微信支付配置(单例)
 */
public class WXConfigUtil implements WXPayConfig {



    private byte[] certData;
    private static WXConfigUtil INSTANCE;


    public static final String APP_ID = "wx1e394700067f8690";//应用AppID
    public static final String KEY = "dopai45a2s14z5fok865sliks78a6s54";//商户密钥
    public static final String MCH_ID = "1630936404";//商户号

    public WXConfigUtil() throws Exception {
      /*  String certPath = "./apiclient_cert.p12";
//        String certPath = "C:\\Users\\ABC\\Desktop\\apiclient_cert.p12";
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();*/
    }

    //双重检查加锁
    public static WXConfigUtil getInstance() throws Exception {
        if (INSTANCE == null) {
            synchronized (WXConfigUtil.class) {
                if (INSTANCE == null) {
                    INSTANCE = new WXConfigUtil();
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public String getAppID() {
        return APP_ID;
    }

    //parnerid，商户号
    @Override
    public String getMchID() {
        return MCH_ID;
    }

    @Override
    public String getKey() {
        return KEY;
    }

    @Override
    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 10000;
    }
}
