package com.spring.development.module.prescription.entity.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.prescription.entity.request
 * @Author xuzhenkui
 * @Date 2019/11/14 21:37
 */
public class PrescriptionRequest implements Serializable {

    private String orgname;
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

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
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
}
