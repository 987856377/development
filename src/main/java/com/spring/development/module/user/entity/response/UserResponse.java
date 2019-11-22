package com.spring.development.module.user.entity.response;

import com.baomidou.mybatisplus.extension.api.R;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.development.module.user.entity.Role;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.model.VO
 * @Author xuzhenkui
 * @Date 2019/10/2 19:31
 */
public class UserResponse implements Serializable {
    private Long id;
    private String username;
    private String realname;
    private String identity;
    private String sex;
    private String age;
    private String phone;
    private String mail;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp registerTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp modifyTime;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Timestamp lastLoginTime;
    private Integer flag;
    private List<Role> role;

    public UserResponse(){}

    public UserResponse(Long id, String username, String realname, String identity, String sex, String age, String phone, String mail, Timestamp registerTime, Timestamp modifyTime, Timestamp lastLoginTime, Integer flag, List<Role> role) {
        this.id = id;
        this.username = username;
        this.realname = realname;
        this.identity = identity;
        this.sex = sex;
        this.age = age;
        this.phone = phone;
        this.mail = mail;
        this.registerTime = registerTime;
        this.modifyTime = modifyTime;
        this.lastLoginTime = lastLoginTime;
        this.flag = flag;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }
}
