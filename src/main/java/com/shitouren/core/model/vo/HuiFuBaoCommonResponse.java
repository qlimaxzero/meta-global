package com.shitouren.core.model.vo;

import lombok.Data;
import lombok.ToString;

/**
 * @description: 汇付宝公共响应参数
 * @author: Donugh
 * @create: 2023-05-09 22:05
 **/

@Data
@ToString
public class HuiFuBaoCommonResponse {
  //返回代码
  private String code;

  //返回码信息提示
  private String msg;

  //业务返回码|仅失败时有值
  private String sub_code;

  //业务处理描述|仅失败时有值
  private String sub_msg;

  //返回业务数据集合，RSA解密拿到
  private String data;

  //返参按a-z顺序&拼接参数串用于验签，data传解密后内容
  private String sign;

}