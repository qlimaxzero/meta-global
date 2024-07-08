package com.shitouren.core.autogenerate.bean;

import java.util.Date;

public class Pullnew {
    /**
     * 
     */
    private Integer id;

    /**
     * 0活动关闭1活动开启
     */
    private Integer openactivity;

    /**
     * 
     */
    private Date openactivitycreattime;

    /**
     * 
     */
    private Date openactivityendtime;

    /**
     * 0盲盒不赠送1盲盒赠送
     */
    private Integer openblind;

    /**
     * 盲盒id
     */
    private Integer blindboxid;

    /**
     * 0全部1只统计实名
     */
    private Integer realnameblind;

    /**
     * 递增信息
     */
    private String ladderinfo;

    /**
     * 0藏品不赠送1藏品赠送
     */
    private Integer opencollection;

    /**
     * 藏品id
     */
    private Integer collectionid;

    /**
     * 0全部1只统计实名
     */
    private Integer realnamecollection;

    /**
     * 藏品递增信息
     */
    private String colladderinfo;

    /**
     * 新用户注册赠送0不赠送1赠送
     */
    private Integer newsend;

    /**
     * 是否需要实名0不需要1需要
     */
    private Integer isrealname;

    /**
     * 0盲盒不赠送1盲盒赠送
     */
    private Integer isendblind;

    /**
     * 盲盒id
     */
    private Integer newblindid;

    /**
     * 0藏品不赠送1藏品赠送
     */
    private Integer isendcol;

    /**
     * 藏品id
     */
    private Integer newcollectionid;

    /**
     * 限制数量
     */
    private Integer isnumber;

    /**
     * 实际剩余数量
     */
    private Integer number;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOpenactivity() {
        return openactivity;
    }

    public void setOpenactivity(Integer openactivity) {
        this.openactivity = openactivity;
    }

    public Date getOpenactivitycreattime() {
        return openactivitycreattime;
    }

    public void setOpenactivitycreattime(Date openactivitycreattime) {
        this.openactivitycreattime = openactivitycreattime;
    }

    public Date getOpenactivityendtime() {
        return openactivityendtime;
    }

    public void setOpenactivityendtime(Date openactivityendtime) {
        this.openactivityendtime = openactivityendtime;
    }

    public Integer getOpenblind() {
        return openblind;
    }

    public void setOpenblind(Integer openblind) {
        this.openblind = openblind;
    }

    public Integer getBlindboxid() {
        return blindboxid;
    }

    public void setBlindboxid(Integer blindboxid) {
        this.blindboxid = blindboxid;
    }

    public Integer getRealnameblind() {
        return realnameblind;
    }

    public void setRealnameblind(Integer realnameblind) {
        this.realnameblind = realnameblind;
    }

    public String getLadderinfo() {
        return ladderinfo;
    }

    public void setLadderinfo(String ladderinfo) {
        this.ladderinfo = ladderinfo == null ? null : ladderinfo.trim();
    }

    public Integer getOpencollection() {
        return opencollection;
    }

    public void setOpencollection(Integer opencollection) {
        this.opencollection = opencollection;
    }

    public Integer getCollectionid() {
        return collectionid;
    }

    public void setCollectionid(Integer collectionid) {
        this.collectionid = collectionid;
    }

    public Integer getRealnamecollection() {
        return realnamecollection;
    }

    public void setRealnamecollection(Integer realnamecollection) {
        this.realnamecollection = realnamecollection;
    }

    public String getColladderinfo() {
        return colladderinfo;
    }

    public void setColladderinfo(String colladderinfo) {
        this.colladderinfo = colladderinfo == null ? null : colladderinfo.trim();
    }

    public Integer getNewsend() {
        return newsend;
    }

    public void setNewsend(Integer newsend) {
        this.newsend = newsend;
    }

    public Integer getIsrealname() {
        return isrealname;
    }

    public void setIsrealname(Integer isrealname) {
        this.isrealname = isrealname;
    }

    public Integer getIsendblind() {
        return isendblind;
    }

    public void setIsendblind(Integer isendblind) {
        this.isendblind = isendblind;
    }

    public Integer getNewblindid() {
        return newblindid;
    }

    public void setNewblindid(Integer newblindid) {
        this.newblindid = newblindid;
    }

    public Integer getIsendcol() {
        return isendcol;
    }

    public void setIsendcol(Integer isendcol) {
        this.isendcol = isendcol;
    }

    public Integer getNewcollectionid() {
        return newcollectionid;
    }

    public void setNewcollectionid(Integer newcollectionid) {
        this.newcollectionid = newcollectionid;
    }

    public Integer getIsnumber() {
        return isnumber;
    }

    public void setIsnumber(Integer isnumber) {
        this.isnumber = isnumber;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}