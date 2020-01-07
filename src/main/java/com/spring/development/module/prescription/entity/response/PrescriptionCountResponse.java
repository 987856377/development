package com.spring.development.module.prescription.entity.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.prescription.entity.response
 * @Author xuzhenkui
 * @Date 2020/1/7 21:57
 */
public class PrescriptionCountResponse implements Serializable {
    private List<String> orgNameList;
    private List<Long> preLocalList;
    private List<Long> preOutsideList;
    private List<Long> preTotalList;

    public PrescriptionCountResponse() {
        orgNameList = new ArrayList<>();
        preLocalList = new ArrayList<>();
        preOutsideList = new ArrayList<>();
        preTotalList = new ArrayList<>();
    }

    public PrescriptionCountResponse(List<String> orgNameList, List<Long> preLocalList, List<Long> preOutsideList, List<Long> preTotalList) {
        this.orgNameList = orgNameList;
        this.preLocalList = preLocalList;
        this.preOutsideList = preOutsideList;
        this.preTotalList = preTotalList;
    }

    public List<String> getOrgNameList() {
        return orgNameList;
    }

    public void setOrgNameList(List<String> orgNameList) {
        this.orgNameList = orgNameList;
    }

    public List<Long> getPreLocalList() {
        return preLocalList;
    }

    public void setPreLocalList(List<Long> preLocalList) {
        this.preLocalList = preLocalList;
    }

    public List<Long> getPreOutsideList() {
        return preOutsideList;
    }

    public void setPreOutsideList(List<Long> preOutsideList) {
        this.preOutsideList = preOutsideList;
    }

    public List<Long> getPreTotalList() {
        return preTotalList;
    }

    public void setPreTotalList(List<Long> preTotalList) {
        this.preTotalList = preTotalList;
    }
}
