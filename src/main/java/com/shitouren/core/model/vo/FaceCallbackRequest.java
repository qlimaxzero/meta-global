package com.shitouren.core.model.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Data
public class FaceCallbackRequest {
    private String     code;
    private String     message;
    private BigDecimal backScore;
    @NotNull(message = "结果不能为空")
    private Integer    backResult;
    @NotBlank(message = "序号不能为空")
    private String     serialNumber;
    private String     sourceIp;
    private String     token;
    private String     backImg;
    private String     backNo;
    private String backVideo;
    @NotBlank(message = "姓名不能为空")
    private String name;
    @NotBlank(message = "身份证号不能为空")
    private String idcard;

    // getter and setter
}
