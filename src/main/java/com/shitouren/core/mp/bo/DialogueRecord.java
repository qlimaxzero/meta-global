package com.shitouren.core.mp.bo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class DialogueRecord {

    /** 主键id */
    private Long id;

    /** 用户id */
    private Integer userid;

    /** nftId */
    private Integer nftId;

    /** 会话id */
    private String dialogueId;

    /** 提问内容 */
    private String q;

    /** 回答内容 */
    private String a;

    /** 金额 */
    private BigDecimal useHealth;

    /** 是否删除 */
    private boolean deleted;

    private LocalDateTime createTime;

}
