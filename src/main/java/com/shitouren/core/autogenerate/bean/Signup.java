package com.shitouren.core.autogenerate.bean;

import java.util.Date;

public class Signup {
    /**
     * 
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 参与发售id
     */
    private Integer isid;

    /**
     * 发售开始时间
     */
    private Date begintime;

    /**
     * 参与时间
     */
    private Date createtime;

    /**
     * 0.未公布1.未中签2.已中签
     */
    private Integer type;

    /**
     * 对应id
     */
    private Integer myorderid;

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

    public Integer getIsid() {
        return isid;
    }

    public void setIsid(Integer isid) {
        this.isid = isid;
    }

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMyorderid() {
        return myorderid;
    }

    public void setMyorderid(Integer myorderid) {
        this.myorderid = myorderid;
    }
}