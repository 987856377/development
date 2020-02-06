package com.spring.development.module.organization.entity.response;

import java.io.Serializable;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.organization.entity.response
 * @Author xuzhenkui
 * @Date 2019/12/9 22:05
 */
public class TargetUser implements Serializable {
    private Long id;
    private String name;

    public TargetUser() {
    }

    public TargetUser(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "TargetUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
