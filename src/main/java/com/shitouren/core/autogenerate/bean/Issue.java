package com.shitouren.core.autogenerate.bean;

import java.util.Date;

public class Issue {
    /**
     * 
     */
    private Integer id;

    /**
     * 1.藏品2.盲盒
     */
    private Integer istype;

    /**
     * 绑定id
     */
    private Integer collid;


    /**
     * 参与形式(1.出售2.抽签)
     */
    private Integer ginsengtype;

    /**
     * 开售时间
     */
    private Date releasetime;

    /**
     * 结束时间
     */
    private Date endtime;

    /**
     * 预售数量
     */
    private Integer presale;

    /**
     * 已售数量
     */
    private Integer sold;

    /**
     * 每人限购数量
     */
    private Integer limitcount;

    /**
     * 抽奖抽取中奖人数
     */
    private Integer ginscount;

    /**
     * 0.未上架1.已上架
     */
    private Integer type;

    /**
     * (抽签专用0.未抽签1.已抽签)
     */
    private Integer checkout;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIstype() {
        return istype;
    }

    public void setIstype(Integer istype) {
        this.istype = istype;
    }

    public Integer getCollid() {
        return collid;
    }

    public void setCollid(Integer collid) {
        this.collid = collid;
    }

    public Integer getGinsengtype() {
        return ginsengtype;
    }

    public void setGinsengtype(Integer ginsengtype) {
        this.ginsengtype = ginsengtype;
    }

    public Date getReleasetime() {
        return releasetime;
    }

    public void setReleasetime(Date releasetime) {
        this.releasetime = releasetime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getPresale() {
        return presale;
    }

    public void setPresale(Integer presale) {
        this.presale = presale;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    public Integer getLimitcount() {
        return limitcount;
    }

    public void setLimitcount(Integer limitcount) {
        this.limitcount = limitcount;
    }

    public Integer getGinscount() {
        return ginscount;
    }

    public void setGinscount(Integer ginscount) {
        this.ginscount = ginscount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCheckout() {
        return checkout;
    }

    public void setCheckout(Integer checkout) {
        this.checkout = checkout;
    }
}