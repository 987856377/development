package com.spring.development.module.user.entity.response;

import java.io.Serializable;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.user.entity.response
 * @Author xuzhenkui
 * @Date 2020/1/7 20:24
 */
public class UserCountData implements Serializable {
    private String orgname;
    private Long manNum;
    private Long womanNum;
    private Long total;

    public UserCountData() {
    }

    public UserCountData(String orgname, Long manNum, Long womanNum, Long total) {
        this.orgname = orgname;
        this.manNum = manNum;
        this.womanNum = womanNum;
        this.total = total;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public Long getManNum() {
        return manNum;
    }

    public void setManNum(Long manNum) {
        this.manNum = manNum;
    }

    public Long getWomanNum() {
        return womanNum;
    }

    public void setWomanNum(Long womanNum) {
        this.womanNum = womanNum;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
