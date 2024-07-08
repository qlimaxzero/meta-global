package com.shitouren.core.autogenerate.bean;

public class Shareimg {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String img;

    /**
     * 0隐藏1显示
     */
    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}