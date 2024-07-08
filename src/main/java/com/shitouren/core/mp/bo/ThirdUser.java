package com.shitouren.core.mp.bo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("third_user")
public class ThirdUser {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userid;
    private Byte type;//1-pi
    private String openid;//三方uid
    private String token;
    private String rawTokenInfo;
    private String nickname;
    private String avatar;
    private String rawUserInfo;
    private Byte status;//1-认证成功
    private LocalDateTime updatetime;
    private LocalDateTime createtime;

}
