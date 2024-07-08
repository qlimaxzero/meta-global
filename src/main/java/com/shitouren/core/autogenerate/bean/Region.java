package com.shitouren.core.autogenerate.bean;

public class Region {
    /**
     * 地区主键编号
     */
    private String regionId;

    /**
     * 地区名称
     */
    private String regionName;

    /**
     * 地区缩写
     */
    private String regionShortName;

    /**
     * 行政地区编号
     */
    private String regionCode;

    /**
     * 地区父id
     */
    private String regionParentId;

    /**
     * 地区级别 1-省、自治区、直辖市 2-地级市、地区、自治州、盟 3-市辖区、县级市、县
     */
    private Integer regionLevel;

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId == null ? null : regionId.trim();
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName == null ? null : regionName.trim();
    }

    public String getRegionShortName() {
        return regionShortName;
    }

    public void setRegionShortName(String regionShortName) {
        this.regionShortName = regionShortName == null ? null : regionShortName.trim();
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode == null ? null : regionCode.trim();
    }

    public String getRegionParentId() {
        return regionParentId;
    }

    public void setRegionParentId(String regionParentId) {
        this.regionParentId = regionParentId == null ? null : regionParentId.trim();
    }

    public Integer getRegionLevel() {
        return regionLevel;
    }

    public void setRegionLevel(Integer regionLevel) {
        this.regionLevel = regionLevel;
    }
}