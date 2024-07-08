package com.shitouren.core.model.dto;

import com.shitouren.core.bean.eums.MiningChangeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MiningSpeedChangeEventDTO {

    private MiningChangeEnum type;
    private Integer userid;

}
