package com.shitouren.core.model.vo;

import lombok.Data;

@Data
public class StatLogVO {

    private String pageId;
    private String actionId;
    private String eventType;
    private long eventTime;
    private String detail;
    private String ua;
    private String browser;
    private String system;

}
