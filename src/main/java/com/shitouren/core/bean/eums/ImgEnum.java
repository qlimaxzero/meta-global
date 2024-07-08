package com.shitouren.core.bean.eums;

public enum ImgEnum {

//    img("https://cdn.dopaimeta.net", "/home/ruoyi/uploadPath/", ""),

    //阿里oss
    img("https://oss.dopaimeta.net", "/home/ruoyi/uploadPath/", "","/tmp/dopai/img/"),

    QrCode("https://oss.dopaimeta.net", "/home/ruoyi/uploadPath/", "","/tmp/dopai/qrcodes/");


    private String url;
    private String path;
    private String src;
    private String tmp;

    ImgEnum(String url, String path, String src, String tmp) {
        this.url = url;
        this.path = path;
        this.src = src;
        this.tmp = tmp;
    }

    public String getUrl() {
        return url;
    }

    public String getPath() {
        return path;
    }

    public String getSrc() {
        return src;
    }

    public String getTmp() {
        return tmp;
    }
}
