package com.shitouren.core.model.vo;

import lombok.Data;

@Data
public class UserInfoVo {
    //手机号(必填)
    private String phone;
    //昵称(必填)
    private String nickName;
    //头像地址(必填)
    private String headImg;
    //用户id(必填)
    private String userId;
    //身份证号 （非必填，已实名则必填，减少实名操作）
    private String identityNumber;
    //真实姓名（非必填，已实名则必填，减少实名操作）
    private String realName;
    //钱包地址(必填)
    private String walletHash;

}
