package com.shitouren.core.autogenerate.bean;

import java.util.Date;

public class Synthesis {
    /**
     * 
     */
    private Integer id;

    /**
     * 合成藏品
     */
    private Integer collid;

    /**
     * 合成藏品名称
     */
    private String name;

    /**
     * 合成藏品图片
     */
    private String img;

    /**
     * 合成开启时间
     */
    private Date sisbigtime;

    /**
     * 合成结束时间
     */
    private Date sisendtime;

    /**
     * 总限量
     */
    private Integer limitcount;

    /**
     * 已合成
     */
    private Integer synthesized;

    /**
     * 每人限量
     */
    private Integer userlimit;

    /**
     * 合成材料
     */
    private String matsci;

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

    public Date getSisbigtime() {
        return sisbigtime;
    }

    public void setSisbigtime(Date sisbigtime) {
        this.sisbigtime = sisbigtime;
    }

    public Date getSisendtime() {
        return sisendtime;
    }

    public void setSisendtime(Date sisendtime) {
        this.sisendtime = sisendtime;
    }

    public Integer getLimitcount() {
        return limitcount;
    }

    public void setLimitcount(Integer limitcount) {
        this.limitcount = limitcount;
    }

    public Integer getSynthesized() {
        return synthesized;
    }

    public void setSynthesized(Integer synthesized) {
        this.synthesized = synthesized;
    }

    public Integer getUserlimit() {
        return userlimit;
    }

    public void setUserlimit(Integer userlimit) {
        this.userlimit = userlimit;
    }

    public String getMatsci() {
        return matsci;
    }

    public void setMatsci(String matsci) {
        this.matsci = matsci == null ? null : matsci.trim();
    }
}