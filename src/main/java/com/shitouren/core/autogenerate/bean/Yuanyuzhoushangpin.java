package com.shitouren.core.autogenerate.bean;

import java.util.Date;

public class Yuanyuzhoushangpin {
    /**
     * ID
     */
    private Integer id;

    /**
     * 名字
     */
    private String name;

    /**
     * 图片地址
     */
    private String url;

    /**
     * 
     */
    private String wangzhi;

    /**
     * 地图ID
     */
    private Integer yuanyuzhouid;

    /**
     * 
     */
    private Date addtime;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getWangzhi() {
        return wangzhi;
    }

    public void setWangzhi(String wangzhi) {
        this.wangzhi = wangzhi == null ? null : wangzhi.trim();
    }

    public Integer getYuanyuzhouid() {
        return yuanyuzhouid;
    }

    public void setYuanyuzhouid(Integer yuanyuzhouid) {
        this.yuanyuzhouid = yuanyuzhouid;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}