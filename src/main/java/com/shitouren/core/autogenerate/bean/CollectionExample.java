package com.shitouren.core.autogenerate.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CollectionExample {
    /**
     * collection
     */
    protected String orderByClause;

    /**
     * collection
     */
    protected boolean distinct;

    /**
     * collection
     */
    protected List<Criteria> oredCriteria;

    /**
     * collection
     */
    protected Integer pageNo = 1;

    /**
     * collection
     */
    protected Integer startRow;

    /**
     * collection
     */
    protected Integer pageSize = 10;

    public CollectionExample() {
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
     * collection 
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

        public Criteria andMinnameIsNull() {
            addCriterion("minname is null");
            return (Criteria) this;
        }

        public Criteria andMinnameIsNotNull() {
            addCriterion("minname is not null");
            return (Criteria) this;
        }

        public Criteria andMinnameEqualTo(String value) {
            addCriterion("minname =", value, "minname");
            return (Criteria) this;
        }

        public Criteria andMinnameNotEqualTo(String value) {
            addCriterion("minname <>", value, "minname");
            return (Criteria) this;
        }

        public Criteria andMinnameGreaterThan(String value) {
            addCriterion("minname >", value, "minname");
            return (Criteria) this;
        }

        public Criteria andMinnameGreaterThanOrEqualTo(String value) {
            addCriterion("minname >=", value, "minname");
            return (Criteria) this;
        }

        public Criteria andMinnameLessThan(String value) {
            addCriterion("minname <", value, "minname");
            return (Criteria) this;
        }

        public Criteria andMinnameLessThanOrEqualTo(String value) {
            addCriterion("minname <=", value, "minname");
            return (Criteria) this;
        }

        public Criteria andMinnameLike(String value) {
            addCriterion("minname like", value, "minname");
            return (Criteria) this;
        }

        public Criteria andMinnameNotLike(String value) {
            addCriterion("minname not like", value, "minname");
            return (Criteria) this;
        }

        public Criteria andMinnameIn(List<String> values) {
            addCriterion("minname in", values, "minname");
            return (Criteria) this;
        }

        public Criteria andMinnameNotIn(List<String> values) {
            addCriterion("minname not in", values, "minname");
            return (Criteria) this;
        }

        public Criteria andMinnameBetween(String value1, String value2) {
            addCriterion("minname between", value1, value2, "minname");
            return (Criteria) this;
        }

        public Criteria andMinnameNotBetween(String value1, String value2) {
            addCriterion("minname not between", value1, value2, "minname");
            return (Criteria) this;
        }

        public Criteria andLimitsIsNull() {
            addCriterion("limits is null");
            return (Criteria) this;
        }

        public Criteria andLimitsIsNotNull() {
            addCriterion("limits is not null");
            return (Criteria) this;
        }

        public Criteria andLimitsEqualTo(Integer value) {
            addCriterion("limits =", value, "limits");
            return (Criteria) this;
        }

        public Criteria andLimitsNotEqualTo(Integer value) {
            addCriterion("limits <>", value, "limits");
            return (Criteria) this;
        }

        public Criteria andLimitsGreaterThan(Integer value) {
            addCriterion("limits >", value, "limits");
            return (Criteria) this;
        }

        public Criteria andLimitsGreaterThanOrEqualTo(Integer value) {
            addCriterion("limits >=", value, "limits");
            return (Criteria) this;
        }

        public Criteria andLimitsLessThan(Integer value) {
            addCriterion("limits <", value, "limits");
            return (Criteria) this;
        }

        public Criteria andLimitsLessThanOrEqualTo(Integer value) {
            addCriterion("limits <=", value, "limits");
            return (Criteria) this;
        }

        public Criteria andLimitsIn(List<Integer> values) {
            addCriterion("limits in", values, "limits");
            return (Criteria) this;
        }

        public Criteria andLimitsNotIn(List<Integer> values) {
            addCriterion("limits not in", values, "limits");
            return (Criteria) this;
        }

        public Criteria andLimitsBetween(Integer value1, Integer value2) {
            addCriterion("limits between", value1, value2, "limits");
            return (Criteria) this;
        }

        public Criteria andLimitsNotBetween(Integer value1, Integer value2) {
            addCriterion("limits not between", value1, value2, "limits");
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

        public Criteria andVideoIsNull() {
            addCriterion("video is null");
            return (Criteria) this;
        }

        public Criteria andVideoIsNotNull() {
            addCriterion("video is not null");
            return (Criteria) this;
        }

        public Criteria andVideoEqualTo(String value) {
            addCriterion("video =", value, "video");
            return (Criteria) this;
        }

        public Criteria andVideoNotEqualTo(String value) {
            addCriterion("video <>", value, "video");
            return (Criteria) this;
        }

        public Criteria andVideoGreaterThan(String value) {
            addCriterion("video >", value, "video");
            return (Criteria) this;
        }

        public Criteria andVideoGreaterThanOrEqualTo(String value) {
            addCriterion("video >=", value, "video");
            return (Criteria) this;
        }

        public Criteria andVideoLessThan(String value) {
            addCriterion("video <", value, "video");
            return (Criteria) this;
        }

        public Criteria andVideoLessThanOrEqualTo(String value) {
            addCriterion("video <=", value, "video");
            return (Criteria) this;
        }

        public Criteria andVideoLike(String value) {
            addCriterion("video like", value, "video");
            return (Criteria) this;
        }

        public Criteria andVideoNotLike(String value) {
            addCriterion("video not like", value, "video");
            return (Criteria) this;
        }

        public Criteria andVideoIn(List<String> values) {
            addCriterion("video in", values, "video");
            return (Criteria) this;
        }

        public Criteria andVideoNotIn(List<String> values) {
            addCriterion("video not in", values, "video");
            return (Criteria) this;
        }

        public Criteria andVideoBetween(String value1, String value2) {
            addCriterion("video between", value1, value2, "video");
            return (Criteria) this;
        }

        public Criteria andVideoNotBetween(String value1, String value2) {
            addCriterion("video not between", value1, value2, "video");
            return (Criteria) this;
        }

        public Criteria andImg1IsNull() {
            addCriterion("img1 is null");
            return (Criteria) this;
        }

        public Criteria andImg1IsNotNull() {
            addCriterion("img1 is not null");
            return (Criteria) this;
        }

        public Criteria andImg1EqualTo(String value) {
            addCriterion("img1 =", value, "img1");
            return (Criteria) this;
        }

        public Criteria andImg1NotEqualTo(String value) {
            addCriterion("img1 <>", value, "img1");
            return (Criteria) this;
        }

        public Criteria andImg1GreaterThan(String value) {
            addCriterion("img1 >", value, "img1");
            return (Criteria) this;
        }

        public Criteria andImg1GreaterThanOrEqualTo(String value) {
            addCriterion("img1 >=", value, "img1");
            return (Criteria) this;
        }

        public Criteria andImg1LessThan(String value) {
            addCriterion("img1 <", value, "img1");
            return (Criteria) this;
        }

        public Criteria andImg1LessThanOrEqualTo(String value) {
            addCriterion("img1 <=", value, "img1");
            return (Criteria) this;
        }

        public Criteria andImg1Like(String value) {
            addCriterion("img1 like", value, "img1");
            return (Criteria) this;
        }

        public Criteria andImg1NotLike(String value) {
            addCriterion("img1 not like", value, "img1");
            return (Criteria) this;
        }

        public Criteria andImg1In(List<String> values) {
            addCriterion("img1 in", values, "img1");
            return (Criteria) this;
        }

        public Criteria andImg1NotIn(List<String> values) {
            addCriterion("img1 not in", values, "img1");
            return (Criteria) this;
        }

        public Criteria andImg1Between(String value1, String value2) {
            addCriterion("img1 between", value1, value2, "img1");
            return (Criteria) this;
        }

        public Criteria andImg1NotBetween(String value1, String value2) {
            addCriterion("img1 not between", value1, value2, "img1");
            return (Criteria) this;
        }

        public Criteria andPublisherIsNull() {
            addCriterion("publisher is null");
            return (Criteria) this;
        }

        public Criteria andPublisherIsNotNull() {
            addCriterion("publisher is not null");
            return (Criteria) this;
        }

        public Criteria andPublisherEqualTo(String value) {
            addCriterion("publisher =", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherNotEqualTo(String value) {
            addCriterion("publisher <>", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherGreaterThan(String value) {
            addCriterion("publisher >", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherGreaterThanOrEqualTo(String value) {
            addCriterion("publisher >=", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherLessThan(String value) {
            addCriterion("publisher <", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherLessThanOrEqualTo(String value) {
            addCriterion("publisher <=", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherLike(String value) {
            addCriterion("publisher like", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherNotLike(String value) {
            addCriterion("publisher not like", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherIn(List<String> values) {
            addCriterion("publisher in", values, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherNotIn(List<String> values) {
            addCriterion("publisher not in", values, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherBetween(String value1, String value2) {
            addCriterion("publisher between", value1, value2, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherNotBetween(String value1, String value2) {
            addCriterion("publisher not between", value1, value2, "publisher");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("creator like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("creator not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorimgIsNull() {
            addCriterion("creatorimg is null");
            return (Criteria) this;
        }

        public Criteria andCreatorimgIsNotNull() {
            addCriterion("creatorimg is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorimgEqualTo(String value) {
            addCriterion("creatorimg =", value, "creatorimg");
            return (Criteria) this;
        }

        public Criteria andCreatorimgNotEqualTo(String value) {
            addCriterion("creatorimg <>", value, "creatorimg");
            return (Criteria) this;
        }

        public Criteria andCreatorimgGreaterThan(String value) {
            addCriterion("creatorimg >", value, "creatorimg");
            return (Criteria) this;
        }

        public Criteria andCreatorimgGreaterThanOrEqualTo(String value) {
            addCriterion("creatorimg >=", value, "creatorimg");
            return (Criteria) this;
        }

        public Criteria andCreatorimgLessThan(String value) {
            addCriterion("creatorimg <", value, "creatorimg");
            return (Criteria) this;
        }

        public Criteria andCreatorimgLessThanOrEqualTo(String value) {
            addCriterion("creatorimg <=", value, "creatorimg");
            return (Criteria) this;
        }

        public Criteria andCreatorimgLike(String value) {
            addCriterion("creatorimg like", value, "creatorimg");
            return (Criteria) this;
        }

        public Criteria andCreatorimgNotLike(String value) {
            addCriterion("creatorimg not like", value, "creatorimg");
            return (Criteria) this;
        }

        public Criteria andCreatorimgIn(List<String> values) {
            addCriterion("creatorimg in", values, "creatorimg");
            return (Criteria) this;
        }

        public Criteria andCreatorimgNotIn(List<String> values) {
            addCriterion("creatorimg not in", values, "creatorimg");
            return (Criteria) this;
        }

        public Criteria andCreatorimgBetween(String value1, String value2) {
            addCriterion("creatorimg between", value1, value2, "creatorimg");
            return (Criteria) this;
        }

        public Criteria andCreatorimgNotBetween(String value1, String value2) {
            addCriterion("creatorimg not between", value1, value2, "creatorimg");
            return (Criteria) this;
        }

        public Criteria andIsdeployIsNull() {
            addCriterion("isdeploy is null");
            return (Criteria) this;
        }

        public Criteria andIsdeployIsNotNull() {
            addCriterion("isdeploy is not null");
            return (Criteria) this;
        }

        public Criteria andIsdeployEqualTo(Integer value) {
            addCriterion("isdeploy =", value, "isdeploy");
            return (Criteria) this;
        }

        public Criteria andIsdeployNotEqualTo(Integer value) {
            addCriterion("isdeploy <>", value, "isdeploy");
            return (Criteria) this;
        }

        public Criteria andIsdeployGreaterThan(Integer value) {
            addCriterion("isdeploy >", value, "isdeploy");
            return (Criteria) this;
        }

        public Criteria andIsdeployGreaterThanOrEqualTo(Integer value) {
            addCriterion("isdeploy >=", value, "isdeploy");
            return (Criteria) this;
        }

        public Criteria andIsdeployLessThan(Integer value) {
            addCriterion("isdeploy <", value, "isdeploy");
            return (Criteria) this;
        }

        public Criteria andIsdeployLessThanOrEqualTo(Integer value) {
            addCriterion("isdeploy <=", value, "isdeploy");
            return (Criteria) this;
        }

        public Criteria andIsdeployIn(List<Integer> values) {
            addCriterion("isdeploy in", values, "isdeploy");
            return (Criteria) this;
        }

        public Criteria andIsdeployNotIn(List<Integer> values) {
            addCriterion("isdeploy not in", values, "isdeploy");
            return (Criteria) this;
        }

        public Criteria andIsdeployBetween(Integer value1, Integer value2) {
            addCriterion("isdeploy between", value1, value2, "isdeploy");
            return (Criteria) this;
        }

        public Criteria andIsdeployNotBetween(Integer value1, Integer value2) {
            addCriterion("isdeploy not between", value1, value2, "isdeploy");
            return (Criteria) this;
        }

        public Criteria andContractaddressIsNull() {
            addCriterion("contractAddress is null");
            return (Criteria) this;
        }

        public Criteria andContractaddressIsNotNull() {
            addCriterion("contractAddress is not null");
            return (Criteria) this;
        }

        public Criteria andContractaddressEqualTo(String value) {
            addCriterion("contractAddress =", value, "contractaddress");
            return (Criteria) this;
        }

        public Criteria andContractaddressNotEqualTo(String value) {
            addCriterion("contractAddress <>", value, "contractaddress");
            return (Criteria) this;
        }

        public Criteria andContractaddressGreaterThan(String value) {
            addCriterion("contractAddress >", value, "contractaddress");
            return (Criteria) this;
        }

        public Criteria andContractaddressGreaterThanOrEqualTo(String value) {
            addCriterion("contractAddress >=", value, "contractaddress");
            return (Criteria) this;
        }

        public Criteria andContractaddressLessThan(String value) {
            addCriterion("contractAddress <", value, "contractaddress");
            return (Criteria) this;
        }

        public Criteria andContractaddressLessThanOrEqualTo(String value) {
            addCriterion("contractAddress <=", value, "contractaddress");
            return (Criteria) this;
        }

        public Criteria andContractaddressLike(String value) {
            addCriterion("contractAddress like", value, "contractaddress");
            return (Criteria) this;
        }

        public Criteria andContractaddressNotLike(String value) {
            addCriterion("contractAddress not like", value, "contractaddress");
            return (Criteria) this;
        }

        public Criteria andContractaddressIn(List<String> values) {
            addCriterion("contractAddress in", values, "contractaddress");
            return (Criteria) this;
        }

        public Criteria andContractaddressNotIn(List<String> values) {
            addCriterion("contractAddress not in", values, "contractaddress");
            return (Criteria) this;
        }

        public Criteria andContractaddressBetween(String value1, String value2) {
            addCriterion("contractAddress between", value1, value2, "contractaddress");
            return (Criteria) this;
        }

        public Criteria andContractaddressNotBetween(String value1, String value2) {
            addCriterion("contractAddress not between", value1, value2, "contractaddress");
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

        public Criteria andStockcIsNull() {
            addCriterion("stockc is null");
            return (Criteria) this;
        }

        public Criteria andStockcIsNotNull() {
            addCriterion("stockc is not null");
            return (Criteria) this;
        }

        public Criteria andStockcEqualTo(Integer value) {
            addCriterion("stockc =", value, "stockc");
            return (Criteria) this;
        }

        public Criteria andStockcNotEqualTo(Integer value) {
            addCriterion("stockc <>", value, "stockc");
            return (Criteria) this;
        }

        public Criteria andStockcGreaterThan(Integer value) {
            addCriterion("stockc >", value, "stockc");
            return (Criteria) this;
        }

        public Criteria andStockcGreaterThanOrEqualTo(Integer value) {
            addCriterion("stockc >=", value, "stockc");
            return (Criteria) this;
        }

        public Criteria andStockcLessThan(Integer value) {
            addCriterion("stockc <", value, "stockc");
            return (Criteria) this;
        }

        public Criteria andStockcLessThanOrEqualTo(Integer value) {
            addCriterion("stockc <=", value, "stockc");
            return (Criteria) this;
        }

        public Criteria andStockcIn(List<Integer> values) {
            addCriterion("stockc in", values, "stockc");
            return (Criteria) this;
        }

        public Criteria andStockcNotIn(List<Integer> values) {
            addCriterion("stockc not in", values, "stockc");
            return (Criteria) this;
        }

        public Criteria andStockcBetween(Integer value1, Integer value2) {
            addCriterion("stockc between", value1, value2, "stockc");
            return (Criteria) this;
        }

        public Criteria andStockcNotBetween(Integer value1, Integer value2) {
            addCriterion("stockc not between", value1, value2, "stockc");
            return (Criteria) this;
        }

        public Criteria andNosetupIsNull() {
            addCriterion("nosetup is null");
            return (Criteria) this;
        }

        public Criteria andNosetupIsNotNull() {
            addCriterion("nosetup is not null");
            return (Criteria) this;
        }

        public Criteria andNosetupEqualTo(String value) {
            addCriterion("nosetup =", value, "nosetup");
            return (Criteria) this;
        }

        public Criteria andNosetupNotEqualTo(String value) {
            addCriterion("nosetup <>", value, "nosetup");
            return (Criteria) this;
        }

        public Criteria andNosetupGreaterThan(String value) {
            addCriterion("nosetup >", value, "nosetup");
            return (Criteria) this;
        }

        public Criteria andNosetupGreaterThanOrEqualTo(String value) {
            addCriterion("nosetup >=", value, "nosetup");
            return (Criteria) this;
        }

        public Criteria andNosetupLessThan(String value) {
            addCriterion("nosetup <", value, "nosetup");
            return (Criteria) this;
        }

        public Criteria andNosetupLessThanOrEqualTo(String value) {
            addCriterion("nosetup <=", value, "nosetup");
            return (Criteria) this;
        }

        public Criteria andNosetupLike(String value) {
            addCriterion("nosetup like", value, "nosetup");
            return (Criteria) this;
        }

        public Criteria andNosetupNotLike(String value) {
            addCriterion("nosetup not like", value, "nosetup");
            return (Criteria) this;
        }

        public Criteria andNosetupIn(List<String> values) {
            addCriterion("nosetup in", values, "nosetup");
            return (Criteria) this;
        }

        public Criteria andNosetupNotIn(List<String> values) {
            addCriterion("nosetup not in", values, "nosetup");
            return (Criteria) this;
        }

        public Criteria andNosetupBetween(String value1, String value2) {
            addCriterion("nosetup between", value1, value2, "nosetup");
            return (Criteria) this;
        }

        public Criteria andNosetupNotBetween(String value1, String value2) {
            addCriterion("nosetup not between", value1, value2, "nosetup");
            return (Criteria) this;
        }

        public Criteria andIntroduceIsNull() {
            addCriterion("introduce is null");
            return (Criteria) this;
        }

        public Criteria andIntroduceIsNotNull() {
            addCriterion("introduce is not null");
            return (Criteria) this;
        }

        public Criteria andIntroduceEqualTo(String value) {
            addCriterion("introduce =", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotEqualTo(String value) {
            addCriterion("introduce <>", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceGreaterThan(String value) {
            addCriterion("introduce >", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceGreaterThanOrEqualTo(String value) {
            addCriterion("introduce >=", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLessThan(String value) {
            addCriterion("introduce <", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLessThanOrEqualTo(String value) {
            addCriterion("introduce <=", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLike(String value) {
            addCriterion("introduce like", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotLike(String value) {
            addCriterion("introduce not like", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceIn(List<String> values) {
            addCriterion("introduce in", values, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotIn(List<String> values) {
            addCriterion("introduce not in", values, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceBetween(String value1, String value2) {
            addCriterion("introduce between", value1, value2, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotBetween(String value1, String value2) {
            addCriterion("introduce not between", value1, value2, "introduce");
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

        public Criteria andClassidEqualTo(Integer value) {
            addCriterion("classid =", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidNotEqualTo(Integer value) {
            addCriterion("classid <>", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidGreaterThan(Integer value) {
            addCriterion("classid >", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidGreaterThanOrEqualTo(Integer value) {
            addCriterion("classid >=", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidLessThan(Integer value) {
            addCriterion("classid <", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidLessThanOrEqualTo(Integer value) {
            addCriterion("classid <=", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidIn(List<Integer> values) {
            addCriterion("classid in", values, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidNotIn(List<Integer> values) {
            addCriterion("classid not in", values, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidBetween(Integer value1, Integer value2) {
            addCriterion("classid between", value1, value2, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidNotBetween(Integer value1, Integer value2) {
            addCriterion("classid not between", value1, value2, "classid");
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

        public Criteria andMustcomidIsNull() {
            addCriterion("mustcomid is null");
            return (Criteria) this;
        }

        public Criteria andMustcomidIsNotNull() {
            addCriterion("mustcomid is not null");
            return (Criteria) this;
        }

        public Criteria andMustcomidEqualTo(Integer value) {
            addCriterion("mustcomid =", value, "mustcomid");
            return (Criteria) this;
        }

        public Criteria andMustcomidNotEqualTo(Integer value) {
            addCriterion("mustcomid <>", value, "mustcomid");
            return (Criteria) this;
        }

        public Criteria andMustcomidGreaterThan(Integer value) {
            addCriterion("mustcomid >", value, "mustcomid");
            return (Criteria) this;
        }

        public Criteria andMustcomidGreaterThanOrEqualTo(Integer value) {
            addCriterion("mustcomid >=", value, "mustcomid");
            return (Criteria) this;
        }

        public Criteria andMustcomidLessThan(Integer value) {
            addCriterion("mustcomid <", value, "mustcomid");
            return (Criteria) this;
        }

        public Criteria andMustcomidLessThanOrEqualTo(Integer value) {
            addCriterion("mustcomid <=", value, "mustcomid");
            return (Criteria) this;
        }

        public Criteria andMustcomidIn(List<Integer> values) {
            addCriterion("mustcomid in", values, "mustcomid");
            return (Criteria) this;
        }

        public Criteria andMustcomidNotIn(List<Integer> values) {
            addCriterion("mustcomid not in", values, "mustcomid");
            return (Criteria) this;
        }

        public Criteria andMustcomidBetween(Integer value1, Integer value2) {
            addCriterion("mustcomid between", value1, value2, "mustcomid");
            return (Criteria) this;
        }

        public Criteria andMustcomidNotBetween(Integer value1, Integer value2) {
            addCriterion("mustcomid not between", value1, value2, "mustcomid");
            return (Criteria) this;
        }

        public Criteria andSelltimeIsNull() {
            addCriterion("selltime is null");
            return (Criteria) this;
        }

        public Criteria andSelltimeIsNotNull() {
            addCriterion("selltime is not null");
            return (Criteria) this;
        }

        public Criteria andSelltimeEqualTo(Integer value) {
            addCriterion("selltime =", value, "selltime");
            return (Criteria) this;
        }

        public Criteria andSelltimeNotEqualTo(Integer value) {
            addCriterion("selltime <>", value, "selltime");
            return (Criteria) this;
        }

        public Criteria andSelltimeGreaterThan(Integer value) {
            addCriterion("selltime >", value, "selltime");
            return (Criteria) this;
        }

        public Criteria andSelltimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("selltime >=", value, "selltime");
            return (Criteria) this;
        }

        public Criteria andSelltimeLessThan(Integer value) {
            addCriterion("selltime <", value, "selltime");
            return (Criteria) this;
        }

        public Criteria andSelltimeLessThanOrEqualTo(Integer value) {
            addCriterion("selltime <=", value, "selltime");
            return (Criteria) this;
        }

        public Criteria andSelltimeIn(List<Integer> values) {
            addCriterion("selltime in", values, "selltime");
            return (Criteria) this;
        }

        public Criteria andSelltimeNotIn(List<Integer> values) {
            addCriterion("selltime not in", values, "selltime");
            return (Criteria) this;
        }

        public Criteria andSelltimeBetween(Integer value1, Integer value2) {
            addCriterion("selltime between", value1, value2, "selltime");
            return (Criteria) this;
        }

        public Criteria andSelltimeNotBetween(Integer value1, Integer value2) {
            addCriterion("selltime not between", value1, value2, "selltime");
            return (Criteria) this;
        }

        public Criteria andSendlocktimeIsNull() {
            addCriterion("sendlocktime is null");
            return (Criteria) this;
        }

        public Criteria andSendlocktimeIsNotNull() {
            addCriterion("sendlocktime is not null");
            return (Criteria) this;
        }

        public Criteria andSendlocktimeEqualTo(Integer value) {
            addCriterion("sendlocktime =", value, "sendlocktime");
            return (Criteria) this;
        }

        public Criteria andSendlocktimeNotEqualTo(Integer value) {
            addCriterion("sendlocktime <>", value, "sendlocktime");
            return (Criteria) this;
        }

        public Criteria andSendlocktimeGreaterThan(Integer value) {
            addCriterion("sendlocktime >", value, "sendlocktime");
            return (Criteria) this;
        }

        public Criteria andSendlocktimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("sendlocktime >=", value, "sendlocktime");
            return (Criteria) this;
        }

        public Criteria andSendlocktimeLessThan(Integer value) {
            addCriterion("sendlocktime <", value, "sendlocktime");
            return (Criteria) this;
        }

        public Criteria andSendlocktimeLessThanOrEqualTo(Integer value) {
            addCriterion("sendlocktime <=", value, "sendlocktime");
            return (Criteria) this;
        }

        public Criteria andSendlocktimeIn(List<Integer> values) {
            addCriterion("sendlocktime in", values, "sendlocktime");
            return (Criteria) this;
        }

        public Criteria andSendlocktimeNotIn(List<Integer> values) {
            addCriterion("sendlocktime not in", values, "sendlocktime");
            return (Criteria) this;
        }

        public Criteria andSendlocktimeBetween(Integer value1, Integer value2) {
            addCriterion("sendlocktime between", value1, value2, "sendlocktime");
            return (Criteria) this;
        }

        public Criteria andSendlocktimeNotBetween(Integer value1, Integer value2) {
            addCriterion("sendlocktime not between", value1, value2, "sendlocktime");
            return (Criteria) this;
        }

        public Criteria andSelltypeIsNull() {
            addCriterion("selltype is null");
            return (Criteria) this;
        }

        public Criteria andSelltypeIsNotNull() {
            addCriterion("selltype is not null");
            return (Criteria) this;
        }

        public Criteria andSelltypeEqualTo(Integer value) {
            addCriterion("selltype =", value, "selltype");
            return (Criteria) this;
        }

        public Criteria andSelltypeNotEqualTo(Integer value) {
            addCriterion("selltype <>", value, "selltype");
            return (Criteria) this;
        }

        public Criteria andSelltypeGreaterThan(Integer value) {
            addCriterion("selltype >", value, "selltype");
            return (Criteria) this;
        }

        public Criteria andSelltypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("selltype >=", value, "selltype");
            return (Criteria) this;
        }

        public Criteria andSelltypeLessThan(Integer value) {
            addCriterion("selltype <", value, "selltype");
            return (Criteria) this;
        }

        public Criteria andSelltypeLessThanOrEqualTo(Integer value) {
            addCriterion("selltype <=", value, "selltype");
            return (Criteria) this;
        }

        public Criteria andSelltypeIn(List<Integer> values) {
            addCriterion("selltype in", values, "selltype");
            return (Criteria) this;
        }

        public Criteria andSelltypeNotIn(List<Integer> values) {
            addCriterion("selltype not in", values, "selltype");
            return (Criteria) this;
        }

        public Criteria andSelltypeBetween(Integer value1, Integer value2) {
            addCriterion("selltype between", value1, value2, "selltype");
            return (Criteria) this;
        }

        public Criteria andSelltypeNotBetween(Integer value1, Integer value2) {
            addCriterion("selltype not between", value1, value2, "selltype");
            return (Criteria) this;
        }

        public Criteria andSendtypeIsNull() {
            addCriterion("sendtype is null");
            return (Criteria) this;
        }

        public Criteria andSendtypeIsNotNull() {
            addCriterion("sendtype is not null");
            return (Criteria) this;
        }

        public Criteria andSendtypeEqualTo(Integer value) {
            addCriterion("sendtype =", value, "sendtype");
            return (Criteria) this;
        }

        public Criteria andSendtypeNotEqualTo(Integer value) {
            addCriterion("sendtype <>", value, "sendtype");
            return (Criteria) this;
        }

        public Criteria andSendtypeGreaterThan(Integer value) {
            addCriterion("sendtype >", value, "sendtype");
            return (Criteria) this;
        }

        public Criteria andSendtypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("sendtype >=", value, "sendtype");
            return (Criteria) this;
        }

        public Criteria andSendtypeLessThan(Integer value) {
            addCriterion("sendtype <", value, "sendtype");
            return (Criteria) this;
        }

        public Criteria andSendtypeLessThanOrEqualTo(Integer value) {
            addCriterion("sendtype <=", value, "sendtype");
            return (Criteria) this;
        }

        public Criteria andSendtypeIn(List<Integer> values) {
            addCriterion("sendtype in", values, "sendtype");
            return (Criteria) this;
        }

        public Criteria andSendtypeNotIn(List<Integer> values) {
            addCriterion("sendtype not in", values, "sendtype");
            return (Criteria) this;
        }

        public Criteria andSendtypeBetween(Integer value1, Integer value2) {
            addCriterion("sendtype between", value1, value2, "sendtype");
            return (Criteria) this;
        }

        public Criteria andSendtypeNotBetween(Integer value1, Integer value2) {
            addCriterion("sendtype not between", value1, value2, "sendtype");
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

        public Criteria andPaytypeEqualTo(String value) {
            addCriterion("paytype =", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeNotEqualTo(String value) {
            addCriterion("paytype <>", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeGreaterThan(String value) {
            addCriterion("paytype >", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeGreaterThanOrEqualTo(String value) {
            addCriterion("paytype >=", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeLessThan(String value) {
            addCriterion("paytype <", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeLessThanOrEqualTo(String value) {
            addCriterion("paytype <=", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeLike(String value) {
            addCriterion("paytype like", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeNotLike(String value) {
            addCriterion("paytype not like", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeIn(List<String> values) {
            addCriterion("paytype in", values, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeNotIn(List<String> values) {
            addCriterion("paytype not in", values, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeBetween(String value1, String value2) {
            addCriterion("paytype between", value1, value2, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeNotBetween(String value1, String value2) {
            addCriterion("paytype not between", value1, value2, "paytype");
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

        public Criteria andBuycardsizeIsNull() {
            addCriterion("buycardsize is null");
            return (Criteria) this;
        }

        public Criteria andBuycardsizeIsNotNull() {
            addCriterion("buycardsize is not null");
            return (Criteria) this;
        }

        public Criteria andBuycardsizeEqualTo(BigDecimal value) {
            addCriterion("buycardsize =", value, "buycardsize");
            return (Criteria) this;
        }

        public Criteria andBuycardsizeNotEqualTo(BigDecimal value) {
            addCriterion("buycardsize <>", value, "buycardsize");
            return (Criteria) this;
        }

        public Criteria andBuycardsizeGreaterThan(BigDecimal value) {
            addCriterion("buycardsize >", value, "buycardsize");
            return (Criteria) this;
        }

        public Criteria andBuycardsizeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("buycardsize >=", value, "buycardsize");
            return (Criteria) this;
        }

        public Criteria andBuycardsizeLessThan(BigDecimal value) {
            addCriterion("buycardsize <", value, "buycardsize");
            return (Criteria) this;
        }

        public Criteria andBuycardsizeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("buycardsize <=", value, "buycardsize");
            return (Criteria) this;
        }

        public Criteria andBuycardsizeIn(List<BigDecimal> values) {
            addCriterion("buycardsize in", values, "buycardsize");
            return (Criteria) this;
        }

        public Criteria andBuycardsizeNotIn(List<BigDecimal> values) {
            addCriterion("buycardsize not in", values, "buycardsize");
            return (Criteria) this;
        }

        public Criteria andBuycardsizeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("buycardsize between", value1, value2, "buycardsize");
            return (Criteria) this;
        }

        public Criteria andBuycardsizeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("buycardsize not between", value1, value2, "buycardsize");
            return (Criteria) this;
        }

        public Criteria andLinkIsNull() {
            addCriterion("link is null");
            return (Criteria) this;
        }

        public Criteria andLinkIsNotNull() {
            addCriterion("link is not null");
            return (Criteria) this;
        }

        public Criteria andLinkEqualTo(String value) {
            addCriterion("link =", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkNotEqualTo(String value) {
            addCriterion("link <>", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkGreaterThan(String value) {
            addCriterion("link >", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkGreaterThanOrEqualTo(String value) {
            addCriterion("link >=", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkLessThan(String value) {
            addCriterion("link <", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkLessThanOrEqualTo(String value) {
            addCriterion("link <=", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkLike(String value) {
            addCriterion("link like", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkNotLike(String value) {
            addCriterion("link not like", value, "link");
            return (Criteria) this;
        }

        public Criteria andLinkIn(List<String> values) {
            addCriterion("link in", values, "link");
            return (Criteria) this;
        }

        public Criteria andLinkNotIn(List<String> values) {
            addCriterion("link not in", values, "link");
            return (Criteria) this;
        }

        public Criteria andLinkBetween(String value1, String value2) {
            addCriterion("link between", value1, value2, "link");
            return (Criteria) this;
        }

        public Criteria andLinkNotBetween(String value1, String value2) {
            addCriterion("link not between", value1, value2, "link");
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

        public Criteria andPublishquantityIsNull() {
            addCriterion("publishQuantity is null");
            return (Criteria) this;
        }

        public Criteria andPublishquantityIsNotNull() {
            addCriterion("publishQuantity is not null");
            return (Criteria) this;
        }

        public Criteria andPublishquantityEqualTo(Integer value) {
            addCriterion("publishQuantity =", value, "publishquantity");
            return (Criteria) this;
        }

        public Criteria andPublishquantityNotEqualTo(Integer value) {
            addCriterion("publishQuantity <>", value, "publishquantity");
            return (Criteria) this;
        }

        public Criteria andPublishquantityGreaterThan(Integer value) {
            addCriterion("publishQuantity >", value, "publishquantity");
            return (Criteria) this;
        }

        public Criteria andPublishquantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("publishQuantity >=", value, "publishquantity");
            return (Criteria) this;
        }

        public Criteria andPublishquantityLessThan(Integer value) {
            addCriterion("publishQuantity <", value, "publishquantity");
            return (Criteria) this;
        }

        public Criteria andPublishquantityLessThanOrEqualTo(Integer value) {
            addCriterion("publishQuantity <=", value, "publishquantity");
            return (Criteria) this;
        }

        public Criteria andPublishquantityIn(List<Integer> values) {
            addCriterion("publishQuantity in", values, "publishquantity");
            return (Criteria) this;
        }

        public Criteria andPublishquantityNotIn(List<Integer> values) {
            addCriterion("publishQuantity not in", values, "publishquantity");
            return (Criteria) this;
        }

        public Criteria andPublishquantityBetween(Integer value1, Integer value2) {
            addCriterion("publishQuantity between", value1, value2, "publishquantity");
            return (Criteria) this;
        }

        public Criteria andPublishquantityNotBetween(Integer value1, Integer value2) {
            addCriterion("publishQuantity not between", value1, value2, "publishquantity");
            return (Criteria) this;
        }

        public Criteria andPublishtimeIsNull() {
            addCriterion("publishTime is null");
            return (Criteria) this;
        }

        public Criteria andPublishtimeIsNotNull() {
            addCriterion("publishTime is not null");
            return (Criteria) this;
        }

        public Criteria andPublishtimeEqualTo(Date value) {
            addCriterion("publishTime =", value, "publishtime");
            return (Criteria) this;
        }

        public Criteria andPublishtimeNotEqualTo(Date value) {
            addCriterion("publishTime <>", value, "publishtime");
            return (Criteria) this;
        }

        public Criteria andPublishtimeGreaterThan(Date value) {
            addCriterion("publishTime >", value, "publishtime");
            return (Criteria) this;
        }

        public Criteria andPublishtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("publishTime >=", value, "publishtime");
            return (Criteria) this;
        }

        public Criteria andPublishtimeLessThan(Date value) {
            addCriterion("publishTime <", value, "publishtime");
            return (Criteria) this;
        }

        public Criteria andPublishtimeLessThanOrEqualTo(Date value) {
            addCriterion("publishTime <=", value, "publishtime");
            return (Criteria) this;
        }

        public Criteria andPublishtimeIn(List<Date> values) {
            addCriterion("publishTime in", values, "publishtime");
            return (Criteria) this;
        }

        public Criteria andPublishtimeNotIn(List<Date> values) {
            addCriterion("publishTime not in", values, "publishtime");
            return (Criteria) this;
        }

        public Criteria andPublishtimeBetween(Date value1, Date value2) {
            addCriterion("publishTime between", value1, value2, "publishtime");
            return (Criteria) this;
        }

        public Criteria andPublishtimeNotBetween(Date value1, Date value2) {
            addCriterion("publishTime not between", value1, value2, "publishtime");
            return (Criteria) this;
        }

        public Criteria andSubtypeIsNull() {
            addCriterion("subtype is null");
            return (Criteria) this;
        }

        public Criteria andSubtypeIsNotNull() {
            addCriterion("subtype is not null");
            return (Criteria) this;
        }

        public Criteria andSubtypeEqualTo(Integer value) {
            addCriterion("subtype =", value, "subtype");
            return (Criteria) this;
        }

        public Criteria andSubtypeNotEqualTo(Integer value) {
            addCriterion("subtype <>", value, "subtype");
            return (Criteria) this;
        }

        public Criteria andSubtypeGreaterThan(Integer value) {
            addCriterion("subtype >", value, "subtype");
            return (Criteria) this;
        }

        public Criteria andSubtypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("subtype >=", value, "subtype");
            return (Criteria) this;
        }

        public Criteria andSubtypeLessThan(Integer value) {
            addCriterion("subtype <", value, "subtype");
            return (Criteria) this;
        }

        public Criteria andSubtypeLessThanOrEqualTo(Integer value) {
            addCriterion("subtype <=", value, "subtype");
            return (Criteria) this;
        }

        public Criteria andSubtypeIn(List<Integer> values) {
            addCriterion("subtype in", values, "subtype");
            return (Criteria) this;
        }

        public Criteria andSubtypeNotIn(List<Integer> values) {
            addCriterion("subtype not in", values, "subtype");
            return (Criteria) this;
        }

        public Criteria andSubtypeBetween(Integer value1, Integer value2) {
            addCriterion("subtype between", value1, value2, "subtype");
            return (Criteria) this;
        }

        public Criteria andSubtypeNotBetween(Integer value1, Integer value2) {
            addCriterion("subtype not between", value1, value2, "subtype");
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

        public Criteria andUseTypeIsNull() {
            addCriterion("use_type is null");
            return (Criteria) this;
        }

        public Criteria andUseTypeIsNotNull() {
            addCriterion("use_type is not null");
            return (Criteria) this;
        }

        public Criteria andUseTypeEqualTo(Integer value) {
            addCriterion("use_type =", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeNotEqualTo(Integer value) {
            addCriterion("use_type <>", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeGreaterThan(Integer value) {
            addCriterion("use_type >", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("use_type >=", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeLessThan(Integer value) {
            addCriterion("use_type <", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeLessThanOrEqualTo(Integer value) {
            addCriterion("use_type <=", value, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeIn(List<Integer> values) {
            addCriterion("use_type in", values, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeNotIn(List<Integer> values) {
            addCriterion("use_type not in", values, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeBetween(Integer value1, Integer value2) {
            addCriterion("use_type between", value1, value2, "useType");
            return (Criteria) this;
        }

        public Criteria andUseTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("use_type not between", value1, value2, "useType");
            return (Criteria) this;
        }

        public Criteria andPointsDaysIsNull() {
            addCriterion("points_days is null");
            return (Criteria) this;
        }

        public Criteria andPointsDaysIsNotNull() {
            addCriterion("points_days is not null");
            return (Criteria) this;
        }

        public Criteria andPointsDaysEqualTo(Integer value) {
            addCriterion("points_days =", value, "pointsDays");
            return (Criteria) this;
        }

        public Criteria andPointsDaysNotEqualTo(Integer value) {
            addCriterion("points_days <>", value, "pointsDays");
            return (Criteria) this;
        }

        public Criteria andPointsDaysGreaterThan(Integer value) {
            addCriterion("points_days >", value, "pointsDays");
            return (Criteria) this;
        }

        public Criteria andPointsDaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("points_days >=", value, "pointsDays");
            return (Criteria) this;
        }

        public Criteria andPointsDaysLessThan(Integer value) {
            addCriterion("points_days <", value, "pointsDays");
            return (Criteria) this;
        }

        public Criteria andPointsDaysLessThanOrEqualTo(Integer value) {
            addCriterion("points_days <=", value, "pointsDays");
            return (Criteria) this;
        }

        public Criteria andPointsDaysIn(List<Integer> values) {
            addCriterion("points_days in", values, "pointsDays");
            return (Criteria) this;
        }

        public Criteria andPointsDaysNotIn(List<Integer> values) {
            addCriterion("points_days not in", values, "pointsDays");
            return (Criteria) this;
        }

        public Criteria andPointsDaysBetween(Integer value1, Integer value2) {
            addCriterion("points_days between", value1, value2, "pointsDays");
            return (Criteria) this;
        }

        public Criteria andPointsDaysNotBetween(Integer value1, Integer value2) {
            addCriterion("points_days not between", value1, value2, "pointsDays");
            return (Criteria) this;
        }

        public Criteria andPointsNumIsNull() {
            addCriterion("points_num is null");
            return (Criteria) this;
        }

        public Criteria andPointsNumIsNotNull() {
            addCriterion("points_num is not null");
            return (Criteria) this;
        }

        public Criteria andPointsNumEqualTo(Integer value) {
            addCriterion("points_num =", value, "pointsNum");
            return (Criteria) this;
        }

        public Criteria andPointsNumNotEqualTo(Integer value) {
            addCriterion("points_num <>", value, "pointsNum");
            return (Criteria) this;
        }

        public Criteria andPointsNumGreaterThan(Integer value) {
            addCriterion("points_num >", value, "pointsNum");
            return (Criteria) this;
        }

        public Criteria andPointsNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("points_num >=", value, "pointsNum");
            return (Criteria) this;
        }

        public Criteria andPointsNumLessThan(Integer value) {
            addCriterion("points_num <", value, "pointsNum");
            return (Criteria) this;
        }

        public Criteria andPointsNumLessThanOrEqualTo(Integer value) {
            addCriterion("points_num <=", value, "pointsNum");
            return (Criteria) this;
        }

        public Criteria andPointsNumIn(List<Integer> values) {
            addCriterion("points_num in", values, "pointsNum");
            return (Criteria) this;
        }

        public Criteria andPointsNumNotIn(List<Integer> values) {
            addCriterion("points_num not in", values, "pointsNum");
            return (Criteria) this;
        }

        public Criteria andPointsNumBetween(Integer value1, Integer value2) {
            addCriterion("points_num between", value1, value2, "pointsNum");
            return (Criteria) this;
        }

        public Criteria andPointsNumNotBetween(Integer value1, Integer value2) {
            addCriterion("points_num not between", value1, value2, "pointsNum");
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

        public Criteria andWordLimitIsNull() {
            addCriterion("word_limit is null");
            return (Criteria) this;
        }

        public Criteria andWordLimitIsNotNull() {
            addCriterion("word_limit is not null");
            return (Criteria) this;
        }

        public Criteria andWordLimitEqualTo(Integer value) {
            addCriterion("word_limit =", value, "wordLimit");
            return (Criteria) this;
        }

        public Criteria andWordLimitNotEqualTo(Integer value) {
            addCriterion("word_limit <>", value, "wordLimit");
            return (Criteria) this;
        }

        public Criteria andWordLimitGreaterThan(Integer value) {
            addCriterion("word_limit >", value, "wordLimit");
            return (Criteria) this;
        }

        public Criteria andWordLimitGreaterThanOrEqualTo(Integer value) {
            addCriterion("word_limit >=", value, "wordLimit");
            return (Criteria) this;
        }

        public Criteria andWordLimitLessThan(Integer value) {
            addCriterion("word_limit <", value, "wordLimit");
            return (Criteria) this;
        }

        public Criteria andWordLimitLessThanOrEqualTo(Integer value) {
            addCriterion("word_limit <=", value, "wordLimit");
            return (Criteria) this;
        }

        public Criteria andWordLimitIn(List<Integer> values) {
            addCriterion("word_limit in", values, "wordLimit");
            return (Criteria) this;
        }

        public Criteria andWordLimitNotIn(List<Integer> values) {
            addCriterion("word_limit not in", values, "wordLimit");
            return (Criteria) this;
        }

        public Criteria andWordLimitBetween(Integer value1, Integer value2) {
            addCriterion("word_limit between", value1, value2, "wordLimit");
            return (Criteria) this;
        }

        public Criteria andWordLimitNotBetween(Integer value1, Integer value2) {
            addCriterion("word_limit not between", value1, value2, "wordLimit");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * collection 
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