package com.spring.development.module.prescription.entity.response;

import java.io.Serializable;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.prescription.entity.response
 * @Author xuzhenkui
 * @Date 2020/1/7 21:58
 */
public class PrescriptionCountData implements Serializable {
    private String orgname;
    private Long local;
    private Long outside;
    private Long normal;
    private Long special;
    private Long total;

    public PrescriptionCountData() {
    }

    public PrescriptionCountData(String orgname, Long local, Long outside, Long normal, Long special, Long total) {
        this.orgname = orgname;
        this.local = local;
        this.outside = outside;
        this.normal = normal;
        this.special = special;
        this.total = total;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public Long getLocal() {
        return local;
    }

    public void setLocal(Long local) {
        this.local = local;
    }

    public Long getOutside() {
        return outside;
    }

    public void setOutside(Long outside) {
        this.outside = outside;
    }

    public Long getNormal() {
        return normal;
    }

    public void setNormal(Long normal) {
        this.normal = normal;
    }

    public Long getSpecial() {
        return special;
    }

    public void setSpecial(Long special) {
        this.special = special;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
