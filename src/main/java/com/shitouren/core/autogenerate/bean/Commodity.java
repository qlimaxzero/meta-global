package com.shitouren.core.autogenerate.bean;

import java.math.BigDecimal;
import java.util.Date;

public class Commodity {
    /**
     * 
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 图片
     */
    private String img;

    /**
     * 更多描述
     */
    private String describes;

    /**
     * 分类id
     */
    private Integer sortsid;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 已售
     */
    private Integer sold;

    /**
     * 创建时间
     */
    private Date creattime;

    /**
     * 0正常1猜你喜欢
     */
    private Integer likely;

    /**
     * 多图
     */
    private String moreimg;

    /**
     * 规格
     */
    private String specifications;

    /**
     * 1上架0下架
     */
    private Integer state;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes == null ? null : describes.trim();
    }

    public Integer getSortsid() {
        return sortsid;
    }

    public void setSortsid(Integer sortsid) {
        this.sortsid = sortsid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

    public Integer getLikely() {
        return likely;
    }

    public void setLikely(Integer likely) {
        this.likely = likely;
    }

    public String getMoreimg() {
        return moreimg;
    }

    public void setMoreimg(String moreimg) {
        this.moreimg = moreimg == null ? null : moreimg.trim();
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications == null ? null : specifications.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details == null ? null : details.trim();
    }
}