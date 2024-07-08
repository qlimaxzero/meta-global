package com.shitouren.core.autogenerate.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserGrantExample {
    /**
     * user_grant
     */
    protected String orderByClause;

    /**
     * user_grant
     */
    protected boolean distinct;

    /**
     * user_grant
     */
    protected List<Criteria> oredCriteria;

    /**
     * user_grant
     */
    protected Integer pageNo = 1;

    /**
     * user_grant
     */
    protected Integer startRow;

    /**
     * user_grant
     */
    protected Integer pageSize = 10;

    public UserGrantExample() {
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
     * user_grant 
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

        public Criteria andTruenumberIsNull() {
            addCriterion("truenumber is null");
            return (Criteria) this;
        }

        public Criteria andTruenumberIsNotNull() {
            addCriterion("truenumber is not null");
            return (Criteria) this;
        }

        public Criteria andTruenumberEqualTo(String value) {
            addCriterion("truenumber =", value, "truenumber");
            return (Criteria) this;
        }

        public Criteria andTruenumberNotEqualTo(String value) {
            addCriterion("truenumber <>", value, "truenumber");
            return (Criteria) this;
        }

        public Criteria andTruenumberGreaterThan(String value) {
            addCriterion("truenumber >", value, "truenumber");
            return (Criteria) this;
        }

        public Criteria andTruenumberGreaterThanOrEqualTo(String value) {
            addCriterion("truenumber >=", value, "truenumber");
            return (Criteria) this;
        }

        public Criteria andTruenumberLessThan(String value) {
            addCriterion("truenumber <", value, "truenumber");
            return (Criteria) this;
        }

        public Criteria andTruenumberLessThanOrEqualTo(String value) {
            addCriterion("truenumber <=", value, "truenumber");
            return (Criteria) this;
        }

        public Criteria andTruenumberLike(String value) {
            addCriterion("truenumber like", value, "truenumber");
            return (Criteria) this;
        }

        public Criteria andTruenumberNotLike(String value) {
            addCriterion("truenumber not like", value, "truenumber");
            return (Criteria) this;
        }

        public Criteria andTruenumberIn(List<String> values) {
            addCriterion("truenumber in", values, "truenumber");
            return (Criteria) this;
        }

        public Criteria andTruenumberNotIn(List<String> values) {
            addCriterion("truenumber not in", values, "truenumber");
            return (Criteria) this;
        }

        public Criteria andTruenumberBetween(String value1, String value2) {
            addCriterion("truenumber between", value1, value2, "truenumber");
            return (Criteria) this;
        }

        public Criteria andTruenumberNotBetween(String value1, String value2) {
            addCriterion("truenumber not between", value1, value2, "truenumber");
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

        public Criteria andBuypriceIsNull() {
            addCriterion("buyprice is null");
            return (Criteria) this;
        }

        public Criteria andBuypriceIsNotNull() {
            addCriterion("buyprice is not null");
            return (Criteria) this;
        }

        public Criteria andBuypriceEqualTo(BigDecimal value) {
            addCriterion("buyprice =", value, "buyprice");
            return (Criteria) this;
        }

        public Criteria andBuypriceNotEqualTo(BigDecimal value) {
            addCriterion("buyprice <>", value, "buyprice");
            return (Criteria) this;
        }

        public Criteria andBuypriceGreaterThan(BigDecimal value) {
            addCriterion("buyprice >", value, "buyprice");
            return (Criteria) this;
        }

        public Criteria andBuypriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("buyprice >=", value, "buyprice");
            return (Criteria) this;
        }

        public Criteria andBuypriceLessThan(BigDecimal value) {
            addCriterion("buyprice <", value, "buyprice");
            return (Criteria) this;
        }

        public Criteria andBuypriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("buyprice <=", value, "buyprice");
            return (Criteria) this;
        }

        public Criteria andBuypriceIn(List<BigDecimal> values) {
            addCriterion("buyprice in", values, "buyprice");
            return (Criteria) this;
        }

        public Criteria andBuypriceNotIn(List<BigDecimal> values) {
            addCriterion("buyprice not in", values, "buyprice");
            return (Criteria) this;
        }

        public Criteria andBuypriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("buyprice between", value1, value2, "buyprice");
            return (Criteria) this;
        }

        public Criteria andBuypriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("buyprice not between", value1, value2, "buyprice");
            return (Criteria) this;
        }

        public Criteria andBuytimeIsNull() {
            addCriterion("buytime is null");
            return (Criteria) this;
        }

        public Criteria andBuytimeIsNotNull() {
            addCriterion("buytime is not null");
            return (Criteria) this;
        }

        public Criteria andBuytimeEqualTo(Date value) {
            addCriterion("buytime =", value, "buytime");
            return (Criteria) this;
        }

        public Criteria andBuytimeNotEqualTo(Date value) {
            addCriterion("buytime <>", value, "buytime");
            return (Criteria) this;
        }

        public Criteria andBuytimeGreaterThan(Date value) {
            addCriterion("buytime >", value, "buytime");
            return (Criteria) this;
        }

        public Criteria andBuytimeGreaterThanOrEqualTo(Date value) {
            addCriterion("buytime >=", value, "buytime");
            return (Criteria) this;
        }

        public Criteria andBuytimeLessThan(Date value) {
            addCriterion("buytime <", value, "buytime");
            return (Criteria) this;
        }

        public Criteria andBuytimeLessThanOrEqualTo(Date value) {
            addCriterion("buytime <=", value, "buytime");
            return (Criteria) this;
        }

        public Criteria andBuytimeIn(List<Date> values) {
            addCriterion("buytime in", values, "buytime");
            return (Criteria) this;
        }

        public Criteria andBuytimeNotIn(List<Date> values) {
            addCriterion("buytime not in", values, "buytime");
            return (Criteria) this;
        }

        public Criteria andBuytimeBetween(Date value1, Date value2) {
            addCriterion("buytime between", value1, value2, "buytime");
            return (Criteria) this;
        }

        public Criteria andBuytimeNotBetween(Date value1, Date value2) {
            addCriterion("buytime not between", value1, value2, "buytime");
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

        public Criteria andSellpriceIsNull() {
            addCriterion("sellprice is null");
            return (Criteria) this;
        }

        public Criteria andSellpriceIsNotNull() {
            addCriterion("sellprice is not null");
            return (Criteria) this;
        }

        public Criteria andSellpriceEqualTo(BigDecimal value) {
            addCriterion("sellprice =", value, "sellprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceNotEqualTo(BigDecimal value) {
            addCriterion("sellprice <>", value, "sellprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceGreaterThan(BigDecimal value) {
            addCriterion("sellprice >", value, "sellprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sellprice >=", value, "sellprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceLessThan(BigDecimal value) {
            addCriterion("sellprice <", value, "sellprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sellprice <=", value, "sellprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceIn(List<BigDecimal> values) {
            addCriterion("sellprice in", values, "sellprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceNotIn(List<BigDecimal> values) {
            addCriterion("sellprice not in", values, "sellprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sellprice between", value1, value2, "sellprice");
            return (Criteria) this;
        }

        public Criteria andSellpriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sellprice not between", value1, value2, "sellprice");
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

        public Criteria andOppositeuserIsNull() {
            addCriterion("oppositeuser is null");
            return (Criteria) this;
        }

        public Criteria andOppositeuserIsNotNull() {
            addCriterion("oppositeuser is not null");
            return (Criteria) this;
        }

        public Criteria andOppositeuserEqualTo(Integer value) {
            addCriterion("oppositeuser =", value, "oppositeuser");
            return (Criteria) this;
        }

        public Criteria andOppositeuserNotEqualTo(Integer value) {
            addCriterion("oppositeuser <>", value, "oppositeuser");
            return (Criteria) this;
        }

        public Criteria andOppositeuserGreaterThan(Integer value) {
            addCriterion("oppositeuser >", value, "oppositeuser");
            return (Criteria) this;
        }

        public Criteria andOppositeuserGreaterThanOrEqualTo(Integer value) {
            addCriterion("oppositeuser >=", value, "oppositeuser");
            return (Criteria) this;
        }

        public Criteria andOppositeuserLessThan(Integer value) {
            addCriterion("oppositeuser <", value, "oppositeuser");
            return (Criteria) this;
        }

        public Criteria andOppositeuserLessThanOrEqualTo(Integer value) {
            addCriterion("oppositeuser <=", value, "oppositeuser");
            return (Criteria) this;
        }

        public Criteria andOppositeuserIn(List<Integer> values) {
            addCriterion("oppositeuser in", values, "oppositeuser");
            return (Criteria) this;
        }

        public Criteria andOppositeuserNotIn(List<Integer> values) {
            addCriterion("oppositeuser not in", values, "oppositeuser");
            return (Criteria) this;
        }

        public Criteria andOppositeuserBetween(Integer value1, Integer value2) {
            addCriterion("oppositeuser between", value1, value2, "oppositeuser");
            return (Criteria) this;
        }

        public Criteria andOppositeuserNotBetween(Integer value1, Integer value2) {
            addCriterion("oppositeuser not between", value1, value2, "oppositeuser");
            return (Criteria) this;
        }

        public Criteria andSjtimeIsNull() {
            addCriterion("sjtime is null");
            return (Criteria) this;
        }

        public Criteria andSjtimeIsNotNull() {
            addCriterion("sjtime is not null");
            return (Criteria) this;
        }

        public Criteria andSjtimeEqualTo(Date value) {
            addCriterion("sjtime =", value, "sjtime");
            return (Criteria) this;
        }

        public Criteria andSjtimeNotEqualTo(Date value) {
            addCriterion("sjtime <>", value, "sjtime");
            return (Criteria) this;
        }

        public Criteria andSjtimeGreaterThan(Date value) {
            addCriterion("sjtime >", value, "sjtime");
            return (Criteria) this;
        }

        public Criteria andSjtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("sjtime >=", value, "sjtime");
            return (Criteria) this;
        }

        public Criteria andSjtimeLessThan(Date value) {
            addCriterion("sjtime <", value, "sjtime");
            return (Criteria) this;
        }

        public Criteria andSjtimeLessThanOrEqualTo(Date value) {
            addCriterion("sjtime <=", value, "sjtime");
            return (Criteria) this;
        }

        public Criteria andSjtimeIn(List<Date> values) {
            addCriterion("sjtime in", values, "sjtime");
            return (Criteria) this;
        }

        public Criteria andSjtimeNotIn(List<Date> values) {
            addCriterion("sjtime not in", values, "sjtime");
            return (Criteria) this;
        }

        public Criteria andSjtimeBetween(Date value1, Date value2) {
            addCriterion("sjtime between", value1, value2, "sjtime");
            return (Criteria) this;
        }

        public Criteria andSjtimeNotBetween(Date value1, Date value2) {
            addCriterion("sjtime not between", value1, value2, "sjtime");
            return (Criteria) this;
        }

        public Criteria andTokenidIsNull() {
            addCriterion("tokenId is null");
            return (Criteria) this;
        }

        public Criteria andTokenidIsNotNull() {
            addCriterion("tokenId is not null");
            return (Criteria) this;
        }

        public Criteria andTokenidEqualTo(String value) {
            addCriterion("tokenId =", value, "tokenid");
            return (Criteria) this;
        }

        public Criteria andTokenidNotEqualTo(String value) {
            addCriterion("tokenId <>", value, "tokenid");
            return (Criteria) this;
        }

        public Criteria andTokenidGreaterThan(String value) {
            addCriterion("tokenId >", value, "tokenid");
            return (Criteria) this;
        }

        public Criteria andTokenidGreaterThanOrEqualTo(String value) {
            addCriterion("tokenId >=", value, "tokenid");
            return (Criteria) this;
        }

        public Criteria andTokenidLessThan(String value) {
            addCriterion("tokenId <", value, "tokenid");
            return (Criteria) this;
        }

        public Criteria andTokenidLessThanOrEqualTo(String value) {
            addCriterion("tokenId <=", value, "tokenid");
            return (Criteria) this;
        }

        public Criteria andTokenidLike(String value) {
            addCriterion("tokenId like", value, "tokenid");
            return (Criteria) this;
        }

        public Criteria andTokenidNotLike(String value) {
            addCriterion("tokenId not like", value, "tokenid");
            return (Criteria) this;
        }

        public Criteria andTokenidIn(List<String> values) {
            addCriterion("tokenId in", values, "tokenid");
            return (Criteria) this;
        }

        public Criteria andTokenidNotIn(List<String> values) {
            addCriterion("tokenId not in", values, "tokenid");
            return (Criteria) this;
        }

        public Criteria andTokenidBetween(String value1, String value2) {
            addCriterion("tokenId between", value1, value2, "tokenid");
            return (Criteria) this;
        }

        public Criteria andTokenidNotBetween(String value1, String value2) {
            addCriterion("tokenId not between", value1, value2, "tokenid");
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

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andClassidIsNull() {
            addCriterion("classid is null");
            return (Criteria) this;
        }

        public Criteria andClassidIsNotNull() {
            addCriterion("classid is not null");
            return (Criteria) this;
        }

        public Criteria andClassidEqualTo(String value) {
            addCriterion("classid =", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidNotEqualTo(String value) {
            addCriterion("classid <>", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidGreaterThan(String value) {
            addCriterion("classid >", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidGreaterThanOrEqualTo(String value) {
            addCriterion("classid >=", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidLessThan(String value) {
            addCriterion("classid <", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidLessThanOrEqualTo(String value) {
            addCriterion("classid <=", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidLike(String value) {
            addCriterion("classid like", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidNotLike(String value) {
            addCriterion("classid not like", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidIn(List<String> values) {
            addCriterion("classid in", values, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidNotIn(List<String> values) {
            addCriterion("classid not in", values, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidBetween(String value1, String value2) {
            addCriterion("classid between", value1, value2, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidNotBetween(String value1, String value2) {
            addCriterion("classid not between", value1, value2, "classid");
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

        public Criteria andUsageIsNull() {
            addCriterion("usage is null");
            return (Criteria) this;
        }

        public Criteria andUsageIsNotNull() {
            addCriterion("usage is not null");
            return (Criteria) this;
        }

        public Criteria andUsageEqualTo(Integer value) {
            addCriterion("usage =", value, "usage");
            return (Criteria) this;
        }

        public Criteria andUsageNotEqualTo(Integer value) {
            addCriterion("usage <>", value, "usage");
            return (Criteria) this;
        }

        public Criteria andUsageGreaterThan(Integer value) {
            addCriterion("usage >", value, "usage");
            return (Criteria) this;
        }

        public Criteria andUsageGreaterThanOrEqualTo(Integer value) {
            addCriterion("usage >=", value, "usage");
            return (Criteria) this;
        }

        public Criteria andUsageLessThan(Integer value) {
            addCriterion("usage <", value, "usage");
            return (Criteria) this;
        }

        public Criteria andUsageLessThanOrEqualTo(Integer value) {
            addCriterion("usage <=", value, "usage");
            return (Criteria) this;
        }

        public Criteria andUsageIn(List<Integer> values) {
            addCriterion("usage in", values, "usage");
            return (Criteria) this;
        }

        public Criteria andUsageNotIn(List<Integer> values) {
            addCriterion("usage not in", values, "usage");
            return (Criteria) this;
        }

        public Criteria andUsageBetween(Integer value1, Integer value2) {
            addCriterion("usage between", value1, value2, "usage");
            return (Criteria) this;
        }

        public Criteria andUsageNotBetween(Integer value1, Integer value2) {
            addCriterion("usage not between", value1, value2, "usage");
            return (Criteria) this;
        }

        public Criteria andTradeTimeIsNull() {
            addCriterion("trade_time is null");
            return (Criteria) this;
        }

        public Criteria andTradeTimeIsNotNull() {
            addCriterion("trade_time is not null");
            return (Criteria) this;
        }

        public Criteria andTradeTimeEqualTo(Date value) {
            addCriterion("trade_time =", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeNotEqualTo(Date value) {
            addCriterion("trade_time <>", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeGreaterThan(Date value) {
            addCriterion("trade_time >", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("trade_time >=", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeLessThan(Date value) {
            addCriterion("trade_time <", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeLessThanOrEqualTo(Date value) {
            addCriterion("trade_time <=", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeIn(List<Date> values) {
            addCriterion("trade_time in", values, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeNotIn(List<Date> values) {
            addCriterion("trade_time not in", values, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeBetween(Date value1, Date value2) {
            addCriterion("trade_time between", value1, value2, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeNotBetween(Date value1, Date value2) {
            addCriterion("trade_time not between", value1, value2, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andPowerIsNull() {
            addCriterion("power is null");
            return (Criteria) this;
        }

        public Criteria andPowerIsNotNull() {
            addCriterion("power is not null");
            return (Criteria) this;
        }

        public Criteria andPowerEqualTo(BigDecimal value) {
            addCriterion("power =", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerNotEqualTo(BigDecimal value) {
            addCriterion("power <>", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerGreaterThan(BigDecimal value) {
            addCriterion("power >", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("power >=", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerLessThan(BigDecimal value) {
            addCriterion("power <", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerLessThanOrEqualTo(BigDecimal value) {
            addCriterion("power <=", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerIn(List<BigDecimal> values) {
            addCriterion("power in", values, "power");
            return (Criteria) this;
        }

        public Criteria andPowerNotIn(List<BigDecimal> values) {
            addCriterion("power not in", values, "power");
            return (Criteria) this;
        }

        public Criteria andPowerBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("power between", value1, value2, "power");
            return (Criteria) this;
        }

        public Criteria andPowerNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("power not between", value1, value2, "power");
            return (Criteria) this;
        }

        public Criteria andHealthIsNull() {
            addCriterion("health is null");
            return (Criteria) this;
        }

        public Criteria andHealthIsNotNull() {
            addCriterion("health is not null");
            return (Criteria) this;
        }

        public Criteria andHealthEqualTo(BigDecimal value) {
            addCriterion("health =", value, "health");
            return (Criteria) this;
        }

        public Criteria andHealthNotEqualTo(BigDecimal value) {
            addCriterion("health <>", value, "health");
            return (Criteria) this;
        }

        public Criteria andHealthGreaterThan(BigDecimal value) {
            addCriterion("health >", value, "health");
            return (Criteria) this;
        }

        public Criteria andHealthGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("health >=", value, "health");
            return (Criteria) this;
        }

        public Criteria andHealthLessThan(BigDecimal value) {
            addCriterion("health <", value, "health");
            return (Criteria) this;
        }

        public Criteria andHealthLessThanOrEqualTo(BigDecimal value) {
            addCriterion("health <=", value, "health");
            return (Criteria) this;
        }

        public Criteria andHealthIn(List<BigDecimal> values) {
            addCriterion("health in", values, "health");
            return (Criteria) this;
        }

        public Criteria andHealthNotIn(List<BigDecimal> values) {
            addCriterion("health not in", values, "health");
            return (Criteria) this;
        }

        public Criteria andHealthBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("health between", value1, value2, "health");
            return (Criteria) this;
        }

        public Criteria andHealthNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("health not between", value1, value2, "health");
            return (Criteria) this;
        }

        public Criteria andDisplayIsNull() {
            addCriterion("display is null");
            return (Criteria) this;
        }

        public Criteria andDisplayIsNotNull() {
            addCriterion("display is not null");
            return (Criteria) this;
        }

        public Criteria andDisplayEqualTo(Byte value) {
            addCriterion("display =", value, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayNotEqualTo(Byte value) {
            addCriterion("display <>", value, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayGreaterThan(Byte value) {
            addCriterion("display >", value, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayGreaterThanOrEqualTo(Byte value) {
            addCriterion("display >=", value, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayLessThan(Byte value) {
            addCriterion("display <", value, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayLessThanOrEqualTo(Byte value) {
            addCriterion("display <=", value, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayIn(List<Byte> values) {
            addCriterion("display in", values, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayNotIn(List<Byte> values) {
            addCriterion("display not in", values, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayBetween(Byte value1, Byte value2) {
            addCriterion("display between", value1, value2, "display");
            return (Criteria) this;
        }

        public Criteria andDisplayNotBetween(Byte value1, Byte value2) {
            addCriterion("display not between", value1, value2, "display");
            return (Criteria) this;
        }

        public Criteria andDialogueIdIsNull() {
            addCriterion("dialogue_id is null");
            return (Criteria) this;
        }

        public Criteria andDialogueIdIsNotNull() {
            addCriterion("dialogue_id is not null");
            return (Criteria) this;
        }

        public Criteria andDialogueIdEqualTo(String value) {
            addCriterion("dialogue_id =", value, "dialogueId");
            return (Criteria) this;
        }

        public Criteria andDialogueIdNotEqualTo(String value) {
            addCriterion("dialogue_id <>", value, "dialogueId");
            return (Criteria) this;
        }

        public Criteria andDialogueIdGreaterThan(String value) {
            addCriterion("dialogue_id >", value, "dialogueId");
            return (Criteria) this;
        }

        public Criteria andDialogueIdGreaterThanOrEqualTo(String value) {
            addCriterion("dialogue_id >=", value, "dialogueId");
            return (Criteria) this;
        }

        public Criteria andDialogueIdLessThan(String value) {
            addCriterion("dialogue_id <", value, "dialogueId");
            return (Criteria) this;
        }

        public Criteria andDialogueIdLessThanOrEqualTo(String value) {
            addCriterion("dialogue_id <=", value, "dialogueId");
            return (Criteria) this;
        }

        public Criteria andDialogueIdLike(String value) {
            addCriterion("dialogue_id like", value, "dialogueId");
            return (Criteria) this;
        }

        public Criteria andDialogueIdNotLike(String value) {
            addCriterion("dialogue_id not like", value, "dialogueId");
            return (Criteria) this;
        }

        public Criteria andDialogueIdIn(List<String> values) {
            addCriterion("dialogue_id in", values, "dialogueId");
            return (Criteria) this;
        }

        public Criteria andDialogueIdNotIn(List<String> values) {
            addCriterion("dialogue_id not in", values, "dialogueId");
            return (Criteria) this;
        }

        public Criteria andDialogueIdBetween(String value1, String value2) {
            addCriterion("dialogue_id between", value1, value2, "dialogueId");
            return (Criteria) this;
        }

        public Criteria andDialogueIdNotBetween(String value1, String value2) {
            addCriterion("dialogue_id not between", value1, value2, "dialogueId");
            return (Criteria) this;
        }

        public Criteria andDialogueRoleIsNull() {
            addCriterion("dialogue_role is null");
            return (Criteria) this;
        }

        public Criteria andDialogueRoleIsNotNull() {
            addCriterion("dialogue_role is not null");
            return (Criteria) this;
        }

        public Criteria andDialogueRoleEqualTo(String value) {
            addCriterion("dialogue_role =", value, "dialogueRole");
            return (Criteria) this;
        }

        public Criteria andDialogueRoleNotEqualTo(String value) {
            addCriterion("dialogue_role <>", value, "dialogueRole");
            return (Criteria) this;
        }

        public Criteria andDialogueRoleGreaterThan(String value) {
            addCriterion("dialogue_role >", value, "dialogueRole");
            return (Criteria) this;
        }

        public Criteria andDialogueRoleGreaterThanOrEqualTo(String value) {
            addCriterion("dialogue_role >=", value, "dialogueRole");
            return (Criteria) this;
        }

        public Criteria andDialogueRoleLessThan(String value) {
            addCriterion("dialogue_role <", value, "dialogueRole");
            return (Criteria) this;
        }

        public Criteria andDialogueRoleLessThanOrEqualTo(String value) {
            addCriterion("dialogue_role <=", value, "dialogueRole");
            return (Criteria) this;
        }

        public Criteria andDialogueRoleLike(String value) {
            addCriterion("dialogue_role like", value, "dialogueRole");
            return (Criteria) this;
        }

        public Criteria andDialogueRoleNotLike(String value) {
            addCriterion("dialogue_role not like", value, "dialogueRole");
            return (Criteria) this;
        }

        public Criteria andDialogueRoleIn(List<String> values) {
            addCriterion("dialogue_role in", values, "dialogueRole");
            return (Criteria) this;
        }

        public Criteria andDialogueRoleNotIn(List<String> values) {
            addCriterion("dialogue_role not in", values, "dialogueRole");
            return (Criteria) this;
        }

        public Criteria andDialogueRoleBetween(String value1, String value2) {
            addCriterion("dialogue_role between", value1, value2, "dialogueRole");
            return (Criteria) this;
        }

        public Criteria andDialogueRoleNotBetween(String value1, String value2) {
            addCriterion("dialogue_role not between", value1, value2, "dialogueRole");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * user_grant 
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