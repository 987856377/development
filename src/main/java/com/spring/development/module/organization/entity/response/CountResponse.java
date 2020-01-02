package com.spring.development.module.organization.entity.response;

import java.io.Serializable;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.organization.entity.response
 * @Author xuzhenkui
 * @Date 2020/1/2 22:58
 */
public class CountResponse implements Serializable {
    private String name;
    private Integer value;

    public CountResponse() {
    }

    public CountResponse(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
