package com.shitouren.core.mp.bo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class InviteeSuccRecord {

    /** 主键id */
    private Long id;

    /** 用户id */
    private Integer inviterId;

    /** 被邀请用户id */
    private Integer inviteeId;

    /** 奖励算力 */
    private BigDecimal power;

    /** 状态:0-已奖励;1-已撤销 */
    private Byte status;

    /** 购买时间 */
    private LocalDateTime createTime;

}
