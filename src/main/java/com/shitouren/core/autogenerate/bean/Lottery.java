package com.shitouren.core.autogenerate.bean;

import java.math.BigDecimal;

public class Lottery {
    /**
     * 
     */
    private Integer id;

    /**
     * 描述
     */
    private String describtion;

    /**
     * 积分奖励
     */
    private BigDecimal wineDropCount;

    /**
     * 概率
     */
    private Integer probability;

    /**
     * 实物id
     */
    private Integer collid;

    /**
     * 0谢谢惠顾1优先购2实物3藏品
     */
    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion == null ? null : describtion.trim();
    }

    public BigDecimal getWineDropCount() {
        return wineDropCount;
    }

    public void setWineDropCount(BigDecimal wineDropCount) {
        this.wineDropCount = wineDropCount;
    }

    public Integer getProbability() {
        return probability;
    }

    public void setProbability(Integer probability) {
        this.probability = probability;
    }

    public Integer getCollid() {
        return collid;
    }

    public void setCollid(Integer collid) {
        this.collid = collid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}