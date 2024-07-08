package com.shitouren.core.autogenerate.bean;

import java.math.BigDecimal;
import java.util.Date;

public class MyOrder {
    /**
     *
     */
    private Integer id;

    /**
     * 订单编号
     */
    private String orderno;

    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 藏品id
     */
    private Integer collid;

    /**
     * 1.藏品2.盲盒
     */
    private Integer istype;

    /**
     * 参与形式(1.出售2.抽签)
     */
    private Integer ginsengtype;

    /**
     * 订单金额
     */
    private BigDecimal price;

    /**
     * 0.待付款1.已完成2.已取消-1.支付未回调
     */
    private Integer ordertype;

    /**
     * -1.未支付1.未发放2.已发放 -> 改为数量
     */
    private Integer grants;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 付款截止时间
     */
    private Date endtime;

    /**
     * 0.暂无1.平台 2.微信 3.支付宝 4.其他
     */
    private Integer paytype;

    /**
     *
     */
    private Integer cyid;

    /**
     * 0未使用1使用
     */
    private Integer buycard;

    /**
     * 元宇宙ID 默认0  不是0 了就是元宇宙ID
     */
    private Integer yuanyuzhouid;

    /**
     * 发售id
     */
    private Integer issueId;

    /**
     * 外部单号
     */
    private String outOrderNo;

    /**
     * 链上交易id
     */
    private String txid;

    /**
     * 支付成功时间
     */
    private Date successTime;

    /**
     * 该用户钱包地址
     */
    private String fromAddr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
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

    public Integer getIstype() {
        return istype;
    }

    public void setIstype(Integer istype) {
        this.istype = istype;
    }

    public Integer getGinsengtype() {
        return ginsengtype;
    }

    public void setGinsengtype(Integer ginsengtype) {
        this.ginsengtype = ginsengtype;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(Integer ordertype) {
        this.ordertype = ordertype;
    }

    public Integer getGrants() {
        return grants;
    }

    public void setGrants(Integer grants) {
        this.grants = grants;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getPaytype() {
        return paytype;
    }

    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }

    public Integer getCyid() {
        return cyid;
    }

    public void setCyid(Integer cyid) {
        this.cyid = cyid;
    }

    public Integer getBuycard() {
        return buycard;
    }

    public void setBuycard(Integer buycard) {
        this.buycard = buycard;
    }

    public Integer getYuanyuzhouid() {
        return yuanyuzhouid;
    }

    public void setYuanyuzhouid(Integer yuanyuzhouid) {
        this.yuanyuzhouid = yuanyuzhouid;
    }

    public Integer getIssueId() {
        return issueId;
    }

    public void setIssueId(Integer issueId) {
        this.issueId = issueId;
    }

    public String getOutOrderNo() {
        return outOrderNo;
    }

    public void setOutOrderNo(String outOrderNo) {
        this.outOrderNo = outOrderNo == null ? null : outOrderNo.trim();
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid == null ? null : txid.trim();
    }

    public Date getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(Date successTime) {
        this.successTime = successTime;
    }

    public String getFromAddr() {
        return fromAddr;
    }

    public void setFromAddr(String fromAddr) {
        this.fromAddr = fromAddr == null ? null : fromAddr.trim();
    }
}
