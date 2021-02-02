package com.oliver.completableFuture.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FsTeacherExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public FsTeacherExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTeacher_nameIsNull() {
            addCriterion("teacher_name is null");
            return (Criteria) this;
        }

        public Criteria andTeacher_nameIsNotNull() {
            addCriterion("teacher_name is not null");
            return (Criteria) this;
        }

        public Criteria andTeacher_nameEqualTo(String value) {
            addCriterion("teacher_name =", value, "teacher_name");
            return (Criteria) this;
        }

        public Criteria andTeacher_nameNotEqualTo(String value) {
            addCriterion("teacher_name <>", value, "teacher_name");
            return (Criteria) this;
        }

        public Criteria andTeacher_nameGreaterThan(String value) {
            addCriterion("teacher_name >", value, "teacher_name");
            return (Criteria) this;
        }

        public Criteria andTeacher_nameGreaterThanOrEqualTo(String value) {
            addCriterion("teacher_name >=", value, "teacher_name");
            return (Criteria) this;
        }

        public Criteria andTeacher_nameLessThan(String value) {
            addCriterion("teacher_name <", value, "teacher_name");
            return (Criteria) this;
        }

        public Criteria andTeacher_nameLessThanOrEqualTo(String value) {
            addCriterion("teacher_name <=", value, "teacher_name");
            return (Criteria) this;
        }

        public Criteria andTeacher_nameLike(String value) {
            addCriterion("teacher_name like", value, "teacher_name");
            return (Criteria) this;
        }

        public Criteria andTeacher_nameNotLike(String value) {
            addCriterion("teacher_name not like", value, "teacher_name");
            return (Criteria) this;
        }

        public Criteria andTeacher_nameIn(List<String> values) {
            addCriterion("teacher_name in", values, "teacher_name");
            return (Criteria) this;
        }

        public Criteria andTeacher_nameNotIn(List<String> values) {
            addCriterion("teacher_name not in", values, "teacher_name");
            return (Criteria) this;
        }

        public Criteria andTeacher_nameBetween(String value1, String value2) {
            addCriterion("teacher_name between", value1, value2, "teacher_name");
            return (Criteria) this;
        }

        public Criteria andTeacher_nameNotBetween(String value1, String value2) {
            addCriterion("teacher_name not between", value1, value2, "teacher_name");
            return (Criteria) this;
        }

        public Criteria andPhone_noIsNull() {
            addCriterion("phone_no is null");
            return (Criteria) this;
        }

        public Criteria andPhone_noIsNotNull() {
            addCriterion("phone_no is not null");
            return (Criteria) this;
        }

        public Criteria andPhone_noEqualTo(String value) {
            addCriterion("phone_no =", value, "phone_no");
            return (Criteria) this;
        }

        public Criteria andPhone_noNotEqualTo(String value) {
            addCriterion("phone_no <>", value, "phone_no");
            return (Criteria) this;
        }

        public Criteria andPhone_noGreaterThan(String value) {
            addCriterion("phone_no >", value, "phone_no");
            return (Criteria) this;
        }

        public Criteria andPhone_noGreaterThanOrEqualTo(String value) {
            addCriterion("phone_no >=", value, "phone_no");
            return (Criteria) this;
        }

        public Criteria andPhone_noLessThan(String value) {
            addCriterion("phone_no <", value, "phone_no");
            return (Criteria) this;
        }

        public Criteria andPhone_noLessThanOrEqualTo(String value) {
            addCriterion("phone_no <=", value, "phone_no");
            return (Criteria) this;
        }

        public Criteria andPhone_noLike(String value) {
            addCriterion("phone_no like", value, "phone_no");
            return (Criteria) this;
        }

        public Criteria andPhone_noNotLike(String value) {
            addCriterion("phone_no not like", value, "phone_no");
            return (Criteria) this;
        }

        public Criteria andPhone_noIn(List<String> values) {
            addCriterion("phone_no in", values, "phone_no");
            return (Criteria) this;
        }

        public Criteria andPhone_noNotIn(List<String> values) {
            addCriterion("phone_no not in", values, "phone_no");
            return (Criteria) this;
        }

        public Criteria andPhone_noBetween(String value1, String value2) {
            addCriterion("phone_no between", value1, value2, "phone_no");
            return (Criteria) this;
        }

        public Criteria andPhone_noNotBetween(String value1, String value2) {
            addCriterion("phone_no not between", value1, value2, "phone_no");
            return (Criteria) this;
        }

        public Criteria andPositionIsNull() {
            addCriterion("`position` is null");
            return (Criteria) this;
        }

        public Criteria andPositionIsNotNull() {
            addCriterion("`position` is not null");
            return (Criteria) this;
        }

        public Criteria andPositionEqualTo(Byte value) {
            addCriterion("`position` =", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotEqualTo(Byte value) {
            addCriterion("`position` <>", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThan(Byte value) {
            addCriterion("`position` >", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThanOrEqualTo(Byte value) {
            addCriterion("`position` >=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThan(Byte value) {
            addCriterion("`position` <", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThanOrEqualTo(Byte value) {
            addCriterion("`position` <=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionIn(List<Byte> values) {
            addCriterion("`position` in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotIn(List<Byte> values) {
            addCriterion("`position` not in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionBetween(Byte value1, Byte value2) {
            addCriterion("`position` between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotBetween(Byte value1, Byte value2) {
            addCriterion("`position` not between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andExpertiseIsNull() {
            addCriterion("expertise is null");
            return (Criteria) this;
        }

        public Criteria andExpertiseIsNotNull() {
            addCriterion("expertise is not null");
            return (Criteria) this;
        }

        public Criteria andExpertiseEqualTo(String value) {
            addCriterion("expertise =", value, "expertise");
            return (Criteria) this;
        }

        public Criteria andExpertiseNotEqualTo(String value) {
            addCriterion("expertise <>", value, "expertise");
            return (Criteria) this;
        }

        public Criteria andExpertiseGreaterThan(String value) {
            addCriterion("expertise >", value, "expertise");
            return (Criteria) this;
        }

        public Criteria andExpertiseGreaterThanOrEqualTo(String value) {
            addCriterion("expertise >=", value, "expertise");
            return (Criteria) this;
        }

        public Criteria andExpertiseLessThan(String value) {
            addCriterion("expertise <", value, "expertise");
            return (Criteria) this;
        }

        public Criteria andExpertiseLessThanOrEqualTo(String value) {
            addCriterion("expertise <=", value, "expertise");
            return (Criteria) this;
        }

        public Criteria andExpertiseLike(String value) {
            addCriterion("expertise like", value, "expertise");
            return (Criteria) this;
        }

        public Criteria andExpertiseNotLike(String value) {
            addCriterion("expertise not like", value, "expertise");
            return (Criteria) this;
        }

        public Criteria andExpertiseIn(List<String> values) {
            addCriterion("expertise in", values, "expertise");
            return (Criteria) this;
        }

        public Criteria andExpertiseNotIn(List<String> values) {
            addCriterion("expertise not in", values, "expertise");
            return (Criteria) this;
        }

        public Criteria andExpertiseBetween(String value1, String value2) {
            addCriterion("expertise between", value1, value2, "expertise");
            return (Criteria) this;
        }

        public Criteria andExpertiseNotBetween(String value1, String value2) {
            addCriterion("expertise not between", value1, value2, "expertise");
            return (Criteria) this;
        }

        public Criteria andEntry_timeIsNull() {
            addCriterion("entry_time is null");
            return (Criteria) this;
        }

        public Criteria andEntry_timeIsNotNull() {
            addCriterion("entry_time is not null");
            return (Criteria) this;
        }

        public Criteria andEntry_timeEqualTo(LocalDateTime value) {
            addCriterion("entry_time =", value, "entry_time");
            return (Criteria) this;
        }

        public Criteria andEntry_timeNotEqualTo(LocalDateTime value) {
            addCriterion("entry_time <>", value, "entry_time");
            return (Criteria) this;
        }

        public Criteria andEntry_timeGreaterThan(LocalDateTime value) {
            addCriterion("entry_time >", value, "entry_time");
            return (Criteria) this;
        }

        public Criteria andEntry_timeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("entry_time >=", value, "entry_time");
            return (Criteria) this;
        }

        public Criteria andEntry_timeLessThan(LocalDateTime value) {
            addCriterion("entry_time <", value, "entry_time");
            return (Criteria) this;
        }

        public Criteria andEntry_timeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("entry_time <=", value, "entry_time");
            return (Criteria) this;
        }

        public Criteria andEntry_timeIn(List<LocalDateTime> values) {
            addCriterion("entry_time in", values, "entry_time");
            return (Criteria) this;
        }

        public Criteria andEntry_timeNotIn(List<LocalDateTime> values) {
            addCriterion("entry_time not in", values, "entry_time");
            return (Criteria) this;
        }

        public Criteria andEntry_timeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("entry_time between", value1, value2, "entry_time");
            return (Criteria) this;
        }

        public Criteria andEntry_timeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("entry_time not between", value1, value2, "entry_time");
            return (Criteria) this;
        }

        public Criteria andTeaching_ageIsNull() {
            addCriterion("teaching_age is null");
            return (Criteria) this;
        }

        public Criteria andTeaching_ageIsNotNull() {
            addCriterion("teaching_age is not null");
            return (Criteria) this;
        }

        public Criteria andTeaching_ageEqualTo(Integer value) {
            addCriterion("teaching_age =", value, "teaching_age");
            return (Criteria) this;
        }

        public Criteria andTeaching_ageNotEqualTo(Integer value) {
            addCriterion("teaching_age <>", value, "teaching_age");
            return (Criteria) this;
        }

        public Criteria andTeaching_ageGreaterThan(Integer value) {
            addCriterion("teaching_age >", value, "teaching_age");
            return (Criteria) this;
        }

        public Criteria andTeaching_ageGreaterThanOrEqualTo(Integer value) {
            addCriterion("teaching_age >=", value, "teaching_age");
            return (Criteria) this;
        }

        public Criteria andTeaching_ageLessThan(Integer value) {
            addCriterion("teaching_age <", value, "teaching_age");
            return (Criteria) this;
        }

        public Criteria andTeaching_ageLessThanOrEqualTo(Integer value) {
            addCriterion("teaching_age <=", value, "teaching_age");
            return (Criteria) this;
        }

        public Criteria andTeaching_ageIn(List<Integer> values) {
            addCriterion("teaching_age in", values, "teaching_age");
            return (Criteria) this;
        }

        public Criteria andTeaching_ageNotIn(List<Integer> values) {
            addCriterion("teaching_age not in", values, "teaching_age");
            return (Criteria) this;
        }

        public Criteria andTeaching_ageBetween(Integer value1, Integer value2) {
            addCriterion("teaching_age between", value1, value2, "teaching_age");
            return (Criteria) this;
        }

        public Criteria andTeaching_ageNotBetween(Integer value1, Integer value2) {
            addCriterion("teaching_age not between", value1, value2, "teaching_age");
            return (Criteria) this;
        }

        public Criteria andLast_update_timeIsNull() {
            addCriterion("last_update_time is null");
            return (Criteria) this;
        }

        public Criteria andLast_update_timeIsNotNull() {
            addCriterion("last_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andLast_update_timeEqualTo(LocalDateTime value) {
            addCriterion("last_update_time =", value, "last_update_time");
            return (Criteria) this;
        }

        public Criteria andLast_update_timeNotEqualTo(LocalDateTime value) {
            addCriterion("last_update_time <>", value, "last_update_time");
            return (Criteria) this;
        }

        public Criteria andLast_update_timeGreaterThan(LocalDateTime value) {
            addCriterion("last_update_time >", value, "last_update_time");
            return (Criteria) this;
        }

        public Criteria andLast_update_timeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("last_update_time >=", value, "last_update_time");
            return (Criteria) this;
        }

        public Criteria andLast_update_timeLessThan(LocalDateTime value) {
            addCriterion("last_update_time <", value, "last_update_time");
            return (Criteria) this;
        }

        public Criteria andLast_update_timeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("last_update_time <=", value, "last_update_time");
            return (Criteria) this;
        }

        public Criteria andLast_update_timeIn(List<LocalDateTime> values) {
            addCriterion("last_update_time in", values, "last_update_time");
            return (Criteria) this;
        }

        public Criteria andLast_update_timeNotIn(List<LocalDateTime> values) {
            addCriterion("last_update_time not in", values, "last_update_time");
            return (Criteria) this;
        }

        public Criteria andLast_update_timeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("last_update_time between", value1, value2, "last_update_time");
            return (Criteria) this;
        }

        public Criteria andLast_update_timeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("last_update_time not between", value1, value2, "last_update_time");
            return (Criteria) this;
        }

        public Criteria andAdd_timeIsNull() {
            addCriterion("add_time is null");
            return (Criteria) this;
        }

        public Criteria andAdd_timeIsNotNull() {
            addCriterion("add_time is not null");
            return (Criteria) this;
        }

        public Criteria andAdd_timeEqualTo(LocalDateTime value) {
            addCriterion("add_time =", value, "add_time");
            return (Criteria) this;
        }

        public Criteria andAdd_timeNotEqualTo(LocalDateTime value) {
            addCriterion("add_time <>", value, "add_time");
            return (Criteria) this;
        }

        public Criteria andAdd_timeGreaterThan(LocalDateTime value) {
            addCriterion("add_time >", value, "add_time");
            return (Criteria) this;
        }

        public Criteria andAdd_timeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("add_time >=", value, "add_time");
            return (Criteria) this;
        }

        public Criteria andAdd_timeLessThan(LocalDateTime value) {
            addCriterion("add_time <", value, "add_time");
            return (Criteria) this;
        }

        public Criteria andAdd_timeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("add_time <=", value, "add_time");
            return (Criteria) this;
        }

        public Criteria andAdd_timeIn(List<LocalDateTime> values) {
            addCriterion("add_time in", values, "add_time");
            return (Criteria) this;
        }

        public Criteria andAdd_timeNotIn(List<LocalDateTime> values) {
            addCriterion("add_time not in", values, "add_time");
            return (Criteria) this;
        }

        public Criteria andAdd_timeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("add_time between", value1, value2, "add_time");
            return (Criteria) this;
        }

        public Criteria andAdd_timeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("add_time not between", value1, value2, "add_time");
            return (Criteria) this;
        }

        public Criteria andIs_delIsNull() {
            addCriterion("is_del is null");
            return (Criteria) this;
        }

        public Criteria andIs_delIsNotNull() {
            addCriterion("is_del is not null");
            return (Criteria) this;
        }

        public Criteria andIs_delEqualTo(Byte value) {
            addCriterion("is_del =", value, "is_del");
            return (Criteria) this;
        }

        public Criteria andIs_delNotEqualTo(Byte value) {
            addCriterion("is_del <>", value, "is_del");
            return (Criteria) this;
        }

        public Criteria andIs_delGreaterThan(Byte value) {
            addCriterion("is_del >", value, "is_del");
            return (Criteria) this;
        }

        public Criteria andIs_delGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_del >=", value, "is_del");
            return (Criteria) this;
        }

        public Criteria andIs_delLessThan(Byte value) {
            addCriterion("is_del <", value, "is_del");
            return (Criteria) this;
        }

        public Criteria andIs_delLessThanOrEqualTo(Byte value) {
            addCriterion("is_del <=", value, "is_del");
            return (Criteria) this;
        }

        public Criteria andIs_delIn(List<Byte> values) {
            addCriterion("is_del in", values, "is_del");
            return (Criteria) this;
        }

        public Criteria andIs_delNotIn(List<Byte> values) {
            addCriterion("is_del not in", values, "is_del");
            return (Criteria) this;
        }

        public Criteria andIs_delBetween(Byte value1, Byte value2) {
            addCriterion("is_del between", value1, value2, "is_del");
            return (Criteria) this;
        }

        public Criteria andIs_delNotBetween(Byte value1, Byte value2) {
            addCriterion("is_del not between", value1, value2, "is_del");
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