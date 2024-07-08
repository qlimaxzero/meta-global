package com.shitouren.core.autogenerate.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyOrderExample {
    /**
     * my_order
     */
    protected String orderByClause;

    /**
     * my_order
     */
    protected boolean distinct;

    /**
     * my_order
     */
    protected List<Criteria> oredCriteria;

    /**
     * my_order
     */
    protected Integer pageNo = 1;

    /**
     * my_order
     */
    protected Integer startRow;

    /**
     * my_order
     */
    protected Integer pageSize = 10;

    public MyOrderExample() {
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
     * my_order 
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

        public Criteria andOrdernoIsNull() {
            addCriterion("orderno is null");
            return (Criteria) this;
        }

        public Criteria andOrdernoIsNotNull() {
            addCriterion("orderno is not null");
            return (Criteria) this;
        }

        public Criteria andOrdernoEqualTo(String value) {
            addCriterion("orderno =", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoNotEqualTo(String value) {
            addCriterion("orderno <>", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoGreaterThan(String value) {
            addCriterion("orderno >", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoGreaterThanOrEqualTo(String value) {
            addCriterion("orderno >=", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoLessThan(String value) {
            addCriterion("orderno <", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoLessThanOrEqualTo(String value) {
            addCriterion("orderno <=", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoLike(String value) {
            addCriterion("orderno like", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoNotLike(String value) {
            addCriterion("orderno not like", value, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoIn(List<String> values) {
            addCriterion("orderno in", values, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoNotIn(List<String> values) {
            addCriterion("orderno not in", values, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoBetween(String value1, String value2) {
            addCriterion("orderno between", value1, value2, "orderno");
            return (Criteria) this;
        }

        public Criteria andOrdernoNotBetween(String value1, String value2) {
            addCriterion("orderno not between", value1, value2, "orderno");
            return (Criteria) this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("userid is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userid is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(Integer value) {
            addCriterion("userid =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(Integer value) {
            addCriterion("userid <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(Integer value) {
            addCriterion("userid >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("userid >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(Integer value) {
            addCriterion("userid <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(Integer value) {
            addCriterion("userid <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<Integer> values) {
            addCriterion("userid in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<Integer> values) {
            addCriterion("userid not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(Integer value1, Integer value2) {
            addCriterion("userid between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(Integer value1, Integer value2) {
            addCriterion("userid not between", value1, value2, "userid");
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

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andOrdertypeIsNull() {
            addCriterion("ordertype is null");
            return (Criteria) this;
        }

        public Criteria andOrdertypeIsNotNull() {
            addCriterion("ordertype is not null");
            return (Criteria) this;
        }

        public Criteria andOrdertypeEqualTo(Integer value) {
            addCriterion("ordertype =", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeNotEqualTo(Integer value) {
            addCriterion("ordertype <>", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeGreaterThan(Integer value) {
            addCriterion("ordertype >", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("ordertype >=", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeLessThan(Integer value) {
            addCriterion("ordertype <", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeLessThanOrEqualTo(Integer value) {
            addCriterion("ordertype <=", value, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeIn(List<Integer> values) {
            addCriterion("ordertype in", values, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeNotIn(List<Integer> values) {
            addCriterion("ordertype not in", values, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeBetween(Integer value1, Integer value2) {
            addCriterion("ordertype between", value1, value2, "ordertype");
            return (Criteria) this;
        }

        public Criteria andOrdertypeNotBetween(Integer value1, Integer value2) {
            addCriterion("ordertype not between", value1, value2, "ordertype");
            return (Criteria) this;
        }

        public Criteria andGrantsIsNull() {
            addCriterion("grants is null");
            return (Criteria) this;
        }

        public Criteria andGrantsIsNotNull() {
            addCriterion("grants is not null");
            return (Criteria) this;
        }

        public Criteria andGrantsEqualTo(Integer value) {
            addCriterion("grants =", value, "grants");
            return (Criteria) this;
        }

        public Criteria andGrantsNotEqualTo(Integer value) {
            addCriterion("grants <>", value, "grants");
            return (Criteria) this;
        }

        public Criteria andGrantsGreaterThan(Integer value) {
            addCriterion("grants >", value, "grants");
            return (Criteria) this;
        }

        public Criteria andGrantsGreaterThanOrEqualTo(Integer value) {
            addCriterion("grants >=", value, "grants");
            return (Criteria) this;
        }

        public Criteria andGrantsLessThan(Integer value) {
            addCriterion("grants <", value, "grants");
            return (Criteria) this;
        }

        public Criteria andGrantsLessThanOrEqualTo(Integer value) {
            addCriterion("grants <=", value, "grants");
            return (Criteria) this;
        }

        public Criteria andGrantsIn(List<Integer> values) {
            addCriterion("grants in", values, "grants");
            return (Criteria) this;
        }

        public Criteria andGrantsNotIn(List<Integer> values) {
            addCriterion("grants not in", values, "grants");
            return (Criteria) this;
        }

        public Criteria andGrantsBetween(Integer value1, Integer value2) {
            addCriterion("grants between", value1, value2, "grants");
            return (Criteria) this;
        }

        public Criteria andGrantsNotBetween(Integer value1, Integer value2) {
            addCriterion("grants not between", value1, value2, "grants");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
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

        public Criteria andPaytypeIsNull() {
            addCriterion("paytype is null");
            return (Criteria) this;
        }

        public Criteria andPaytypeIsNotNull() {
            addCriterion("paytype is not null");
            return (Criteria) this;
        }

        public Criteria andPaytypeEqualTo(Integer value) {
            addCriterion("paytype =", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeNotEqualTo(Integer value) {
            addCriterion("paytype <>", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeGreaterThan(Integer value) {
            addCriterion("paytype >", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("paytype >=", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeLessThan(Integer value) {
            addCriterion("paytype <", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeLessThanOrEqualTo(Integer value) {
            addCriterion("paytype <=", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeIn(List<Integer> values) {
            addCriterion("paytype in", values, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeNotIn(List<Integer> values) {
            addCriterion("paytype not in", values, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeBetween(Integer value1, Integer value2) {
            addCriterion("paytype between", value1, value2, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeNotBetween(Integer value1, Integer value2) {
            addCriterion("paytype not between", value1, value2, "paytype");
            return (Criteria) this;
        }

        public Criteria andCyidIsNull() {
            addCriterion("cyid is null");
            return (Criteria) this;
        }

        public Criteria andCyidIsNotNull() {
            addCriterion("cyid is not null");
            return (Criteria) this;
        }

        public Criteria andCyidEqualTo(Integer value) {
            addCriterion("cyid =", value, "cyid");
            return (Criteria) this;
        }

        public Criteria andCyidNotEqualTo(Integer value) {
            addCriterion("cyid <>", value, "cyid");
            return (Criteria) this;
        }

        public Criteria andCyidGreaterThan(Integer value) {
            addCriterion("cyid >", value, "cyid");
            return (Criteria) this;
        }

        public Criteria andCyidGreaterThanOrEqualTo(Integer value) {
            addCriterion("cyid >=", value, "cyid");
            return (Criteria) this;
        }

        public Criteria andCyidLessThan(Integer value) {
            addCriterion("cyid <", value, "cyid");
            return (Criteria) this;
        }

        public Criteria andCyidLessThanOrEqualTo(Integer value) {
            addCriterion("cyid <=", value, "cyid");
            return (Criteria) this;
        }

        public Criteria andCyidIn(List<Integer> values) {
            addCriterion("cyid in", values, "cyid");
            return (Criteria) this;
        }

        public Criteria andCyidNotIn(List<Integer> values) {
            addCriterion("cyid not in", values, "cyid");
            return (Criteria) this;
        }

        public Criteria andCyidBetween(Integer value1, Integer value2) {
            addCriterion("cyid between", value1, value2, "cyid");
            return (Criteria) this;
        }

        public Criteria andCyidNotBetween(Integer value1, Integer value2) {
            addCriterion("cyid not between", value1, value2, "cyid");
            return (Criteria) this;
        }

        public Criteria andBuycardIsNull() {
            addCriterion("buycard is null");
            return (Criteria) this;
        }

        public Criteria andBuycardIsNotNull() {
            addCriterion("buycard is not null");
            return (Criteria) this;
        }

        public Criteria andBuycardEqualTo(Integer value) {
            addCriterion("buycard =", value, "buycard");
            return (Criteria) this;
        }

        public Criteria andBuycardNotEqualTo(Integer value) {
            addCriterion("buycard <>", value, "buycard");
            return (Criteria) this;
        }

        public Criteria andBuycardGreaterThan(Integer value) {
            addCriterion("buycard >", value, "buycard");
            return (Criteria) this;
        }

        public Criteria andBuycardGreaterThanOrEqualTo(Integer value) {
            addCriterion("buycard >=", value, "buycard");
            return (Criteria) this;
        }

        public Criteria andBuycardLessThan(Integer value) {
            addCriterion("buycard <", value, "buycard");
            return (Criteria) this;
        }

        public Criteria andBuycardLessThanOrEqualTo(Integer value) {
            addCriterion("buycard <=", value, "buycard");
            return (Criteria) this;
        }

        public Criteria andBuycardIn(List<Integer> values) {
            addCriterion("buycard in", values, "buycard");
            return (Criteria) this;
        }

        public Criteria andBuycardNotIn(List<Integer> values) {
            addCriterion("buycard not in", values, "buycard");
            return (Criteria) this;
        }

        public Criteria andBuycardBetween(Integer value1, Integer value2) {
            addCriterion("buycard between", value1, value2, "buycard");
            return (Criteria) this;
        }

        public Criteria andBuycardNotBetween(Integer value1, Integer value2) {
            addCriterion("buycard not between", value1, value2, "buycard");
            return (Criteria) this;
        }

        public Criteria andYuanyuzhouidIsNull() {
            addCriterion("yuanyuzhouid is null");
            return (Criteria) this;
        }

        public Criteria andYuanyuzhouidIsNotNull() {
            addCriterion("yuanyuzhouid is not null");
            return (Criteria) this;
        }

        public Criteria andYuanyuzhouidEqualTo(Integer value) {
            addCriterion("yuanyuzhouid =", value, "yuanyuzhouid");
            return (Criteria) this;
        }

        public Criteria andYuanyuzhouidNotEqualTo(Integer value) {
            addCriterion("yuanyuzhouid <>", value, "yuanyuzhouid");
            return (Criteria) this;
        }

        public Criteria andYuanyuzhouidGreaterThan(Integer value) {
            addCriterion("yuanyuzhouid >", value, "yuanyuzhouid");
            return (Criteria) this;
        }

        public Criteria andYuanyuzhouidGreaterThanOrEqualTo(Integer value) {
            addCriterion("yuanyuzhouid >=", value, "yuanyuzhouid");
            return (Criteria) this;
        }

        public Criteria andYuanyuzhouidLessThan(Integer value) {
            addCriterion("yuanyuzhouid <", value, "yuanyuzhouid");
            return (Criteria) this;
        }

        public Criteria andYuanyuzhouidLessThanOrEqualTo(Integer value) {
            addCriterion("yuanyuzhouid <=", value, "yuanyuzhouid");
            return (Criteria) this;
        }

        public Criteria andYuanyuzhouidIn(List<Integer> values) {
            addCriterion("yuanyuzhouid in", values, "yuanyuzhouid");
            return (Criteria) this;
        }

        public Criteria andYuanyuzhouidNotIn(List<Integer> values) {
            addCriterion("yuanyuzhouid not in", values, "yuanyuzhouid");
            return (Criteria) this;
        }

        public Criteria andYuanyuzhouidBetween(Integer value1, Integer value2) {
            addCriterion("yuanyuzhouid between", value1, value2, "yuanyuzhouid");
            return (Criteria) this;
        }

        public Criteria andYuanyuzhouidNotBetween(Integer value1, Integer value2) {
            addCriterion("yuanyuzhouid not between", value1, value2, "yuanyuzhouid");
            return (Criteria) this;
        }

        public Criteria andIssueIdIsNull() {
            addCriterion("issue_id is null");
            return (Criteria) this;
        }

        public Criteria andIssueIdIsNotNull() {
            addCriterion("issue_id is not null");
            return (Criteria) this;
        }

        public Criteria andIssueIdEqualTo(Integer value) {
            addCriterion("issue_id =", value, "issueId");
            return (Criteria) this;
        }

        public Criteria andIssueIdNotEqualTo(Integer value) {
            addCriterion("issue_id <>", value, "issueId");
            return (Criteria) this;
        }

        public Criteria andIssueIdGreaterThan(Integer value) {
            addCriterion("issue_id >", value, "issueId");
            return (Criteria) this;
        }

        public Criteria andIssueIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("issue_id >=", value, "issueId");
            return (Criteria) this;
        }

        public Criteria andIssueIdLessThan(Integer value) {
            addCriterion("issue_id <", value, "issueId");
            return (Criteria) this;
        }

        public Criteria andIssueIdLessThanOrEqualTo(Integer value) {
            addCriterion("issue_id <=", value, "issueId");
            return (Criteria) this;
        }

        public Criteria andIssueIdIn(List<Integer> values) {
            addCriterion("issue_id in", values, "issueId");
            return (Criteria) this;
        }

        public Criteria andIssueIdNotIn(List<Integer> values) {
            addCriterion("issue_id not in", values, "issueId");
            return (Criteria) this;
        }

        public Criteria andIssueIdBetween(Integer value1, Integer value2) {
            addCriterion("issue_id between", value1, value2, "issueId");
            return (Criteria) this;
        }

        public Criteria andIssueIdNotBetween(Integer value1, Integer value2) {
            addCriterion("issue_id not between", value1, value2, "issueId");
            return (Criteria) this;
        }

        public Criteria andOutOrderNoIsNull() {
            addCriterion("out_order_no is null");
            return (Criteria) this;
        }

        public Criteria andOutOrderNoIsNotNull() {
            addCriterion("out_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andOutOrderNoEqualTo(String value) {
            addCriterion("out_order_no =", value, "outOrderNo");
            return (Criteria) this;
        }

        public Criteria andOutOrderNoNotEqualTo(String value) {
            addCriterion("out_order_no <>", value, "outOrderNo");
            return (Criteria) this;
        }

        public Criteria andOutOrderNoGreaterThan(String value) {
            addCriterion("out_order_no >", value, "outOrderNo");
            return (Criteria) this;
        }

        public Criteria andOutOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("out_order_no >=", value, "outOrderNo");
            return (Criteria) this;
        }

        public Criteria andOutOrderNoLessThan(String value) {
            addCriterion("out_order_no <", value, "outOrderNo");
            return (Criteria) this;
        }

        public Criteria andOutOrderNoLessThanOrEqualTo(String value) {
            addCriterion("out_order_no <=", value, "outOrderNo");
            return (Criteria) this;
        }

        public Criteria andOutOrderNoLike(String value) {
            addCriterion("out_order_no like", value, "outOrderNo");
            return (Criteria) this;
        }

        public Criteria andOutOrderNoNotLike(String value) {
            addCriterion("out_order_no not like", value, "outOrderNo");
            return (Criteria) this;
        }

        public Criteria andOutOrderNoIn(List<String> values) {
            addCriterion("out_order_no in", values, "outOrderNo");
            return (Criteria) this;
        }

        public Criteria andOutOrderNoNotIn(List<String> values) {
            addCriterion("out_order_no not in", values, "outOrderNo");
            return (Criteria) this;
        }

        public Criteria andOutOrderNoBetween(String value1, String value2) {
            addCriterion("out_order_no between", value1, value2, "outOrderNo");
            return (Criteria) this;
        }

        public Criteria andOutOrderNoNotBetween(String value1, String value2) {
            addCriterion("out_order_no not between", value1, value2, "outOrderNo");
            return (Criteria) this;
        }

        public Criteria andTxidIsNull() {
            addCriterion("txid is null");
            return (Criteria) this;
        }

        public Criteria andTxidIsNotNull() {
            addCriterion("txid is not null");
            return (Criteria) this;
        }

        public Criteria andTxidEqualTo(String value) {
            addCriterion("txid =", value, "txid");
            return (Criteria) this;
        }

        public Criteria andTxidNotEqualTo(String value) {
            addCriterion("txid <>", value, "txid");
            return (Criteria) this;
        }

        public Criteria andTxidGreaterThan(String value) {
            addCriterion("txid >", value, "txid");
            return (Criteria) this;
        }

        public Criteria andTxidGreaterThanOrEqualTo(String value) {
            addCriterion("txid >=", value, "txid");
            return (Criteria) this;
        }

        public Criteria andTxidLessThan(String value) {
            addCriterion("txid <", value, "txid");
            return (Criteria) this;
        }

        public Criteria andTxidLessThanOrEqualTo(String value) {
            addCriterion("txid <=", value, "txid");
            return (Criteria) this;
        }

        public Criteria andTxidLike(String value) {
            addCriterion("txid like", value, "txid");
            return (Criteria) this;
        }

        public Criteria andTxidNotLike(String value) {
            addCriterion("txid not like", value, "txid");
            return (Criteria) this;
        }

        public Criteria andTxidIn(List<String> values) {
            addCriterion("txid in", values, "txid");
            return (Criteria) this;
        }

        public Criteria andTxidNotIn(List<String> values) {
            addCriterion("txid not in", values, "txid");
            return (Criteria) this;
        }

        public Criteria andTxidBetween(String value1, String value2) {
            addCriterion("txid between", value1, value2, "txid");
            return (Criteria) this;
        }

        public Criteria andTxidNotBetween(String value1, String value2) {
            addCriterion("txid not between", value1, value2, "txid");
            return (Criteria) this;
        }

        public Criteria andSuccessTimeIsNull() {
            addCriterion("success_time is null");
            return (Criteria) this;
        }

        public Criteria andSuccessTimeIsNotNull() {
            addCriterion("success_time is not null");
            return (Criteria) this;
        }

        public Criteria andSuccessTimeEqualTo(Date value) {
            addCriterion("success_time =", value, "successTime");
            return (Criteria) this;
        }

        public Criteria andSuccessTimeNotEqualTo(Date value) {
            addCriterion("success_time <>", value, "successTime");
            return (Criteria) this;
        }

        public Criteria andSuccessTimeGreaterThan(Date value) {
            addCriterion("success_time >", value, "successTime");
            return (Criteria) this;
        }

        public Criteria andSuccessTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("success_time >=", value, "successTime");
            return (Criteria) this;
        }

        public Criteria andSuccessTimeLessThan(Date value) {
            addCriterion("success_time <", value, "successTime");
            return (Criteria) this;
        }

        public Criteria andSuccessTimeLessThanOrEqualTo(Date value) {
            addCriterion("success_time <=", value, "successTime");
            return (Criteria) this;
        }

        public Criteria andSuccessTimeIn(List<Date> values) {
            addCriterion("success_time in", values, "successTime");
            return (Criteria) this;
        }

        public Criteria andSuccessTimeNotIn(List<Date> values) {
            addCriterion("success_time not in", values, "successTime");
            return (Criteria) this;
        }

        public Criteria andSuccessTimeBetween(Date value1, Date value2) {
            addCriterion("success_time between", value1, value2, "successTime");
            return (Criteria) this;
        }

        public Criteria andSuccessTimeNotBetween(Date value1, Date value2) {
            addCriterion("success_time not between", value1, value2, "successTime");
            return (Criteria) this;
        }

        public Criteria andFromAddrIsNull() {
            addCriterion("from_addr is null");
            return (Criteria) this;
        }

        public Criteria andFromAddrIsNotNull() {
            addCriterion("from_addr is not null");
            return (Criteria) this;
        }

        public Criteria andFromAddrEqualTo(String value) {
            addCriterion("from_addr =", value, "fromAddr");
            return (Criteria) this;
        }

        public Criteria andFromAddrNotEqualTo(String value) {
            addCriterion("from_addr <>", value, "fromAddr");
            return (Criteria) this;
        }

        public Criteria andFromAddrGreaterThan(String value) {
            addCriterion("from_addr >", value, "fromAddr");
            return (Criteria) this;
        }

        public Criteria andFromAddrGreaterThanOrEqualTo(String value) {
            addCriterion("from_addr >=", value, "fromAddr");
            return (Criteria) this;
        }

        public Criteria andFromAddrLessThan(String value) {
            addCriterion("from_addr <", value, "fromAddr");
            return (Criteria) this;
        }

        public Criteria andFromAddrLessThanOrEqualTo(String value) {
            addCriterion("from_addr <=", value, "fromAddr");
            return (Criteria) this;
        }

        public Criteria andFromAddrLike(String value) {
            addCriterion("from_addr like", value, "fromAddr");
            return (Criteria) this;
        }

        public Criteria andFromAddrNotLike(String value) {
            addCriterion("from_addr not like", value, "fromAddr");
            return (Criteria) this;
        }

        public Criteria andFromAddrIn(List<String> values) {
            addCriterion("from_addr in", values, "fromAddr");
            return (Criteria) this;
        }

        public Criteria andFromAddrNotIn(List<String> values) {
            addCriterion("from_addr not in", values, "fromAddr");
            return (Criteria) this;
        }

        public Criteria andFromAddrBetween(String value1, String value2) {
            addCriterion("from_addr between", value1, value2, "fromAddr");
            return (Criteria) this;
        }

        public Criteria andFromAddrNotBetween(String value1, String value2) {
            addCriterion("from_addr not between", value1, value2, "fromAddr");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * my_order 
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