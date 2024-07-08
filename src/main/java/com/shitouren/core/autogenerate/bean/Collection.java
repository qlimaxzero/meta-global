package com.shitouren.core.autogenerate.bean;

import java.math.BigDecimal;
import java.util.Date;

public class Collection {
    /**
     * id
     */
    private Integer id;

    /**
     * 藏品图
     */
    private String img;

    /**
     * 藏品名称
     */
    private String name;

    /**
     * 藏品缩写
     */
    private String minname;

    /**
     * 限量
     */
    private Integer limits;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 视频
     */
    private String video;

    /**
     * 详情展示图
     */
    private String img1;

    /**
     * 发行者
     */
    private String publisher;

    /**
     * 创作者
     */
    private String creator;

    /**
     * 创作者头像
     */
    private String creatorimg;

    /**
     * 0未部署1已部署
     */
    private Integer isdeploy;

    /**
     * 合约地址
     */
    private String contractaddress;

    /**
     * 已售
     */
    private Integer sold;

    /**
     * 碎片专属可售库存
     */
    private Integer stockc;

    /**
     * 编号设置
     */
    private String nosetup;

    /**
     * 作品介绍
     */
    private String introduce;

    /**
     * 分类id
     */
    private Integer classid;

    /**
     * 0数字藏品1nft藏品2创作者藏品
     */
    private Integer type;

    /**
     * 0无需1所需藏品
     */
    private Integer mustcomid;

    /**
     * 出售锁单时间
     */
    private Integer selltime;

    /**
     * 转赠锁单时间
     */
    private Integer sendlocktime;

    /**
     * 0禁止出售1可以出售
     */
    private Integer selltype;

    /**
     * 0禁止转赠1可以转赠
     */
    private Integer sendtype;

    /**
     * 0所有1微信2支付宝
     */
    private String paytype;

    /**
     * 0不可使用1可以使用
     */
    private Integer buycard;

    /**
     * 折扣力度
     */
    private BigDecimal buycardsize;

    /**
     * 下载链接
     */
    private String link;

    /**
     * 链接地址
     */
    private String url;

    /**
     * 发行数量
     */
    private Integer publishquantity;

    /**
     * 发行时间
     */
    private Date publishtime;

    /**
     * 藏品子类型
     */
    private Integer subtype;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 商用类型 0:所有店铺可用; 其他根据映射表
     */
    private Integer useType;

    /**
     * 持有积分天数
     */
    private Integer pointsDays;

    /**
     * 增加积分数量,配合points_days使用
     */
    private Integer pointsNum;

    /**
     * 算力
     */
    private BigDecimal power;

    /**
     * 每日字数限制
     */
    private Integer wordLimit;

    /**
     * 详情
     */
    private String details;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMinname() {
        return minname;
    }

    public void setMinname(String minname) {
        this.minname = minname == null ? null : minname.trim();
    }

    public Integer getLimits() {
        return limits;
    }

    public void setLimits(Integer limits) {
        this.limits = limits;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video == null ? null : video.trim();
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1 == null ? null : img1.trim();
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher == null ? null : publisher.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getCreatorimg() {
        return creatorimg;
    }

    public void setCreatorimg(String creatorimg) {
        this.creatorimg = creatorimg == null ? null : creatorimg.trim();
    }

    public Integer getIsdeploy() {
        return isdeploy;
    }

    public void setIsdeploy(Integer isdeploy) {
        this.isdeploy = isdeploy;
    }

    public String getContractaddress() {
        return contractaddress;
    }

    public void setContractaddress(String contractaddress) {
        this.contractaddress = contractaddress == null ? null : contractaddress.trim();
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    public Integer getStockc() {
        return stockc;
    }

    public void setStockc(Integer stockc) {
        this.stockc = stockc;
    }

    public String getNosetup() {
        return nosetup;
    }

    public void setNosetup(String nosetup) {
        this.nosetup = nosetup == null ? null : nosetup.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMustcomid() {
        return mustcomid;
    }

    public void setMustcomid(Integer mustcomid) {
        this.mustcomid = mustcomid;
    }

    public Integer getSelltime() {
        return selltime;
    }

    public void setSelltime(Integer selltime) {
        this.selltime = selltime;
    }

    public Integer getSendlocktime() {
        return sendlocktime;
    }

    public void setSendlocktime(Integer sendlocktime) {
        this.sendlocktime = sendlocktime;
    }

    public Integer getSelltype() {
        return selltype;
    }

    public void setSelltype(Integer selltype) {
        this.selltype = selltype;
    }

    public Integer getSendtype() {
        return sendtype;
    }

    public void setSendtype(Integer sendtype) {
        this.sendtype = sendtype;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype == null ? null : paytype.trim();
    }

    public Integer getBuycard() {
        return buycard;
    }

    public void setBuycard(Integer buycard) {
        this.buycard = buycard;
    }

    public BigDecimal getBuycardsize() {
        return buycardsize;
    }

    public void setBuycardsize(BigDecimal buycardsize) {
        this.buycardsize = buycardsize;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getPublishquantity() {
        return publishquantity;
    }

    public void setPublishquantity(Integer publishquantity) {
        this.publishquantity = publishquantity;
    }

    public Date getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    public Integer getSubtype() {
        return subtype;
    }

    public void setSubtype(Integer subtype) {
        this.subtype = subtype;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUseType() {
        return useType;
    }

    public void setUseType(Integer useType) {
        this.useType = useType;
    }

    public Integer getPointsDays() {
        return pointsDays;
    }

    public void setPointsDays(Integer pointsDays) {
        this.pointsDays = pointsDays;
    }

    public Integer getPointsNum() {
        return pointsNum;
    }

    public void setPointsNum(Integer pointsNum) {
        this.pointsNum = pointsNum;
    }

    public BigDecimal getPower() {
        return power;
    }

    public void setPower(BigDecimal power) {
        this.power = power;
    }

    public Integer getWordLimit() {
        return wordLimit;
    }

    public void setWordLimit(Integer wordLimit) {
        this.wordLimit = wordLimit;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details == null ? null : details.trim();
    }
}