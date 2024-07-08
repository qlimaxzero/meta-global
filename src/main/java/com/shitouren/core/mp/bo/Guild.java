package com.shitouren.core.mp.bo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Guild {

    /** 主键id */
    private Integer id;

    /** 用户id */
    private Integer userid;

    /** 公会名 */
    private String name;

    /** 邀请码 */
    private String code;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;

}
