package model;

import java.util.ArrayList;
import java.util.List;

public class T_monitorinfoExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_monitorinfo
     *
     * @mbggenerated Mon Nov 24 11:21:54 CST 2014
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_monitorinfo
     *
     * @mbggenerated Mon Nov 24 11:21:54 CST 2014
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_monitorinfo
     *
     * @mbggenerated Mon Nov 24 11:21:54 CST 2014
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_monitorinfo
     *
     * @mbggenerated Mon Nov 24 11:21:54 CST 2014
     */
    public T_monitorinfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_monitorinfo
     *
     * @mbggenerated Mon Nov 24 11:21:54 CST 2014
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_monitorinfo
     *
     * @mbggenerated Mon Nov 24 11:21:54 CST 2014
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_monitorinfo
     *
     * @mbggenerated Mon Nov 24 11:21:54 CST 2014
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_monitorinfo
     *
     * @mbggenerated Mon Nov 24 11:21:54 CST 2014
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_monitorinfo
     *
     * @mbggenerated Mon Nov 24 11:21:54 CST 2014
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_monitorinfo
     *
     * @mbggenerated Mon Nov 24 11:21:54 CST 2014
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_monitorinfo
     *
     * @mbggenerated Mon Nov 24 11:21:54 CST 2014
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_monitorinfo
     *
     * @mbggenerated Mon Nov 24 11:21:54 CST 2014
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_monitorinfo
     *
     * @mbggenerated Mon Nov 24 11:21:54 CST 2014
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_monitorinfo
     *
     * @mbggenerated Mon Nov 24 11:21:54 CST 2014
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_monitorinfo
     *
     * @mbggenerated Mon Nov 24 11:21:54 CST 2014
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

        public Criteria andProgram_idIsNull() {
            addCriterion("program_id is null");
            return (Criteria) this;
        }

        public Criteria andProgram_idIsNotNull() {
            addCriterion("program_id is not null");
            return (Criteria) this;
        }

        public Criteria andProgram_idEqualTo(String value) {
            addCriterion("program_id =", value, "program_id");
            return (Criteria) this;
        }

        public Criteria andProgram_idNotEqualTo(String value) {
            addCriterion("program_id <>", value, "program_id");
            return (Criteria) this;
        }

        public Criteria andProgram_idGreaterThan(String value) {
            addCriterion("program_id >", value, "program_id");
            return (Criteria) this;
        }

        public Criteria andProgram_idGreaterThanOrEqualTo(String value) {
            addCriterion("program_id >=", value, "program_id");
            return (Criteria) this;
        }

        public Criteria andProgram_idLessThan(String value) {
            addCriterion("program_id <", value, "program_id");
            return (Criteria) this;
        }

        public Criteria andProgram_idLessThanOrEqualTo(String value) {
            addCriterion("program_id <=", value, "program_id");
            return (Criteria) this;
        }

        public Criteria andProgram_idLike(String value) {
            addCriterion("program_id like", value, "program_id");
            return (Criteria) this;
        }

        public Criteria andProgram_idNotLike(String value) {
            addCriterion("program_id not like", value, "program_id");
            return (Criteria) this;
        }

        public Criteria andProgram_idIn(List<String> values) {
            addCriterion("program_id in", values, "program_id");
            return (Criteria) this;
        }

        public Criteria andProgram_idNotIn(List<String> values) {
            addCriterion("program_id not in", values, "program_id");
            return (Criteria) this;
        }

        public Criteria andProgram_idBetween(String value1, String value2) {
            addCriterion("program_id between", value1, value2, "program_id");
            return (Criteria) this;
        }

        public Criteria andProgram_idNotBetween(String value1, String value2) {
            addCriterion("program_id not between", value1, value2, "program_id");
            return (Criteria) this;
        }

        public Criteria andProgram_typeIsNull() {
            addCriterion("program_type is null");
            return (Criteria) this;
        }

        public Criteria andProgram_typeIsNotNull() {
            addCriterion("program_type is not null");
            return (Criteria) this;
        }

        public Criteria andProgram_typeEqualTo(String value) {
            addCriterion("program_type =", value, "program_type");
            return (Criteria) this;
        }

        public Criteria andProgram_typeNotEqualTo(String value) {
            addCriterion("program_type <>", value, "program_type");
            return (Criteria) this;
        }

        public Criteria andProgram_typeGreaterThan(String value) {
            addCriterion("program_type >", value, "program_type");
            return (Criteria) this;
        }

        public Criteria andProgram_typeGreaterThanOrEqualTo(String value) {
            addCriterion("program_type >=", value, "program_type");
            return (Criteria) this;
        }

        public Criteria andProgram_typeLessThan(String value) {
            addCriterion("program_type <", value, "program_type");
            return (Criteria) this;
        }

        public Criteria andProgram_typeLessThanOrEqualTo(String value) {
            addCriterion("program_type <=", value, "program_type");
            return (Criteria) this;
        }

        public Criteria andProgram_typeLike(String value) {
            addCriterion("program_type like", value, "program_type");
            return (Criteria) this;
        }

        public Criteria andProgram_typeNotLike(String value) {
            addCriterion("program_type not like", value, "program_type");
            return (Criteria) this;
        }

        public Criteria andProgram_typeIn(List<String> values) {
            addCriterion("program_type in", values, "program_type");
            return (Criteria) this;
        }

        public Criteria andProgram_typeNotIn(List<String> values) {
            addCriterion("program_type not in", values, "program_type");
            return (Criteria) this;
        }

        public Criteria andProgram_typeBetween(String value1, String value2) {
            addCriterion("program_type between", value1, value2, "program_type");
            return (Criteria) this;
        }

        public Criteria andProgram_typeNotBetween(String value1, String value2) {
            addCriterion("program_type not between", value1, value2, "program_type");
            return (Criteria) this;
        }

        public Criteria andGroup_idIsNull() {
            addCriterion("group_id is null");
            return (Criteria) this;
        }

        public Criteria andGroup_idIsNotNull() {
            addCriterion("group_id is not null");
            return (Criteria) this;
        }

        public Criteria andGroup_idEqualTo(Integer value) {
            addCriterion("group_id =", value, "group_id");
            return (Criteria) this;
        }

        public Criteria andGroup_idNotEqualTo(Integer value) {
            addCriterion("group_id <>", value, "group_id");
            return (Criteria) this;
        }

        public Criteria andGroup_idGreaterThan(Integer value) {
            addCriterion("group_id >", value, "group_id");
            return (Criteria) this;
        }

        public Criteria andGroup_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("group_id >=", value, "group_id");
            return (Criteria) this;
        }

        public Criteria andGroup_idLessThan(Integer value) {
            addCriterion("group_id <", value, "group_id");
            return (Criteria) this;
        }

        public Criteria andGroup_idLessThanOrEqualTo(Integer value) {
            addCriterion("group_id <=", value, "group_id");
            return (Criteria) this;
        }

        public Criteria andGroup_idIn(List<Integer> values) {
            addCriterion("group_id in", values, "group_id");
            return (Criteria) this;
        }

        public Criteria andGroup_idNotIn(List<Integer> values) {
            addCriterion("group_id not in", values, "group_id");
            return (Criteria) this;
        }

        public Criteria andGroup_idBetween(Integer value1, Integer value2) {
            addCriterion("group_id between", value1, value2, "group_id");
            return (Criteria) this;
        }

        public Criteria andGroup_idNotBetween(Integer value1, Integer value2) {
            addCriterion("group_id not between", value1, value2, "group_id");
            return (Criteria) this;
        }

        public Criteria andPlc_addressIsNull() {
            addCriterion("plc_address is null");
            return (Criteria) this;
        }

        public Criteria andPlc_addressIsNotNull() {
            addCriterion("plc_address is not null");
            return (Criteria) this;
        }

        public Criteria andPlc_addressEqualTo(String value) {
            addCriterion("plc_address =", value, "plc_address");
            return (Criteria) this;
        }

        public Criteria andPlc_addressNotEqualTo(String value) {
            addCriterion("plc_address <>", value, "plc_address");
            return (Criteria) this;
        }

        public Criteria andPlc_addressGreaterThan(String value) {
            addCriterion("plc_address >", value, "plc_address");
            return (Criteria) this;
        }

        public Criteria andPlc_addressGreaterThanOrEqualTo(String value) {
            addCriterion("plc_address >=", value, "plc_address");
            return (Criteria) this;
        }

        public Criteria andPlc_addressLessThan(String value) {
            addCriterion("plc_address <", value, "plc_address");
            return (Criteria) this;
        }

        public Criteria andPlc_addressLessThanOrEqualTo(String value) {
            addCriterion("plc_address <=", value, "plc_address");
            return (Criteria) this;
        }

        public Criteria andPlc_addressLike(String value) {
            addCriterion("plc_address like", value, "plc_address");
            return (Criteria) this;
        }

        public Criteria andPlc_addressNotLike(String value) {
            addCriterion("plc_address not like", value, "plc_address");
            return (Criteria) this;
        }

        public Criteria andPlc_addressIn(List<String> values) {
            addCriterion("plc_address in", values, "plc_address");
            return (Criteria) this;
        }

        public Criteria andPlc_addressNotIn(List<String> values) {
            addCriterion("plc_address not in", values, "plc_address");
            return (Criteria) this;
        }

        public Criteria andPlc_addressBetween(String value1, String value2) {
            addCriterion("plc_address between", value1, value2, "plc_address");
            return (Criteria) this;
        }

        public Criteria andPlc_addressNotBetween(String value1, String value2) {
            addCriterion("plc_address not between", value1, value2, "plc_address");
            return (Criteria) this;
        }

        public Criteria andMonitor_tableIsNull() {
            addCriterion("monitor_table is null");
            return (Criteria) this;
        }

        public Criteria andMonitor_tableIsNotNull() {
            addCriterion("monitor_table is not null");
            return (Criteria) this;
        }

        public Criteria andMonitor_tableEqualTo(String value) {
            addCriterion("monitor_table =", value, "monitor_table");
            return (Criteria) this;
        }

        public Criteria andMonitor_tableNotEqualTo(String value) {
            addCriterion("monitor_table <>", value, "monitor_table");
            return (Criteria) this;
        }

        public Criteria andMonitor_tableGreaterThan(String value) {
            addCriterion("monitor_table >", value, "monitor_table");
            return (Criteria) this;
        }

        public Criteria andMonitor_tableGreaterThanOrEqualTo(String value) {
            addCriterion("monitor_table >=", value, "monitor_table");
            return (Criteria) this;
        }

        public Criteria andMonitor_tableLessThan(String value) {
            addCriterion("monitor_table <", value, "monitor_table");
            return (Criteria) this;
        }

        public Criteria andMonitor_tableLessThanOrEqualTo(String value) {
            addCriterion("monitor_table <=", value, "monitor_table");
            return (Criteria) this;
        }

        public Criteria andMonitor_tableLike(String value) {
            addCriterion("monitor_table like", value, "monitor_table");
            return (Criteria) this;
        }

        public Criteria andMonitor_tableNotLike(String value) {
            addCriterion("monitor_table not like", value, "monitor_table");
            return (Criteria) this;
        }

        public Criteria andMonitor_tableIn(List<String> values) {
            addCriterion("monitor_table in", values, "monitor_table");
            return (Criteria) this;
        }

        public Criteria andMonitor_tableNotIn(List<String> values) {
            addCriterion("monitor_table not in", values, "monitor_table");
            return (Criteria) this;
        }

        public Criteria andMonitor_tableBetween(String value1, String value2) {
            addCriterion("monitor_table between", value1, value2, "monitor_table");
            return (Criteria) this;
        }

        public Criteria andMonitor_tableNotBetween(String value1, String value2) {
            addCriterion("monitor_table not between", value1, value2, "monitor_table");
            return (Criteria) this;
        }

        public Criteria andCount_tableIsNull() {
            addCriterion("count_table is null");
            return (Criteria) this;
        }

        public Criteria andCount_tableIsNotNull() {
            addCriterion("count_table is not null");
            return (Criteria) this;
        }

        public Criteria andCount_tableEqualTo(String value) {
            addCriterion("count_table =", value, "count_table");
            return (Criteria) this;
        }

        public Criteria andCount_tableNotEqualTo(String value) {
            addCriterion("count_table <>", value, "count_table");
            return (Criteria) this;
        }

        public Criteria andCount_tableGreaterThan(String value) {
            addCriterion("count_table >", value, "count_table");
            return (Criteria) this;
        }

        public Criteria andCount_tableGreaterThanOrEqualTo(String value) {
            addCriterion("count_table >=", value, "count_table");
            return (Criteria) this;
        }

        public Criteria andCount_tableLessThan(String value) {
            addCriterion("count_table <", value, "count_table");
            return (Criteria) this;
        }

        public Criteria andCount_tableLessThanOrEqualTo(String value) {
            addCriterion("count_table <=", value, "count_table");
            return (Criteria) this;
        }

        public Criteria andCount_tableLike(String value) {
            addCriterion("count_table like", value, "count_table");
            return (Criteria) this;
        }

        public Criteria andCount_tableNotLike(String value) {
            addCriterion("count_table not like", value, "count_table");
            return (Criteria) this;
        }

        public Criteria andCount_tableIn(List<String> values) {
            addCriterion("count_table in", values, "count_table");
            return (Criteria) this;
        }

        public Criteria andCount_tableNotIn(List<String> values) {
            addCriterion("count_table not in", values, "count_table");
            return (Criteria) this;
        }

        public Criteria andCount_tableBetween(String value1, String value2) {
            addCriterion("count_table between", value1, value2, "count_table");
            return (Criteria) this;
        }

        public Criteria andCount_tableNotBetween(String value1, String value2) {
            addCriterion("count_table not between", value1, value2, "count_table");
            return (Criteria) this;
        }

        public Criteria andProgram_descIsNull() {
            addCriterion("program_desc is null");
            return (Criteria) this;
        }

        public Criteria andProgram_descIsNotNull() {
            addCriterion("program_desc is not null");
            return (Criteria) this;
        }

        public Criteria andProgram_descEqualTo(String value) {
            addCriterion("program_desc =", value, "program_desc");
            return (Criteria) this;
        }

        public Criteria andProgram_descNotEqualTo(String value) {
            addCriterion("program_desc <>", value, "program_desc");
            return (Criteria) this;
        }

        public Criteria andProgram_descGreaterThan(String value) {
            addCriterion("program_desc >", value, "program_desc");
            return (Criteria) this;
        }

        public Criteria andProgram_descGreaterThanOrEqualTo(String value) {
            addCriterion("program_desc >=", value, "program_desc");
            return (Criteria) this;
        }

        public Criteria andProgram_descLessThan(String value) {
            addCriterion("program_desc <", value, "program_desc");
            return (Criteria) this;
        }

        public Criteria andProgram_descLessThanOrEqualTo(String value) {
            addCriterion("program_desc <=", value, "program_desc");
            return (Criteria) this;
        }

        public Criteria andProgram_descLike(String value) {
            addCriterion("program_desc like", value, "program_desc");
            return (Criteria) this;
        }

        public Criteria andProgram_descNotLike(String value) {
            addCriterion("program_desc not like", value, "program_desc");
            return (Criteria) this;
        }

        public Criteria andProgram_descIn(List<String> values) {
            addCriterion("program_desc in", values, "program_desc");
            return (Criteria) this;
        }

        public Criteria andProgram_descNotIn(List<String> values) {
            addCriterion("program_desc not in", values, "program_desc");
            return (Criteria) this;
        }

        public Criteria andProgram_descBetween(String value1, String value2) {
            addCriterion("program_desc between", value1, value2, "program_desc");
            return (Criteria) this;
        }

        public Criteria andProgram_descNotBetween(String value1, String value2) {
            addCriterion("program_desc not between", value1, value2, "program_desc");
            return (Criteria) this;
        }

        public Criteria andLocal_idIsNull() {
            addCriterion("local_id is null");
            return (Criteria) this;
        }

        public Criteria andLocal_idIsNotNull() {
            addCriterion("local_id is not null");
            return (Criteria) this;
        }

        public Criteria andLocal_idEqualTo(String value) {
            addCriterion("local_id =", value, "local_id");
            return (Criteria) this;
        }

        public Criteria andLocal_idNotEqualTo(String value) {
            addCriterion("local_id <>", value, "local_id");
            return (Criteria) this;
        }

        public Criteria andLocal_idGreaterThan(String value) {
            addCriterion("local_id >", value, "local_id");
            return (Criteria) this;
        }

        public Criteria andLocal_idGreaterThanOrEqualTo(String value) {
            addCriterion("local_id >=", value, "local_id");
            return (Criteria) this;
        }

        public Criteria andLocal_idLessThan(String value) {
            addCriterion("local_id <", value, "local_id");
            return (Criteria) this;
        }

        public Criteria andLocal_idLessThanOrEqualTo(String value) {
            addCriterion("local_id <=", value, "local_id");
            return (Criteria) this;
        }

        public Criteria andLocal_idLike(String value) {
            addCriterion("local_id like", value, "local_id");
            return (Criteria) this;
        }

        public Criteria andLocal_idNotLike(String value) {
            addCriterion("local_id not like", value, "local_id");
            return (Criteria) this;
        }

        public Criteria andLocal_idIn(List<String> values) {
            addCriterion("local_id in", values, "local_id");
            return (Criteria) this;
        }

        public Criteria andLocal_idNotIn(List<String> values) {
            addCriterion("local_id not in", values, "local_id");
            return (Criteria) this;
        }

        public Criteria andLocal_idBetween(String value1, String value2) {
            addCriterion("local_id between", value1, value2, "local_id");
            return (Criteria) this;
        }

        public Criteria andLocal_idNotBetween(String value1, String value2) {
            addCriterion("local_id not between", value1, value2, "local_id");
            return (Criteria) this;
        }

        public Criteria andProgram_unitIsNull() {
            addCriterion("program_unit is null");
            return (Criteria) this;
        }

        public Criteria andProgram_unitIsNotNull() {
            addCriterion("program_unit is not null");
            return (Criteria) this;
        }

        public Criteria andProgram_unitEqualTo(String value) {
            addCriterion("program_unit =", value, "program_unit");
            return (Criteria) this;
        }

        public Criteria andProgram_unitNotEqualTo(String value) {
            addCriterion("program_unit <>", value, "program_unit");
            return (Criteria) this;
        }

        public Criteria andProgram_unitGreaterThan(String value) {
            addCriterion("program_unit >", value, "program_unit");
            return (Criteria) this;
        }

        public Criteria andProgram_unitGreaterThanOrEqualTo(String value) {
            addCriterion("program_unit >=", value, "program_unit");
            return (Criteria) this;
        }

        public Criteria andProgram_unitLessThan(String value) {
            addCriterion("program_unit <", value, "program_unit");
            return (Criteria) this;
        }

        public Criteria andProgram_unitLessThanOrEqualTo(String value) {
            addCriterion("program_unit <=", value, "program_unit");
            return (Criteria) this;
        }

        public Criteria andProgram_unitLike(String value) {
            addCriterion("program_unit like", value, "program_unit");
            return (Criteria) this;
        }

        public Criteria andProgram_unitNotLike(String value) {
            addCriterion("program_unit not like", value, "program_unit");
            return (Criteria) this;
        }

        public Criteria andProgram_unitIn(List<String> values) {
            addCriterion("program_unit in", values, "program_unit");
            return (Criteria) this;
        }

        public Criteria andProgram_unitNotIn(List<String> values) {
            addCriterion("program_unit not in", values, "program_unit");
            return (Criteria) this;
        }

        public Criteria andProgram_unitBetween(String value1, String value2) {
            addCriterion("program_unit between", value1, value2, "program_unit");
            return (Criteria) this;
        }

        public Criteria andProgram_unitNotBetween(String value1, String value2) {
            addCriterion("program_unit not between", value1, value2, "program_unit");
            return (Criteria) this;
        }

        public Criteria andProgram_chartIsNull() {
            addCriterion("program_chart is null");
            return (Criteria) this;
        }

        public Criteria andProgram_chartIsNotNull() {
            addCriterion("program_chart is not null");
            return (Criteria) this;
        }

        public Criteria andProgram_chartEqualTo(Integer value) {
            addCriterion("program_chart =", value, "program_chart");
            return (Criteria) this;
        }

        public Criteria andProgram_chartNotEqualTo(Integer value) {
            addCriterion("program_chart <>", value, "program_chart");
            return (Criteria) this;
        }

        public Criteria andProgram_chartGreaterThan(Integer value) {
            addCriterion("program_chart >", value, "program_chart");
            return (Criteria) this;
        }

        public Criteria andProgram_chartGreaterThanOrEqualTo(Integer value) {
            addCriterion("program_chart >=", value, "program_chart");
            return (Criteria) this;
        }

        public Criteria andProgram_chartLessThan(Integer value) {
            addCriterion("program_chart <", value, "program_chart");
            return (Criteria) this;
        }

        public Criteria andProgram_chartLessThanOrEqualTo(Integer value) {
            addCriterion("program_chart <=", value, "program_chart");
            return (Criteria) this;
        }

        public Criteria andProgram_chartIn(List<Integer> values) {
            addCriterion("program_chart in", values, "program_chart");
            return (Criteria) this;
        }

        public Criteria andProgram_chartNotIn(List<Integer> values) {
            addCriterion("program_chart not in", values, "program_chart");
            return (Criteria) this;
        }

        public Criteria andProgram_chartBetween(Integer value1, Integer value2) {
            addCriterion("program_chart between", value1, value2, "program_chart");
            return (Criteria) this;
        }

        public Criteria andProgram_chartNotBetween(Integer value1, Integer value2) {
            addCriterion("program_chart not between", value1, value2, "program_chart");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_monitorinfo
     *
     * @mbggenerated do_not_delete_during_merge Mon Nov 24 11:21:54 CST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }

        public Criteria andProgram_idLikeInsensitive(String value) {
            addCriterion("upper(program_id) like", value.toUpperCase(), "program_id");
            return this;
        }

        public Criteria andProgram_typeLikeInsensitive(String value) {
            addCriterion("upper(program_type) like", value.toUpperCase(), "program_type");
            return this;
        }

        public Criteria andPlc_addressLikeInsensitive(String value) {
            addCriterion("upper(plc_address) like", value.toUpperCase(), "plc_address");
            return this;
        }

        public Criteria andMonitor_tableLikeInsensitive(String value) {
            addCriterion("upper(monitor_table) like", value.toUpperCase(), "monitor_table");
            return this;
        }

        public Criteria andCount_tableLikeInsensitive(String value) {
            addCriterion("upper(count_table) like", value.toUpperCase(), "count_table");
            return this;
        }

        public Criteria andProgram_descLikeInsensitive(String value) {
            addCriterion("upper(program_desc) like", value.toUpperCase(), "program_desc");
            return this;
        }

        public Criteria andLocal_idLikeInsensitive(String value) {
            addCriterion("upper(local_id) like", value.toUpperCase(), "local_id");
            return this;
        }

        public Criteria andProgram_unitLikeInsensitive(String value) {
            addCriterion("upper(program_unit) like", value.toUpperCase(), "program_unit");
            return this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_monitorinfo
     *
     * @mbggenerated Mon Nov 24 11:21:54 CST 2014
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