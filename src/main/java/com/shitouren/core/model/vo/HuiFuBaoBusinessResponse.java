package com.shitouren.core.model.vo;

import lombok.Data;

/**
 * @description: 汇付宝业务响应参数
 * @author: Donugh
 * @create: 2023-05-09 22:06
 **/

@Data
public class HuiFuBaoBusinessResponse {
  //汇付宝商户号
  private String merch_id;

  //商户交易单号
  private String out_trade_no;

  //商户订单时间 yyyy-MM-dd HH：mm：ss
  private String out_trade_time;

  //支付跳转链接 | 连接有效期为30分钟
  private String redirect_uri;
}