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
    private Integer flag = 1;

    public UserRoleRequest(){
    }

    public UserRoleRequest(Long uid, String destRole){
        this.uid = uid;
        this.destRole = destRole;
    }

    public UserRoleRequest(Long uid, String destRole, Integer flag) {
        this.uid = uid;
        this.destRole = destRole;
        this.flag = flag;
    }

    public UserRoleRequest(Long uid, String sourceRole, String destRole, Integer flag) {
        this.uid = uid;
        this.sourceRole = sourceRole;
        this.destRole = destRole;
        this.flag = flag;
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

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "UserRoleRequest{" +
                "uid=" + uid +
                ", sourceRole='" + sourceRole + '\'' +
                ", destRole='" + destRole + '\'' +
                ", flag=" + flag +
                '}';
    }
}
