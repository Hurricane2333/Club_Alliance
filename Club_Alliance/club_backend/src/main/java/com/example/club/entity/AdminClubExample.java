package com.example.club.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminClubExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public AdminClubExample() {
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
    }

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

        public Criteria andClubIdIsNull() {
            addCriterion("club_id is null");
            return (Criteria) this;
        }

        public Criteria andClubIdIsNotNull() {
            addCriterion("club_id is not null");
            return (Criteria) this;
        }

        public Criteria andClubIdEqualTo(Integer value) {
            addCriterion("club_id =", value, "clubId");
            return (Criteria) this;
        }

        public Criteria andClubIdNotEqualTo(Integer value) {
            addCriterion("club_id <>", value, "clubId");
            return (Criteria) this;
        }

        public Criteria andClubIdGreaterThan(Integer value) {
            addCriterion("club_id >", value, "clubId");
            return (Criteria) this;
        }

        public Criteria andClubIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("club_id >=", value, "clubId");
            return (Criteria) this;
        }

        public Criteria andClubIdLessThan(Integer value) {
            addCriterion("club_id <", value, "clubId");
            return (Criteria) this;
        }

        public Criteria andClubIdLessThanOrEqualTo(Integer value) {
            addCriterion("club_id <=", value, "clubId");
            return (Criteria) this;
        }

        public Criteria andClubIdIn(List<Integer> values) {
            addCriterion("club_id in", values, "clubId");
            return (Criteria) this;
        }

        public Criteria andClubIdNotIn(List<Integer> values) {
            addCriterion("club_id not in", values, "clubId");
            return (Criteria) this;
        }

        public Criteria andClubIdBetween(Integer value1, Integer value2) {
            addCriterion("club_id between", value1, value2, "clubId");
            return (Criteria) this;
        }

        public Criteria andClubIdNotBetween(Integer value1, Integer value2) {
            addCriterion("club_id not between", value1, value2, "clubId");
            return (Criteria) this;
        }

        public Criteria andClubNameIsNull() {
            addCriterion("club_name is null");
            return (Criteria) this;
        }

        public Criteria andClubNameIsNotNull() {
            addCriterion("club_name is not null");
            return (Criteria) this;
        }

        public Criteria andClubNameEqualTo(String value) {
            addCriterion("club_name =", value, "clubName");
            return (Criteria) this;
        }

        public Criteria andClubNameNotEqualTo(String value) {
            addCriterion("club_name <>", value, "clubName");
            return (Criteria) this;
        }

        public Criteria andClubNameGreaterThan(String value) {
            addCriterion("club_name >", value, "clubName");
            return (Criteria) this;
        }

        public Criteria andClubNameGreaterThanOrEqualTo(String value) {
            addCriterion("club_name >=", value, "clubName");
            return (Criteria) this;
        }

        public Criteria andClubNameLessThan(String value) {
            addCriterion("club_name <", value, "clubName");
            return (Criteria) this;
        }

        public Criteria andClubNameLessThanOrEqualTo(String value) {
            addCriterion("club_name <=", value, "clubName");
            return (Criteria) this;
        }

        public Criteria andClubNameLike(String value) {
            addCriterion("club_name like", value, "clubName");
            return (Criteria) this;
        }

        public Criteria andClubNameNotLike(String value) {
            addCriterion("club_name not like", value, "clubName");
            return (Criteria) this;
        }

        public Criteria andClubNameIn(List<String> values) {
            addCriterion("club_name in", values, "clubName");
            return (Criteria) this;
        }

        public Criteria andClubNameNotIn(List<String> values) {
            addCriterion("club_name not in", values, "clubName");
            return (Criteria) this;
        }

        public Criteria andClubNameBetween(String value1, String value2) {
            addCriterion("club_name between", value1, value2, "clubName");
            return (Criteria) this;
        }

        public Criteria andClubNameNotBetween(String value1, String value2) {
            addCriterion("club_name not between", value1, value2, "clubName");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNull() {
            addCriterion("category is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNotNull() {
            addCriterion("category is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryEqualTo(String value) {
            addCriterion("category =", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotEqualTo(String value) {
            addCriterion("category <>", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThan(String value) {
            addCriterion("category >", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("category >=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThan(String value) {
            addCriterion("category <", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThanOrEqualTo(String value) {
            addCriterion("category <=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLike(String value) {
            addCriterion("category like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotLike(String value) {
            addCriterion("category not like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryIn(List<String> values) {
            addCriterion("category in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotIn(List<String> values) {
            addCriterion("category not in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryBetween(String value1, String value2) {
            addCriterion("category between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotBetween(String value1, String value2) {
            addCriterion("category not between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andIconIsNull() {
            addCriterion("icon is null");
            return (Criteria) this;
        }

        public Criteria andIconIsNotNull() {
            addCriterion("icon is not null");
            return (Criteria) this;
        }

        public Criteria andIconEqualTo(String value) {
            addCriterion("icon =", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotEqualTo(String value) {
            addCriterion("icon <>", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconGreaterThan(String value) {
            addCriterion("icon >", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconGreaterThanOrEqualTo(String value) {
            addCriterion("icon >=", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLessThan(String value) {
            addCriterion("icon <", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLessThanOrEqualTo(String value) {
            addCriterion("icon <=", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLike(String value) {
            addCriterion("icon like", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotLike(String value) {
            addCriterion("icon not like", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconIn(List<String> values) {
            addCriterion("icon in", values, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotIn(List<String> values) {
            addCriterion("icon not in", values, "icon");
            return (Criteria) this;
        }

        public Criteria andIconBetween(String value1, String value2) {
            addCriterion("icon between", value1, value2, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotBetween(String value1, String value2) {
            addCriterion("icon not between", value1, value2, "icon");
            return (Criteria) this;
        }

        public Criteria andPresidentIdIsNull() {
            addCriterion("president_id is null");
            return (Criteria) this;
        }

        public Criteria andPresidentIdIsNotNull() {
            addCriterion("president_id is not null");
            return (Criteria) this;
        }

        public Criteria andPresidentIdEqualTo(Integer value) {
            addCriterion("president_id =", value, "presidentId");
            return (Criteria) this;
        }

        public Criteria andPresidentIdNotEqualTo(Integer value) {
            addCriterion("president_id <>", value, "presidentId");
            return (Criteria) this;
        }

        public Criteria andPresidentIdGreaterThan(Integer value) {
            addCriterion("president_id >", value, "presidentId");
            return (Criteria) this;
        }

        public Criteria andPresidentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("president_id >=", value, "presidentId");
            return (Criteria) this;
        }

        public Criteria andPresidentIdLessThan(Integer value) {
            addCriterion("president_id <", value, "presidentId");
            return (Criteria) this;
        }

        public Criteria andPresidentIdLessThanOrEqualTo(Integer value) {
            addCriterion("president_id <=", value, "presidentId");
            return (Criteria) this;
        }

        public Criteria andPresidentIdIn(List<Integer> values) {
            addCriterion("president_id in", values, "presidentId");
            return (Criteria) this;
        }

        public Criteria andPresidentIdNotIn(List<Integer> values) {
            addCriterion("president_id not in", values, "presidentId");
            return (Criteria) this;
        }

        public Criteria andPresidentIdBetween(Integer value1, Integer value2) {
            addCriterion("president_id between", value1, value2, "presidentId");
            return (Criteria) this;
        }

        public Criteria andPresidentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("president_id not between", value1, value2, "presidentId");
            return (Criteria) this;
        }

        public Criteria andRequirementsIsNull() {
            addCriterion("requirements is null");
            return (Criteria) this;
        }

        public Criteria andRequirementsIsNotNull() {
            addCriterion("requirements is not null");
            return (Criteria) this;
        }

        public Criteria andRequirementsEqualTo(String value) {
            addCriterion("requirements =", value, "requirements");
            return (Criteria) this;
        }

        public Criteria andRequirementsNotEqualTo(String value) {
            addCriterion("requirements <>", value, "requirements");
            return (Criteria) this;
        }

        public Criteria andRequirementsGreaterThan(String value) {
            addCriterion("requirements >", value, "requirements");
            return (Criteria) this;
        }

        public Criteria andRequirementsGreaterThanOrEqualTo(String value) {
            addCriterion("requirements >=", value, "requirements");
            return (Criteria) this;
        }

        public Criteria andRequirementsLessThan(String value) {
            addCriterion("requirements <", value, "requirements");
            return (Criteria) this;
        }

        public Criteria andRequirementsLessThanOrEqualTo(String value) {
            addCriterion("requirements <=", value, "requirements");
            return (Criteria) this;
        }

        public Criteria andRequirementsLike(String value) {
            addCriterion("requirements like", value, "requirements");
            return (Criteria) this;
        }

        public Criteria andRequirementsNotLike(String value) {
            addCriterion("requirements not like", value, "requirements");
            return (Criteria) this;
        }

        public Criteria andRequirementsIn(List<String> values) {
            addCriterion("requirements in", values, "requirements");
            return (Criteria) this;
        }

        public Criteria andRequirementsNotIn(List<String> values) {
            addCriterion("requirements not in", values, "requirements");
            return (Criteria) this;
        }

        public Criteria andRequirementsBetween(String value1, String value2) {
            addCriterion("requirements between", value1, value2, "requirements");
            return (Criteria) this;
        }

        public Criteria andRequirementsNotBetween(String value1, String value2) {
            addCriterion("requirements not between", value1, value2, "requirements");
            return (Criteria) this;
        }

        public Criteria andFavoriteCountIsNull() {
            addCriterion("favorite_count is null");
            return (Criteria) this;
        }

        public Criteria andFavoriteCountIsNotNull() {
            addCriterion("favorite_count is not null");
            return (Criteria) this;
        }

        public Criteria andFavoriteCountEqualTo(Integer value) {
            addCriterion("favorite_count =", value, "favoriteCount");
            return (Criteria) this;
        }

        public Criteria andFavoriteCountNotEqualTo(Integer value) {
            addCriterion("favorite_count <>", value, "favoriteCount");
            return (Criteria) this;
        }

        public Criteria andFavoriteCountGreaterThan(Integer value) {
            addCriterion("favorite_count >", value, "favoriteCount");
            return (Criteria) this;
        }

        public Criteria andFavoriteCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("favorite_count >=", value, "favoriteCount");
            return (Criteria) this;
        }

        public Criteria andFavoriteCountLessThan(Integer value) {
            addCriterion("favorite_count <", value, "favoriteCount");
            return (Criteria) this;
        }

        public Criteria andFavoriteCountLessThanOrEqualTo(Integer value) {
            addCriterion("favorite_count <=", value, "favoriteCount");
            return (Criteria) this;
        }

        public Criteria andFavoriteCountIn(List<Integer> values) {
            addCriterion("favorite_count in", values, "favoriteCount");
            return (Criteria) this;
        }

        public Criteria andFavoriteCountNotIn(List<Integer> values) {
            addCriterion("favorite_count not in", values, "favoriteCount");
            return (Criteria) this;
        }

        public Criteria andFavoriteCountBetween(Integer value1, Integer value2) {
            addCriterion("favorite_count between", value1, value2, "favoriteCount");
            return (Criteria) this;
        }

        public Criteria andFavoriteCountNotBetween(Integer value1, Integer value2) {
            addCriterion("favorite_count not between", value1, value2, "favoriteCount");
            return (Criteria) this;
        }

        public Criteria andCurrentMembersIsNull() {
            addCriterion("current_members is null");
            return (Criteria) this;
        }

        public Criteria andCurrentMembersIsNotNull() {
            addCriterion("current_members is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentMembersEqualTo(Integer value) {
            addCriterion("current_members =", value, "currentMembers");
            return (Criteria) this;
        }

        public Criteria andCurrentMembersNotEqualTo(Integer value) {
            addCriterion("current_members <>", value, "currentMembers");
            return (Criteria) this;
        }

        public Criteria andCurrentMembersGreaterThan(Integer value) {
            addCriterion("current_members >", value, "currentMembers");
            return (Criteria) this;
        }

        public Criteria andCurrentMembersGreaterThanOrEqualTo(Integer value) {
            addCriterion("current_members >=", value, "currentMembers");
            return (Criteria) this;
        }

        public Criteria andCurrentMembersLessThan(Integer value) {
            addCriterion("current_members <", value, "currentMembers");
            return (Criteria) this;
        }

        public Criteria andCurrentMembersLessThanOrEqualTo(Integer value) {
            addCriterion("current_members <=", value, "currentMembers");
            return (Criteria) this;
        }

        public Criteria andCurrentMembersIn(List<Integer> values) {
            addCriterion("current_members in", values, "currentMembers");
            return (Criteria) this;
        }

        public Criteria andCurrentMembersNotIn(List<Integer> values) {
            addCriterion("current_members not in", values, "currentMembers");
            return (Criteria) this;
        }

        public Criteria andCurrentMembersBetween(Integer value1, Integer value2) {
            addCriterion("current_members between", value1, value2, "currentMembers");
            return (Criteria) this;
        }

        public Criteria andCurrentMembersNotBetween(Integer value1, Integer value2) {
            addCriterion("current_members not between", value1, value2, "currentMembers");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("`status` like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("`status` not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("`status` not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNull() {
            addCriterion("created_at is null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNotNull() {
            addCriterion("created_at is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtEqualTo(Date value) {
            addCriterion("created_at =", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(Date value) {
            addCriterion("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(Date value) {
            addCriterion("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(Date value) {
            addCriterion("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIn(List<Date> values) {
            addCriterion("created_at in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotIn(List<Date> values) {
            addCriterion("created_at not in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtBetween(Date value1, Date value2) {
            addCriterion("created_at between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotBetween(Date value1, Date value2) {
            addCriterion("created_at not between", value1, value2, "createdAt");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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
