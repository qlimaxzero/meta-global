package com.shitouren.core.model.vo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DialogueRecordVO {

    private String q;
    private String a;
    private LocalDateTime createTime;

}
