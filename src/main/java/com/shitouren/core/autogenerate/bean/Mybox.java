package com.shitouren.core.autogenerate.bean;

public class Mybox {
    /**
     * 
     */
    private Integer id;

    /**
     * 盲盒编号
     */
    private String no;

    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 盲盒id
     */
    private Integer boxid;

    /**
     * 碎片id
     */
    private Integer spid;

    /**
     * 0未开启1.已开启
     */
    private Integer type;

    /**
     * 
     */
    private Integer orderid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getBoxid() {
        return boxid;
    }

    public void setBoxid(Integer boxid) {
        this.boxid = boxid;
    }

    public Integer getSpid() {
        return spid;
    }

    public void setSpid(Integer spid) {
        this.spid = spid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }
}