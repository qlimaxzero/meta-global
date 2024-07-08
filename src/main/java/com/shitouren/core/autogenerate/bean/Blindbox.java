package com.shitouren.core.autogenerate.bean;

import java.math.BigDecimal;

public class Blindbox {
    /**
     * id
     */
    private Integer id;

    /**
     * 盲盒图片
     */
    private String img;

    /**
     * 盲盒名称
     */
    private String name;

    /**
     * 限量
     */
    private Integer limits;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 详情展示图
     */
    private String img1;

    /**
     * 发行者
     */
    private String publisher;

    /**
     * 可能获得
     */
    private String probably;

    /**
     * 头像
     */
    private String creatorimg;

    /**
     * 0数字藏品1积分藏品
     */
    private Integer type;

    /**
     * 0所有支付方式1微信2支付宝
     */
    private String paytype;

    /**
     * 0不可使用1可以使用
     */
    private Integer buycard;

    /**
     * 打折力度
     */
    private BigDecimal buycardsize;

    /**
     * 详情
     */
    private String details;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getLimits() {
        return limits;
    }

    public void setLimits(Integer limits) {
        this.limits = limits;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1 == null ? null : img1.trim();
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher == null ? null : publisher.trim();
    }

    public String getProbably() {
        return probably;
    }

    public void setProbably(String probably) {
        this.probably = probably == null ? null : probably.trim();
    }

    public String getCreatorimg() {
        return creatorimg;
    }

    public void setCreatorimg(String creatorimg) {
        this.creatorimg = creatorimg == null ? null : creatorimg.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype == null ? null : paytype.trim();
    }

    public Integer getBuycard() {
        return buycard;
    }

    public void setBuycard(Integer buycard) {
        this.buycard = buycard;
    }

    public BigDecimal getBuycardsize() {
        return buycardsize;
    }

    public void setBuycardsize(BigDecimal buycardsize) {
        this.buycardsize = buycardsize;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details == null ? null : details.trim();
    }
}