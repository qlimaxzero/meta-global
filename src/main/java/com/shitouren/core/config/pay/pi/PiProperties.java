package com.shitouren.core.config.pay.pi;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "meta.pi")
@Data
@Slf4j
public class PiProperties {

    private String serverAccessKey;

    private String domain;

    private String authUrl;

    private String paymentCreateUrl = "https://api.minepi.com/v2/payments/";//A2U

    private String paymentInfoUrl;

    private String paymentApproveUrl;

    private String paymentCompleteUrl;

    private String paymentCancelUrl = "https://api.minepi.com/v2/payments/{paymentId}/cancel";

    private String paymentIncompleteUrl = "https://api.minepi.com/v2/payments/incomplete_server_payments";

}
