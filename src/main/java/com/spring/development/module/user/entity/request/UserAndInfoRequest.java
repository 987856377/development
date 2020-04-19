package com.spring.development.module.user.entity.request;

import com.spring.development.module.user.entity.User;
import com.spring.development.module.user.entity.UserInfo;

import java.io.Serializable;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.user.entity.request
 * @Author xuzhenkui
 * @Date 2020/4/20 0:13
 */
public class UserAndInfoRequest implements Serializable {
    private User user;
    private UserInfo userInfo;

    public UserAndInfoRequest() {
    }

    public UserAndInfoRequest(User user, UserInfo userInfo) {
        this.user = user;
        this.userInfo = userInfo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
