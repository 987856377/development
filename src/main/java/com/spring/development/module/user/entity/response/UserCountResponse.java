package com.spring.development.module.user.entity.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.user.entity.response
 * @Author xuzhenkui
 * @Date 2020/1/7 20:21
 */
public class UserCountResponse implements Serializable {
    private List<String> orgNameList;
    private List<Long> orgManList;
    private List<Long> orgWomanList;
    private List<Long> orgTotalList;

    public UserCountResponse() {
        orgNameList = new ArrayList<>();
        orgManList = new ArrayList<>();
        orgWomanList = new ArrayList<>();
        orgTotalList = new ArrayList<>();
    }

    public UserCountResponse(List<String> orgNameList, List<Long> orgManList, List<Long> orgWomanList, List<Long> orgTotalList) {
        this.orgNameList = orgNameList;
        this.orgManList = orgManList;
        this.orgWomanList = orgWomanList;
        this.orgTotalList = orgTotalList;
    }

    public List<String> getOrgNameList() {
        return orgNameList;
    }

    public void setOrgNameList(List<String> orgNameList) {
        this.orgNameList = orgNameList;
    }

    public List<Long> getOrgManList() {
        return orgManList;
    }

    public void setOrgManList(List<Long> orgManList) {
        this.orgManList = orgManList;
    }

    public List<Long> getOrgWomanList() {
        return orgWomanList;
    }

    public void setOrgWomanList(List<Long> orgWomanList) {
        this.orgWomanList = orgWomanList;
    }

    public List<Long> getOrgTotalList() {
        return orgTotalList;
    }

    public void setOrgTotalList(List<Long> orgTotalList) {
        this.orgTotalList = orgTotalList;
    }

    @Override
    public String toString() {
        return "UserCountResponse{" +
                "orgNameList=" + orgNameList +
                ", orgManList=" + orgManList +
                ", orgWomanList=" + orgWomanList +
                ", orgTotalList=" + orgTotalList +
                '}';
    }
}
