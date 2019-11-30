package com.spring.development.module.user.entity.request;

import java.io.Serializable;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.model.request
 * @Author xuzhenkui
 * @Date 2019/10/5 19:56
 */
public class UserRoleRequest implements Serializable {
    private Long uid;
    private String sourceRole;
    private String destRole = "USER";

    public UserRoleRequest(){
    }

    public UserRoleRequest(Long uid, String destRole){
        this.uid = uid;
        this.destRole = destRole;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getSourceRole() {
        return sourceRole;
    }

    public void setSourceRole(String sourceRole) {
        this.sourceRole = sourceRole;
    }

    public String getDestRole() {
        return destRole;
    }

    public void setDestRole(String destRole) {
        this.destRole = destRole;
    }
}
