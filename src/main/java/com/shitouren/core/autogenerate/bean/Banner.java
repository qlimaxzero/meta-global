package com.shitouren.core.autogenerate.bean;

public class Banner {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String img;

    /**
     * 
     */
    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}