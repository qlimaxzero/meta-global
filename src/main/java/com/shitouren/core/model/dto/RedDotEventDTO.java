package com.shitouren.core.model.dto;

import com.shitouren.core.bean.eums.RedDotEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RedDotEventDTO {

    private Integer uid;
    private RedDotEnum type;

}
