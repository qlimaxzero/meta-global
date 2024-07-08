package com.shitouren.core.autogenerate.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IssueExample {
    /**
     * issue
     */
    protected String orderByClause;

    /**
     * issue
     */
    protected boolean distinct;

    /**
     * issue
     */
    protected List<Criteria> oredCriteria;

    /**
     * issue
     */
    protected Integer pageNo = 1;

    /**
     * issue
     */
    protected Integer startRow;

    /**
     * issue
     */
    protected Integer pageSize = 10;

    public IssueExample() {
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
     * issue 
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

        public Criteria andIstypeIsNull() {
            addCriterion("istype is null");
            return (Criteria) this;
        }

        public Criteria andIstypeIsNotNull() {
            addCriterion("istype is not null");
            return (Criteria) this;
        }

        public Criteria andIstypeEqualTo(Integer value) {
            addCriterion("istype =", value, "istype");
            return (Criteria) this;
        }

        public Criteria andIstypeNotEqualTo(Integer value) {
            addCriterion("istype <>", value, "istype");
            return (Criteria) this;
        }

        public Criteria andIstypeGreaterThan(Integer value) {
            addCriterion("istype >", value, "istype");
            return (Criteria) this;
        }

        public Criteria andIstypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("istype >=", value, "istype");
            return (Criteria) this;
        }

        public Criteria andIstypeLessThan(Integer value) {
            addCriterion("istype <", value, "istype");
            return (Criteria) this;
        }

        public Criteria andIstypeLessThanOrEqualTo(Integer value) {
            addCriterion("istype <=", value, "istype");
            return (Criteria) this;
        }

        public Criteria andIstypeIn(List<Integer> values) {
            addCriterion("istype in", values, "istype");
            return (Criteria) this;
        }

        public Criteria andIstypeNotIn(List<Integer> values) {
            addCriterion("istype not in", values, "istype");
            return (Criteria) this;
        }

        public Criteria andIstypeBetween(Integer value1, Integer value2) {
            addCriterion("istype between", value1, value2, "istype");
            return (Criteria) this;
        }

        public Criteria andIstypeNotBetween(Integer value1, Integer value2) {
            addCriterion("istype not between", value1, value2, "istype");
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

        public Criteria andGinsengtypeIsNull() {
            addCriterion("ginsengtype is null");
            return (Criteria) this;
        }

        public Criteria andGinsengtypeIsNotNull() {
            addCriterion("ginsengtype is not null");
            return (Criteria) this;
        }

        public Criteria andGinsengtypeEqualTo(Integer value) {
            addCriterion("ginsengtype =", value, "ginsengtype");
            return (Criteria) this;
        }

        public Criteria andGinsengtypeNotEqualTo(Integer value) {
            addCriterion("ginsengtype <>", value, "ginsengtype");
            return (Criteria) this;
        }

        public Criteria andGinsengtypeGreaterThan(Integer value) {
            addCriterion("ginsengtype >", value, "ginsengtype");
            return (Criteria) this;
        }

        public Criteria andGinsengtypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("ginsengtype >=", value, "ginsengtype");
            return (Criteria) this;
        }

        public Criteria andGinsengtypeLessThan(Integer value) {
            addCriterion("ginsengtype <", value, "ginsengtype");
            return (Criteria) this;
        }

        public Criteria andGinsengtypeLessThanOrEqualTo(Integer value) {
            addCriterion("ginsengtype <=", value, "ginsengtype");
            return (Criteria) this;
        }

        public Criteria andGinsengtypeIn(List<Integer> values) {
            addCriterion("ginsengtype in", values, "ginsengtype");
            return (Criteria) this;
        }

        public Criteria andGinsengtypeNotIn(List<Integer> values) {
            addCriterion("ginsengtype not in", values, "ginsengtype");
            return (Criteria) this;
        }

        public Criteria andGinsengtypeBetween(Integer value1, Integer value2) {
            addCriterion("ginsengtype between", value1, value2, "ginsengtype");
            return (Criteria) this;
        }

        public Criteria andGinsengtypeNotBetween(Integer value1, Integer value2) {
            addCriterion("ginsengtype not between", value1, value2, "ginsengtype");
            return (Criteria) this;
        }

        public Criteria andReleasetimeIsNull() {
            addCriterion("releasetime is null");
            return (Criteria) this;
        }

        public Criteria andReleasetimeIsNotNull() {
            addCriterion("releasetime is not null");
            return (Criteria) this;
        }

        public Criteria andReleasetimeEqualTo(Date value) {
            addCriterion("releasetime =", value, "releasetime");
            return (Criteria) this;
        }

        public Criteria andReleasetimeNotEqualTo(Date value) {
            addCriterion("releasetime <>", value, "releasetime");
            return (Criteria) this;
        }

        public Criteria andReleasetimeGreaterThan(Date value) {
            addCriterion("releasetime >", value, "releasetime");
            return (Criteria) this;
        }

        public Criteria andReleasetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("releasetime >=", value, "releasetime");
            return (Criteria) this;
        }

        public Criteria andReleasetimeLessThan(Date value) {
            addCriterion("releasetime <", value, "releasetime");
            return (Criteria) this;
        }

        public Criteria andReleasetimeLessThanOrEqualTo(Date value) {
            addCriterion("releasetime <=", value, "releasetime");
            return (Criteria) this;
        }

        public Criteria andReleasetimeIn(List<Date> values) {
            addCriterion("releasetime in", values, "releasetime");
            return (Criteria) this;
        }

        public Criteria andReleasetimeNotIn(List<Date> values) {
            addCriterion("releasetime not in", values, "releasetime");
            return (Criteria) this;
        }

        public Criteria andReleasetimeBetween(Date value1, Date value2) {
            addCriterion("releasetime between", value1, value2, "releasetime");
            return (Criteria) this;
        }

        public Criteria andReleasetimeNotBetween(Date value1, Date value2) {
            addCriterion("releasetime not between", value1, value2, "releasetime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNull() {
            addCriterion("endtime is null");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNotNull() {
            addCriterion("endtime is not null");
            return (Criteria) this;
        }

        public Criteria andEndtimeEqualTo(Date value) {
            addCriterion("endtime =", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotEqualTo(Date value) {
            addCriterion("endtime <>", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThan(Date value) {
            addCriterion("endtime >", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("endtime >=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThan(Date value) {
            addCriterion("endtime <", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThanOrEqualTo(Date value) {
            addCriterion("endtime <=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIn(List<Date> values) {
            addCriterion("endtime in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotIn(List<Date> values) {
            addCriterion("endtime not in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeBetween(Date value1, Date value2) {
            addCriterion("endtime between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotBetween(Date value1, Date value2) {
            addCriterion("endtime not between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andPresaleIsNull() {
            addCriterion("presale is null");
            return (Criteria) this;
        }

        public Criteria andPresaleIsNotNull() {
            addCriterion("presale is not null");
            return (Criteria) this;
        }

        public Criteria andPresaleEqualTo(Integer value) {
            addCriterion("presale =", value, "presale");
            return (Criteria) this;
        }

        public Criteria andPresaleNotEqualTo(Integer value) {
            addCriterion("presale <>", value, "presale");
            return (Criteria) this;
        }

        public Criteria andPresaleGreaterThan(Integer value) {
            addCriterion("presale >", value, "presale");
            return (Criteria) this;
        }

        public Criteria andPresaleGreaterThanOrEqualTo(Integer value) {
            addCriterion("presale >=", value, "presale");
            return (Criteria) this;
        }

        public Criteria andPresaleLessThan(Integer value) {
            addCriterion("presale <", value, "presale");
            return (Criteria) this;
        }

        public Criteria andPresaleLessThanOrEqualTo(Integer value) {
            addCriterion("presale <=", value, "presale");
            return (Criteria) this;
        }

        public Criteria andPresaleIn(List<Integer> values) {
            addCriterion("presale in", values, "presale");
            return (Criteria) this;
        }

        public Criteria andPresaleNotIn(List<Integer> values) {
            addCriterion("presale not in", values, "presale");
            return (Criteria) this;
        }

        public Criteria andPresaleBetween(Integer value1, Integer value2) {
            addCriterion("presale between", value1, value2, "presale");
            return (Criteria) this;
        }

        public Criteria andPresaleNotBetween(Integer value1, Integer value2) {
            addCriterion("presale not between", value1, value2, "presale");
            return (Criteria) this;
        }

        public Criteria andSoldIsNull() {
            addCriterion("sold is null");
            return (Criteria) this;
        }

        public Criteria andSoldIsNotNull() {
            addCriterion("sold is not null");
            return (Criteria) this;
        }

        public Criteria andSoldEqualTo(Integer value) {
            addCriterion("sold =", value, "sold");
            return (Criteria) this;
        }

        public Criteria andSoldNotEqualTo(Integer value) {
            addCriterion("sold <>", value, "sold");
            return (Criteria) this;
        }

        public Criteria andSoldGreaterThan(Integer value) {
            addCriterion("sold >", value, "sold");
            return (Criteria) this;
        }

        public Criteria andSoldGreaterThanOrEqualTo(Integer value) {
            addCriterion("sold >=", value, "sold");
            return (Criteria) this;
        }

        public Criteria andSoldLessThan(Integer value) {
            addCriterion("sold <", value, "sold");
            return (Criteria) this;
        }

        public Criteria andSoldLessThanOrEqualTo(Integer value) {
            addCriterion("sold <=", value, "sold");
            return (Criteria) this;
        }

        public Criteria andSoldIn(List<Integer> values) {
            addCriterion("sold in", values, "sold");
            return (Criteria) this;
        }

        public Criteria andSoldNotIn(List<Integer> values) {
            addCriterion("sold not in", values, "sold");
            return (Criteria) this;
        }

        public Criteria andSoldBetween(Integer value1, Integer value2) {
            addCriterion("sold between", value1, value2, "sold");
            return (Criteria) this;
        }

        public Criteria andSoldNotBetween(Integer value1, Integer value2) {
            addCriterion("sold not between", value1, value2, "sold");
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

        public Criteria andGinscountIsNull() {
            addCriterion("ginscount is null");
            return (Criteria) this;
        }

        public Criteria andGinscountIsNotNull() {
            addCriterion("ginscount is not null");
            return (Criteria) this;
        }

        public Criteria andGinscountEqualTo(Integer value) {
            addCriterion("ginscount =", value, "ginscount");
            return (Criteria) this;
        }

        public Criteria andGinscountNotEqualTo(Integer value) {
            addCriterion("ginscount <>", value, "ginscount");
            return (Criteria) this;
        }

        public Criteria andGinscountGreaterThan(Integer value) {
            addCriterion("ginscount >", value, "ginscount");
            return (Criteria) this;
        }

        public Criteria andGinscountGreaterThanOrEqualTo(Integer value) {
            addCriterion("ginscount >=", value, "ginscount");
            return (Criteria) this;
        }

        public Criteria andGinscountLessThan(Integer value) {
            addCriterion("ginscount <", value, "ginscount");
            return (Criteria) this;
        }

        public Criteria andGinscountLessThanOrEqualTo(Integer value) {
            addCriterion("ginscount <=", value, "ginscount");
            return (Criteria) this;
        }

        public Criteria andGinscountIn(List<Integer> values) {
            addCriterion("ginscount in", values, "ginscount");
            return (Criteria) this;
        }

        public Criteria andGinscountNotIn(List<Integer> values) {
            addCriterion("ginscount not in", values, "ginscount");
            return (Criteria) this;
        }

        public Criteria andGinscountBetween(Integer value1, Integer value2) {
            addCriterion("ginscount between", value1, value2, "ginscount");
            return (Criteria) this;
        }

        public Criteria andGinscountNotBetween(Integer value1, Integer value2) {
            addCriterion("ginscount not between", value1, value2, "ginscount");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andCheckoutIsNull() {
            addCriterion("checkout is null");
            return (Criteria) this;
        }

        public Criteria andCheckoutIsNotNull() {
            addCriterion("checkout is not null");
            return (Criteria) this;
        }

        public Criteria andCheckoutEqualTo(Integer value) {
            addCriterion("checkout =", value, "checkout");
            return (Criteria) this;
        }

        public Criteria andCheckoutNotEqualTo(Integer value) {
            addCriterion("checkout <>", value, "checkout");
            return (Criteria) this;
        }

        public Criteria andCheckoutGreaterThan(Integer value) {
            addCriterion("checkout >", value, "checkout");
            return (Criteria) this;
        }

        public Criteria andCheckoutGreaterThanOrEqualTo(Integer value) {
            addCriterion("checkout >=", value, "checkout");
            return (Criteria) this;
        }

        public Criteria andCheckoutLessThan(Integer value) {
            addCriterion("checkout <", value, "checkout");
            return (Criteria) this;
        }

        public Criteria andCheckoutLessThanOrEqualTo(Integer value) {
            addCriterion("checkout <=", value, "checkout");
            return (Criteria) this;
        }

        public Criteria andCheckoutIn(List<Integer> values) {
            addCriterion("checkout in", values, "checkout");
            return (Criteria) this;
        }

        public Criteria andCheckoutNotIn(List<Integer> values) {
            addCriterion("checkout not in", values, "checkout");
            return (Criteria) this;
        }

        public Criteria andCheckoutBetween(Integer value1, Integer value2) {
            addCriterion("checkout between", value1, value2, "checkout");
            return (Criteria) this;
        }

        public Criteria andCheckoutNotBetween(Integer value1, Integer value2) {
            addCriterion("checkout not between", value1, value2, "checkout");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * issue 
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