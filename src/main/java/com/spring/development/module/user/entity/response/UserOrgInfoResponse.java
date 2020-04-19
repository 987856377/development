package com.spring.development.module.user.entity.response;

import com.spring.development.module.organization.entity.response.OrgResponse;

import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.user.entity.response
 * @Author xuzhenkui
 * @Date 2020/4/19 23:08
 */
public class UserOrgInfoResponse implements Serializable {
    private Long id;
    private String name;
    private String orgcode;
    private String orgname;
    private String orgflag;

    private List<OrgResponse> subOrgList;

    public UserOrgInfoResponse() {
    }

    public UserOrgInfoResponse(Long id, String name, String orgcode, String orgname, String orgflag, List<OrgResponse> subOrgList) {
        this.id = id;
        this.name = name;
        this.orgcode = orgcode;
        this.orgname = orgname;
        this.orgflag = orgflag;
        this.subOrgList = subOrgList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
