package com.shitouren.core.autogenerate.bean;

import java.math.BigDecimal;
import java.util.Date;

public class DealRecord {
    /**
     * 
     */
    private Integer id;

    /**
     * 成交商品
     */
    private Integer collid;

    /**
     * 头像
     */
    private String headPrtraits;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 
     */
    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCollid() {
        return collid;
    }

    public void setCollid(Integer collid) {
        this.collid = collid;
    }

    public String getHeadPrtraits() {
        return headPrtraits;
    }

    public void setHeadPrtraits(String headPrtraits) {
        this.headPrtraits = headPrtraits == null ? null : headPrtraits.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}