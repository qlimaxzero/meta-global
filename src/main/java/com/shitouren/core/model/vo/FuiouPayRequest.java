package com.shitouren.core.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @description: 富掌柜请求参数
 * @author: Donugh
 * @create: 2023-05-09 22:03
 **/
@Data
@Builder
@AllArgsConstructor
public class FuiouPayRequest {
    public FuiouPayRequest() {}

    //1.0
    private String version;

    //机构号,接入机构在富友的唯一代码
    private String ins_cd;

    //商户号,富友分配给二级商户的商户号
    private String mchnt_cd;

    //终端号(没有真实终端号统一填88888888)
    private String term_id;

    //随机字符串，不长于32位
    private String random_str;

    //签名, 详见签名生成算法
    private String sign;

    //订单类型：
    //ALIPAY(支付宝)
    //WECHAT(微信)
    //UNIONPAY(银联二维码)
    //BESTPAY(翼支付)
    //PY68(银联分期-商户贴息)
    //PY69(银联分期-持卡人贴息)
    private String order_type;

    //商品名称, 显示在用户账单的商品、商品说明等地方
    private String goods_des;

    //单品优惠功能字段，见文档中good_detail说明字段(非必填)
    private String goods_detail;

    //附加数据
    //如果需要用到微信点餐数据回传，该字段需要填写OrderSource=FoodOrder(非必填)
    private String addn_inf;


    //商户订单号,商户系统内部的订单号（5到30个字符、只能包含字母数字,区分大小写)
    private String mchnt_order_no;

    //货币类型,默认人民币：CNY(非必填)
    private String curr_type;

    //总金额,订单总金额,单位为分
    private String order_amt;

    //实时交易终端IP(后期富友、银联侧风控主要依据，请真实填写)
    //暂时仅支持IPV4
    private String term_ip;

    //交易起始时间,订单生成时间,格式为yyyyMMddHHmmss
    private String txn_begin_ts;

    //商品标记(非必填)
    private String goods_tag;

    //通知地址,接收富友异步通知回调地址,通知url必须为直接可访问的url,不能携带参数
    private String notify_url;

}