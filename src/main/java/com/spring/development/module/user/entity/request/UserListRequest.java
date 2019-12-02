package com.spring.development.module.user.entity.request;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.development.module.user.entity.response.UserResponse;

import java.io.Serializable;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.user.entity.request
 * @Author xuzhenkui
 * @Date 2019/12/2 10:23
 */
public class UserListRequest implements Serializable {
    Page<UserResponse> page;
    String orgflag;

    public UserListRequest() {
    }

    public UserListRequest(Page<UserResponse> page, String orgflag) {
        this.page = page;
        this.orgflag = orgflag;
    }

    public Page<UserResponse> getPage() {
        return page;
    }

    public void setPage(Page<UserResponse> page) {
        this.page = page;
    }

    public String getOrgflag() {
        return orgflag;
    }

    public void setOrgflag(String orgflag) {
        this.orgflag = orgflag;
    }
}
