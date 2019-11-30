package com.spring.development.module.organization.entity.response;

import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.organization.entity
 * @Author xuzhenkui
 * @Date 2019/11/30 13:27
 */
public class OrgResponse implements Serializable {
    private String name;
    private String code;
    private String orgflag;
    private List<OrgResponse> subOrgList;

    public OrgResponse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrgflag() {
        return orgflag;
    }

    public void setOrgflag(String orgflag) {
        this.orgflag = orgflag;
    }

    public List<OrgResponse> getSubOrgList() {
        return subOrgList;
    }

    public void setSubOrgList(List<OrgResponse> subOrgList) {
        this.subOrgList = subOrgList;
    }
}
