package com.shitouren.core.autogenerate.bean;

import java.util.Date;

public class Yuanyuzhou {
    /**
     * ID
     */
    private Integer id;

    /**
     * 合并之后的ID
     */
    private Integer pid;

    /**
     * 大小
     */
    private String daxiao;

    /**
     * X位置
     */
    private Integer x;

    /**
     * Y位置
     */
    private Integer y;

    /**
     * 所有者
     */
    private String nickName;

    /**
     * 所有者ID
     */
    private Integer userId;

    /**
     * 得到时间
     */
    private Date time;

    /**
     * 图片地址
     */
    private String url;

    /**
     * 第二张图
     */
    private String url1;

    /**
     * 选择的藏品数据
     */
    private Integer usergrantid;

    /**
     * 网址
     */
    private String wangzhi;

    /**
     * 是否可以购买
     */
    private Integer isGoumai;

    /**
     * 简介
     */
    private String jianjie;

    /**
     * 建筑模板id
     */
    private Integer forwork;

    /**
     * 公共建筑标识(0否,1是)
     */
    private Integer publicFlag;

    /**
     * 建筑是否公开标识
     */
    private Integer overt;

    /**
     * 藏品id(所有藏品表id)
     */
    private Integer collid;

    /**
     * 用户藏品id(用户藏品表id)
     */
    private Integer bldgGrantid;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 公共建筑名称
     */
    private String publicName;

    /**
     * 是否开放:1-开放(默认)
     */
    private Byte open;

    /**
     * 状态 0:放开可进入,1:自己进入,2:好友进入
     */
    private Byte enter;

    /**
     * 网页地址用来跳转，用户自定义
     */
    private String webUrl;

    /**
     * 租赁价格 day:price,day:price
     */
    private String buyPrice;

    /**
     * 状态 0:可以租赁,1:不可以租赁
     */
    private Byte canBuy;

    /**
     *  租赁开始时间
     */
    private Date buyTime;

    /**
     *  租赁过期时间
     */
    private Date expireTime;

    /**
     * 租赁价格 day:jifen,day:jifen
     */
    private String buyPriceJifen;

    /**
     * 地块名称
     */
    private String landName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getDaxiao() {
        return daxiao;
    }

    public void setDaxiao(String daxiao) {
        this.daxiao = daxiao == null ? null : daxiao.trim();
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1 == null ? null : url1.trim();
    }

    public Integer getUsergrantid() {
        return usergrantid;
    }

    public void setUsergrantid(Integer usergrantid) {
        this.usergrantid = usergrantid;
    }

    public String getWangzhi() {
        return wangzhi;
    }

    public void setWangzhi(String wangzhi) {
        this.wangzhi = wangzhi == null ? null : wangzhi.trim();
    }

    public Integer getIsGoumai() {
        return isGoumai;
    }

    public void setIsGoumai(Integer isGoumai) {
        this.isGoumai = isGoumai;
    }

    public String getJianjie() {
        return jianjie;
    }

    public void setJianjie(String jianjie) {
        this.jianjie = jianjie == null ? null : jianjie.trim();
    }

    public Integer getForwork() {
        return forwork;
    }

    public void setForwork(Integer forwork) {
        this.forwork = forwork;
    }

    public Integer getPublicFlag() {
        return publicFlag;
    }

    public void setPublicFlag(Integer publicFlag) {
        this.publicFlag = publicFlag;
    }

    public Integer getOvert() {
        return overt;
    }

    public void setOvert(Integer overt) {
        this.overt = overt;
    }

    public Integer getCollid() {
        return collid;
    }

    public void setCollid(Integer collid) {
        this.collid = collid;
    }

    public Integer getBldgGrantid() {
        return bldgGrantid;
    }

    public void setBldgGrantid(Integer bldgGrantid) {
        this.bldgGrantid = bldgGrantid;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName == null ? null : publicName.trim();
    }

    public Byte getOpen() {
        return open;
    }

    public void setOpen(Byte open) {
        this.open = open;
    }

    public Byte getEnter() {
        return enter;
    }

    public void setEnter(Byte enter) {
        this.enter = enter;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl == null ? null : webUrl.trim();
    }

    public String getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice == null ? null : buyPrice.trim();
    }

    public Byte getCanBuy() {
        return canBuy;
    }

    public void setCanBuy(Byte canBuy) {
        this.canBuy = canBuy;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getBuyPriceJifen() {
        return buyPriceJifen;
    }

    public void setBuyPriceJifen(String buyPriceJifen) {
        this.buyPriceJifen = buyPriceJifen == null ? null : buyPriceJifen.trim();
    }

    public String getLandName() {
        return landName;
    }

    public void setLandName(String landName) {
        this.landName = landName == null ? null : landName.trim();
    }
}