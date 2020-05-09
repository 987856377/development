package com.spring.development.module.user.entity.request;

import java.io.Serializable;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.user.entity.request
 * @Author xuzhenkui
 * @Date 2020/5/9 19:31
 */
public class CountUserRequest implements Serializable {
    private String orgflag;

    public CountUserRequest() {
    }

    public CountUserRequest(String orgflag) {
        this.orgflag = orgflag;
    }

    public String getOrgflag() {
        return orgflag;
    }

    public void setOrgflag(String orgflag) {
        this.orgflag = orgflag;
    }

    @Override
    public String toString() {
        return "CountUserRequest{" +
                "orgflag='" + orgflag + '\'' +
                '}';
    }
}
