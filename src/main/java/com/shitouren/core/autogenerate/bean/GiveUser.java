package com.shitouren.core.autogenerate.bean;

public class GiveUser {
    /**
     * 
     */
    private Integer id;

    /**
     * 1.盲盒2.藏品
     */
    private Integer type;

    /**
     * 赠送id
     */
    private Integer collid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCollid() {
        return collid;
    }

    public void setCollid(Integer collid) {
        this.collid = collid;
    }
}