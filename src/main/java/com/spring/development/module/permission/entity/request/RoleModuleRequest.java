package com.spring.development.module.permission.entity.request;

import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.permission.entity.request
 * @Author xuzhenkui
 * @Date 2019/12/12 23:37
 */
public class RoleModuleRequest implements Serializable {
    private Long rid;
    private List<Long> midList;
//    flag: 1. 角色拥有的菜单 0. 角色未拥有的菜单
    private Integer own = 1;
    private List<String> userRoles;

    public RoleModuleRequest() {
    }

    public RoleModuleRequest(Long rid, List<Long> midList) {
        this.rid = rid;
        this.midList = midList;
    }

    public RoleModuleRequest(Integer own, List<String> userRoles) {
        this.own = own;
        this.userRoles = userRoles;
    }

    public RoleModuleRequest(Long rid, List<Long> midList, Integer own, List<String> userRoles) {
        this.rid = rid;
        this.midList = midList;
        this.own = own;
        this.userRoles = userRoles;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public List<Long> getMidList() {
        return midList;
    }

    public void setMidList(List<Long> midList) {
        this.midList = midList;
    }

    public Integer getOwn() {
        return own;
    }

    public void setOwn(Integer own) {
        this.own = own;
    }

    public List<String> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<String> userRoles) {
        this.userRoles = userRoles;
    }
}
