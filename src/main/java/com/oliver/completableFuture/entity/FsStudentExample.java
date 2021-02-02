package com.oliver.completableFuture.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FsStudentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public FsStudentExample() {
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

        public Criteria andStudent_nameIsNull() {
            addCriterion("student_name is null");
            return (Criteria) this;
        }

        public Criteria andStudent_nameIsNotNull() {
            addCriterion("student_name is not null");
            return (Criteria) this;
        }

        public Criteria andStudent_nameEqualTo(String value) {
            addCriterion("student_name =", value, "student_name");
            return (Criteria) this;
        }

        public Criteria andStudent_nameNotEqualTo(String value) {
            addCriterion("student_name <>", value, "student_name");
            return (Criteria) this;
        }

        public Criteria andStudent_nameGreaterThan(String value) {
            addCriterion("student_name >", value, "student_name");
            return (Criteria) this;
        }

        public Criteria andStudent_nameGreaterThanOrEqualTo(String value) {
            addCriterion("student_name >=", value, "student_name");
            return (Criteria) this;
        }

        public Criteria andStudent_nameLessThan(String value) {
            addCriterion("student_name <", value, "student_name");
            return (Criteria) this;
        }

        public Criteria andStudent_nameLessThanOrEqualTo(String value) {
            addCriterion("student_name <=", value, "student_name");
            return (Criteria) this;
        }

        public Criteria andStudent_nameLike(String value) {
            addCriterion("student_name like", value, "student_name");
            return (Criteria) this;
        }

        public Criteria andStudent_nameNotLike(String value) {
            addCriterion("student_name not like", value, "student_name");
            return (Criteria) this;
        }

        public Criteria andStudent_nameIn(List<String> values) {
            addCriterion("student_name in", values, "student_name");
            return (Criteria) this;
        }

        public Criteria andStudent_nameNotIn(List<String> values) {
            addCriterion("student_name not in", values, "student_name");
            return (Criteria) this;
        }

        public Criteria andStudent_nameBetween(String value1, String value2) {
            addCriterion("student_name between", value1, value2, "student_name");
            return (Criteria) this;
        }

        public Criteria andStudent_nameNotBetween(String value1, String value2) {
            addCriterion("student_name not between", value1, value2, "student_name");
            return (Criteria) this;
        }

        public Criteria andStudent_noIsNull() {
            addCriterion("student_no is null");
            return (Criteria) this;
        }

        public Criteria andStudent_noIsNotNull() {
            addCriterion("student_no is not null");
            return (Criteria) this;
        }

        public Criteria andStudent_noEqualTo(String value) {
            addCriterion("student_no =", value, "student_no");
            return (Criteria) this;
        }

        public Criteria andStudent_noNotEqualTo(String value) {
            addCriterion("student_no <>", value, "student_no");
            return (Criteria) this;
        }

        public Criteria andStudent_noGreaterThan(String value) {
            addCriterion("student_no >", value, "student_no");
            return (Criteria) this;
        }

        public Criteria andStudent_noGreaterThanOrEqualTo(String value) {
            addCriterion("student_no >=", value, "student_no");
            return (Criteria) this;
        }

        public Criteria andStudent_noLessThan(String value) {
            addCriterion("student_no <", value, "student_no");
            return (Criteria) this;
        }

        public Criteria andStudent_noLessThanOrEqualTo(String value) {
            addCriterion("student_no <=", value, "student_no");
            return (Criteria) this;
        }

        public Criteria andStudent_noLike(String value) {
            addCriterion("student_no like", value, "student_no");
            return (Criteria) this;
        }

        public Criteria andStudent_noNotLike(String value) {
            addCriterion("student_no not like", value, "student_no");
            return (Criteria) this;
        }

        public Criteria andStudent_noIn(List<String> values) {
            addCriterion("student_no in", values, "student_no");
            return (Criteria) this;
        }

        public Criteria andStudent_noNotIn(List<String> values) {
            addCriterion("student_no not in", values, "student_no");
            return (Criteria) this;
        }

        public Criteria andStudent_noBetween(String value1, String value2) {
            addCriterion("student_no between", value1, value2, "student_no");
            return (Criteria) this;
        }

        public Criteria andStudent_noNotBetween(String value1, String value2) {
            addCriterion("student_no not between", value1, value2, "student_no");
            return (Criteria) this;
        }

        public Criteria andConnection_infoIsNull() {
            addCriterion("connection_info is null");
            return (Criteria) this;
        }

        public Criteria andConnection_infoIsNotNull() {
            addCriterion("connection_info is not null");
            return (Criteria) this;
        }

        public Criteria andConnection_infoEqualTo(String value) {
            addCriterion("connection_info =", value, "connection_info");
            return (Criteria) this;
        }

        public Criteria andConnection_infoNotEqualTo(String value) {
            addCriterion("connection_info <>", value, "connection_info");
            return (Criteria) this;
        }

        public Criteria andConnection_infoGreaterThan(String value) {
            addCriterion("connection_info >", value, "connection_info");
            return (Criteria) this;
        }

        public Criteria andConnection_infoGreaterThanOrEqualTo(String value) {
            addCriterion("connection_info >=", value, "connection_info");
            return (Criteria) this;
        }

        public Criteria andConnection_infoLessThan(String value) {
            addCriterion("connection_info <", value, "connection_info");
            return (Criteria) this;
        }

        public Criteria andConnection_infoLessThanOrEqualTo(String value) {
            addCriterion("connection_info <=", value, "connection_info");
            return (Criteria) this;
        }

        public Criteria andConnection_infoLike(String value) {
            addCriterion("connection_info like", value, "connection_info");
            return (Criteria) this;
        }

        public Criteria andConnection_infoNotLike(String value) {
            addCriterion("connection_info not like", value, "connection_info");
            return (Criteria) this;
        }

        public Criteria andConnection_infoIn(List<String> values) {
            addCriterion("connection_info in", values, "connection_info");
            return (Criteria) this;
        }

        public Criteria andConnection_infoNotIn(List<String> values) {
            addCriterion("connection_info not in", values, "connection_info");
            return (Criteria) this;
        }

        public Criteria andConnection_infoBetween(String value1, String value2) {
            addCriterion("connection_info between", value1, value2, "connection_info");
            return (Criteria) this;
        }

        public Criteria andConnection_infoNotBetween(String value1, String value2) {
            addCriterion("connection_info not between", value1, value2, "connection_info");
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

        public Criteria andGradeIsNull() {
            addCriterion("grade is null");
            return (Criteria) this;
        }

        public Criteria andGradeIsNotNull() {
            addCriterion("grade is not null");
            return (Criteria) this;
        }

        public Criteria andGradeEqualTo(Integer value) {
            addCriterion("grade =", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotEqualTo(Integer value) {
            addCriterion("grade <>", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThan(Integer value) {
            addCriterion("grade >", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("grade >=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThan(Integer value) {
            addCriterion("grade <", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThanOrEqualTo(Integer value) {
            addCriterion("grade <=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeIn(List<Integer> values) {
            addCriterion("grade in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotIn(List<Integer> values) {
            addCriterion("grade not in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeBetween(Integer value1, Integer value2) {
            addCriterion("grade between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotBetween(Integer value1, Integer value2) {
            addCriterion("grade not between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andClass_roonIsNull() {
            addCriterion("class_roon is null");
            return (Criteria) this;
        }

        public Criteria andClass_roonIsNotNull() {
            addCriterion("class_roon is not null");
            return (Criteria) this;
        }

        public Criteria andClass_roonEqualTo(String value) {
            addCriterion("class_roon =", value, "class_roon");
            return (Criteria) this;
        }

        public Criteria andClass_roonNotEqualTo(String value) {
            addCriterion("class_roon <>", value, "class_roon");
            return (Criteria) this;
        }

        public Criteria andClass_roonGreaterThan(String value) {
            addCriterion("class_roon >", value, "class_roon");
            return (Criteria) this;
        }

        public Criteria andClass_roonGreaterThanOrEqualTo(String value) {
            addCriterion("class_roon >=", value, "class_roon");
            return (Criteria) this;
        }

        public Criteria andClass_roonLessThan(String value) {
            addCriterion("class_roon <", value, "class_roon");
            return (Criteria) this;
        }

        public Criteria andClass_roonLessThanOrEqualTo(String value) {
            addCriterion("class_roon <=", value, "class_roon");
            return (Criteria) this;
        }

        public Criteria andClass_roonLike(String value) {
            addCriterion("class_roon like", value, "class_roon");
            return (Criteria) this;
        }

        public Criteria andClass_roonNotLike(String value) {
            addCriterion("class_roon not like", value, "class_roon");
            return (Criteria) this;
        }

        public Criteria andClass_roonIn(List<String> values) {
            addCriterion("class_roon in", values, "class_roon");
            return (Criteria) this;
        }

        public Criteria andClass_roonNotIn(List<String> values) {
            addCriterion("class_roon not in", values, "class_roon");
            return (Criteria) this;
        }

        public Criteria andClass_roonBetween(String value1, String value2) {
            addCriterion("class_roon between", value1, value2, "class_roon");
            return (Criteria) this;
        }

        public Criteria andClass_roonNotBetween(String value1, String value2) {
            addCriterion("class_roon not between", value1, value2, "class_roon");
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