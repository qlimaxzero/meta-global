package com.shitouren.core.autogenerate.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PullnewExample {
    /**
     * pullnew
     */
    protected String orderByClause;

    /**
     * pullnew
     */
    protected boolean distinct;

    /**
     * pullnew
     */
    protected List<Criteria> oredCriteria;

    /**
     * pullnew
     */
    protected Integer pageNo = 1;

    /**
     * pullnew
     */
    protected Integer startRow;

    /**
     * pullnew
     */
    protected Integer pageSize = 10;

    public PullnewExample() {
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
     * pullnew 
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

        public Criteria andOpenactivityIsNull() {
            addCriterion("openactivity is null");
            return (Criteria) this;
        }

        public Criteria andOpenactivityIsNotNull() {
            addCriterion("openactivity is not null");
            return (Criteria) this;
        }

        public Criteria andOpenactivityEqualTo(Integer value) {
            addCriterion("openactivity =", value, "openactivity");
            return (Criteria) this;
        }

        public Criteria andOpenactivityNotEqualTo(Integer value) {
            addCriterion("openactivity <>", value, "openactivity");
            return (Criteria) this;
        }

        public Criteria andOpenactivityGreaterThan(Integer value) {
            addCriterion("openactivity >", value, "openactivity");
            return (Criteria) this;
        }

        public Criteria andOpenactivityGreaterThanOrEqualTo(Integer value) {
            addCriterion("openactivity >=", value, "openactivity");
            return (Criteria) this;
        }

        public Criteria andOpenactivityLessThan(Integer value) {
            addCriterion("openactivity <", value, "openactivity");
            return (Criteria) this;
        }

        public Criteria andOpenactivityLessThanOrEqualTo(Integer value) {
            addCriterion("openactivity <=", value, "openactivity");
            return (Criteria) this;
        }

        public Criteria andOpenactivityIn(List<Integer> values) {
            addCriterion("openactivity in", values, "openactivity");
            return (Criteria) this;
        }

        public Criteria andOpenactivityNotIn(List<Integer> values) {
            addCriterion("openactivity not in", values, "openactivity");
            return (Criteria) this;
        }

        public Criteria andOpenactivityBetween(Integer value1, Integer value2) {
            addCriterion("openactivity between", value1, value2, "openactivity");
            return (Criteria) this;
        }

        public Criteria andOpenactivityNotBetween(Integer value1, Integer value2) {
            addCriterion("openactivity not between", value1, value2, "openactivity");
            return (Criteria) this;
        }

        public Criteria andOpenactivitycreattimeIsNull() {
            addCriterion("openactivitycreattime is null");
            return (Criteria) this;
        }

        public Criteria andOpenactivitycreattimeIsNotNull() {
            addCriterion("openactivitycreattime is not null");
            return (Criteria) this;
        }

        public Criteria andOpenactivitycreattimeEqualTo(Date value) {
            addCriterion("openactivitycreattime =", value, "openactivitycreattime");
            return (Criteria) this;
        }

        public Criteria andOpenactivitycreattimeNotEqualTo(Date value) {
            addCriterion("openactivitycreattime <>", value, "openactivitycreattime");
            return (Criteria) this;
        }

        public Criteria andOpenactivitycreattimeGreaterThan(Date value) {
            addCriterion("openactivitycreattime >", value, "openactivitycreattime");
            return (Criteria) this;
        }

        public Criteria andOpenactivitycreattimeGreaterThanOrEqualTo(Date value) {
            addCriterion("openactivitycreattime >=", value, "openactivitycreattime");
            return (Criteria) this;
        }

        public Criteria andOpenactivitycreattimeLessThan(Date value) {
            addCriterion("openactivitycreattime <", value, "openactivitycreattime");
            return (Criteria) this;
        }

        public Criteria andOpenactivitycreattimeLessThanOrEqualTo(Date value) {
            addCriterion("openactivitycreattime <=", value, "openactivitycreattime");
            return (Criteria) this;
        }

        public Criteria andOpenactivitycreattimeIn(List<Date> values) {
            addCriterion("openactivitycreattime in", values, "openactivitycreattime");
            return (Criteria) this;
        }

        public Criteria andOpenactivitycreattimeNotIn(List<Date> values) {
            addCriterion("openactivitycreattime not in", values, "openactivitycreattime");
            return (Criteria) this;
        }

        public Criteria andOpenactivitycreattimeBetween(Date value1, Date value2) {
            addCriterion("openactivitycreattime between", value1, value2, "openactivitycreattime");
            return (Criteria) this;
        }

        public Criteria andOpenactivitycreattimeNotBetween(Date value1, Date value2) {
            addCriterion("openactivitycreattime not between", value1, value2, "openactivitycreattime");
            return (Criteria) this;
        }

        public Criteria andOpenactivityendtimeIsNull() {
            addCriterion("openactivityendtime is null");
            return (Criteria) this;
        }

        public Criteria andOpenactivityendtimeIsNotNull() {
            addCriterion("openactivityendtime is not null");
            return (Criteria) this;
        }

        public Criteria andOpenactivityendtimeEqualTo(Date value) {
            addCriterion("openactivityendtime =", value, "openactivityendtime");
            return (Criteria) this;
        }

        public Criteria andOpenactivityendtimeNotEqualTo(Date value) {
            addCriterion("openactivityendtime <>", value, "openactivityendtime");
            return (Criteria) this;
        }

        public Criteria andOpenactivityendtimeGreaterThan(Date value) {
            addCriterion("openactivityendtime >", value, "openactivityendtime");
            return (Criteria) this;
        }

        public Criteria andOpenactivityendtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("openactivityendtime >=", value, "openactivityendtime");
            return (Criteria) this;
        }

        public Criteria andOpenactivityendtimeLessThan(Date value) {
            addCriterion("openactivityendtime <", value, "openactivityendtime");
            return (Criteria) this;
        }

        public Criteria andOpenactivityendtimeLessThanOrEqualTo(Date value) {
            addCriterion("openactivityendtime <=", value, "openactivityendtime");
            return (Criteria) this;
        }

        public Criteria andOpenactivityendtimeIn(List<Date> values) {
            addCriterion("openactivityendtime in", values, "openactivityendtime");
            return (Criteria) this;
        }

        public Criteria andOpenactivityendtimeNotIn(List<Date> values) {
            addCriterion("openactivityendtime not in", values, "openactivityendtime");
            return (Criteria) this;
        }

        public Criteria andOpenactivityendtimeBetween(Date value1, Date value2) {
            addCriterion("openactivityendtime between", value1, value2, "openactivityendtime");
            return (Criteria) this;
        }

        public Criteria andOpenactivityendtimeNotBetween(Date value1, Date value2) {
            addCriterion("openactivityendtime not between", value1, value2, "openactivityendtime");
            return (Criteria) this;
        }

        public Criteria andOpenblindIsNull() {
            addCriterion("openblind is null");
            return (Criteria) this;
        }

        public Criteria andOpenblindIsNotNull() {
            addCriterion("openblind is not null");
            return (Criteria) this;
        }

        public Criteria andOpenblindEqualTo(Integer value) {
            addCriterion("openblind =", value, "openblind");
            return (Criteria) this;
        }

        public Criteria andOpenblindNotEqualTo(Integer value) {
            addCriterion("openblind <>", value, "openblind");
            return (Criteria) this;
        }

        public Criteria andOpenblindGreaterThan(Integer value) {
            addCriterion("openblind >", value, "openblind");
            return (Criteria) this;
        }

        public Criteria andOpenblindGreaterThanOrEqualTo(Integer value) {
            addCriterion("openblind >=", value, "openblind");
            return (Criteria) this;
        }

        public Criteria andOpenblindLessThan(Integer value) {
            addCriterion("openblind <", value, "openblind");
            return (Criteria) this;
        }

        public Criteria andOpenblindLessThanOrEqualTo(Integer value) {
            addCriterion("openblind <=", value, "openblind");
            return (Criteria) this;
        }

        public Criteria andOpenblindIn(List<Integer> values) {
            addCriterion("openblind in", values, "openblind");
            return (Criteria) this;
        }

        public Criteria andOpenblindNotIn(List<Integer> values) {
            addCriterion("openblind not in", values, "openblind");
            return (Criteria) this;
        }

        public Criteria andOpenblindBetween(Integer value1, Integer value2) {
            addCriterion("openblind between", value1, value2, "openblind");
            return (Criteria) this;
        }

        public Criteria andOpenblindNotBetween(Integer value1, Integer value2) {
            addCriterion("openblind not between", value1, value2, "openblind");
            return (Criteria) this;
        }

        public Criteria andBlindboxidIsNull() {
            addCriterion("blindboxid is null");
            return (Criteria) this;
        }

        public Criteria andBlindboxidIsNotNull() {
            addCriterion("blindboxid is not null");
            return (Criteria) this;
        }

        public Criteria andBlindboxidEqualTo(Integer value) {
            addCriterion("blindboxid =", value, "blindboxid");
            return (Criteria) this;
        }

        public Criteria andBlindboxidNotEqualTo(Integer value) {
            addCriterion("blindboxid <>", value, "blindboxid");
            return (Criteria) this;
        }

        public Criteria andBlindboxidGreaterThan(Integer value) {
            addCriterion("blindboxid >", value, "blindboxid");
            return (Criteria) this;
        }

        public Criteria andBlindboxidGreaterThanOrEqualTo(Integer value) {
            addCriterion("blindboxid >=", value, "blindboxid");
            return (Criteria) this;
        }

        public Criteria andBlindboxidLessThan(Integer value) {
            addCriterion("blindboxid <", value, "blindboxid");
            return (Criteria) this;
        }

        public Criteria andBlindboxidLessThanOrEqualTo(Integer value) {
            addCriterion("blindboxid <=", value, "blindboxid");
            return (Criteria) this;
        }

        public Criteria andBlindboxidIn(List<Integer> values) {
            addCriterion("blindboxid in", values, "blindboxid");
            return (Criteria) this;
        }

        public Criteria andBlindboxidNotIn(List<Integer> values) {
            addCriterion("blindboxid not in", values, "blindboxid");
            return (Criteria) this;
        }

        public Criteria andBlindboxidBetween(Integer value1, Integer value2) {
            addCriterion("blindboxid between", value1, value2, "blindboxid");
            return (Criteria) this;
        }

        public Criteria andBlindboxidNotBetween(Integer value1, Integer value2) {
            addCriterion("blindboxid not between", value1, value2, "blindboxid");
            return (Criteria) this;
        }

        public Criteria andRealnameblindIsNull() {
            addCriterion("realnameblind is null");
            return (Criteria) this;
        }

        public Criteria andRealnameblindIsNotNull() {
            addCriterion("realnameblind is not null");
            return (Criteria) this;
        }

        public Criteria andRealnameblindEqualTo(Integer value) {
            addCriterion("realnameblind =", value, "realnameblind");
            return (Criteria) this;
        }

        public Criteria andRealnameblindNotEqualTo(Integer value) {
            addCriterion("realnameblind <>", value, "realnameblind");
            return (Criteria) this;
        }

        public Criteria andRealnameblindGreaterThan(Integer value) {
            addCriterion("realnameblind >", value, "realnameblind");
            return (Criteria) this;
        }

        public Criteria andRealnameblindGreaterThanOrEqualTo(Integer value) {
            addCriterion("realnameblind >=", value, "realnameblind");
            return (Criteria) this;
        }

        public Criteria andRealnameblindLessThan(Integer value) {
            addCriterion("realnameblind <", value, "realnameblind");
            return (Criteria) this;
        }

        public Criteria andRealnameblindLessThanOrEqualTo(Integer value) {
            addCriterion("realnameblind <=", value, "realnameblind");
            return (Criteria) this;
        }

        public Criteria andRealnameblindIn(List<Integer> values) {
            addCriterion("realnameblind in", values, "realnameblind");
            return (Criteria) this;
        }

        public Criteria andRealnameblindNotIn(List<Integer> values) {
            addCriterion("realnameblind not in", values, "realnameblind");
            return (Criteria) this;
        }

        public Criteria andRealnameblindBetween(Integer value1, Integer value2) {
            addCriterion("realnameblind between", value1, value2, "realnameblind");
            return (Criteria) this;
        }

        public Criteria andRealnameblindNotBetween(Integer value1, Integer value2) {
            addCriterion("realnameblind not between", value1, value2, "realnameblind");
            return (Criteria) this;
        }

        public Criteria andLadderinfoIsNull() {
            addCriterion("ladderinfo is null");
            return (Criteria) this;
        }

        public Criteria andLadderinfoIsNotNull() {
            addCriterion("ladderinfo is not null");
            return (Criteria) this;
        }

        public Criteria andLadderinfoEqualTo(String value) {
            addCriterion("ladderinfo =", value, "ladderinfo");
            return (Criteria) this;
        }

        public Criteria andLadderinfoNotEqualTo(String value) {
            addCriterion("ladderinfo <>", value, "ladderinfo");
            return (Criteria) this;
        }

        public Criteria andLadderinfoGreaterThan(String value) {
            addCriterion("ladderinfo >", value, "ladderinfo");
            return (Criteria) this;
        }

        public Criteria andLadderinfoGreaterThanOrEqualTo(String value) {
            addCriterion("ladderinfo >=", value, "ladderinfo");
            return (Criteria) this;
        }

        public Criteria andLadderinfoLessThan(String value) {
            addCriterion("ladderinfo <", value, "ladderinfo");
            return (Criteria) this;
        }

        public Criteria andLadderinfoLessThanOrEqualTo(String value) {
            addCriterion("ladderinfo <=", value, "ladderinfo");
            return (Criteria) this;
        }

        public Criteria andLadderinfoLike(String value) {
            addCriterion("ladderinfo like", value, "ladderinfo");
            return (Criteria) this;
        }

        public Criteria andLadderinfoNotLike(String value) {
            addCriterion("ladderinfo not like", value, "ladderinfo");
            return (Criteria) this;
        }

        public Criteria andLadderinfoIn(List<String> values) {
            addCriterion("ladderinfo in", values, "ladderinfo");
            return (Criteria) this;
        }

        public Criteria andLadderinfoNotIn(List<String> values) {
            addCriterion("ladderinfo not in", values, "ladderinfo");
            return (Criteria) this;
        }

        public Criteria andLadderinfoBetween(String value1, String value2) {
            addCriterion("ladderinfo between", value1, value2, "ladderinfo");
            return (Criteria) this;
        }

        public Criteria andLadderinfoNotBetween(String value1, String value2) {
            addCriterion("ladderinfo not between", value1, value2, "ladderinfo");
            return (Criteria) this;
        }

        public Criteria andOpencollectionIsNull() {
            addCriterion("opencollection is null");
            return (Criteria) this;
        }

        public Criteria andOpencollectionIsNotNull() {
            addCriterion("opencollection is not null");
            return (Criteria) this;
        }

        public Criteria andOpencollectionEqualTo(Integer value) {
            addCriterion("opencollection =", value, "opencollection");
            return (Criteria) this;
        }

        public Criteria andOpencollectionNotEqualTo(Integer value) {
            addCriterion("opencollection <>", value, "opencollection");
            return (Criteria) this;
        }

        public Criteria andOpencollectionGreaterThan(Integer value) {
            addCriterion("opencollection >", value, "opencollection");
            return (Criteria) this;
        }

        public Criteria andOpencollectionGreaterThanOrEqualTo(Integer value) {
            addCriterion("opencollection >=", value, "opencollection");
            return (Criteria) this;
        }

        public Criteria andOpencollectionLessThan(Integer value) {
            addCriterion("opencollection <", value, "opencollection");
            return (Criteria) this;
        }

        public Criteria andOpencollectionLessThanOrEqualTo(Integer value) {
            addCriterion("opencollection <=", value, "opencollection");
            return (Criteria) this;
        }

        public Criteria andOpencollectionIn(List<Integer> values) {
            addCriterion("opencollection in", values, "opencollection");
            return (Criteria) this;
        }

        public Criteria andOpencollectionNotIn(List<Integer> values) {
            addCriterion("opencollection not in", values, "opencollection");
            return (Criteria) this;
        }

        public Criteria andOpencollectionBetween(Integer value1, Integer value2) {
            addCriterion("opencollection between", value1, value2, "opencollection");
            return (Criteria) this;
        }

        public Criteria andOpencollectionNotBetween(Integer value1, Integer value2) {
            addCriterion("opencollection not between", value1, value2, "opencollection");
            return (Criteria) this;
        }

        public Criteria andCollectionidIsNull() {
            addCriterion("collectionid is null");
            return (Criteria) this;
        }

        public Criteria andCollectionidIsNotNull() {
            addCriterion("collectionid is not null");
            return (Criteria) this;
        }

        public Criteria andCollectionidEqualTo(Integer value) {
            addCriterion("collectionid =", value, "collectionid");
            return (Criteria) this;
        }

        public Criteria andCollectionidNotEqualTo(Integer value) {
            addCriterion("collectionid <>", value, "collectionid");
            return (Criteria) this;
        }

        public Criteria andCollectionidGreaterThan(Integer value) {
            addCriterion("collectionid >", value, "collectionid");
            return (Criteria) this;
        }

        public Criteria andCollectionidGreaterThanOrEqualTo(Integer value) {
            addCriterion("collectionid >=", value, "collectionid");
            return (Criteria) this;
        }

        public Criteria andCollectionidLessThan(Integer value) {
            addCriterion("collectionid <", value, "collectionid");
            return (Criteria) this;
        }

        public Criteria andCollectionidLessThanOrEqualTo(Integer value) {
            addCriterion("collectionid <=", value, "collectionid");
            return (Criteria) this;
        }

        public Criteria andCollectionidIn(List<Integer> values) {
            addCriterion("collectionid in", values, "collectionid");
            return (Criteria) this;
        }

        public Criteria andCollectionidNotIn(List<Integer> values) {
            addCriterion("collectionid not in", values, "collectionid");
            return (Criteria) this;
        }

        public Criteria andCollectionidBetween(Integer value1, Integer value2) {
            addCriterion("collectionid between", value1, value2, "collectionid");
            return (Criteria) this;
        }

        public Criteria andCollectionidNotBetween(Integer value1, Integer value2) {
            addCriterion("collectionid not between", value1, value2, "collectionid");
            return (Criteria) this;
        }

        public Criteria andRealnamecollectionIsNull() {
            addCriterion("realnamecollection is null");
            return (Criteria) this;
        }

        public Criteria andRealnamecollectionIsNotNull() {
            addCriterion("realnamecollection is not null");
            return (Criteria) this;
        }

        public Criteria andRealnamecollectionEqualTo(Integer value) {
            addCriterion("realnamecollection =", value, "realnamecollection");
            return (Criteria) this;
        }

        public Criteria andRealnamecollectionNotEqualTo(Integer value) {
            addCriterion("realnamecollection <>", value, "realnamecollection");
            return (Criteria) this;
        }

        public Criteria andRealnamecollectionGreaterThan(Integer value) {
            addCriterion("realnamecollection >", value, "realnamecollection");
            return (Criteria) this;
        }

        public Criteria andRealnamecollectionGreaterThanOrEqualTo(Integer value) {
            addCriterion("realnamecollection >=", value, "realnamecollection");
            return (Criteria) this;
        }

        public Criteria andRealnamecollectionLessThan(Integer value) {
            addCriterion("realnamecollection <", value, "realnamecollection");
            return (Criteria) this;
        }

        public Criteria andRealnamecollectionLessThanOrEqualTo(Integer value) {
            addCriterion("realnamecollection <=", value, "realnamecollection");
            return (Criteria) this;
        }

        public Criteria andRealnamecollectionIn(List<Integer> values) {
            addCriterion("realnamecollection in", values, "realnamecollection");
            return (Criteria) this;
        }

        public Criteria andRealnamecollectionNotIn(List<Integer> values) {
            addCriterion("realnamecollection not in", values, "realnamecollection");
            return (Criteria) this;
        }

        public Criteria andRealnamecollectionBetween(Integer value1, Integer value2) {
            addCriterion("realnamecollection between", value1, value2, "realnamecollection");
            return (Criteria) this;
        }

        public Criteria andRealnamecollectionNotBetween(Integer value1, Integer value2) {
            addCriterion("realnamecollection not between", value1, value2, "realnamecollection");
            return (Criteria) this;
        }

        public Criteria andColladderinfoIsNull() {
            addCriterion("colLadderinfo is null");
            return (Criteria) this;
        }

        public Criteria andColladderinfoIsNotNull() {
            addCriterion("colLadderinfo is not null");
            return (Criteria) this;
        }

        public Criteria andColladderinfoEqualTo(String value) {
            addCriterion("colLadderinfo =", value, "colladderinfo");
            return (Criteria) this;
        }

        public Criteria andColladderinfoNotEqualTo(String value) {
            addCriterion("colLadderinfo <>", value, "colladderinfo");
            return (Criteria) this;
        }

        public Criteria andColladderinfoGreaterThan(String value) {
            addCriterion("colLadderinfo >", value, "colladderinfo");
            return (Criteria) this;
        }

        public Criteria andColladderinfoGreaterThanOrEqualTo(String value) {
            addCriterion("colLadderinfo >=", value, "colladderinfo");
            return (Criteria) this;
        }

        public Criteria andColladderinfoLessThan(String value) {
            addCriterion("colLadderinfo <", value, "colladderinfo");
            return (Criteria) this;
        }

        public Criteria andColladderinfoLessThanOrEqualTo(String value) {
            addCriterion("colLadderinfo <=", value, "colladderinfo");
            return (Criteria) this;
        }

        public Criteria andColladderinfoLike(String value) {
            addCriterion("colLadderinfo like", value, "colladderinfo");
            return (Criteria) this;
        }

        public Criteria andColladderinfoNotLike(String value) {
            addCriterion("colLadderinfo not like", value, "colladderinfo");
            return (Criteria) this;
        }

        public Criteria andColladderinfoIn(List<String> values) {
            addCriterion("colLadderinfo in", values, "colladderinfo");
            return (Criteria) this;
        }

        public Criteria andColladderinfoNotIn(List<String> values) {
            addCriterion("colLadderinfo not in", values, "colladderinfo");
            return (Criteria) this;
        }

        public Criteria andColladderinfoBetween(String value1, String value2) {
            addCriterion("colLadderinfo between", value1, value2, "colladderinfo");
            return (Criteria) this;
        }

        public Criteria andColladderinfoNotBetween(String value1, String value2) {
            addCriterion("colLadderinfo not between", value1, value2, "colladderinfo");
            return (Criteria) this;
        }

        public Criteria andNewsendIsNull() {
            addCriterion("newsend is null");
            return (Criteria) this;
        }

        public Criteria andNewsendIsNotNull() {
            addCriterion("newsend is not null");
            return (Criteria) this;
        }

        public Criteria andNewsendEqualTo(Integer value) {
            addCriterion("newsend =", value, "newsend");
            return (Criteria) this;
        }

        public Criteria andNewsendNotEqualTo(Integer value) {
            addCriterion("newsend <>", value, "newsend");
            return (Criteria) this;
        }

        public Criteria andNewsendGreaterThan(Integer value) {
            addCriterion("newsend >", value, "newsend");
            return (Criteria) this;
        }

        public Criteria andNewsendGreaterThanOrEqualTo(Integer value) {
            addCriterion("newsend >=", value, "newsend");
            return (Criteria) this;
        }

        public Criteria andNewsendLessThan(Integer value) {
            addCriterion("newsend <", value, "newsend");
            return (Criteria) this;
        }

        public Criteria andNewsendLessThanOrEqualTo(Integer value) {
            addCriterion("newsend <=", value, "newsend");
            return (Criteria) this;
        }

        public Criteria andNewsendIn(List<Integer> values) {
            addCriterion("newsend in", values, "newsend");
            return (Criteria) this;
        }

        public Criteria andNewsendNotIn(List<Integer> values) {
            addCriterion("newsend not in", values, "newsend");
            return (Criteria) this;
        }

        public Criteria andNewsendBetween(Integer value1, Integer value2) {
            addCriterion("newsend between", value1, value2, "newsend");
            return (Criteria) this;
        }

        public Criteria andNewsendNotBetween(Integer value1, Integer value2) {
            addCriterion("newsend not between", value1, value2, "newsend");
            return (Criteria) this;
        }

        public Criteria andIsrealnameIsNull() {
            addCriterion("isrealname is null");
            return (Criteria) this;
        }

        public Criteria andIsrealnameIsNotNull() {
            addCriterion("isrealname is not null");
            return (Criteria) this;
        }

        public Criteria andIsrealnameEqualTo(Integer value) {
            addCriterion("isrealname =", value, "isrealname");
            return (Criteria) this;
        }

        public Criteria andIsrealnameNotEqualTo(Integer value) {
            addCriterion("isrealname <>", value, "isrealname");
            return (Criteria) this;
        }

        public Criteria andIsrealnameGreaterThan(Integer value) {
            addCriterion("isrealname >", value, "isrealname");
            return (Criteria) this;
        }

        public Criteria andIsrealnameGreaterThanOrEqualTo(Integer value) {
            addCriterion("isrealname >=", value, "isrealname");
            return (Criteria) this;
        }

        public Criteria andIsrealnameLessThan(Integer value) {
            addCriterion("isrealname <", value, "isrealname");
            return (Criteria) this;
        }

        public Criteria andIsrealnameLessThanOrEqualTo(Integer value) {
            addCriterion("isrealname <=", value, "isrealname");
            return (Criteria) this;
        }

        public Criteria andIsrealnameIn(List<Integer> values) {
            addCriterion("isrealname in", values, "isrealname");
            return (Criteria) this;
        }

        public Criteria andIsrealnameNotIn(List<Integer> values) {
            addCriterion("isrealname not in", values, "isrealname");
            return (Criteria) this;
        }

        public Criteria andIsrealnameBetween(Integer value1, Integer value2) {
            addCriterion("isrealname between", value1, value2, "isrealname");
            return (Criteria) this;
        }

        public Criteria andIsrealnameNotBetween(Integer value1, Integer value2) {
            addCriterion("isrealname not between", value1, value2, "isrealname");
            return (Criteria) this;
        }

        public Criteria andIsendblindIsNull() {
            addCriterion("isendblind is null");
            return (Criteria) this;
        }

        public Criteria andIsendblindIsNotNull() {
            addCriterion("isendblind is not null");
            return (Criteria) this;
        }

        public Criteria andIsendblindEqualTo(Integer value) {
            addCriterion("isendblind =", value, "isendblind");
            return (Criteria) this;
        }

        public Criteria andIsendblindNotEqualTo(Integer value) {
            addCriterion("isendblind <>", value, "isendblind");
            return (Criteria) this;
        }

        public Criteria andIsendblindGreaterThan(Integer value) {
            addCriterion("isendblind >", value, "isendblind");
            return (Criteria) this;
        }

        public Criteria andIsendblindGreaterThanOrEqualTo(Integer value) {
            addCriterion("isendblind >=", value, "isendblind");
            return (Criteria) this;
        }

        public Criteria andIsendblindLessThan(Integer value) {
            addCriterion("isendblind <", value, "isendblind");
            return (Criteria) this;
        }

        public Criteria andIsendblindLessThanOrEqualTo(Integer value) {
            addCriterion("isendblind <=", value, "isendblind");
            return (Criteria) this;
        }

        public Criteria andIsendblindIn(List<Integer> values) {
            addCriterion("isendblind in", values, "isendblind");
            return (Criteria) this;
        }

        public Criteria andIsendblindNotIn(List<Integer> values) {
            addCriterion("isendblind not in", values, "isendblind");
            return (Criteria) this;
        }

        public Criteria andIsendblindBetween(Integer value1, Integer value2) {
            addCriterion("isendblind between", value1, value2, "isendblind");
            return (Criteria) this;
        }

        public Criteria andIsendblindNotBetween(Integer value1, Integer value2) {
            addCriterion("isendblind not between", value1, value2, "isendblind");
            return (Criteria) this;
        }

        public Criteria andNewblindidIsNull() {
            addCriterion("newblindid is null");
            return (Criteria) this;
        }

        public Criteria andNewblindidIsNotNull() {
            addCriterion("newblindid is not null");
            return (Criteria) this;
        }

        public Criteria andNewblindidEqualTo(Integer value) {
            addCriterion("newblindid =", value, "newblindid");
            return (Criteria) this;
        }

        public Criteria andNewblindidNotEqualTo(Integer value) {
            addCriterion("newblindid <>", value, "newblindid");
            return (Criteria) this;
        }

        public Criteria andNewblindidGreaterThan(Integer value) {
            addCriterion("newblindid >", value, "newblindid");
            return (Criteria) this;
        }

        public Criteria andNewblindidGreaterThanOrEqualTo(Integer value) {
            addCriterion("newblindid >=", value, "newblindid");
            return (Criteria) this;
        }

        public Criteria andNewblindidLessThan(Integer value) {
            addCriterion("newblindid <", value, "newblindid");
            return (Criteria) this;
        }

        public Criteria andNewblindidLessThanOrEqualTo(Integer value) {
            addCriterion("newblindid <=", value, "newblindid");
            return (Criteria) this;
        }

        public Criteria andNewblindidIn(List<Integer> values) {
            addCriterion("newblindid in", values, "newblindid");
            return (Criteria) this;
        }

        public Criteria andNewblindidNotIn(List<Integer> values) {
            addCriterion("newblindid not in", values, "newblindid");
            return (Criteria) this;
        }

        public Criteria andNewblindidBetween(Integer value1, Integer value2) {
            addCriterion("newblindid between", value1, value2, "newblindid");
            return (Criteria) this;
        }

        public Criteria andNewblindidNotBetween(Integer value1, Integer value2) {
            addCriterion("newblindid not between", value1, value2, "newblindid");
            return (Criteria) this;
        }

        public Criteria andIsendcolIsNull() {
            addCriterion("isendcol is null");
            return (Criteria) this;
        }

        public Criteria andIsendcolIsNotNull() {
            addCriterion("isendcol is not null");
            return (Criteria) this;
        }

        public Criteria andIsendcolEqualTo(Integer value) {
            addCriterion("isendcol =", value, "isendcol");
            return (Criteria) this;
        }

        public Criteria andIsendcolNotEqualTo(Integer value) {
            addCriterion("isendcol <>", value, "isendcol");
            return (Criteria) this;
        }

        public Criteria andIsendcolGreaterThan(Integer value) {
            addCriterion("isendcol >", value, "isendcol");
            return (Criteria) this;
        }

        public Criteria andIsendcolGreaterThanOrEqualTo(Integer value) {
            addCriterion("isendcol >=", value, "isendcol");
            return (Criteria) this;
        }

        public Criteria andIsendcolLessThan(Integer value) {
            addCriterion("isendcol <", value, "isendcol");
            return (Criteria) this;
        }

        public Criteria andIsendcolLessThanOrEqualTo(Integer value) {
            addCriterion("isendcol <=", value, "isendcol");
            return (Criteria) this;
        }

        public Criteria andIsendcolIn(List<Integer> values) {
            addCriterion("isendcol in", values, "isendcol");
            return (Criteria) this;
        }

        public Criteria andIsendcolNotIn(List<Integer> values) {
            addCriterion("isendcol not in", values, "isendcol");
            return (Criteria) this;
        }

        public Criteria andIsendcolBetween(Integer value1, Integer value2) {
            addCriterion("isendcol between", value1, value2, "isendcol");
            return (Criteria) this;
        }

        public Criteria andIsendcolNotBetween(Integer value1, Integer value2) {
            addCriterion("isendcol not between", value1, value2, "isendcol");
            return (Criteria) this;
        }

        public Criteria andNewcollectionidIsNull() {
            addCriterion("newcollectionid is null");
            return (Criteria) this;
        }

        public Criteria andNewcollectionidIsNotNull() {
            addCriterion("newcollectionid is not null");
            return (Criteria) this;
        }

        public Criteria andNewcollectionidEqualTo(Integer value) {
            addCriterion("newcollectionid =", value, "newcollectionid");
            return (Criteria) this;
        }

        public Criteria andNewcollectionidNotEqualTo(Integer value) {
            addCriterion("newcollectionid <>", value, "newcollectionid");
            return (Criteria) this;
        }

        public Criteria andNewcollectionidGreaterThan(Integer value) {
            addCriterion("newcollectionid >", value, "newcollectionid");
            return (Criteria) this;
        }

        public Criteria andNewcollectionidGreaterThanOrEqualTo(Integer value) {
            addCriterion("newcollectionid >=", value, "newcollectionid");
            return (Criteria) this;
        }

        public Criteria andNewcollectionidLessThan(Integer value) {
            addCriterion("newcollectionid <", value, "newcollectionid");
            return (Criteria) this;
        }

        public Criteria andNewcollectionidLessThanOrEqualTo(Integer value) {
            addCriterion("newcollectionid <=", value, "newcollectionid");
            return (Criteria) this;
        }

        public Criteria andNewcollectionidIn(List<Integer> values) {
            addCriterion("newcollectionid in", values, "newcollectionid");
            return (Criteria) this;
        }

        public Criteria andNewcollectionidNotIn(List<Integer> values) {
            addCriterion("newcollectionid not in", values, "newcollectionid");
            return (Criteria) this;
        }

        public Criteria andNewcollectionidBetween(Integer value1, Integer value2) {
            addCriterion("newcollectionid between", value1, value2, "newcollectionid");
            return (Criteria) this;
        }

        public Criteria andNewcollectionidNotBetween(Integer value1, Integer value2) {
            addCriterion("newcollectionid not between", value1, value2, "newcollectionid");
            return (Criteria) this;
        }

        public Criteria andIsnumberIsNull() {
            addCriterion("isnumber is null");
            return (Criteria) this;
        }

        public Criteria andIsnumberIsNotNull() {
            addCriterion("isnumber is not null");
            return (Criteria) this;
        }

        public Criteria andIsnumberEqualTo(Integer value) {
            addCriterion("isnumber =", value, "isnumber");
            return (Criteria) this;
        }

        public Criteria andIsnumberNotEqualTo(Integer value) {
            addCriterion("isnumber <>", value, "isnumber");
            return (Criteria) this;
        }

        public Criteria andIsnumberGreaterThan(Integer value) {
            addCriterion("isnumber >", value, "isnumber");
            return (Criteria) this;
        }

        public Criteria andIsnumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("isnumber >=", value, "isnumber");
            return (Criteria) this;
        }

        public Criteria andIsnumberLessThan(Integer value) {
            addCriterion("isnumber <", value, "isnumber");
            return (Criteria) this;
        }

        public Criteria andIsnumberLessThanOrEqualTo(Integer value) {
            addCriterion("isnumber <=", value, "isnumber");
            return (Criteria) this;
        }

        public Criteria andIsnumberIn(List<Integer> values) {
            addCriterion("isnumber in", values, "isnumber");
            return (Criteria) this;
        }

        public Criteria andIsnumberNotIn(List<Integer> values) {
            addCriterion("isnumber not in", values, "isnumber");
            return (Criteria) this;
        }

        public Criteria andIsnumberBetween(Integer value1, Integer value2) {
            addCriterion("isnumber between", value1, value2, "isnumber");
            return (Criteria) this;
        }

        public Criteria andIsnumberNotBetween(Integer value1, Integer value2) {
            addCriterion("isnumber not between", value1, value2, "isnumber");
            return (Criteria) this;
        }

        public Criteria andNumberIsNull() {
            addCriterion("number is null");
            return (Criteria) this;
        }

        public Criteria andNumberIsNotNull() {
            addCriterion("number is not null");
            return (Criteria) this;
        }

        public Criteria andNumberEqualTo(Integer value) {
            addCriterion("number =", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotEqualTo(Integer value) {
            addCriterion("number <>", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThan(Integer value) {
            addCriterion("number >", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("number >=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThan(Integer value) {
            addCriterion("number <", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThanOrEqualTo(Integer value) {
            addCriterion("number <=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberIn(List<Integer> values) {
            addCriterion("number in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotIn(List<Integer> values) {
            addCriterion("number not in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberBetween(Integer value1, Integer value2) {
            addCriterion("number between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("number not between", value1, value2, "number");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * pullnew 
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