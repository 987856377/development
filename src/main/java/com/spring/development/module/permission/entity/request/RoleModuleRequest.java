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
    private Long mid;
//    flag: 1. 角色拥有的菜单 0. 角色未拥有的菜单
    private Integer own = 1;
    private List<String> userRoles;

    public RoleModuleRequest() {
    }

    public RoleModuleRequest(Integer own, List<String> userRoles) {
        this.own = own;
        this.userRoles = userRoles;
    }

    public RoleModuleRequest(Long rid, Long mid, Integer own, List<String> userRoles) {
        this.rid = rid;
        this.mid = mid;
        this.own = own;
        this.userRoles = userRoles;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
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
