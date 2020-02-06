package com.spring.development.module.user.entity.request;

import java.io.Serializable;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.user.entity.request
 * @Author xuzhenkui
 * @Date 2019/11/13 11:44
 */
public class UserRequest implements Serializable {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String orgcode;
    private String identity;
    private String sex;
    private String nation;
    private String phone;
    private Integer flag;

    public UserRequest() {
    }

    public UserRequest(Long id, String username, String password, String name, String orgcode, String identity, String sex, String nation, String phone, Integer flag) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.orgcode = orgcode;
        this.identity = identity;
        this.sex = sex;
        this.nation = nation;
        this.phone = phone;
        this.flag = flag;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
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

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", orgcode='" + orgcode + '\'' +
                ", identity='" + identity + '\'' +
                ", sex='" + sex + '\'' +
                ", nation='" + nation + '\'' +
                ", phone='" + phone + '\'' +
                ", flag=" + flag +
                '}';
    }
}
