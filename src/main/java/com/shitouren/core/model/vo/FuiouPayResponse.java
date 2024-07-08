package com.shitouren.core.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @description: 富掌柜响应参数
 * @author: Donugh
 * @create: 2023-05-09 22:03
 **/
@Data
@Builder
@AllArgsConstructor
public class FuiouPayResponse {
    public FuiouPayResponse() {}

    //错误代码, 000000 成功,其他详细参见错误列表
    private String result_code;

    //错误代码描述
    private String result_msg;

    //机构号,接入机构在富友的唯一代码
    private String ins_cd;

    //商户号, 富友分配的商户号
    private String mchnt_cd;

    //终端号(没有真实终端号统一填88888888)
    private String term_id;

    //随机字符串，不长于32位
    private String random_str;

    //签名, 详见签名生成算法
    private String sign;

    //订单类型:ALIPAY,WECHAT,UNIONPAY(银联二维码),BESTPAY(翼支付)
    private String order_type;

    //预支付交易会话标识,富友返回支付宝生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时
    private String session_id;

    //二维码链接
    private String qr_code;

    //富友生成的订单号,需要商户与商户订单号进行关联
    private String reserved_fy_order_no;

    //富友系统内部追踪号
    private String reserved_fy_trace_no;

    //条码流水号，用户账单二维码对应的流水
    private String reserved_channel_order_id;
}