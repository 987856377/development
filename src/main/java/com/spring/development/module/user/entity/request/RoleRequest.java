package com.spring.development.module.user.entity.request;

import java.io.Serializable;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.user.entity.request
 * @Author xuzhenkui
 * @Date 2019/12/3 17:45
 */
public class RoleRequest implements Serializable {
    private Long id;
    private Integer flag;

    public RoleRequest() {
    }

    public RoleRequest(Long id, Integer flag) {
        this.id = id;
        this.flag = flag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "RoleRequest{" +
                "id=" + id +
                ", flag=" + flag +
                '}';
    }
}
