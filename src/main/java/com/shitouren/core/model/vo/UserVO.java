package com.shitouren.core.model.vo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class UserVO {

    private Integer uid;
    private String nickname;
    private BigDecimal power;

}
