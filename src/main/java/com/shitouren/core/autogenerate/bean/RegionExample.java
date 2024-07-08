package com.shitouren.core.autogenerate.bean;

import java.util.ArrayList;
import java.util.List;

public class RegionExample {
    /**
     * region
     */
    protected String orderByClause;

    /**
     * region
     */
    protected boolean distinct;

    /**
     * region
     */
    protected List<Criteria> oredCriteria;

    /**
     * region
     */
    protected Integer pageNo = 1;

    /**
     * region
     */
    protected Integer startRow;

    /**
     * region
     */
    protected Integer pageSize = 10;

    public RegionExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo=pageNo;
        this.startRow = (pageNo-1)*this.pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setStartRow(Integer startRow) {
        this.startRow=startRow;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize=pageSize;
        this.startRow = (pageNo-1)*this.pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * region 
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andRegionIdIsNull() {
            addCriterion("region_id is null");
            return (Criteria) this;
        }

        public Criteria andRegionIdIsNotNull() {
            addCriterion("region_id is not null");
            return (Criteria) this;
        }

        public Criteria andRegionIdEqualTo(String value) {
            addCriterion("region_id =", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdNotEqualTo(String value) {
            addCriterion("region_id <>", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdGreaterThan(String value) {
            addCriterion("region_id >", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdGreaterThanOrEqualTo(String value) {
            addCriterion("region_id >=", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdLessThan(String value) {
            addCriterion("region_id <", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdLessThanOrEqualTo(String value) {
            addCriterion("region_id <=", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdLike(String value) {
            addCriterion("region_id like", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdNotLike(String value) {
            addCriterion("region_id not like", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdIn(List<String> values) {
            addCriterion("region_id in", values, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdNotIn(List<String> values) {
            addCriterion("region_id not in", values, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdBetween(String value1, String value2) {
            addCriterion("region_id between", value1, value2, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdNotBetween(String value1, String value2) {
            addCriterion("region_id not between", value1, value2, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionNameIsNull() {
            addCriterion("region_name is null");
            return (Criteria) this;
        }

        public Criteria andRegionNameIsNotNull() {
            addCriterion("region_name is not null");
            return (Criteria) this;
        }

        public Criteria andRegionNameEqualTo(String value) {
            addCriterion("region_name =", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameNotEqualTo(String value) {
            addCriterion("region_name <>", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameGreaterThan(String value) {
            addCriterion("region_name >", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameGreaterThanOrEqualTo(String value) {
            addCriterion("region_name >=", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameLessThan(String value) {
            addCriterion("region_name <", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameLessThanOrEqualTo(String value) {
            addCriterion("region_name <=", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameLike(String value) {
            addCriterion("region_name like", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameNotLike(String value) {
            addCriterion("region_name not like", value, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameIn(List<String> values) {
            addCriterion("region_name in", values, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameNotIn(List<String> values) {
            addCriterion("region_name not in", values, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameBetween(String value1, String value2) {
            addCriterion("region_name between", value1, value2, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionNameNotBetween(String value1, String value2) {
            addCriterion("region_name not between", value1, value2, "regionName");
            return (Criteria) this;
        }

        public Criteria andRegionShortNameIsNull() {
            addCriterion("region_short_name is null");
            return (Criteria) this;
        }

        public Criteria andRegionShortNameIsNotNull() {
            addCriterion("region_short_name is not null");
            return (Criteria) this;
        }

        public Criteria andRegionShortNameEqualTo(String value) {
            addCriterion("region_short_name =", value, "regionShortName");
            return (Criteria) this;
        }

        public Criteria andRegionShortNameNotEqualTo(String value) {
            addCriterion("region_short_name <>", value, "regionShortName");
            return (Criteria) this;
        }

        public Criteria andRegionShortNameGreaterThan(String value) {
            addCriterion("region_short_name >", value, "regionShortName");
            return (Criteria) this;
        }

        public Criteria andRegionShortNameGreaterThanOrEqualTo(String value) {
            addCriterion("region_short_name >=", value, "regionShortName");
            return (Criteria) this;
        }

        public Criteria andRegionShortNameLessThan(String value) {
            addCriterion("region_short_name <", value, "regionShortName");
            return (Criteria) this;
        }

        public Criteria andRegionShortNameLessThanOrEqualTo(String value) {
            addCriterion("region_short_name <=", value, "regionShortName");
            return (Criteria) this;
        }

        public Criteria andRegionShortNameLike(String value) {
            addCriterion("region_short_name like", value, "regionShortName");
            return (Criteria) this;
        }

        public Criteria andRegionShortNameNotLike(String value) {
            addCriterion("region_short_name not like", value, "regionShortName");
            return (Criteria) this;
        }

        public Criteria andRegionShortNameIn(List<String> values) {
            addCriterion("region_short_name in", values, "regionShortName");
            return (Criteria) this;
        }

        public Criteria andRegionShortNameNotIn(List<String> values) {
            addCriterion("region_short_name not in", values, "regionShortName");
            return (Criteria) this;
        }

        public Criteria andRegionShortNameBetween(String value1, String value2) {
            addCriterion("region_short_name between", value1, value2, "regionShortName");
            return (Criteria) this;
        }

        public Criteria andRegionShortNameNotBetween(String value1, String value2) {
            addCriterion("region_short_name not between", value1, value2, "regionShortName");
            return (Criteria) this;
        }

        public Criteria andRegionCodeIsNull() {
            addCriterion("region_code is null");
            return (Criteria) this;
        }

        public Criteria andRegionCodeIsNotNull() {
            addCriterion("region_code is not null");
            return (Criteria) this;
        }

        public Criteria andRegionCodeEqualTo(String value) {
            addCriterion("region_code =", value, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeNotEqualTo(String value) {
            addCriterion("region_code <>", value, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeGreaterThan(String value) {
            addCriterion("region_code >", value, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeGreaterThanOrEqualTo(String value) {
            addCriterion("region_code >=", value, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeLessThan(String value) {
            addCriterion("region_code <", value, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeLessThanOrEqualTo(String value) {
            addCriterion("region_code <=", value, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeLike(String value) {
            addCriterion("region_code like", value, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeNotLike(String value) {
            addCriterion("region_code not like", value, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeIn(List<String> values) {
            addCriterion("region_code in", values, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeNotIn(List<String> values) {
            addCriterion("region_code not in", values, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeBetween(String value1, String value2) {
            addCriterion("region_code between", value1, value2, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeNotBetween(String value1, String value2) {
            addCriterion("region_code not between", value1, value2, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionParentIdIsNull() {
            addCriterion("region_parent_id is null");
            return (Criteria) this;
        }

        public Criteria andRegionParentIdIsNotNull() {
            addCriterion("region_parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andRegionParentIdEqualTo(String value) {
            addCriterion("region_parent_id =", value, "regionParentId");
            return (Criteria) this;
        }

        public Criteria andRegionParentIdNotEqualTo(String value) {
            addCriterion("region_parent_id <>", value, "regionParentId");
            return (Criteria) this;
        }

        public Criteria andRegionParentIdGreaterThan(String value) {
            addCriterion("region_parent_id >", value, "regionParentId");
            return (Criteria) this;
        }

        public Criteria andRegionParentIdGreaterThanOrEqualTo(String value) {
            addCriterion("region_parent_id >=", value, "regionParentId");
            return (Criteria) this;
        }

        public Criteria andRegionParentIdLessThan(String value) {
            addCriterion("region_parent_id <", value, "regionParentId");
            return (Criteria) this;
        }

        public Criteria andRegionParentIdLessThanOrEqualTo(String value) {
            addCriterion("region_parent_id <=", value, "regionParentId");
            return (Criteria) this;
        }

        public Criteria andRegionParentIdLike(String value) {
            addCriterion("region_parent_id like", value, "regionParentId");
            return (Criteria) this;
        }

        public Criteria andRegionParentIdNotLike(String value) {
            addCriterion("region_parent_id not like", value, "regionParentId");
            return (Criteria) this;
        }

        public Criteria andRegionParentIdIn(List<String> values) {
            addCriterion("region_parent_id in", values, "regionParentId");
            return (Criteria) this;
        }

        public Criteria andRegionParentIdNotIn(List<String> values) {
            addCriterion("region_parent_id not in", values, "regionParentId");
            return (Criteria) this;
        }

        public Criteria andRegionParentIdBetween(String value1, String value2) {
            addCriterion("region_parent_id between", value1, value2, "regionParentId");
            return (Criteria) this;
        }

        public Criteria andRegionParentIdNotBetween(String value1, String value2) {
            addCriterion("region_parent_id not between", value1, value2, "regionParentId");
            return (Criteria) this;
        }

        public Criteria andRegionLevelIsNull() {
            addCriterion("region_level is null");
            return (Criteria) this;
        }

        public Criteria andRegionLevelIsNotNull() {
            addCriterion("region_level is not null");
            return (Criteria) this;
        }

        public Criteria andRegionLevelEqualTo(Integer value) {
            addCriterion("region_level =", value, "regionLevel");
            return (Criteria) this;
        }

        public Criteria andRegionLevelNotEqualTo(Integer value) {
            addCriterion("region_level <>", value, "regionLevel");
            return (Criteria) this;
        }

        public Criteria andRegionLevelGreaterThan(Integer value) {
            addCriterion("region_level >", value, "regionLevel");
            return (Criteria) this;
        }

        public Criteria andRegionLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("region_level >=", value, "regionLevel");
            return (Criteria) this;
        }

        public Criteria andRegionLevelLessThan(Integer value) {
            addCriterion("region_level <", value, "regionLevel");
            return (Criteria) this;
        }

        public Criteria andRegionLevelLessThanOrEqualTo(Integer value) {
            addCriterion("region_level <=", value, "regionLevel");
            return (Criteria) this;
        }

        public Criteria andRegionLevelIn(List<Integer> values) {
            addCriterion("region_level in", values, "regionLevel");
            return (Criteria) this;
        }

        public Criteria andRegionLevelNotIn(List<Integer> values) {
            addCriterion("region_level not in", values, "regionLevel");
            return (Criteria) this;
        }

        public Criteria andRegionLevelBetween(Integer value1, Integer value2) {
            addCriterion("region_level between", value1, value2, "regionLevel");
            return (Criteria) this;
        }

        public Criteria andRegionLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("region_level not between", value1, value2, "regionLevel");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * region 
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}