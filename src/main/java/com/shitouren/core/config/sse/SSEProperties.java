package com.shitouren.core.config.sse;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "meta.sse")
@Data
@Slf4j
public class SSEProperties {

    private String domain;

    private String createUrl;

    private String streamUrl;

}
