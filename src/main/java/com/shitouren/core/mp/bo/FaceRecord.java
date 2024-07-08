package com.shitouren.core.mp.bo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("face_record")
public class FaceRecord {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userid;
    private Byte status;//认证状态:0-初始化;1-认证中;2-成功;3-失败
    private String sn;
    private String rs;
    private LocalDateTime updatetime;
    private LocalDateTime createtime;

}
