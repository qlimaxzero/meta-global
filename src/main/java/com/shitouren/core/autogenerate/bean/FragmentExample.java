package com.shitouren.core.autogenerate.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FragmentExample {
    /**
     * fragment
     */
    protected String orderByClause;

    /**
     * fragment
     */
    protected boolean distinct;

    /**
     * fragment
     */
    protected List<Criteria> oredCriteria;

    /**
     * fragment
     */
    protected Integer pageNo = 1;

    /**
     * fragment
     */
    protected Integer startRow;

    /**
     * fragment
     */
    protected Integer pageSize = 10;

    public FragmentExample() {
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
     * fragment 
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

        public Criteria andHashsIsNull() {
            addCriterion("hashs is null");
            return (Criteria) this;
        }

        public Criteria andHashsIsNotNull() {
            addCriterion("hashs is not null");
            return (Criteria) this;
        }

        public Criteria andHashsEqualTo(String value) {
            addCriterion("hashs =", value, "hashs");
            return (Criteria) this;
        }

        public Criteria andHashsNotEqualTo(String value) {
            addCriterion("hashs <>", value, "hashs");
            return (Criteria) this;
        }

        public Criteria andHashsGreaterThan(String value) {
            addCriterion("hashs >", value, "hashs");
            return (Criteria) this;
        }

        public Criteria andHashsGreaterThanOrEqualTo(String value) {
            addCriterion("hashs >=", value, "hashs");
            return (Criteria) this;
        }

        public Criteria andHashsLessThan(String value) {
            addCriterion("hashs <", value, "hashs");
            return (Criteria) this;
        }

        public Criteria andHashsLessThanOrEqualTo(String value) {
            addCriterion("hashs <=", value, "hashs");
            return (Criteria) this;
        }

        public Criteria andHashsLike(String value) {
            addCriterion("hashs like", value, "hashs");
            return (Criteria) this;
        }

        public Criteria andHashsNotLike(String value) {
            addCriterion("hashs not like", value, "hashs");
            return (Criteria) this;
        }

        public Criteria andHashsIn(List<String> values) {
            addCriterion("hashs in", values, "hashs");
            return (Criteria) this;
        }

        public Criteria andHashsNotIn(List<String> values) {
            addCriterion("hashs not in", values, "hashs");
            return (Criteria) this;
        }

        public Criteria andHashsBetween(String value1, String value2) {
            addCriterion("hashs between", value1, value2, "hashs");
            return (Criteria) this;
        }

        public Criteria andHashsNotBetween(String value1, String value2) {
            addCriterion("hashs not between", value1, value2, "hashs");
            return (Criteria) this;
        }

        public Criteria andBegintimeIsNull() {
            addCriterion("begintime is null");
            return (Criteria) this;
        }

        public Criteria andBegintimeIsNotNull() {
            addCriterion("begintime is not null");
            return (Criteria) this;
        }

        public Criteria andBegintimeEqualTo(Date value) {
            addCriterion("begintime =", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeNotEqualTo(Date value) {
            addCriterion("begintime <>", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeGreaterThan(Date value) {
            addCriterion("begintime >", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeGreaterThanOrEqualTo(Date value) {
            addCriterion("begintime >=", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeLessThan(Date value) {
            addCriterion("begintime <", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeLessThanOrEqualTo(Date value) {
            addCriterion("begintime <=", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeIn(List<Date> values) {
            addCriterion("begintime in", values, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeNotIn(List<Date> values) {
            addCriterion("begintime not in", values, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeBetween(Date value1, Date value2) {
            addCriterion("begintime between", value1, value2, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeNotBetween(Date value1, Date value2) {
            addCriterion("begintime not between", value1, value2, "begintime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * fragment 
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