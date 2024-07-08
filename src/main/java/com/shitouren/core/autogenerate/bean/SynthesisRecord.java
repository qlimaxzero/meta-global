package com.shitouren.core.autogenerate.bean;

import java.util.Date;

public class SynthesisRecord {
    /**
     * id
     */
    private Integer id;

    /**
     * 用户
     */
    private Integer userid;

    /**
     * 名称
     */
    private String name;

    /**
     * 时间
     */
    private Date createtime;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}