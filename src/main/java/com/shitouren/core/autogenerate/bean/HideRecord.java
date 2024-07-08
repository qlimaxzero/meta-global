package com.shitouren.core.autogenerate.bean;

import java.math.BigDecimal;
import java.util.Date;

public class HideRecord {

    // 废弃字段
    private String img;
    private String name;
    private String ms;
    private String no;
    private BigDecimal price;
    //---------------------

    /**
     *
     */
    private Integer id;

    /**
     *
     */
    private Integer userid;

    /**
     *
     */
    private Integer collid;

    /**
     *
     */
    private Integer orderid;

    /**
     *
     */
    private Integer type;

    /**
     *
     */
    private Date createTime;

    /**
     *
     */
    private Integer getuid;

    /**
     * 交易的Id
     */
    private Integer userGrantId;

    /**
     * xm交易号
     */
    private String transNo;

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

    public Integer getCollid() {
        return collid;
    }

    public void setCollid(Integer collid) {
        this.collid = collid;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getGetuid() {
        return getuid;
    }

    public void setGetuid(Integer getuid) {
        this.getuid = getuid;
    }

    public Integer getUserGrantId() {
        return userGrantId;
    }

    public void setUserGrantId(Integer userGrantId) {
        this.userGrantId = userGrantId;
    }

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo == null ? null : transNo.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
