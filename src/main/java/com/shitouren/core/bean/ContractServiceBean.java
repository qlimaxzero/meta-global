package com.shitouren.core.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "contract.service")
public class ContractServiceBean {

    private String issue;
    private String upLinkQuery;
    private String address;

}
