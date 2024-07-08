package com.shitouren.core.autogenerate.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class YuanyuzhouExample {
    /**
     * yuanyuzhou
     */
    protected String orderByClause;

    /**
     * yuanyuzhou
     */
    protected boolean distinct;

    /**
     * yuanyuzhou
     */
    protected List<Criteria> oredCriteria;

    /**
     * yuanyuzhou
     */
    protected Integer pageNo = 1;

    /**
     * yuanyuzhou
     */
    protected Integer startRow;

    /**
     * yuanyuzhou
     */
    protected Integer pageSize = 10;

    public YuanyuzhouExample() {
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
     * yuanyuzhou
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

        public Criteria andPidIsNull() {
            addCriterion("pid is null");
            return (Criteria) this;
        }

        public Criteria andPidIsNotNull() {
            addCriterion("pid is not null");
            return (Criteria) this;
        }

        public Criteria andPidEqualTo(Integer value) {
            addCriterion("pid =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(Integer value) {
            addCriterion("pid <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(Integer value) {
            addCriterion("pid >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(Integer value) {
            addCriterion("pid >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(Integer value) {
            addCriterion("pid <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(Integer value) {
            addCriterion("pid <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<Integer> values) {
            addCriterion("pid in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<Integer> values) {
            addCriterion("pid not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(Integer value1, Integer value2) {
            addCriterion("pid between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(Integer value1, Integer value2) {
            addCriterion("pid not between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andDaxiaoIsNull() {
            addCriterion("daxiao is null");
            return (Criteria) this;
        }

        public Criteria andDaxiaoIsNotNull() {
            addCriterion("daxiao is not null");
            return (Criteria) this;
        }

        public Criteria andDaxiaoEqualTo(String value) {
            addCriterion("daxiao =", value, "daxiao");
            return (Criteria) this;
        }

        public Criteria andDaxiaoNotEqualTo(String value) {
            addCriterion("daxiao <>", value, "daxiao");
            return (Criteria) this;
        }

        public Criteria andDaxiaoGreaterThan(String value) {
            addCriterion("daxiao >", value, "daxiao");
            return (Criteria) this;
        }

        public Criteria andDaxiaoGreaterThanOrEqualTo(String value) {
            addCriterion("daxiao >=", value, "daxiao");
            return (Criteria) this;
        }

        public Criteria andDaxiaoLessThan(String value) {
            addCriterion("daxiao <", value, "daxiao");
            return (Criteria) this;
        }

        public Criteria andDaxiaoLessThanOrEqualTo(String value) {
            addCriterion("daxiao <=", value, "daxiao");
            return (Criteria) this;
        }

        public Criteria andDaxiaoLike(String value) {
            addCriterion("daxiao like", value, "daxiao");
            return (Criteria) this;
        }

        public Criteria andDaxiaoNotLike(String value) {
            addCriterion("daxiao not like", value, "daxiao");
            return (Criteria) this;
        }

        public Criteria andDaxiaoIn(List<String> values) {
            addCriterion("daxiao in", values, "daxiao");
            return (Criteria) this;
        }

        public Criteria andDaxiaoNotIn(List<String> values) {
            addCriterion("daxiao not in", values, "daxiao");
            return (Criteria) this;
        }

        public Criteria andDaxiaoBetween(String value1, String value2) {
            addCriterion("daxiao between", value1, value2, "daxiao");
            return (Criteria) this;
        }

        public Criteria andDaxiaoNotBetween(String value1, String value2) {
            addCriterion("daxiao not between", value1, value2, "daxiao");
            return (Criteria) this;
        }

        public Criteria andXIsNull() {
            addCriterion("x is null");
            return (Criteria) this;
        }

        public Criteria andXIsNotNull() {
            addCriterion("x is not null");
            return (Criteria) this;
        }

        public Criteria andXEqualTo(Integer value) {
            addCriterion("x =", value, "x");
            return (Criteria) this;
        }

        public Criteria andXNotEqualTo(Integer value) {
            addCriterion("x <>", value, "x");
            return (Criteria) this;
        }

        public Criteria andXGreaterThan(Integer value) {
            addCriterion("x >", value, "x");
            return (Criteria) this;
        }

        public Criteria andXGreaterThanOrEqualTo(Integer value) {
            addCriterion("x >=", value, "x");
            return (Criteria) this;
        }

        public Criteria andXLessThan(Integer value) {
            addCriterion("x <", value, "x");
            return (Criteria) this;
        }

        public Criteria andXLessThanOrEqualTo(Integer value) {
            addCriterion("x <=", value, "x");
            return (Criteria) this;
        }

        public Criteria andXIn(List<Integer> values) {
            addCriterion("x in", values, "x");
            return (Criteria) this;
        }

        public Criteria andXNotIn(List<Integer> values) {
            addCriterion("x not in", values, "x");
            return (Criteria) this;
        }

        public Criteria andXBetween(Integer value1, Integer value2) {
            addCriterion("x between", value1, value2, "x");
            return (Criteria) this;
        }

        public Criteria andXNotBetween(Integer value1, Integer value2) {
            addCriterion("x not between", value1, value2, "x");
            return (Criteria) this;
        }

        public Criteria andYIsNull() {
            addCriterion("y is null");
            return (Criteria) this;
        }

        public Criteria andYIsNotNull() {
            addCriterion("y is not null");
            return (Criteria) this;
        }

        public Criteria andYEqualTo(Integer value) {
            addCriterion("y =", value, "y");
            return (Criteria) this;
        }

        public Criteria andYNotEqualTo(Integer value) {
            addCriterion("y <>", value, "y");
            return (Criteria) this;
        }

        public Criteria andYGreaterThan(Integer value) {
            addCriterion("y >", value, "y");
            return (Criteria) this;
        }

        public Criteria andYGreaterThanOrEqualTo(Integer value) {
            addCriterion("y >=", value, "y");
            return (Criteria) this;
        }

        public Criteria andYLessThan(Integer value) {
            addCriterion("y <", value, "y");
            return (Criteria) this;
        }

        public Criteria andYLessThanOrEqualTo(Integer value) {
            addCriterion("y <=", value, "y");
            return (Criteria) this;
        }

        public Criteria andYIn(List<Integer> values) {
            addCriterion("y in", values, "y");
            return (Criteria) this;
        }

        public Criteria andYNotIn(List<Integer> values) {
            addCriterion("y not in", values, "y");
            return (Criteria) this;
        }

        public Criteria andYBetween(Integer value1, Integer value2) {
            addCriterion("y between", value1, value2, "y");
            return (Criteria) this;
        }

        public Criteria andYNotBetween(Integer value1, Integer value2) {
            addCriterion("y not between", value1, value2, "y");
            return (Criteria) this;
        }

        public Criteria andNickNameIsNull() {
            addCriterion("nick_name is null");
            return (Criteria) this;
        }

        public Criteria andNickNameIsNotNull() {
            addCriterion("nick_name is not null");
            return (Criteria) this;
        }

        public Criteria andNickNameEqualTo(String value) {
            addCriterion("nick_name =", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotEqualTo(String value) {
            addCriterion("nick_name <>", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameGreaterThan(String value) {
            addCriterion("nick_name >", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameGreaterThanOrEqualTo(String value) {
            addCriterion("nick_name >=", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameLessThan(String value) {
            addCriterion("nick_name <", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameLessThanOrEqualTo(String value) {
            addCriterion("nick_name <=", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameLike(String value) {
            addCriterion("nick_name like", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotLike(String value) {
            addCriterion("nick_name not like", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameIn(List<String> values) {
            addCriterion("nick_name in", values, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotIn(List<String> values) {
            addCriterion("nick_name not in", values, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameBetween(String value1, String value2) {
            addCriterion("nick_name between", value1, value2, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotBetween(String value1, String value2) {
            addCriterion("nick_name not between", value1, value2, "nickName");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("time is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("time is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(Date value) {
            addCriterion("time =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(Date value) {
            addCriterion("time <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(Date value) {
            addCriterion("time >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("time >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(Date value) {
            addCriterion("time <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(Date value) {
            addCriterion("time <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<Date> values) {
            addCriterion("time in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<Date> values) {
            addCriterion("time not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(Date value1, Date value2) {
            addCriterion("time between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(Date value1, Date value2) {
            addCriterion("time not between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("url not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrl1IsNull() {
            addCriterion("url1 is null");
            return (Criteria) this;
        }

        public Criteria andUrl1IsNotNull() {
            addCriterion("url1 is not null");
            return (Criteria) this;
        }

        public Criteria andUrl1EqualTo(String value) {
            addCriterion("url1 =", value, "url1");
            return (Criteria) this;
        }

        public Criteria andUrl1NotEqualTo(String value) {
            addCriterion("url1 <>", value, "url1");
            return (Criteria) this;
        }

        public Criteria andUrl1GreaterThan(String value) {
            addCriterion("url1 >", value, "url1");
            return (Criteria) this;
        }

        public Criteria andUrl1GreaterThanOrEqualTo(String value) {
            addCriterion("url1 >=", value, "url1");
            return (Criteria) this;
        }

        public Criteria andUrl1LessThan(String value) {
            addCriterion("url1 <", value, "url1");
            return (Criteria) this;
        }

        public Criteria andUrl1LessThanOrEqualTo(String value) {
            addCriterion("url1 <=", value, "url1");
            return (Criteria) this;
        }

        public Criteria andUrl1Like(String value) {
            addCriterion("url1 like", value, "url1");
            return (Criteria) this;
        }

        public Criteria andUrl1NotLike(String value) {
            addCriterion("url1 not like", value, "url1");
            return (Criteria) this;
        }

        public Criteria andUrl1In(List<String> values) {
            addCriterion("url1 in", values, "url1");
            return (Criteria) this;
        }

        public Criteria andUrl1NotIn(List<String> values) {
            addCriterion("url1 not in", values, "url1");
            return (Criteria) this;
        }

        public Criteria andUrl1Between(String value1, String value2) {
            addCriterion("url1 between", value1, value2, "url1");
            return (Criteria) this;
        }

        public Criteria andUrl1NotBetween(String value1, String value2) {
            addCriterion("url1 not between", value1, value2, "url1");
            return (Criteria) this;
        }

        public Criteria andUsergrantidIsNull() {
            addCriterion("usergrantid is null");
            return (Criteria) this;
        }

        public Criteria andUsergrantidIsNotNull() {
            addCriterion("usergrantid is not null");
            return (Criteria) this;
        }

        public Criteria andUsergrantidEqualTo(Integer value) {
            addCriterion("usergrantid =", value, "usergrantid");
            return (Criteria) this;
        }

        public Criteria andUsergrantidNotEqualTo(Integer value) {
            addCriterion("usergrantid <>", value, "usergrantid");
            return (Criteria) this;
        }

        public Criteria andUsergrantidGreaterThan(Integer value) {
            addCriterion("usergrantid >", value, "usergrantid");
            return (Criteria) this;
        }

        public Criteria andUsergrantidGreaterThanOrEqualTo(Integer value) {
            addCriterion("usergrantid >=", value, "usergrantid");
            return (Criteria) this;
        }

        public Criteria andUsergrantidLessThan(Integer value) {
            addCriterion("usergrantid <", value, "usergrantid");
            return (Criteria) this;
        }

        public Criteria andUsergrantidLessThanOrEqualTo(Integer value) {
            addCriterion("usergrantid <=", value, "usergrantid");
            return (Criteria) this;
        }

        public Criteria andUsergrantidIn(List<Integer> values) {
            addCriterion("usergrantid in", values, "usergrantid");
            return (Criteria) this;
        }

        public Criteria andUsergrantidNotIn(List<Integer> values) {
            addCriterion("usergrantid not in", values, "usergrantid");
            return (Criteria) this;
        }

        public Criteria andUsergrantidBetween(Integer value1, Integer value2) {
            addCriterion("usergrantid between", value1, value2, "usergrantid");
            return (Criteria) this;
        }

        public Criteria andUsergrantidNotBetween(Integer value1, Integer value2) {
            addCriterion("usergrantid not between", value1, value2, "usergrantid");
            return (Criteria) this;
        }

        public Criteria andWangzhiIsNull() {
            addCriterion("wangzhi is null");
            return (Criteria) this;
        }

        public Criteria andWangzhiIsNotNull() {
            addCriterion("wangzhi is not null");
            return (Criteria) this;
        }

        public Criteria andWangzhiEqualTo(String value) {
            addCriterion("wangzhi =", value, "wangzhi");
            return (Criteria) this;
        }

        public Criteria andWangzhiNotEqualTo(String value) {
            addCriterion("wangzhi <>", value, "wangzhi");
            return (Criteria) this;
        }

        public Criteria andWangzhiGreaterThan(String value) {
            addCriterion("wangzhi >", value, "wangzhi");
            return (Criteria) this;
        }

        public Criteria andWangzhiGreaterThanOrEqualTo(String value) {
            addCriterion("wangzhi >=", value, "wangzhi");
            return (Criteria) this;
        }

        public Criteria andWangzhiLessThan(String value) {
            addCriterion("wangzhi <", value, "wangzhi");
            return (Criteria) this;
        }

        public Criteria andWangzhiLessThanOrEqualTo(String value) {
            addCriterion("wangzhi <=", value, "wangzhi");
            return (Criteria) this;
        }

        public Criteria andWangzhiLike(String value) {
            addCriterion("wangzhi like", value, "wangzhi");
            return (Criteria) this;
        }

        public Criteria andWangzhiNotLike(String value) {
            addCriterion("wangzhi not like", value, "wangzhi");
            return (Criteria) this;
        }

        public Criteria andWangzhiIn(List<String> values) {
            addCriterion("wangzhi in", values, "wangzhi");
            return (Criteria) this;
        }

        public Criteria andWangzhiNotIn(List<String> values) {
            addCriterion("wangzhi not in", values, "wangzhi");
            return (Criteria) this;
        }

        public Criteria andWangzhiBetween(String value1, String value2) {
            addCriterion("wangzhi between", value1, value2, "wangzhi");
            return (Criteria) this;
        }

        public Criteria andWangzhiNotBetween(String value1, String value2) {
            addCriterion("wangzhi not between", value1, value2, "wangzhi");
            return (Criteria) this;
        }

        public Criteria andIsGoumaiIsNull() {
            addCriterion("is_goumai is null");
            return (Criteria) this;
        }

        public Criteria andIsGoumaiIsNotNull() {
            addCriterion("is_goumai is not null");
            return (Criteria) this;
        }

        public Criteria andIsGoumaiEqualTo(Integer value) {
            addCriterion("is_goumai =", value, "isGoumai");
            return (Criteria) this;
        }

        public Criteria andIsGoumaiNotEqualTo(Integer value) {
            addCriterion("is_goumai <>", value, "isGoumai");
            return (Criteria) this;
        }

        public Criteria andIsGoumaiGreaterThan(Integer value) {
            addCriterion("is_goumai >", value, "isGoumai");
            return (Criteria) this;
        }

        public Criteria andIsGoumaiGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_goumai >=", value, "isGoumai");
            return (Criteria) this;
        }

        public Criteria andIsGoumaiLessThan(Integer value) {
            addCriterion("is_goumai <", value, "isGoumai");
            return (Criteria) this;
        }

        public Criteria andIsGoumaiLessThanOrEqualTo(Integer value) {
            addCriterion("is_goumai <=", value, "isGoumai");
            return (Criteria) this;
        }

        public Criteria andIsGoumaiIn(List<Integer> values) {
            addCriterion("is_goumai in", values, "isGoumai");
            return (Criteria) this;
        }

        public Criteria andIsGoumaiNotIn(List<Integer> values) {
            addCriterion("is_goumai not in", values, "isGoumai");
            return (Criteria) this;
        }

        public Criteria andIsGoumaiBetween(Integer value1, Integer value2) {
            addCriterion("is_goumai between", value1, value2, "isGoumai");
            return (Criteria) this;
        }

        public Criteria andIsGoumaiNotBetween(Integer value1, Integer value2) {
            addCriterion("is_goumai not between", value1, value2, "isGoumai");
            return (Criteria) this;
        }

        public Criteria andJianjieIsNull() {
            addCriterion("jianjie is null");
            return (Criteria) this;
        }

        public Criteria andJianjieIsNotNull() {
            addCriterion("jianjie is not null");
            return (Criteria) this;
        }

        public Criteria andJianjieEqualTo(String value) {
            addCriterion("jianjie =", value, "jianjie");
            return (Criteria) this;
        }

        public Criteria andJianjieNotEqualTo(String value) {
            addCriterion("jianjie <>", value, "jianjie");
            return (Criteria) this;
        }

        public Criteria andJianjieGreaterThan(String value) {
            addCriterion("jianjie >", value, "jianjie");
            return (Criteria) this;
        }

        public Criteria andJianjieGreaterThanOrEqualTo(String value) {
            addCriterion("jianjie >=", value, "jianjie");
            return (Criteria) this;
        }

        public Criteria andJianjieLessThan(String value) {
            addCriterion("jianjie <", value, "jianjie");
            return (Criteria) this;
        }

        public Criteria andJianjieLessThanOrEqualTo(String value) {
            addCriterion("jianjie <=", value, "jianjie");
            return (Criteria) this;
        }

        public Criteria andJianjieLike(String value) {
            addCriterion("jianjie like", value, "jianjie");
            return (Criteria) this;
        }

        public Criteria andJianjieNotLike(String value) {
            addCriterion("jianjie not like", value, "jianjie");
            return (Criteria) this;
        }

        public Criteria andJianjieIn(List<String> values) {
            addCriterion("jianjie in", values, "jianjie");
            return (Criteria) this;
        }

        public Criteria andJianjieNotIn(List<String> values) {
            addCriterion("jianjie not in", values, "jianjie");
            return (Criteria) this;
        }

        public Criteria andJianjieBetween(String value1, String value2) {
            addCriterion("jianjie between", value1, value2, "jianjie");
            return (Criteria) this;
        }

        public Criteria andJianjieNotBetween(String value1, String value2) {
            addCriterion("jianjie not between", value1, value2, "jianjie");
            return (Criteria) this;
        }

        public Criteria andForworkIsNull() {
            addCriterion("forwork is null");
            return (Criteria) this;
        }

        public Criteria andForworkIsNotNull() {
            addCriterion("forwork is not null");
            return (Criteria) this;
        }

        public Criteria andForworkEqualTo(Integer value) {
            addCriterion("forwork =", value, "forwork");
            return (Criteria) this;
        }

        public Criteria andForworkNotEqualTo(Integer value) {
            addCriterion("forwork <>", value, "forwork");
            return (Criteria) this;
        }

        public Criteria andForworkGreaterThan(Integer value) {
            addCriterion("forwork >", value, "forwork");
            return (Criteria) this;
        }

        public Criteria andForworkGreaterThanOrEqualTo(Integer value) {
            addCriterion("forwork >=", value, "forwork");
            return (Criteria) this;
        }

        public Criteria andForworkLessThan(Integer value) {
            addCriterion("forwork <", value, "forwork");
            return (Criteria) this;
        }

        public Criteria andForworkLessThanOrEqualTo(Integer value) {
            addCriterion("forwork <=", value, "forwork");
            return (Criteria) this;
        }

        public Criteria andForworkIn(List<Integer> values) {
            addCriterion("forwork in", values, "forwork");
            return (Criteria) this;
        }

        public Criteria andForworkNotIn(List<Integer> values) {
            addCriterion("forwork not in", values, "forwork");
            return (Criteria) this;
        }

        public Criteria andForworkBetween(Integer value1, Integer value2) {
            addCriterion("forwork between", value1, value2, "forwork");
            return (Criteria) this;
        }

        public Criteria andForworkNotBetween(Integer value1, Integer value2) {
            addCriterion("forwork not between", value1, value2, "forwork");
            return (Criteria) this;
        }

        public Criteria andPublicFlagIsNull() {
            addCriterion("public_flag is null");
            return (Criteria) this;
        }

        public Criteria andPublicFlagIsNotNull() {
            addCriterion("public_flag is not null");
            return (Criteria) this;
        }

        public Criteria andPublicFlagEqualTo(Integer value) {
            addCriterion("public_flag =", value, "publicFlag");
            return (Criteria) this;
        }

        public Criteria andPublicFlagNotEqualTo(Integer value) {
            addCriterion("public_flag <>", value, "publicFlag");
            return (Criteria) this;
        }

        public Criteria andPublicFlagGreaterThan(Integer value) {
            addCriterion("public_flag >", value, "publicFlag");
            return (Criteria) this;
        }

        public Criteria andPublicFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("public_flag >=", value, "publicFlag");
            return (Criteria) this;
        }

        public Criteria andPublicFlagLessThan(Integer value) {
            addCriterion("public_flag <", value, "publicFlag");
            return (Criteria) this;
        }

        public Criteria andPublicFlagLessThanOrEqualTo(Integer value) {
            addCriterion("public_flag <=", value, "publicFlag");
            return (Criteria) this;
        }

        public Criteria andPublicFlagIn(List<Integer> values) {
            addCriterion("public_flag in", values, "publicFlag");
            return (Criteria) this;
        }

        public Criteria andPublicFlagNotIn(List<Integer> values) {
            addCriterion("public_flag not in", values, "publicFlag");
            return (Criteria) this;
        }

        public Criteria andPublicFlagBetween(Integer value1, Integer value2) {
            addCriterion("public_flag between", value1, value2, "publicFlag");
            return (Criteria) this;
        }

        public Criteria andPublicFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("public_flag not between", value1, value2, "publicFlag");
            return (Criteria) this;
        }

        public Criteria andOvertIsNull() {
            addCriterion("overt is null");
            return (Criteria) this;
        }

        public Criteria andOvertIsNotNull() {
            addCriterion("overt is not null");
            return (Criteria) this;
        }

        public Criteria andOvertEqualTo(Integer value) {
            addCriterion("overt =", value, "overt");
            return (Criteria) this;
        }

        public Criteria andOvertNotEqualTo(Integer value) {
            addCriterion("overt <>", value, "overt");
            return (Criteria) this;
        }

        public Criteria andOvertGreaterThan(Integer value) {
            addCriterion("overt >", value, "overt");
            return (Criteria) this;
        }

        public Criteria andOvertGreaterThanOrEqualTo(Integer value) {
            addCriterion("overt >=", value, "overt");
            return (Criteria) this;
        }

        public Criteria andOvertLessThan(Integer value) {
            addCriterion("overt <", value, "overt");
            return (Criteria) this;
        }

        public Criteria andOvertLessThanOrEqualTo(Integer value) {
            addCriterion("overt <=", value, "overt");
            return (Criteria) this;
        }

        public Criteria andOvertIn(List<Integer> values) {
            addCriterion("overt in", values, "overt");
            return (Criteria) this;
        }

        public Criteria andOvertNotIn(List<Integer> values) {
            addCriterion("overt not in", values, "overt");
            return (Criteria) this;
        }

        public Criteria andOvertBetween(Integer value1, Integer value2) {
            addCriterion("overt between", value1, value2, "overt");
            return (Criteria) this;
        }

        public Criteria andOvertNotBetween(Integer value1, Integer value2) {
            addCriterion("overt not between", value1, value2, "overt");
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

        public Criteria andBldgGrantidIsNull() {
            addCriterion("bldg_grantid is null");
            return (Criteria) this;
        }

        public Criteria andBldgGrantidIsNotNull() {
            addCriterion("bldg_grantid is not null");
            return (Criteria) this;
        }

        public Criteria andBldgGrantidEqualTo(Integer value) {
            addCriterion("bldg_grantid =", value, "bldgGrantid");
            return (Criteria) this;
        }

        public Criteria andBldgGrantidNotEqualTo(Integer value) {
            addCriterion("bldg_grantid <>", value, "bldgGrantid");
            return (Criteria) this;
        }

        public Criteria andBldgGrantidGreaterThan(Integer value) {
            addCriterion("bldg_grantid >", value, "bldgGrantid");
            return (Criteria) this;
        }

        public Criteria andBldgGrantidGreaterThanOrEqualTo(Integer value) {
            addCriterion("bldg_grantid >=", value, "bldgGrantid");
            return (Criteria) this;
        }

        public Criteria andBldgGrantidLessThan(Integer value) {
            addCriterion("bldg_grantid <", value, "bldgGrantid");
            return (Criteria) this;
        }

        public Criteria andBldgGrantidLessThanOrEqualTo(Integer value) {
            addCriterion("bldg_grantid <=", value, "bldgGrantid");
            return (Criteria) this;
        }

        public Criteria andBldgGrantidIn(List<Integer> values) {
            addCriterion("bldg_grantid in", values, "bldgGrantid");
            return (Criteria) this;
        }

        public Criteria andBldgGrantidNotIn(List<Integer> values) {
            addCriterion("bldg_grantid not in", values, "bldgGrantid");
            return (Criteria) this;
        }

        public Criteria andBldgGrantidBetween(Integer value1, Integer value2) {
            addCriterion("bldg_grantid between", value1, value2, "bldgGrantid");
            return (Criteria) this;
        }

        public Criteria andBldgGrantidNotBetween(Integer value1, Integer value2) {
            addCriterion("bldg_grantid not between", value1, value2, "bldgGrantid");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andPublicNameIsNull() {
            addCriterion("public_name is null");
            return (Criteria) this;
        }

        public Criteria andPublicNameIsNotNull() {
            addCriterion("public_name is not null");
            return (Criteria) this;
        }

        public Criteria andPublicNameEqualTo(String value) {
            addCriterion("public_name =", value, "publicName");
            return (Criteria) this;
        }

        public Criteria andPublicNameNotEqualTo(String value) {
            addCriterion("public_name <>", value, "publicName");
            return (Criteria) this;
        }

        public Criteria andPublicNameGreaterThan(String value) {
            addCriterion("public_name >", value, "publicName");
            return (Criteria) this;
        }

        public Criteria andPublicNameGreaterThanOrEqualTo(String value) {
            addCriterion("public_name >=", value, "publicName");
            return (Criteria) this;
        }

        public Criteria andPublicNameLessThan(String value) {
            addCriterion("public_name <", value, "publicName");
            return (Criteria) this;
        }

        public Criteria andPublicNameLessThanOrEqualTo(String value) {
            addCriterion("public_name <=", value, "publicName");
            return (Criteria) this;
        }

        public Criteria andPublicNameLike(String value) {
            addCriterion("public_name like", value, "publicName");
            return (Criteria) this;
        }

        public Criteria andPublicNameNotLike(String value) {
            addCriterion("public_name not like", value, "publicName");
            return (Criteria) this;
        }

        public Criteria andPublicNameIn(List<String> values) {
            addCriterion("public_name in", values, "publicName");
            return (Criteria) this;
        }

        public Criteria andPublicNameNotIn(List<String> values) {
            addCriterion("public_name not in", values, "publicName");
            return (Criteria) this;
        }

        public Criteria andPublicNameBetween(String value1, String value2) {
            addCriterion("public_name between", value1, value2, "publicName");
            return (Criteria) this;
        }

        public Criteria andPublicNameNotBetween(String value1, String value2) {
            addCriterion("public_name not between", value1, value2, "publicName");
            return (Criteria) this;
        }

        public Criteria andOpenIsNull() {
            addCriterion("open is null");
            return (Criteria) this;
        }

        public Criteria andOpenIsNotNull() {
            addCriterion("open is not null");
            return (Criteria) this;
        }

        public Criteria andOpenEqualTo(Byte value) {
            addCriterion("open =", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenNotEqualTo(Byte value) {
            addCriterion("open <>", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenGreaterThan(Byte value) {
            addCriterion("open >", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenGreaterThanOrEqualTo(Byte value) {
            addCriterion("open >=", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenLessThan(Byte value) {
            addCriterion("open <", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenLessThanOrEqualTo(Byte value) {
            addCriterion("open <=", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenIn(List<Byte> values) {
            addCriterion("open in", values, "open");
            return (Criteria) this;
        }

        public Criteria andOpenNotIn(List<Byte> values) {
            addCriterion("open not in", values, "open");
            return (Criteria) this;
        }

        public Criteria andOpenBetween(Byte value1, Byte value2) {
            addCriterion("open between", value1, value2, "open");
            return (Criteria) this;
        }

        public Criteria andOpenNotBetween(Byte value1, Byte value2) {
            addCriterion("open not between", value1, value2, "open");
            return (Criteria) this;
        }

        public Criteria andEnterIsNull() {
            addCriterion("enter is null");
            return (Criteria) this;
        }

        public Criteria andEnterIsNotNull() {
            addCriterion("enter is not null");
            return (Criteria) this;
        }

        public Criteria andEnterEqualTo(Byte value) {
            addCriterion("enter =", value, "enter");
            return (Criteria) this;
        }

        public Criteria andEnterNotEqualTo(Byte value) {
            addCriterion("enter <>", value, "enter");
            return (Criteria) this;
        }

        public Criteria andEnterGreaterThan(Byte value) {
            addCriterion("enter >", value, "enter");
            return (Criteria) this;
        }

        public Criteria andEnterGreaterThanOrEqualTo(Byte value) {
            addCriterion("enter >=", value, "enter");
            return (Criteria) this;
        }

        public Criteria andEnterLessThan(Byte value) {
            addCriterion("enter <", value, "enter");
            return (Criteria) this;
        }

        public Criteria andEnterLessThanOrEqualTo(Byte value) {
            addCriterion("enter <=", value, "enter");
            return (Criteria) this;
        }

        public Criteria andEnterIn(List<Byte> values) {
            addCriterion("enter in", values, "enter");
            return (Criteria) this;
        }

        public Criteria andEnterNotIn(List<Byte> values) {
            addCriterion("enter not in", values, "enter");
            return (Criteria) this;
        }

        public Criteria andEnterBetween(Byte value1, Byte value2) {
            addCriterion("enter between", value1, value2, "enter");
            return (Criteria) this;
        }

        public Criteria andEnterNotBetween(Byte value1, Byte value2) {
            addCriterion("enter not between", value1, value2, "enter");
            return (Criteria) this;
        }

        public Criteria andWebUrlIsNull() {
            addCriterion("web_url is null");
            return (Criteria) this;
        }

        public Criteria andWebUrlIsNotNull() {
            addCriterion("web_url is not null");
            return (Criteria) this;
        }

        public Criteria andWebUrlEqualTo(String value) {
            addCriterion("web_url =", value, "webUrl");
            return (Criteria) this;
        }

        public Criteria andWebUrlNotEqualTo(String value) {
            addCriterion("web_url <>", value, "webUrl");
            return (Criteria) this;
        }

        public Criteria andWebUrlGreaterThan(String value) {
            addCriterion("web_url >", value, "webUrl");
            return (Criteria) this;
        }

        public Criteria andWebUrlGreaterThanOrEqualTo(String value) {
            addCriterion("web_url >=", value, "webUrl");
            return (Criteria) this;
        }

        public Criteria andWebUrlLessThan(String value) {
            addCriterion("web_url <", value, "webUrl");
            return (Criteria) this;
        }

        public Criteria andWebUrlLessThanOrEqualTo(String value) {
            addCriterion("web_url <=", value, "webUrl");
            return (Criteria) this;
        }

        public Criteria andWebUrlLike(String value) {
            addCriterion("web_url like", value, "webUrl");
            return (Criteria) this;
        }

        public Criteria andWebUrlNotLike(String value) {
            addCriterion("web_url not like", value, "webUrl");
            return (Criteria) this;
        }

        public Criteria andWebUrlIn(List<String> values) {
            addCriterion("web_url in", values, "webUrl");
            return (Criteria) this;
        }

        public Criteria andWebUrlNotIn(List<String> values) {
            addCriterion("web_url not in", values, "webUrl");
            return (Criteria) this;
        }

        public Criteria andWebUrlBetween(String value1, String value2) {
            addCriterion("web_url between", value1, value2, "webUrl");
            return (Criteria) this;
        }

        public Criteria andWebUrlNotBetween(String value1, String value2) {
            addCriterion("web_url not between", value1, value2, "webUrl");
            return (Criteria) this;
        }

        public Criteria andBuyPriceIsNull() {
            addCriterion("buy_price is null");
            return (Criteria) this;
        }

        public Criteria andBuyPriceIsNotNull() {
            addCriterion("buy_price is not null");
            return (Criteria) this;
        }

        public Criteria andBuyPriceEqualTo(String value) {
            addCriterion("buy_price =", value, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceNotEqualTo(String value) {
            addCriterion("buy_price <>", value, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceGreaterThan(String value) {
            addCriterion("buy_price >", value, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceGreaterThanOrEqualTo(String value) {
            addCriterion("buy_price >=", value, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceLessThan(String value) {
            addCriterion("buy_price <", value, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceLessThanOrEqualTo(String value) {
            addCriterion("buy_price <=", value, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceLike(String value) {
            addCriterion("buy_price like", value, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceNotLike(String value) {
            addCriterion("buy_price not like", value, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceIn(List<String> values) {
            addCriterion("buy_price in", values, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceNotIn(List<String> values) {
            addCriterion("buy_price not in", values, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceBetween(String value1, String value2) {
            addCriterion("buy_price between", value1, value2, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andBuyPriceNotBetween(String value1, String value2) {
            addCriterion("buy_price not between", value1, value2, "buyPrice");
            return (Criteria) this;
        }

        public Criteria andCanBuyIsNull() {
            addCriterion("can_buy is null");
            return (Criteria) this;
        }

        public Criteria andCanBuyIsNotNull() {
            addCriterion("can_buy is not null");
            return (Criteria) this;
        }

        public Criteria andCanBuyEqualTo(Byte value) {
            addCriterion("can_buy =", value, "canBuy");
            return (Criteria) this;
        }

        public Criteria andCanBuyNotEqualTo(Byte value) {
            addCriterion("can_buy <>", value, "canBuy");
            return (Criteria) this;
        }

        public Criteria andCanBuyGreaterThan(Byte value) {
            addCriterion("can_buy >", value, "canBuy");
            return (Criteria) this;
        }

        public Criteria andCanBuyGreaterThanOrEqualTo(Byte value) {
            addCriterion("can_buy >=", value, "canBuy");
            return (Criteria) this;
        }

        public Criteria andCanBuyLessThan(Byte value) {
            addCriterion("can_buy <", value, "canBuy");
            return (Criteria) this;
        }

        public Criteria andCanBuyLessThanOrEqualTo(Byte value) {
            addCriterion("can_buy <=", value, "canBuy");
            return (Criteria) this;
        }

        public Criteria andCanBuyIn(List<Byte> values) {
            addCriterion("can_buy in", values, "canBuy");
            return (Criteria) this;
        }

        public Criteria andCanBuyNotIn(List<Byte> values) {
            addCriterion("can_buy not in", values, "canBuy");
            return (Criteria) this;
        }

        public Criteria andCanBuyBetween(Byte value1, Byte value2) {
            addCriterion("can_buy between", value1, value2, "canBuy");
            return (Criteria) this;
        }

        public Criteria andCanBuyNotBetween(Byte value1, Byte value2) {
            addCriterion("can_buy not between", value1, value2, "canBuy");
            return (Criteria) this;
        }

        public Criteria andBuyTimeIsNull() {
            addCriterion("buy_time is null");
            return (Criteria) this;
        }

        public Criteria andBuyTimeIsNotNull() {
            addCriterion("buy_time is not null");
            return (Criteria) this;
        }

        public Criteria andBuyTimeEqualTo(Date value) {
            addCriterion("buy_time =", value, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeNotEqualTo(Date value) {
            addCriterion("buy_time <>", value, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeGreaterThan(Date value) {
            addCriterion("buy_time >", value, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("buy_time >=", value, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeLessThan(Date value) {
            addCriterion("buy_time <", value, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeLessThanOrEqualTo(Date value) {
            addCriterion("buy_time <=", value, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeIn(List<Date> values) {
            addCriterion("buy_time in", values, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeNotIn(List<Date> values) {
            addCriterion("buy_time not in", values, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeBetween(Date value1, Date value2) {
            addCriterion("buy_time between", value1, value2, "buyTime");
            return (Criteria) this;
        }

        public Criteria andBuyTimeNotBetween(Date value1, Date value2) {
            addCriterion("buy_time not between", value1, value2, "buyTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeIsNull() {
            addCriterion("expire_time is null");
            return (Criteria) this;
        }

        public Criteria andExpireTimeIsNotNull() {
            addCriterion("expire_time is not null");
            return (Criteria) this;
        }

        public Criteria andExpireTimeEqualTo(Date value) {
            addCriterion("expire_time =", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeNotEqualTo(Date value) {
            addCriterion("expire_time <>", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeGreaterThan(Date value) {
            addCriterion("expire_time >", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("expire_time >=", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeLessThan(Date value) {
            addCriterion("expire_time <", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeLessThanOrEqualTo(Date value) {
            addCriterion("expire_time <=", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeIn(List<Date> values) {
            addCriterion("expire_time in", values, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeNotIn(List<Date> values) {
            addCriterion("expire_time not in", values, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeBetween(Date value1, Date value2) {
            addCriterion("expire_time between", value1, value2, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeNotBetween(Date value1, Date value2) {
            addCriterion("expire_time not between", value1, value2, "expireTime");
            return (Criteria) this;
        }

        public Criteria andBuyPriceJifenIsNull() {
            addCriterion("buy_price_jifen is null");
            return (Criteria) this;
        }

        public Criteria andBuyPriceJifenIsNotNull() {
            addCriterion("buy_price_jifen is not null");
            return (Criteria) this;
        }

        public Criteria andBuyPriceJifenEqualTo(String value) {
            addCriterion("buy_price_jifen =", value, "buyPriceJifen");
            return (Criteria) this;
        }

        public Criteria andBuyPriceJifenNotEqualTo(String value) {
            addCriterion("buy_price_jifen <>", value, "buyPriceJifen");
            return (Criteria) this;
        }

        public Criteria andBuyPriceJifenGreaterThan(String value) {
            addCriterion("buy_price_jifen >", value, "buyPriceJifen");
            return (Criteria) this;
        }

        public Criteria andBuyPriceJifenGreaterThanOrEqualTo(String value) {
            addCriterion("buy_price_jifen >=", value, "buyPriceJifen");
            return (Criteria) this;
        }

        public Criteria andBuyPriceJifenLessThan(String value) {
            addCriterion("buy_price_jifen <", value, "buyPriceJifen");
            return (Criteria) this;
        }

        public Criteria andBuyPriceJifenLessThanOrEqualTo(String value) {
            addCriterion("buy_price_jifen <=", value, "buyPriceJifen");
            return (Criteria) this;
        }

        public Criteria andBuyPriceJifenLike(String value) {
            addCriterion("buy_price_jifen like", value, "buyPriceJifen");
            return (Criteria) this;
        }

        public Criteria andBuyPriceJifenNotLike(String value) {
            addCriterion("buy_price_jifen not like", value, "buyPriceJifen");
            return (Criteria) this;
        }

        public Criteria andBuyPriceJifenIn(List<String> values) {
            addCriterion("buy_price_jifen in", values, "buyPriceJifen");
            return (Criteria) this;
        }

        public Criteria andBuyPriceJifenNotIn(List<String> values) {
            addCriterion("buy_price_jifen not in", values, "buyPriceJifen");
            return (Criteria) this;
        }

        public Criteria andBuyPriceJifenBetween(String value1, String value2) {
            addCriterion("buy_price_jifen between", value1, value2, "buyPriceJifen");
            return (Criteria) this;
        }

        public Criteria andBuyPriceJifenNotBetween(String value1, String value2) {
            addCriterion("buy_price_jifen not between", value1, value2, "buyPriceJifen");
            return (Criteria) this;
        }

        public Criteria andLandNameIsNull() {
            addCriterion("land_name is null");
            return (Criteria) this;
        }

        public Criteria andLandNameIsNotNull() {
            addCriterion("land_name is not null");
            return (Criteria) this;
        }

        public Criteria andLandNameEqualTo(String value) {
            addCriterion("land_name =", value, "landName");
            return (Criteria) this;
        }

        public Criteria andLandNameNotEqualTo(String value) {
            addCriterion("land_name <>", value, "landName");
            return (Criteria) this;
        }

        public Criteria andLandNameGreaterThan(String value) {
            addCriterion("land_name >", value, "landName");
            return (Criteria) this;
        }

        public Criteria andLandNameGreaterThanOrEqualTo(String value) {
            addCriterion("land_name >=", value, "landName");
            return (Criteria) this;
        }

        public Criteria andLandNameLessThan(String value) {
            addCriterion("land_name <", value, "landName");
            return (Criteria) this;
        }

        public Criteria andLandNameLessThanOrEqualTo(String value) {
            addCriterion("land_name <=", value, "landName");
            return (Criteria) this;
        }

        public Criteria andLandNameLike(String value) {
            addCriterion("land_name like", value, "landName");
            return (Criteria) this;
        }

        public Criteria andLandNameNotLike(String value) {
            addCriterion("land_name not like", value, "landName");
            return (Criteria) this;
        }

        public Criteria andLandNameIn(List<String> values) {
            addCriterion("land_name in", values, "landName");
            return (Criteria) this;
        }

        public Criteria andLandNameNotIn(List<String> values) {
            addCriterion("land_name not in", values, "landName");
            return (Criteria) this;
        }

        public Criteria andLandNameBetween(String value1, String value2) {
            addCriterion("land_name between", value1, value2, "landName");
            return (Criteria) this;
        }

        public Criteria andLandNameNotBetween(String value1, String value2) {
            addCriterion("land_name not between", value1, value2, "landName");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * yuanyuzhou
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
