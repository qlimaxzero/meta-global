package com.shitouren.core.bean.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RealNameParam {
  /*  @ApiModelProperty("银行卡卡号")
    private String accountNo;*/

/*    @ApiModelProperty("持卡人姓名")
    private String name;

    @ApiModelProperty("身份证号码")
    private String idCardCode;*/

  /*  @ApiModelProperty("银行预留手机号码")
    private String mobile;*/


    /**
     *
     */
    @ApiModelProperty("姓名")
    private String name;

    /**
     *
     */
    @ApiModelProperty("身份证号")
    private String idCardNum;

    /**
     *
     */
 /*   @ApiModelProperty("图片")
    private String imgUrl;*/



}
