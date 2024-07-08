package com.shitouren.core.autogenerate.bean;

public class Bank {
    /**
     * 
     */
    private Integer id;

    /**
     * 银行名称
     */
    private String name;

    /**
     * 说明
     */
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}