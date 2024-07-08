package com.shitouren.core.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @description: 汇付宝业务请求参数
 * @author: Donugh
 * @create: 2023-05-09 22:03
 **/
@Data
@Builder
@AllArgsConstructor
public class HuiFuBaoBusinessRequest {
    public HuiFuBaoBusinessRequest() {}

    //商户订单号，自定义保证唯一
    private String out_trade_no;

    //商户订单时间 yyyy-MM-dd HH：mm：ss
    private String out_trade_time;

    //支付金额 | 单位：元，支持小数点后两位
    private String pay_amt;

    //订单失效时间 | 单位：分钟；默认30(即订单30分钟内支付有效),最低1分钟
    private String time_expire;

    //支付类型 | 63=快捷支付
    private String pay_type = "63";

    //异步返回的商户处理页面，URL参数是以http://或https://开头的完整URL地址(后台处理)，提交的url地址必须外网能访问到，否则无法通知商户。值可以为空，但不可以为null。
    private String notify_url;

    //同步返回的商户显示页面，URL参数是以http://或https://开头的完整URL地址(前台显示)，原则上该参数与notify_url提交的参数不一致。值可以为空，但不可以为null。
    private String return_url;

    //商户平台支付者信息
    private PayerInfo payer_info;

    //商户平台商品描述
    private GoodsInfo goods_info;

}