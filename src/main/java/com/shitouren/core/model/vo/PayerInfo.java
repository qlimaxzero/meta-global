package com.shitouren.core.model.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @description: 商户平台支付者信息
 * @author: Donugh
 * @create: 2023-05-09 22:27
 **/

@Data
@Builder
public class PayerInfo {
    //商户平台用户唯一标识，自定义
    private String merch_user_id;

    //持卡人银行卡号,若传值页面自动代入
    private String bank_card_no;

    //持卡人姓名,若传值页面自动代入
    private String bank_user_name;

    //持卡人预留银行手机号，若传值页面自动代入
    private String bank_user_mobile;

    //持卡人身份证号，若传值页面自动代入
    private String cert_no;

    //签约协议编码 | 有值时，收银台限制修改。签约码与持卡人信息同时传送时，以签约码优先。
    private String sign_no;
}