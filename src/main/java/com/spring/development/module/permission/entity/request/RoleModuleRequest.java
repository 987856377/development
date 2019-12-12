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
    private List<String> userRoles;

    public RoleModuleRequest() {
    }

    public RoleModuleRequest(List<String> userRoles) {
        this.userRoles = userRoles;
    }

    public List<String> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<String> userRoles) {
        this.userRoles = userRoles;
    }
}
