package com.shitouren.core.mp.bo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GuildMember {

    /** 主键id */
    private Integer id;

    /** 公会id */
    private Integer guildId;

    /** 用户id */
    private Integer userid;

    private LocalDateTime createTime;

}
