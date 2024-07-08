package com.shitouren.core.model.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NFTAvatarVO {

    private Integer id;
    private String imgUrl;
    private String avatarUrl;
    private Byte display;

}
