package com.shitouren.core.model.vo;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class BankUserInfoVO {
    /**
     *
     */
    private Integer id;


    /**
     * 收款人姓名
     */
    @NotBlank(message = "收款人姓名不能为空")
    private String username;

    /**
     * 银行卡号
     */
    @NotBlank(message = "银行卡号不能为空")
    private String cardNo;

    /**
     * 开户行名称
     */
    @NotBlank(message = "开户行名称不能为空")
    private String openingBank;

    /**
     * 提现方式0(对私付款)/1(对公付款)
     */
    @NotNull(message = "提现方式不能为空")
    @Range(min = 0, max = 1, message = "提现方式错误")
    private Integer cardState;

    /**
     * 身份证号
     */
    @NotBlank(message = "身份证号不能为空")
    private String idcard;

    /**
     * 电话号码
     */
    @NotBlank(message = "开户行预留电话号码不能为空")
    private String phone;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public String getOpeningBank() {
        return openingBank;
    }

    public void setOpeningBank(String openingBank) {
        this.openingBank = openingBank == null ? null : openingBank.trim();
    }

    public Integer getCardState() {
        return cardState;
    }

    public void setCardState(Integer cardState) {
        this.cardState = cardState;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

}