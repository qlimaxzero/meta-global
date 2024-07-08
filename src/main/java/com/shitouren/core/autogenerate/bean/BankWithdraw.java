package com.shitouren.core.autogenerate.bean;

import com.alibaba.fastjson.annotation.JSONField;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

public class BankWithdraw {
    /**
     * 
     */
    private Integer id;

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private Integer userid;

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
     * 开户行编码
     */
    @NotNull(message = "开户行编码不能为空")
    private Integer bankCode;

    /**
     * 开户行省份
     */
    @NotBlank(message = "开户行省份不能为空")
    private String province;

    /**
     * 开户行城市
     */
    @NotBlank(message = "开户行城市不能为空")
    private String city;

    /**
     * 开户行名称
     */
    @NotBlank(message = "开户行名称不能为空")
    private String bankName;

    /**
     * 提现金额
     */
    @NotNull(message = "提现金额不能为空")
    private BigDecimal money;

    /**
     * 提现方式0(对私付款)/1(对公付款)
     */
    @NotNull(message = "提现方式0(对私付款)/1(对公付款)不能为空")
    private Integer withdrawType;

    /**
     * 身份证号
     */
    private String idcard;

    /**
     * 手续费
     */
    private BigDecimal serviceFee;

    /**
     * 实际到账金额
     */
    private BigDecimal actualMoney;

    /**
     * 提现时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    /**
     * 0正在审核1审核通过2审核失败
     */
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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

    public Integer getBankCode() {
        return bankCode;
    }

    public void setBankCode(Integer bankCode) {
        this.bankCode = bankCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getWithdrawType() {
        return withdrawType;
    }

    public void setWithdrawType(Integer withdrawType) {
        this.withdrawType = withdrawType;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    public BigDecimal getActualMoney() {
        return actualMoney;
    }

    public void setActualMoney(BigDecimal actualMoney) {
        this.actualMoney = actualMoney;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}