package com.spring.development.module.prescription.entity.request;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.development.module.prescription.entity.response.PrescriptionResponse;

import java.io.Serializable;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.prescription.entity.request
 * @Author xuzhenkui
 * @Date 2019/11/14 21:37
 */
public class PrescriptionRequest implements Serializable {

    private Page<PrescriptionResponse> page;
    private Long id;
    private String orgname;
    private String department;
    private Integer type;
    private String symptom;
    private Integer flag;
    private Integer verify;
    private Integer enable;

    public PrescriptionRequest() {
    }

    public PrescriptionRequest(String orgname, Integer type, String symptom, Integer flag, Integer verify, Integer enable) {
        this.orgname = orgname;
        this.type = type;
        this.symptom = symptom;
        this.flag = flag;
        this.verify = verify;
        this.enable = enable;
    }

    public PrescriptionRequest(Page<PrescriptionResponse> page, String orgname, Integer type, String symptom, Integer flag, Integer verify, Integer enable) {
        this.page = page;
        this.orgname = orgname;
        this.type = type;
        this.symptom = symptom;
        this.flag = flag;
        this.verify = verify;
        this.enable = enable;
    }

    public PrescriptionRequest(Page<PrescriptionResponse> page, String orgname, String department, Integer type, String symptom, Integer flag, Integer verify, Integer enable) {
        this.page = page;
        this.orgname = orgname;
        this.department = department;
        this.type = type;
        this.symptom = symptom;
        this.flag = flag;
        this.verify = verify;
        this.enable = enable;
    }

    public PrescriptionRequest(Page<PrescriptionResponse> page, Long id, String orgname, String department, Integer type, String symptom, Integer flag, Integer verify, Integer enable) {
        this.page = page;
        this.id = id;
        this.orgname = orgname;
        this.department = department;
        this.type = type;
        this.symptom = symptom;
        this.flag = flag;
        this.verify = verify;
        this.enable = enable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getVerify() {
        return verify;
    }

    public void setVerify(Integer verify) {
        this.verify = verify;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Page<PrescriptionResponse> getPage() {
        return page;
    }

    public void setPage(Page<PrescriptionResponse> page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "PrescriptionRequest{" +
                "page=" + page +
                ", id=" + id +
                ", orgname='" + orgname + '\'' +
                ", department='" + department + '\'' +
                ", type=" + type +
                ", symptom='" + symptom + '\'' +
                ", flag=" + flag +
                ", verify=" + verify +
                ", enable=" + enable +
                '}';
    }
}
