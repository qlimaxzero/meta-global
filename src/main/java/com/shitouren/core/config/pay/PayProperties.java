package com.shitouren.core.config.pay;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "pay.channel")
@Data
public class PayProperties {

    private String heepayNotifyBashUrl;

    /**
     * 充值回调
     */
    private String heepayNotifyRechargeUrl;

    /**
     * 支付回调
     */
    private String heepayNotifyPayUrl;

    private String domain;
    private String addressUrl;
    private String createUrl;
    private String queryUrl;

}
