package com.shitouren.core.mp.bo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("files")
public class Files {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userid;
    private Integer type;
    private String url;
    private LocalDateTime createtime;

}
