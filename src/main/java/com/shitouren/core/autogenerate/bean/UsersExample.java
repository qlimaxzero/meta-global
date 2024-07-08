package com.shitouren.core.autogenerate.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsersExample {
    /**
     * users
     */
    protected String orderByClause;

    /**
     * users
     */
    protected boolean distinct;

    /**
     * users
     */
    protected List<Criteria> oredCriteria;

    /**
     * users
     */
    protected Integer pageNo = 1;

    /**
     * users
     */
    protected Integer startRow;

    /**
     * users
     */
    protected Integer pageSize = 10;

    public UsersExample() {
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
     * users 
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

        public Criteria andPhoneNumberIsNull() {
            addCriterion("phone_number is null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberIsNotNull() {
            addCriterion("phone_number is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberEqualTo(String value) {
            addCriterion("phone_number =", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotEqualTo(String value) {
            addCriterion("phone_number <>", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberGreaterThan(String value) {
            addCriterion("phone_number >", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberGreaterThanOrEqualTo(String value) {
            addCriterion("phone_number >=", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberLessThan(String value) {
            addCriterion("phone_number <", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberLessThanOrEqualTo(String value) {
            addCriterion("phone_number <=", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberLike(String value) {
            addCriterion("phone_number like", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotLike(String value) {
            addCriterion("phone_number not like", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberIn(List<String> values) {
            addCriterion("phone_number in", values, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotIn(List<String> values) {
            addCriterion("phone_number not in", values, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberBetween(String value1, String value2) {
            addCriterion("phone_number between", value1, value2, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotBetween(String value1, String value2) {
            addCriterion("phone_number not between", value1, value2, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andMailboxIsNull() {
            addCriterion("mailbox is null");
            return (Criteria) this;
        }

        public Criteria andMailboxIsNotNull() {
            addCriterion("mailbox is not null");
            return (Criteria) this;
        }

        public Criteria andMailboxEqualTo(String value) {
            addCriterion("mailbox =", value, "mailbox");
            return (Criteria) this;
        }

        public Criteria andMailboxNotEqualTo(String value) {
            addCriterion("mailbox <>", value, "mailbox");
            return (Criteria) this;
        }

        public Criteria andMailboxGreaterThan(String value) {
            addCriterion("mailbox >", value, "mailbox");
            return (Criteria) this;
        }

        public Criteria andMailboxGreaterThanOrEqualTo(String value) {
            addCriterion("mailbox >=", value, "mailbox");
            return (Criteria) this;
        }

        public Criteria andMailboxLessThan(String value) {
            addCriterion("mailbox <", value, "mailbox");
            return (Criteria) this;
        }

        public Criteria andMailboxLessThanOrEqualTo(String value) {
            addCriterion("mailbox <=", value, "mailbox");
            return (Criteria) this;
        }

        public Criteria andMailboxLike(String value) {
            addCriterion("mailbox like", value, "mailbox");
            return (Criteria) this;
        }

        public Criteria andMailboxNotLike(String value) {
            addCriterion("mailbox not like", value, "mailbox");
            return (Criteria) this;
        }

        public Criteria andMailboxIn(List<String> values) {
            addCriterion("mailbox in", values, "mailbox");
            return (Criteria) this;
        }

        public Criteria andMailboxNotIn(List<String> values) {
            addCriterion("mailbox not in", values, "mailbox");
            return (Criteria) this;
        }

        public Criteria andMailboxBetween(String value1, String value2) {
            addCriterion("mailbox between", value1, value2, "mailbox");
            return (Criteria) this;
        }

        public Criteria andMailboxNotBetween(String value1, String value2) {
            addCriterion("mailbox not between", value1, value2, "mailbox");
            return (Criteria) this;
        }

        public Criteria andHeadPrtraitsIsNull() {
            addCriterion("head_prtraits is null");
            return (Criteria) this;
        }

        public Criteria andHeadPrtraitsIsNotNull() {
            addCriterion("head_prtraits is not null");
            return (Criteria) this;
        }

        public Criteria andHeadPrtraitsEqualTo(String value) {
            addCriterion("head_prtraits =", value, "headPrtraits");
            return (Criteria) this;
        }

        public Criteria andHeadPrtraitsNotEqualTo(String value) {
            addCriterion("head_prtraits <>", value, "headPrtraits");
            return (Criteria) this;
        }

        public Criteria andHeadPrtraitsGreaterThan(String value) {
            addCriterion("head_prtraits >", value, "headPrtraits");
            return (Criteria) this;
        }

        public Criteria andHeadPrtraitsGreaterThanOrEqualTo(String value) {
            addCriterion("head_prtraits >=", value, "headPrtraits");
            return (Criteria) this;
        }

        public Criteria andHeadPrtraitsLessThan(String value) {
            addCriterion("head_prtraits <", value, "headPrtraits");
            return (Criteria) this;
        }

        public Criteria andHeadPrtraitsLessThanOrEqualTo(String value) {
            addCriterion("head_prtraits <=", value, "headPrtraits");
            return (Criteria) this;
        }

        public Criteria andHeadPrtraitsLike(String value) {
            addCriterion("head_prtraits like", value, "headPrtraits");
            return (Criteria) this;
        }

        public Criteria andHeadPrtraitsNotLike(String value) {
            addCriterion("head_prtraits not like", value, "headPrtraits");
            return (Criteria) this;
        }

        public Criteria andHeadPrtraitsIn(List<String> values) {
            addCriterion("head_prtraits in", values, "headPrtraits");
            return (Criteria) this;
        }

        public Criteria andHeadPrtraitsNotIn(List<String> values) {
            addCriterion("head_prtraits not in", values, "headPrtraits");
            return (Criteria) this;
        }

        public Criteria andHeadPrtraitsBetween(String value1, String value2) {
            addCriterion("head_prtraits between", value1, value2, "headPrtraits");
            return (Criteria) this;
        }

        public Criteria andHeadPrtraitsNotBetween(String value1, String value2) {
            addCriterion("head_prtraits not between", value1, value2, "headPrtraits");
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

        public Criteria andPasswdIsNull() {
            addCriterion("passwd is null");
            return (Criteria) this;
        }

        public Criteria andPasswdIsNotNull() {
            addCriterion("passwd is not null");
            return (Criteria) this;
        }

        public Criteria andPasswdEqualTo(String value) {
            addCriterion("passwd =", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdNotEqualTo(String value) {
            addCriterion("passwd <>", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdGreaterThan(String value) {
            addCriterion("passwd >", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdGreaterThanOrEqualTo(String value) {
            addCriterion("passwd >=", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdLessThan(String value) {
            addCriterion("passwd <", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdLessThanOrEqualTo(String value) {
            addCriterion("passwd <=", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdLike(String value) {
            addCriterion("passwd like", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdNotLike(String value) {
            addCriterion("passwd not like", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdIn(List<String> values) {
            addCriterion("passwd in", values, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdNotIn(List<String> values) {
            addCriterion("passwd not in", values, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdBetween(String value1, String value2) {
            addCriterion("passwd between", value1, value2, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdNotBetween(String value1, String value2) {
            addCriterion("passwd not between", value1, value2, "passwd");
            return (Criteria) this;
        }

        public Criteria andTradePassWordIsNull() {
            addCriterion("trade_pass_word is null");
            return (Criteria) this;
        }

        public Criteria andTradePassWordIsNotNull() {
            addCriterion("trade_pass_word is not null");
            return (Criteria) this;
        }

        public Criteria andTradePassWordEqualTo(String value) {
            addCriterion("trade_pass_word =", value, "tradePassWord");
            return (Criteria) this;
        }

        public Criteria andTradePassWordNotEqualTo(String value) {
            addCriterion("trade_pass_word <>", value, "tradePassWord");
            return (Criteria) this;
        }

        public Criteria andTradePassWordGreaterThan(String value) {
            addCriterion("trade_pass_word >", value, "tradePassWord");
            return (Criteria) this;
        }

        public Criteria andTradePassWordGreaterThanOrEqualTo(String value) {
            addCriterion("trade_pass_word >=", value, "tradePassWord");
            return (Criteria) this;
        }

        public Criteria andTradePassWordLessThan(String value) {
            addCriterion("trade_pass_word <", value, "tradePassWord");
            return (Criteria) this;
        }

        public Criteria andTradePassWordLessThanOrEqualTo(String value) {
            addCriterion("trade_pass_word <=", value, "tradePassWord");
            return (Criteria) this;
        }

        public Criteria andTradePassWordLike(String value) {
            addCriterion("trade_pass_word like", value, "tradePassWord");
            return (Criteria) this;
        }

        public Criteria andTradePassWordNotLike(String value) {
            addCriterion("trade_pass_word not like", value, "tradePassWord");
            return (Criteria) this;
        }

        public Criteria andTradePassWordIn(List<String> values) {
            addCriterion("trade_pass_word in", values, "tradePassWord");
            return (Criteria) this;
        }

        public Criteria andTradePassWordNotIn(List<String> values) {
            addCriterion("trade_pass_word not in", values, "tradePassWord");
            return (Criteria) this;
        }

        public Criteria andTradePassWordBetween(String value1, String value2) {
            addCriterion("trade_pass_word between", value1, value2, "tradePassWord");
            return (Criteria) this;
        }

        public Criteria andTradePassWordNotBetween(String value1, String value2) {
            addCriterion("trade_pass_word not between", value1, value2, "tradePassWord");
            return (Criteria) this;
        }

        public Criteria andAutographIsNull() {
            addCriterion("autograph is null");
            return (Criteria) this;
        }

        public Criteria andAutographIsNotNull() {
            addCriterion("autograph is not null");
            return (Criteria) this;
        }

        public Criteria andAutographEqualTo(String value) {
            addCriterion("autograph =", value, "autograph");
            return (Criteria) this;
        }

        public Criteria andAutographNotEqualTo(String value) {
            addCriterion("autograph <>", value, "autograph");
            return (Criteria) this;
        }

        public Criteria andAutographGreaterThan(String value) {
            addCriterion("autograph >", value, "autograph");
            return (Criteria) this;
        }

        public Criteria andAutographGreaterThanOrEqualTo(String value) {
            addCriterion("autograph >=", value, "autograph");
            return (Criteria) this;
        }

        public Criteria andAutographLessThan(String value) {
            addCriterion("autograph <", value, "autograph");
            return (Criteria) this;
        }

        public Criteria andAutographLessThanOrEqualTo(String value) {
            addCriterion("autograph <=", value, "autograph");
            return (Criteria) this;
        }

        public Criteria andAutographLike(String value) {
            addCriterion("autograph like", value, "autograph");
            return (Criteria) this;
        }

        public Criteria andAutographNotLike(String value) {
            addCriterion("autograph not like", value, "autograph");
            return (Criteria) this;
        }

        public Criteria andAutographIn(List<String> values) {
            addCriterion("autograph in", values, "autograph");
            return (Criteria) this;
        }

        public Criteria andAutographNotIn(List<String> values) {
            addCriterion("autograph not in", values, "autograph");
            return (Criteria) this;
        }

        public Criteria andAutographBetween(String value1, String value2) {
            addCriterion("autograph between", value1, value2, "autograph");
            return (Criteria) this;
        }

        public Criteria andAutographNotBetween(String value1, String value2) {
            addCriterion("autograph not between", value1, value2, "autograph");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNull() {
            addCriterion("balance is null");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNotNull() {
            addCriterion("balance is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceEqualTo(BigDecimal value) {
            addCriterion("balance =", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotEqualTo(BigDecimal value) {
            addCriterion("balance <>", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThan(BigDecimal value) {
            addCriterion("balance >", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("balance >=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThan(BigDecimal value) {
            addCriterion("balance <", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("balance <=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceIn(List<BigDecimal> values) {
            addCriterion("balance in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotIn(List<BigDecimal> values) {
            addCriterion("balance not in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance not between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andAlipayIsNull() {
            addCriterion("alipay is null");
            return (Criteria) this;
        }

        public Criteria andAlipayIsNotNull() {
            addCriterion("alipay is not null");
            return (Criteria) this;
        }

        public Criteria andAlipayEqualTo(String value) {
            addCriterion("alipay =", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayNotEqualTo(String value) {
            addCriterion("alipay <>", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayGreaterThan(String value) {
            addCriterion("alipay >", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayGreaterThanOrEqualTo(String value) {
            addCriterion("alipay >=", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayLessThan(String value) {
            addCriterion("alipay <", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayLessThanOrEqualTo(String value) {
            addCriterion("alipay <=", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayLike(String value) {
            addCriterion("alipay like", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayNotLike(String value) {
            addCriterion("alipay not like", value, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayIn(List<String> values) {
            addCriterion("alipay in", values, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayNotIn(List<String> values) {
            addCriterion("alipay not in", values, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayBetween(String value1, String value2) {
            addCriterion("alipay between", value1, value2, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipayNotBetween(String value1, String value2) {
            addCriterion("alipay not between", value1, value2, "alipay");
            return (Criteria) this;
        }

        public Criteria andAlipaynameIsNull() {
            addCriterion("alipayname is null");
            return (Criteria) this;
        }

        public Criteria andAlipaynameIsNotNull() {
            addCriterion("alipayname is not null");
            return (Criteria) this;
        }

        public Criteria andAlipaynameEqualTo(String value) {
            addCriterion("alipayname =", value, "alipayname");
            return (Criteria) this;
        }

        public Criteria andAlipaynameNotEqualTo(String value) {
            addCriterion("alipayname <>", value, "alipayname");
            return (Criteria) this;
        }

        public Criteria andAlipaynameGreaterThan(String value) {
            addCriterion("alipayname >", value, "alipayname");
            return (Criteria) this;
        }

        public Criteria andAlipaynameGreaterThanOrEqualTo(String value) {
            addCriterion("alipayname >=", value, "alipayname");
            return (Criteria) this;
        }

        public Criteria andAlipaynameLessThan(String value) {
            addCriterion("alipayname <", value, "alipayname");
            return (Criteria) this;
        }

        public Criteria andAlipaynameLessThanOrEqualTo(String value) {
            addCriterion("alipayname <=", value, "alipayname");
            return (Criteria) this;
        }

        public Criteria andAlipaynameLike(String value) {
            addCriterion("alipayname like", value, "alipayname");
            return (Criteria) this;
        }

        public Criteria andAlipaynameNotLike(String value) {
            addCriterion("alipayname not like", value, "alipayname");
            return (Criteria) this;
        }

        public Criteria andAlipaynameIn(List<String> values) {
            addCriterion("alipayname in", values, "alipayname");
            return (Criteria) this;
        }

        public Criteria andAlipaynameNotIn(List<String> values) {
            addCriterion("alipayname not in", values, "alipayname");
            return (Criteria) this;
        }

        public Criteria andAlipaynameBetween(String value1, String value2) {
            addCriterion("alipayname between", value1, value2, "alipayname");
            return (Criteria) this;
        }

        public Criteria andAlipaynameNotBetween(String value1, String value2) {
            addCriterion("alipayname not between", value1, value2, "alipayname");
            return (Criteria) this;
        }

        public Criteria andRealnametypeIsNull() {
            addCriterion("realnametype is null");
            return (Criteria) this;
        }

        public Criteria andRealnametypeIsNotNull() {
            addCriterion("realnametype is not null");
            return (Criteria) this;
        }

        public Criteria andRealnametypeEqualTo(Integer value) {
            addCriterion("realnametype =", value, "realnametype");
            return (Criteria) this;
        }

        public Criteria andRealnametypeNotEqualTo(Integer value) {
            addCriterion("realnametype <>", value, "realnametype");
            return (Criteria) this;
        }

        public Criteria andRealnametypeGreaterThan(Integer value) {
            addCriterion("realnametype >", value, "realnametype");
            return (Criteria) this;
        }

        public Criteria andRealnametypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("realnametype >=", value, "realnametype");
            return (Criteria) this;
        }

        public Criteria andRealnametypeLessThan(Integer value) {
            addCriterion("realnametype <", value, "realnametype");
            return (Criteria) this;
        }

        public Criteria andRealnametypeLessThanOrEqualTo(Integer value) {
            addCriterion("realnametype <=", value, "realnametype");
            return (Criteria) this;
        }

        public Criteria andRealnametypeIn(List<Integer> values) {
            addCriterion("realnametype in", values, "realnametype");
            return (Criteria) this;
        }

        public Criteria andRealnametypeNotIn(List<Integer> values) {
            addCriterion("realnametype not in", values, "realnametype");
            return (Criteria) this;
        }

        public Criteria andRealnametypeBetween(Integer value1, Integer value2) {
            addCriterion("realnametype between", value1, value2, "realnametype");
            return (Criteria) this;
        }

        public Criteria andRealnametypeNotBetween(Integer value1, Integer value2) {
            addCriterion("realnametype not between", value1, value2, "realnametype");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andStatusIdIsNull() {
            addCriterion("status_id is null");
            return (Criteria) this;
        }

        public Criteria andStatusIdIsNotNull() {
            addCriterion("status_id is not null");
            return (Criteria) this;
        }

        public Criteria andStatusIdEqualTo(String value) {
            addCriterion("status_id =", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotEqualTo(String value) {
            addCriterion("status_id <>", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdGreaterThan(String value) {
            addCriterion("status_id >", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("status_id >=", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdLessThan(String value) {
            addCriterion("status_id <", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdLessThanOrEqualTo(String value) {
            addCriterion("status_id <=", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdLike(String value) {
            addCriterion("status_id like", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotLike(String value) {
            addCriterion("status_id not like", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdIn(List<String> values) {
            addCriterion("status_id in", values, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotIn(List<String> values) {
            addCriterion("status_id not in", values, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdBetween(String value1, String value2) {
            addCriterion("status_id between", value1, value2, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotBetween(String value1, String value2) {
            addCriterion("status_id not between", value1, value2, "statusId");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyIsNull() {
            addCriterion("privateKey is null");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyIsNotNull() {
            addCriterion("privateKey is not null");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyEqualTo(String value) {
            addCriterion("privateKey =", value, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyNotEqualTo(String value) {
            addCriterion("privateKey <>", value, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyGreaterThan(String value) {
            addCriterion("privateKey >", value, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyGreaterThanOrEqualTo(String value) {
            addCriterion("privateKey >=", value, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyLessThan(String value) {
            addCriterion("privateKey <", value, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyLessThanOrEqualTo(String value) {
            addCriterion("privateKey <=", value, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyLike(String value) {
            addCriterion("privateKey like", value, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyNotLike(String value) {
            addCriterion("privateKey not like", value, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyIn(List<String> values) {
            addCriterion("privateKey in", values, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyNotIn(List<String> values) {
            addCriterion("privateKey not in", values, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyBetween(String value1, String value2) {
            addCriterion("privateKey between", value1, value2, "privatekey");
            return (Criteria) this;
        }

        public Criteria andPrivatekeyNotBetween(String value1, String value2) {
            addCriterion("privateKey not between", value1, value2, "privatekey");
            return (Criteria) this;
        }

        public Criteria andWhitelistIsNull() {
            addCriterion("whitelist is null");
            return (Criteria) this;
        }

        public Criteria andWhitelistIsNotNull() {
            addCriterion("whitelist is not null");
            return (Criteria) this;
        }

        public Criteria andWhitelistEqualTo(Integer value) {
            addCriterion("whitelist =", value, "whitelist");
            return (Criteria) this;
        }

        public Criteria andWhitelistNotEqualTo(Integer value) {
            addCriterion("whitelist <>", value, "whitelist");
            return (Criteria) this;
        }

        public Criteria andWhitelistGreaterThan(Integer value) {
            addCriterion("whitelist >", value, "whitelist");
            return (Criteria) this;
        }

        public Criteria andWhitelistGreaterThanOrEqualTo(Integer value) {
            addCriterion("whitelist >=", value, "whitelist");
            return (Criteria) this;
        }

        public Criteria andWhitelistLessThan(Integer value) {
            addCriterion("whitelist <", value, "whitelist");
            return (Criteria) this;
        }

        public Criteria andWhitelistLessThanOrEqualTo(Integer value) {
            addCriterion("whitelist <=", value, "whitelist");
            return (Criteria) this;
        }

        public Criteria andWhitelistIn(List<Integer> values) {
            addCriterion("whitelist in", values, "whitelist");
            return (Criteria) this;
        }

        public Criteria andWhitelistNotIn(List<Integer> values) {
            addCriterion("whitelist not in", values, "whitelist");
            return (Criteria) this;
        }

        public Criteria andWhitelistBetween(Integer value1, Integer value2) {
            addCriterion("whitelist between", value1, value2, "whitelist");
            return (Criteria) this;
        }

        public Criteria andWhitelistNotBetween(Integer value1, Integer value2) {
            addCriterion("whitelist not between", value1, value2, "whitelist");
            return (Criteria) this;
        }

        public Criteria andRealnameIsNull() {
            addCriterion("realname is null");
            return (Criteria) this;
        }

        public Criteria andRealnameIsNotNull() {
            addCriterion("realname is not null");
            return (Criteria) this;
        }

        public Criteria andRealnameEqualTo(String value) {
            addCriterion("realname =", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotEqualTo(String value) {
            addCriterion("realname <>", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameGreaterThan(String value) {
            addCriterion("realname >", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameGreaterThanOrEqualTo(String value) {
            addCriterion("realname >=", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLessThan(String value) {
            addCriterion("realname <", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLessThanOrEqualTo(String value) {
            addCriterion("realname <=", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLike(String value) {
            addCriterion("realname like", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotLike(String value) {
            addCriterion("realname not like", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameIn(List<String> values) {
            addCriterion("realname in", values, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotIn(List<String> values) {
            addCriterion("realname not in", values, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameBetween(String value1, String value2) {
            addCriterion("realname between", value1, value2, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotBetween(String value1, String value2) {
            addCriterion("realname not between", value1, value2, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnoIsNull() {
            addCriterion("realno is null");
            return (Criteria) this;
        }

        public Criteria andRealnoIsNotNull() {
            addCriterion("realno is not null");
            return (Criteria) this;
        }

        public Criteria andRealnoEqualTo(String value) {
            addCriterion("realno =", value, "realno");
            return (Criteria) this;
        }

        public Criteria andRealnoNotEqualTo(String value) {
            addCriterion("realno <>", value, "realno");
            return (Criteria) this;
        }

        public Criteria andRealnoGreaterThan(String value) {
            addCriterion("realno >", value, "realno");
            return (Criteria) this;
        }

        public Criteria andRealnoGreaterThanOrEqualTo(String value) {
            addCriterion("realno >=", value, "realno");
            return (Criteria) this;
        }

        public Criteria andRealnoLessThan(String value) {
            addCriterion("realno <", value, "realno");
            return (Criteria) this;
        }

        public Criteria andRealnoLessThanOrEqualTo(String value) {
            addCriterion("realno <=", value, "realno");
            return (Criteria) this;
        }

        public Criteria andRealnoLike(String value) {
            addCriterion("realno like", value, "realno");
            return (Criteria) this;
        }

        public Criteria andRealnoNotLike(String value) {
            addCriterion("realno not like", value, "realno");
            return (Criteria) this;
        }

        public Criteria andRealnoIn(List<String> values) {
            addCriterion("realno in", values, "realno");
            return (Criteria) this;
        }

        public Criteria andRealnoNotIn(List<String> values) {
            addCriterion("realno not in", values, "realno");
            return (Criteria) this;
        }

        public Criteria andRealnoBetween(String value1, String value2) {
            addCriterion("realno between", value1, value2, "realno");
            return (Criteria) this;
        }

        public Criteria andRealnoNotBetween(String value1, String value2) {
            addCriterion("realno not between", value1, value2, "realno");
            return (Criteria) this;
        }

        public Criteria andInvitationIdIsNull() {
            addCriterion("invitation_id is null");
            return (Criteria) this;
        }

        public Criteria andInvitationIdIsNotNull() {
            addCriterion("invitation_id is not null");
            return (Criteria) this;
        }

        public Criteria andInvitationIdEqualTo(Integer value) {
            addCriterion("invitation_id =", value, "invitationId");
            return (Criteria) this;
        }

        public Criteria andInvitationIdNotEqualTo(Integer value) {
            addCriterion("invitation_id <>", value, "invitationId");
            return (Criteria) this;
        }

        public Criteria andInvitationIdGreaterThan(Integer value) {
            addCriterion("invitation_id >", value, "invitationId");
            return (Criteria) this;
        }

        public Criteria andInvitationIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("invitation_id >=", value, "invitationId");
            return (Criteria) this;
        }

        public Criteria andInvitationIdLessThan(Integer value) {
            addCriterion("invitation_id <", value, "invitationId");
            return (Criteria) this;
        }

        public Criteria andInvitationIdLessThanOrEqualTo(Integer value) {
            addCriterion("invitation_id <=", value, "invitationId");
            return (Criteria) this;
        }

        public Criteria andInvitationIdIn(List<Integer> values) {
            addCriterion("invitation_id in", values, "invitationId");
            return (Criteria) this;
        }

        public Criteria andInvitationIdNotIn(List<Integer> values) {
            addCriterion("invitation_id not in", values, "invitationId");
            return (Criteria) this;
        }

        public Criteria andInvitationIdBetween(Integer value1, Integer value2) {
            addCriterion("invitation_id between", value1, value2, "invitationId");
            return (Criteria) this;
        }

        public Criteria andInvitationIdNotBetween(Integer value1, Integer value2) {
            addCriterion("invitation_id not between", value1, value2, "invitationId");
            return (Criteria) this;
        }

        public Criteria andInvitationcountIsNull() {
            addCriterion("invitationcount is null");
            return (Criteria) this;
        }

        public Criteria andInvitationcountIsNotNull() {
            addCriterion("invitationcount is not null");
            return (Criteria) this;
        }

        public Criteria andInvitationcountEqualTo(Integer value) {
            addCriterion("invitationcount =", value, "invitationcount");
            return (Criteria) this;
        }

        public Criteria andInvitationcountNotEqualTo(Integer value) {
            addCriterion("invitationcount <>", value, "invitationcount");
            return (Criteria) this;
        }

        public Criteria andInvitationcountGreaterThan(Integer value) {
            addCriterion("invitationcount >", value, "invitationcount");
            return (Criteria) this;
        }

        public Criteria andInvitationcountGreaterThanOrEqualTo(Integer value) {
            addCriterion("invitationcount >=", value, "invitationcount");
            return (Criteria) this;
        }

        public Criteria andInvitationcountLessThan(Integer value) {
            addCriterion("invitationcount <", value, "invitationcount");
            return (Criteria) this;
        }

        public Criteria andInvitationcountLessThanOrEqualTo(Integer value) {
            addCriterion("invitationcount <=", value, "invitationcount");
            return (Criteria) this;
        }

        public Criteria andInvitationcountIn(List<Integer> values) {
            addCriterion("invitationcount in", values, "invitationcount");
            return (Criteria) this;
        }

        public Criteria andInvitationcountNotIn(List<Integer> values) {
            addCriterion("invitationcount not in", values, "invitationcount");
            return (Criteria) this;
        }

        public Criteria andInvitationcountBetween(Integer value1, Integer value2) {
            addCriterion("invitationcount between", value1, value2, "invitationcount");
            return (Criteria) this;
        }

        public Criteria andInvitationcountNotBetween(Integer value1, Integer value2) {
            addCriterion("invitationcount not between", value1, value2, "invitationcount");
            return (Criteria) this;
        }

        public Criteria andSzcountIsNull() {
            addCriterion("szcount is null");
            return (Criteria) this;
        }

        public Criteria andSzcountIsNotNull() {
            addCriterion("szcount is not null");
            return (Criteria) this;
        }

        public Criteria andSzcountEqualTo(Integer value) {
            addCriterion("szcount =", value, "szcount");
            return (Criteria) this;
        }

        public Criteria andSzcountNotEqualTo(Integer value) {
            addCriterion("szcount <>", value, "szcount");
            return (Criteria) this;
        }

        public Criteria andSzcountGreaterThan(Integer value) {
            addCriterion("szcount >", value, "szcount");
            return (Criteria) this;
        }

        public Criteria andSzcountGreaterThanOrEqualTo(Integer value) {
            addCriterion("szcount >=", value, "szcount");
            return (Criteria) this;
        }

        public Criteria andSzcountLessThan(Integer value) {
            addCriterion("szcount <", value, "szcount");
            return (Criteria) this;
        }

        public Criteria andSzcountLessThanOrEqualTo(Integer value) {
            addCriterion("szcount <=", value, "szcount");
            return (Criteria) this;
        }

        public Criteria andSzcountIn(List<Integer> values) {
            addCriterion("szcount in", values, "szcount");
            return (Criteria) this;
        }

        public Criteria andSzcountNotIn(List<Integer> values) {
            addCriterion("szcount not in", values, "szcount");
            return (Criteria) this;
        }

        public Criteria andSzcountBetween(Integer value1, Integer value2) {
            addCriterion("szcount between", value1, value2, "szcount");
            return (Criteria) this;
        }

        public Criteria andSzcountNotBetween(Integer value1, Integer value2) {
            addCriterion("szcount not between", value1, value2, "szcount");
            return (Criteria) this;
        }

        public Criteria andSztimeIsNull() {
            addCriterion("sztime is null");
            return (Criteria) this;
        }

        public Criteria andSztimeIsNotNull() {
            addCriterion("sztime is not null");
            return (Criteria) this;
        }

        public Criteria andSztimeEqualTo(Date value) {
            addCriterion("sztime =", value, "sztime");
            return (Criteria) this;
        }

        public Criteria andSztimeNotEqualTo(Date value) {
            addCriterion("sztime <>", value, "sztime");
            return (Criteria) this;
        }

        public Criteria andSztimeGreaterThan(Date value) {
            addCriterion("sztime >", value, "sztime");
            return (Criteria) this;
        }

        public Criteria andSztimeGreaterThanOrEqualTo(Date value) {
            addCriterion("sztime >=", value, "sztime");
            return (Criteria) this;
        }

        public Criteria andSztimeLessThan(Date value) {
            addCriterion("sztime <", value, "sztime");
            return (Criteria) this;
        }

        public Criteria andSztimeLessThanOrEqualTo(Date value) {
            addCriterion("sztime <=", value, "sztime");
            return (Criteria) this;
        }

        public Criteria andSztimeIn(List<Date> values) {
            addCriterion("sztime in", values, "sztime");
            return (Criteria) this;
        }

        public Criteria andSztimeNotIn(List<Date> values) {
            addCriterion("sztime not in", values, "sztime");
            return (Criteria) this;
        }

        public Criteria andSztimeBetween(Date value1, Date value2) {
            addCriterion("sztime between", value1, value2, "sztime");
            return (Criteria) this;
        }

        public Criteria andSztimeNotBetween(Date value1, Date value2) {
            addCriterion("sztime not between", value1, value2, "sztime");
            return (Criteria) this;
        }

        public Criteria andIscreaterIsNull() {
            addCriterion("iscreater is null");
            return (Criteria) this;
        }

        public Criteria andIscreaterIsNotNull() {
            addCriterion("iscreater is not null");
            return (Criteria) this;
        }

        public Criteria andIscreaterEqualTo(Integer value) {
            addCriterion("iscreater =", value, "iscreater");
            return (Criteria) this;
        }

        public Criteria andIscreaterNotEqualTo(Integer value) {
            addCriterion("iscreater <>", value, "iscreater");
            return (Criteria) this;
        }

        public Criteria andIscreaterGreaterThan(Integer value) {
            addCriterion("iscreater >", value, "iscreater");
            return (Criteria) this;
        }

        public Criteria andIscreaterGreaterThanOrEqualTo(Integer value) {
            addCriterion("iscreater >=", value, "iscreater");
            return (Criteria) this;
        }

        public Criteria andIscreaterLessThan(Integer value) {
            addCriterion("iscreater <", value, "iscreater");
            return (Criteria) this;
        }

        public Criteria andIscreaterLessThanOrEqualTo(Integer value) {
            addCriterion("iscreater <=", value, "iscreater");
            return (Criteria) this;
        }

        public Criteria andIscreaterIn(List<Integer> values) {
            addCriterion("iscreater in", values, "iscreater");
            return (Criteria) this;
        }

        public Criteria andIscreaterNotIn(List<Integer> values) {
            addCriterion("iscreater not in", values, "iscreater");
            return (Criteria) this;
        }

        public Criteria andIscreaterBetween(Integer value1, Integer value2) {
            addCriterion("iscreater between", value1, value2, "iscreater");
            return (Criteria) this;
        }

        public Criteria andIscreaterNotBetween(Integer value1, Integer value2) {
            addCriterion("iscreater not between", value1, value2, "iscreater");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNull() {
            addCriterion("money is null");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNotNull() {
            addCriterion("money is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyEqualTo(BigDecimal value) {
            addCriterion("money =", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotEqualTo(BigDecimal value) {
            addCriterion("money <>", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThan(BigDecimal value) {
            addCriterion("money >", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("money >=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThan(BigDecimal value) {
            addCriterion("money <", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("money <=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyIn(List<BigDecimal> values) {
            addCriterion("money in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotIn(List<BigDecimal> values) {
            addCriterion("money not in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("money between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("money not between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeIsNull() {
            addCriterion("invitation_code is null");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeIsNotNull() {
            addCriterion("invitation_code is not null");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeEqualTo(String value) {
            addCriterion("invitation_code =", value, "invitationCode");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeNotEqualTo(String value) {
            addCriterion("invitation_code <>", value, "invitationCode");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeGreaterThan(String value) {
            addCriterion("invitation_code >", value, "invitationCode");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeGreaterThanOrEqualTo(String value) {
            addCriterion("invitation_code >=", value, "invitationCode");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeLessThan(String value) {
            addCriterion("invitation_code <", value, "invitationCode");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeLessThanOrEqualTo(String value) {
            addCriterion("invitation_code <=", value, "invitationCode");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeLike(String value) {
            addCriterion("invitation_code like", value, "invitationCode");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeNotLike(String value) {
            addCriterion("invitation_code not like", value, "invitationCode");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeIn(List<String> values) {
            addCriterion("invitation_code in", values, "invitationCode");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeNotIn(List<String> values) {
            addCriterion("invitation_code not in", values, "invitationCode");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeBetween(String value1, String value2) {
            addCriterion("invitation_code between", value1, value2, "invitationCode");
            return (Criteria) this;
        }

        public Criteria andInvitationCodeNotBetween(String value1, String value2) {
            addCriterion("invitation_code not between", value1, value2, "invitationCode");
            return (Criteria) this;
        }

        public Criteria andLotterytimesIsNull() {
            addCriterion("lotterytimes is null");
            return (Criteria) this;
        }

        public Criteria andLotterytimesIsNotNull() {
            addCriterion("lotterytimes is not null");
            return (Criteria) this;
        }

        public Criteria andLotterytimesEqualTo(Integer value) {
            addCriterion("lotterytimes =", value, "lotterytimes");
            return (Criteria) this;
        }

        public Criteria andLotterytimesNotEqualTo(Integer value) {
            addCriterion("lotterytimes <>", value, "lotterytimes");
            return (Criteria) this;
        }

        public Criteria andLotterytimesGreaterThan(Integer value) {
            addCriterion("lotterytimes >", value, "lotterytimes");
            return (Criteria) this;
        }

        public Criteria andLotterytimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("lotterytimes >=", value, "lotterytimes");
            return (Criteria) this;
        }

        public Criteria andLotterytimesLessThan(Integer value) {
            addCriterion("lotterytimes <", value, "lotterytimes");
            return (Criteria) this;
        }

        public Criteria andLotterytimesLessThanOrEqualTo(Integer value) {
            addCriterion("lotterytimes <=", value, "lotterytimes");
            return (Criteria) this;
        }

        public Criteria andLotterytimesIn(List<Integer> values) {
            addCriterion("lotterytimes in", values, "lotterytimes");
            return (Criteria) this;
        }

        public Criteria andLotterytimesNotIn(List<Integer> values) {
            addCriterion("lotterytimes not in", values, "lotterytimes");
            return (Criteria) this;
        }

        public Criteria andLotterytimesBetween(Integer value1, Integer value2) {
            addCriterion("lotterytimes between", value1, value2, "lotterytimes");
            return (Criteria) this;
        }

        public Criteria andLotterytimesNotBetween(Integer value1, Integer value2) {
            addCriterion("lotterytimes not between", value1, value2, "lotterytimes");
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

        public Criteria andUstypeIsNull() {
            addCriterion("ustype is null");
            return (Criteria) this;
        }

        public Criteria andUstypeIsNotNull() {
            addCriterion("ustype is not null");
            return (Criteria) this;
        }

        public Criteria andUstypeEqualTo(Integer value) {
            addCriterion("ustype =", value, "ustype");
            return (Criteria) this;
        }

        public Criteria andUstypeNotEqualTo(Integer value) {
            addCriterion("ustype <>", value, "ustype");
            return (Criteria) this;
        }

        public Criteria andUstypeGreaterThan(Integer value) {
            addCriterion("ustype >", value, "ustype");
            return (Criteria) this;
        }

        public Criteria andUstypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("ustype >=", value, "ustype");
            return (Criteria) this;
        }

        public Criteria andUstypeLessThan(Integer value) {
            addCriterion("ustype <", value, "ustype");
            return (Criteria) this;
        }

        public Criteria andUstypeLessThanOrEqualTo(Integer value) {
            addCriterion("ustype <=", value, "ustype");
            return (Criteria) this;
        }

        public Criteria andUstypeIn(List<Integer> values) {
            addCriterion("ustype in", values, "ustype");
            return (Criteria) this;
        }

        public Criteria andUstypeNotIn(List<Integer> values) {
            addCriterion("ustype not in", values, "ustype");
            return (Criteria) this;
        }

        public Criteria andUstypeBetween(Integer value1, Integer value2) {
            addCriterion("ustype between", value1, value2, "ustype");
            return (Criteria) this;
        }

        public Criteria andUstypeNotBetween(Integer value1, Integer value2) {
            addCriterion("ustype not between", value1, value2, "ustype");
            return (Criteria) this;
        }

        public Criteria andTudicardIsNull() {
            addCriterion("tudicard is null");
            return (Criteria) this;
        }

        public Criteria andTudicardIsNotNull() {
            addCriterion("tudicard is not null");
            return (Criteria) this;
        }

        public Criteria andTudicardEqualTo(Integer value) {
            addCriterion("tudicard =", value, "tudicard");
            return (Criteria) this;
        }

        public Criteria andTudicardNotEqualTo(Integer value) {
            addCriterion("tudicard <>", value, "tudicard");
            return (Criteria) this;
        }

        public Criteria andTudicardGreaterThan(Integer value) {
            addCriterion("tudicard >", value, "tudicard");
            return (Criteria) this;
        }

        public Criteria andTudicardGreaterThanOrEqualTo(Integer value) {
            addCriterion("tudicard >=", value, "tudicard");
            return (Criteria) this;
        }

        public Criteria andTudicardLessThan(Integer value) {
            addCriterion("tudicard <", value, "tudicard");
            return (Criteria) this;
        }

        public Criteria andTudicardLessThanOrEqualTo(Integer value) {
            addCriterion("tudicard <=", value, "tudicard");
            return (Criteria) this;
        }

        public Criteria andTudicardIn(List<Integer> values) {
            addCriterion("tudicard in", values, "tudicard");
            return (Criteria) this;
        }

        public Criteria andTudicardNotIn(List<Integer> values) {
            addCriterion("tudicard not in", values, "tudicard");
            return (Criteria) this;
        }

        public Criteria andTudicardBetween(Integer value1, Integer value2) {
            addCriterion("tudicard between", value1, value2, "tudicard");
            return (Criteria) this;
        }

        public Criteria andTudicardNotBetween(Integer value1, Integer value2) {
            addCriterion("tudicard not between", value1, value2, "tudicard");
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

        public Criteria andValidIsNull() {
            addCriterion("valid is null");
            return (Criteria) this;
        }

        public Criteria andValidIsNotNull() {
            addCriterion("valid is not null");
            return (Criteria) this;
        }

        public Criteria andValidEqualTo(Byte value) {
            addCriterion("valid =", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotEqualTo(Byte value) {
            addCriterion("valid <>", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidGreaterThan(Byte value) {
            addCriterion("valid >", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidGreaterThanOrEqualTo(Byte value) {
            addCriterion("valid >=", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidLessThan(Byte value) {
            addCriterion("valid <", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidLessThanOrEqualTo(Byte value) {
            addCriterion("valid <=", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidIn(List<Byte> values) {
            addCriterion("valid in", values, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotIn(List<Byte> values) {
            addCriterion("valid not in", values, "valid");
            return (Criteria) this;
        }

        public Criteria andValidBetween(Byte value1, Byte value2) {
            addCriterion("valid between", value1, value2, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotBetween(Byte value1, Byte value2) {
            addCriterion("valid not between", value1, value2, "valid");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * users 
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