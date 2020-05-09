package com.spring.development.module.prescription.entity.request;

import java.io.Serializable;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.prescription.entity.request
 * @Author xuzhenkui
 * @Date 2020/5/9 19:15
 */
public class CountPrescriptionRequest implements Serializable {
    private String orgflag;

    public CountPrescriptionRequest() {
    }

    public CountPrescriptionRequest(String orgflag) {
        this.orgflag = orgflag;
    }

    public String getOrgflag() {
        return orgflag;
    }

    public void setOrgflag(String orgflag) {
        this.orgflag = orgflag;
    }

    @Override
    public String toString() {
        return "CountPrescriptionRequest{" +
                "orgflag='" + orgflag + '\'' +
                '}';
    }
}
