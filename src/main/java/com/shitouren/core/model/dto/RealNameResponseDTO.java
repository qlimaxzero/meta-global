package com.shitouren.core.model.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RealNameResponseDTO {

    /**
     * msg
     */
    @JSONField(name = "msg")
    private String msg;
    /**
     * code
     */
    @JSONField(name = "code")
    private Integer code;
    /**
     * charge
     */
    @JSONField(name = "charge")
    private Boolean         charge;
    /**
     * data
     */
    @JSONField(name = "data")
    private RealNameDataDto data;
    /**
     * success
     */
    @JSONField(name = "success")
    private Boolean         success;

    /**
     * DataDto
     */
    @NoArgsConstructor
    @Data
    public static class RealNameDataDto {
        private Integer result;
    }
}
