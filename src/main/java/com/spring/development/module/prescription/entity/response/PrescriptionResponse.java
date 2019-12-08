package com.spring.development.module.prescription.entity.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.prescription.entity.response
 * @Author xuzhenkui
 * @Date 2019/11/14 11:45
 */
public class PrescriptionResponse implements Serializable {
    /**
     * 处方代码
     */
    private Long id;

    /**
     * 机构代码 (关联 organization 表 code)
     */
    private String orgcode;

    /**
     * 机构名称
     */
    private String orgname;

    /**
     * 录入处方者 (关联 user 表 id)
     */
    private Long uid;

    /**
     * 录入处方者姓名
     */
    private String uname;

    /**
     * 处方类型: 1. 普通处方  2. 特殊处方
     */
    private Integer type;

    /**
     * 开方医生
     */
    private String doctorName;

    /**
     * 开方医生手机号
     */
    private String doctorPhone;

    /**
     * 归属科室
     */
    private String department;

    /**
     * 审核医生
     */
    private String checkDoctor;

    /**
     * 审核医生手机号
     */
    private String checkPhone;

    /**
     * 适用症状
     */
    private String symptom;

    /**
     * 适用性别
     */
    private String sex;

    /**
     * 使用年龄
     */
    private String age;

    /**
     * 开方日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp date;

    /**
     * 用药
     */
    private String medicine;

    /**
     * 医嘱
     */
    private String advice;

    /**
     * 建议价格
     */
    private BigDecimal price;

    /**
     * 处方来源: 1:本机构,9:外来
     */
    private Integer origin;

    /**
     * 流转状态: 1:正常,0:停用
     */
    private Integer flag;

    /**
     * 审核员 (关联user表的 id)
     */
    private Long operator;

    /**
     * 审核员 (关联user表的 id)
     */
    private String operatorName;

    /**
     * 审核状态: 1:已审核,0: 待审核:9:停用
     */
    private Integer verify;

    /**
     * 审核日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp verifyTime;

    /**
     * 开停方日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp forbiddenTime;

    /**
     * 是否可流转 1:可以,0: 不可以
     */
    private Integer enable;

    /**
     * 备注
     */
    private String extra;

    public PrescriptionResponse() {
    }

    public PrescriptionResponse(Long id, String orgcode, String orgname, Long uid, String uname, Integer type, String doctorName, String doctorPhone, String checkDoctor, String checkPhone, String symptom, String sex, String age, Timestamp date, String medicine, String advice, BigDecimal price, Integer flag, Long operator, String operatorName, Integer verify, Timestamp verifyTime, Timestamp forbiddenTime, Integer enable, String extra) {
        this.id = id;
        this.orgcode = orgcode;
        this.orgname = orgname;
        this.uid = uid;
        this.uname = uname;
        this.type = type;
        this.doctorName = doctorName;
        this.doctorPhone = doctorPhone;
        this.checkDoctor = checkDoctor;
        this.checkPhone = checkPhone;
        this.symptom = symptom;
        this.sex = sex;
        this.age = age;
        this.date = date;
        this.medicine = medicine;
        this.advice = advice;
        this.price = price;
        this.flag = flag;
        this.operator = operator;
        this.operatorName = operatorName;
        this.verify = verify;
        this.verifyTime = verifyTime;
        this.forbiddenTime = forbiddenTime;
        this.enable = enable;
        this.extra = extra;
    }

    public PrescriptionResponse(Long id, String orgcode, String orgname, Long uid, String uname, Integer type, String doctorName, String doctorPhone, String department, String checkDoctor, String checkPhone, String symptom, String sex, String age, Timestamp date, String medicine, String advice, BigDecimal price, Integer flag, Long operator, String operatorName, Integer verify, Timestamp verifyTime, Timestamp forbiddenTime, Integer enable, String extra) {
        this.id = id;
        this.orgcode = orgcode;
        this.orgname = orgname;
        this.uid = uid;
        this.uname = uname;
        this.type = type;
        this.doctorName = doctorName;
        this.doctorPhone = doctorPhone;
        this.department = department;
        this.checkDoctor = checkDoctor;
        this.checkPhone = checkPhone;
        this.symptom = symptom;
        this.sex = sex;
        this.age = age;
        this.date = date;
        this.medicine = medicine;
        this.advice = advice;
        this.price = price;
        this.flag = flag;
        this.operator = operator;
        this.operatorName = operatorName;
        this.verify = verify;
        this.verifyTime = verifyTime;
        this.forbiddenTime = forbiddenTime;
        this.enable = enable;
        this.extra = extra;
    }

    public PrescriptionResponse(Long id, String orgcode, String orgname, Long uid, String uname, Integer type, String doctorName, String doctorPhone, String department, String checkDoctor, String checkPhone, String symptom, String sex, String age, Timestamp date, String medicine, String advice, BigDecimal price, Integer origin, Integer flag, Long operator, String operatorName, Integer verify, Timestamp verifyTime, Timestamp forbiddenTime, Integer enable, String extra) {
        this.id = id;
        this.orgcode = orgcode;
        this.orgname = orgname;
        this.uid = uid;
        this.uname = uname;
        this.type = type;
        this.doctorName = doctorName;
        this.doctorPhone = doctorPhone;
        this.department = department;
        this.checkDoctor = checkDoctor;
        this.checkPhone = checkPhone;
        this.symptom = symptom;
        this.sex = sex;
        this.age = age;
        this.date = date;
        this.medicine = medicine;
        this.advice = advice;
        this.price = price;
        this.origin = origin;
        this.flag = flag;
        this.operator = operator;
        this.operatorName = operatorName;
        this.verify = verify;
        this.verifyTime = verifyTime;
        this.forbiddenTime = forbiddenTime;
        this.enable = enable;
        this.extra = extra;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorPhone() {
        return doctorPhone;
    }

    public void setDoctorPhone(String doctorPhone) {
        this.doctorPhone = doctorPhone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCheckDoctor() {
        return checkDoctor;
    }

    public void setCheckDoctor(String checkDoctor) {
        this.checkDoctor = checkDoctor;
    }

    public String getCheckPhone() {
        return checkPhone;
    }

    public void setCheckPhone(String checkPhone) {
        this.checkPhone = checkPhone;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getOrigin() {
        return origin;
    }

    public void setOrigin(Integer origin) {
        this.origin = origin;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Long getOperator() {
        return operator;
    }

    public void setOperator(Long operator) {
        this.operator = operator;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Integer getVerify() {
        return verify;
    }

    public void setVerify(Integer verify) {
        this.verify = verify;
    }

    public Timestamp getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(Timestamp verifyTime) {
        this.verifyTime = verifyTime;
    }

    public Timestamp getForbiddenTime() {
        return forbiddenTime;
    }

    public void setForbiddenTime(Timestamp forbiddenTime) {
        this.forbiddenTime = forbiddenTime;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
