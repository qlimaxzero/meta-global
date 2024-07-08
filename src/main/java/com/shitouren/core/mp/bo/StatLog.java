package com.shitouren.core.mp.bo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StatLog {

    private Long id;
    private Integer userid;
    private String pageId;
    private String actionId;
    private String eventType;
    private String detail;
    private String ua;
    private String browser;
    @TableField("`system`")
    private String system;
    private String ip;
    private LocalDateTime eventTime;
    private LocalDateTime createTime;

}
