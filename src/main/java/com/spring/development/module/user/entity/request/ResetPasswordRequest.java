package com.spring.development.module.user.entity.request;

import java.io.Serializable;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.user.entity.request
 * @Author xuzhenkui
 * @Date 2019/12/22 19:39
 */
public class ResetPasswordRequest implements Serializable {
    private Long id;
    private String raw;
    private String password;

    public ResetPasswordRequest() {
    }

    public ResetPasswordRequest(Long id, String raw, String password) {
        this.id = id;
        this.raw = raw;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
