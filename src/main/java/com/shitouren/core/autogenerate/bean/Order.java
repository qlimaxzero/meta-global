package com.shitouren.core.autogenerate.bean;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    /**
     * 订单id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 订单编号
     */
    private String orderno;

    /**
     * 商品详情
     */
    private String goodsinfo;

    /**
     * 在线支付金额
     */
    private BigDecimal money;

    /**
     * 购物积分
     */
    private BigDecimal balance;

    /**
     * 奖金金额
     */
    private BigDecimal dou;

    /**
     * 下单时间
     */
    private Date createtime;

    /**
     * 支付时间
     */
    private Date paymenttime;

    /**
     * 收货联系人
     */
    private String adrname;

    /**
     * 收货电话
     */
    private String adrphone;

    /**
     * 收货详细地址
     */
    private String address;

    /**
     * 订单状态(1.待付款,2.待发货,3.待收货,4.已完成)
     */
    private Integer ordertype;

    /**
     * 快递方式
     */
    private String express;

    /**
     * 快递号
     */
    private String expressno;

    /**
     * （未使用）
     */
    private Integer couponid;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 支付方式0微信1支付宝
     */
    private Integer paytype;

    /**
     * 0未删除1删除
     */
    private Integer num;

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

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public String getGoodsinfo() {
        return goodsinfo;
    }

    public void setGoodsinfo(String goodsinfo) {
        this.goodsinfo = goodsinfo == null ? null : goodsinfo.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getDou() {
        return dou;
    }

    public void setDou(BigDecimal dou) {
        this.dou = dou;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getPaymenttime() {
        return paymenttime;
    }

    public void setPaymenttime(Date paymenttime) {
        this.paymenttime = paymenttime;
    }

    public String getAdrname() {
        return adrname;
    }

    public void setAdrname(String adrname) {
        this.adrname = adrname == null ? null : adrname.trim();
    }

    public String getAdrphone() {
        return adrphone;
    }

    public void setAdrphone(String adrphone) {
        this.adrphone = adrphone == null ? null : adrphone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(Integer ordertype) {
        this.ordertype = ordertype;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express == null ? null : express.trim();
    }

    public String getExpressno() {
        return expressno;
    }

    public void setExpressno(String expressno) {
        this.expressno = expressno == null ? null : expressno.trim();
    }

    public Integer getCouponid() {
        return couponid;
    }

    public void setCouponid(Integer couponid) {
        this.couponid = couponid;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Integer getPaytype() {
        return paytype;
    }

    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}