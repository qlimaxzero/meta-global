package com.shitouren.core.autogenerate.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SynthesisExample {
    /**
     * synthesis
     */
    protected String orderByClause;

    /**
     * synthesis
     */
    protected boolean distinct;

    /**
     * synthesis
     */
    protected List<Criteria> oredCriteria;

    /**
     * synthesis
     */
    protected Integer pageNo = 1;

    /**
     * synthesis
     */
    protected Integer startRow;

    /**
     * synthesis
     */
    protected Integer pageSize = 10;

    public SynthesisExample() {
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
     * synthesis 
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCollidIsNull() {
            addCriterion("collid is null");
            return (Criteria) this;
        }

        public Criteria andCollidIsNotNull() {
            addCriterion("collid is not null");
            return (Criteria) this;
        }

        public Criteria andCollidEqualTo(Integer value) {
            addCriterion("collid =", value, "collid");
            return (Criteria) this;
        }

        public Criteria andCollidNotEqualTo(Integer value) {
            addCriterion("collid <>", value, "collid");
            return (Criteria) this;
        }

        public Criteria andCollidGreaterThan(Integer value) {
            addCriterion("collid >", value, "collid");
            return (Criteria) this;
        }

        public Criteria andCollidGreaterThanOrEqualTo(Integer value) {
            addCriterion("collid >=", value, "collid");
            return (Criteria) this;
        }

        public Criteria andCollidLessThan(Integer value) {
            addCriterion("collid <", value, "collid");
            return (Criteria) this;
        }

        public Criteria andCollidLessThanOrEqualTo(Integer value) {
            addCriterion("collid <=", value, "collid");
            return (Criteria) this;
        }

        public Criteria andCollidIn(List<Integer> values) {
            addCriterion("collid in", values, "collid");
            return (Criteria) this;
        }

        public Criteria andCollidNotIn(List<Integer> values) {
            addCriterion("collid not in", values, "collid");
            return (Criteria) this;
        }

        public Criteria andCollidBetween(Integer value1, Integer value2) {
            addCriterion("collid between", value1, value2, "collid");
            return (Criteria) this;
        }

        public Criteria andCollidNotBetween(Integer value1, Integer value2) {
            addCriterion("collid not between", value1, value2, "collid");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andImgIsNull() {
            addCriterion("img is null");
            return (Criteria) this;
        }

        public Criteria andImgIsNotNull() {
            addCriterion("img is not null");
            return (Criteria) this;
        }

        public Criteria andImgEqualTo(String value) {
            addCriterion("img =", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotEqualTo(String value) {
            addCriterion("img <>", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgGreaterThan(String value) {
            addCriterion("img >", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgGreaterThanOrEqualTo(String value) {
            addCriterion("img >=", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLessThan(String value) {
            addCriterion("img <", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLessThanOrEqualTo(String value) {
            addCriterion("img <=", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLike(String value) {
            addCriterion("img like", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotLike(String value) {
            addCriterion("img not like", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgIn(List<String> values) {
            addCriterion("img in", values, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotIn(List<String> values) {
            addCriterion("img not in", values, "img");
            return (Criteria) this;
        }

        public Criteria andImgBetween(String value1, String value2) {
            addCriterion("img between", value1, value2, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotBetween(String value1, String value2) {
            addCriterion("img not between", value1, value2, "img");
            return (Criteria) this;
        }

        public Criteria andSisbigtimeIsNull() {
            addCriterion("sisbigtime is null");
            return (Criteria) this;
        }

        public Criteria andSisbigtimeIsNotNull() {
            addCriterion("sisbigtime is not null");
            return (Criteria) this;
        }

        public Criteria andSisbigtimeEqualTo(Date value) {
            addCriterion("sisbigtime =", value, "sisbigtime");
            return (Criteria) this;
        }

        public Criteria andSisbigtimeNotEqualTo(Date value) {
            addCriterion("sisbigtime <>", value, "sisbigtime");
            return (Criteria) this;
        }

        public Criteria andSisbigtimeGreaterThan(Date value) {
            addCriterion("sisbigtime >", value, "sisbigtime");
            return (Criteria) this;
        }

        public Criteria andSisbigtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("sisbigtime >=", value, "sisbigtime");
            return (Criteria) this;
        }

        public Criteria andSisbigtimeLessThan(Date value) {
            addCriterion("sisbigtime <", value, "sisbigtime");
            return (Criteria) this;
        }

        public Criteria andSisbigtimeLessThanOrEqualTo(Date value) {
            addCriterion("sisbigtime <=", value, "sisbigtime");
            return (Criteria) this;
        }

        public Criteria andSisbigtimeIn(List<Date> values) {
            addCriterion("sisbigtime in", values, "sisbigtime");
            return (Criteria) this;
        }

        public Criteria andSisbigtimeNotIn(List<Date> values) {
            addCriterion("sisbigtime not in", values, "sisbigtime");
            return (Criteria) this;
        }

        public Criteria andSisbigtimeBetween(Date value1, Date value2) {
            addCriterion("sisbigtime between", value1, value2, "sisbigtime");
            return (Criteria) this;
        }

        public Criteria andSisbigtimeNotBetween(Date value1, Date value2) {
            addCriterion("sisbigtime not between", value1, value2, "sisbigtime");
            return (Criteria) this;
        }

        public Criteria andSisendtimeIsNull() {
            addCriterion("sisendtime is null");
            return (Criteria) this;
        }

        public Criteria andSisendtimeIsNotNull() {
            addCriterion("sisendtime is not null");
            return (Criteria) this;
        }

        public Criteria andSisendtimeEqualTo(Date value) {
            addCriterion("sisendtime =", value, "sisendtime");
            return (Criteria) this;
        }

        public Criteria andSisendtimeNotEqualTo(Date value) {
            addCriterion("sisendtime <>", value, "sisendtime");
            return (Criteria) this;
        }

        public Criteria andSisendtimeGreaterThan(Date value) {
            addCriterion("sisendtime >", value, "sisendtime");
            return (Criteria) this;
        }

        public Criteria andSisendtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("sisendtime >=", value, "sisendtime");
            return (Criteria) this;
        }

        public Criteria andSisendtimeLessThan(Date value) {
            addCriterion("sisendtime <", value, "sisendtime");
            return (Criteria) this;
        }

        public Criteria andSisendtimeLessThanOrEqualTo(Date value) {
            addCriterion("sisendtime <=", value, "sisendtime");
            return (Criteria) this;
        }

        public Criteria andSisendtimeIn(List<Date> values) {
            addCriterion("sisendtime in", values, "sisendtime");
            return (Criteria) this;
        }

        public Criteria andSisendtimeNotIn(List<Date> values) {
            addCriterion("sisendtime not in", values, "sisendtime");
            return (Criteria) this;
        }

        public Criteria andSisendtimeBetween(Date value1, Date value2) {
            addCriterion("sisendtime between", value1, value2, "sisendtime");
            return (Criteria) this;
        }

        public Criteria andSisendtimeNotBetween(Date value1, Date value2) {
            addCriterion("sisendtime not between", value1, value2, "sisendtime");
            return (Criteria) this;
        }

        public Criteria andLimitcountIsNull() {
            addCriterion("limitcount is null");
            return (Criteria) this;
        }

        public Criteria andLimitcountIsNotNull() {
            addCriterion("limitcount is not null");
            return (Criteria) this;
        }

        public Criteria andLimitcountEqualTo(Integer value) {
            addCriterion("limitcount =", value, "limitcount");
            return (Criteria) this;
        }

        public Criteria andLimitcountNotEqualTo(Integer value) {
            addCriterion("limitcount <>", value, "limitcount");
            return (Criteria) this;
        }

        public Criteria andLimitcountGreaterThan(Integer value) {
            addCriterion("limitcount >", value, "limitcount");
            return (Criteria) this;
        }

        public Criteria andLimitcountGreaterThanOrEqualTo(Integer value) {
            addCriterion("limitcount >=", value, "limitcount");
            return (Criteria) this;
        }

        public Criteria andLimitcountLessThan(Integer value) {
            addCriterion("limitcount <", value, "limitcount");
            return (Criteria) this;
        }

        public Criteria andLimitcountLessThanOrEqualTo(Integer value) {
            addCriterion("limitcount <=", value, "limitcount");
            return (Criteria) this;
        }

        public Criteria andLimitcountIn(List<Integer> values) {
            addCriterion("limitcount in", values, "limitcount");
            return (Criteria) this;
        }

        public Criteria andLimitcountNotIn(List<Integer> values) {
            addCriterion("limitcount not in", values, "limitcount");
            return (Criteria) this;
        }

        public Criteria andLimitcountBetween(Integer value1, Integer value2) {
            addCriterion("limitcount between", value1, value2, "limitcount");
            return (Criteria) this;
        }

        public Criteria andLimitcountNotBetween(Integer value1, Integer value2) {
            addCriterion("limitcount not between", value1, value2, "limitcount");
            return (Criteria) this;
        }

        public Criteria andSynthesizedIsNull() {
            addCriterion("synthesized is null");
            return (Criteria) this;
        }

        public Criteria andSynthesizedIsNotNull() {
            addCriterion("synthesized is not null");
            return (Criteria) this;
        }

        public Criteria andSynthesizedEqualTo(Integer value) {
            addCriterion("synthesized =", value, "synthesized");
            return (Criteria) this;
        }

        public Criteria andSynthesizedNotEqualTo(Integer value) {
            addCriterion("synthesized <>", value, "synthesized");
            return (Criteria) this;
        }

        public Criteria andSynthesizedGreaterThan(Integer value) {
            addCriterion("synthesized >", value, "synthesized");
            return (Criteria) this;
        }

        public Criteria andSynthesizedGreaterThanOrEqualTo(Integer value) {
            addCriterion("synthesized >=", value, "synthesized");
            return (Criteria) this;
        }

        public Criteria andSynthesizedLessThan(Integer value) {
            addCriterion("synthesized <", value, "synthesized");
            return (Criteria) this;
        }

        public Criteria andSynthesizedLessThanOrEqualTo(Integer value) {
            addCriterion("synthesized <=", value, "synthesized");
            return (Criteria) this;
        }

        public Criteria andSynthesizedIn(List<Integer> values) {
            addCriterion("synthesized in", values, "synthesized");
            return (Criteria) this;
        }

        public Criteria andSynthesizedNotIn(List<Integer> values) {
            addCriterion("synthesized not in", values, "synthesized");
            return (Criteria) this;
        }

        public Criteria andSynthesizedBetween(Integer value1, Integer value2) {
            addCriterion("synthesized between", value1, value2, "synthesized");
            return (Criteria) this;
        }

        public Criteria andSynthesizedNotBetween(Integer value1, Integer value2) {
            addCriterion("synthesized not between", value1, value2, "synthesized");
            return (Criteria) this;
        }

        public Criteria andUserlimitIsNull() {
            addCriterion("userlimit is null");
            return (Criteria) this;
        }

        public Criteria andUserlimitIsNotNull() {
            addCriterion("userlimit is not null");
            return (Criteria) this;
        }

        public Criteria andUserlimitEqualTo(Integer value) {
            addCriterion("userlimit =", value, "userlimit");
            return (Criteria) this;
        }

        public Criteria andUserlimitNotEqualTo(Integer value) {
            addCriterion("userlimit <>", value, "userlimit");
            return (Criteria) this;
        }

        public Criteria andUserlimitGreaterThan(Integer value) {
            addCriterion("userlimit >", value, "userlimit");
            return (Criteria) this;
        }

        public Criteria andUserlimitGreaterThanOrEqualTo(Integer value) {
            addCriterion("userlimit >=", value, "userlimit");
            return (Criteria) this;
        }

        public Criteria andUserlimitLessThan(Integer value) {
            addCriterion("userlimit <", value, "userlimit");
            return (Criteria) this;
        }

        public Criteria andUserlimitLessThanOrEqualTo(Integer value) {
            addCriterion("userlimit <=", value, "userlimit");
            return (Criteria) this;
        }

        public Criteria andUserlimitIn(List<Integer> values) {
            addCriterion("userlimit in", values, "userlimit");
            return (Criteria) this;
        }

        public Criteria andUserlimitNotIn(List<Integer> values) {
            addCriterion("userlimit not in", values, "userlimit");
            return (Criteria) this;
        }

        public Criteria andUserlimitBetween(Integer value1, Integer value2) {
            addCriterion("userlimit between", value1, value2, "userlimit");
            return (Criteria) this;
        }

        public Criteria andUserlimitNotBetween(Integer value1, Integer value2) {
            addCriterion("userlimit not between", value1, value2, "userlimit");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * synthesis 
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