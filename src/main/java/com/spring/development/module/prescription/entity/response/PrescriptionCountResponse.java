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
    private List<Long> preNormalList;
    private List<Long> preSpecialList;
    private List<Long> preTotalList;

    public PrescriptionCountResponse() {
        orgNameList = new ArrayList<>();
        preLocalList = new ArrayList<>();
        preOutsideList = new ArrayList<>();
        preNormalList = new ArrayList<>();
        preSpecialList = new ArrayList<>();
        preTotalList = new ArrayList<>();
    }

    public PrescriptionCountResponse(List<String> orgNameList, List<Long> preLocalList, List<Long> preOutsideList, List<Long> preNormalList, List<Long> preSpecialList, List<Long> preTotalList) {
        this.orgNameList = orgNameList;
        this.preLocalList = preLocalList;
        this.preOutsideList = preOutsideList;
        this.preNormalList = preNormalList;
        this.preSpecialList = preSpecialList;
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

    public List<Long> getPreNormalList() {
        return preNormalList;
    }

    public void setPreNormalList(List<Long> preNormalList) {
        this.preNormalList = preNormalList;
    }

    public List<Long> getPreSpecialList() {
        return preSpecialList;
    }

    public void setPreSpecialList(List<Long> preSpecialList) {
        this.preSpecialList = preSpecialList;
    }

    public List<Long> getPreTotalList() {
        return preTotalList;
    }

    public void setPreTotalList(List<Long> preTotalList) {
        this.preTotalList = preTotalList;
    }

    @Override
    public String toString() {
        return "PrescriptionCountResponse{" +
                "orgNameList=" + orgNameList +
                ", preLocalList=" + preLocalList +
                ", preOutsideList=" + preOutsideList +
                ", preNormalList=" + preNormalList +
                ", preSpecialList=" + preSpecialList +
                ", preTotalList=" + preTotalList +
                '}';
    }
}
