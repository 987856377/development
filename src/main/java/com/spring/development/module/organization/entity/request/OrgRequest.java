package com.spring.development.module.organization.entity.request;

import java.io.Serializable;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.organization.entity.request
 * @Author xuzhenkui
 * @Date 2019/11/12 21:03
 */
public class OrgRequest implements Serializable {
    private Long id;
    private String code;
    private String orgflag;
    private String name;
    private Integer classify;
    private Integer type;
    private Integer host;
    private Integer relation;
    private String phone;
    private String responser;
    private String officer;
    private String supervising;
    private Integer flag;
    private Long current;
    private Integer size;

    public OrgRequest() {
    }

    public OrgRequest(Long id, String code, String orgflag, String name, Integer classify, Integer type, Integer host, Integer relation, String phone, String responser, String officer, String supervising, Integer flag, Long current, Integer size) {
        this.id = id;
        this.code = code;
        this.orgflag = orgflag;
        this.name = name;
        this.classify = classify;
        this.type = type;
        this.host = host;
        this.relation = relation;
        this.phone = phone;
        this.responser = responser;
        this.officer = officer;
        this.supervising = supervising;
        this.flag = flag;
        this.current = current;
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getHost() {
        return host;
    }

    public void setHost(Integer host) {
        this.host = host;
    }

    public Integer getRelation() {
        return relation;
    }

    public void setRelation(Integer relation) {
        this.relation = relation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getResponser() {
        return responser;
    }

    public void setResponser(String responser) {
        this.responser = responser;
    }

    public String getOfficer() {
        return officer;
    }

    public void setOfficer(String officer) {
        this.officer = officer;
    }

    public String getSupervising() {
        return supervising;
    }

    public void setSupervising(String supervising) {
        this.supervising = supervising;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
