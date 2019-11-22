package com.spring.development.module.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Date;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.model
 * @Author xuzhenkui
 * @Date 2019/10/2 14:08
 */
@TableName("user_info")
public class UserInfo extends Model<UserInfo> {
    @TableId(value = "id",type = IdType.INPUT)
    Long id;

    @TableField(value = "orgcode")
    String orgcode;

    @TableField(value = "orgname")
    String orgname;

    @TableField(value = "name")
    String name;

    @TableField(value = "identity")
    String identity;

    @TableField(value = "sex")
    String sex;

    @TableField(value = "age")
    Integer age;

    @TableField(value = "nation")
    String nation;

    @TableField(value = "phone")
    String phone;

    @TableField(value = "mail")
    String mail;

    public UserInfo(){}

    public UserInfo(Long id, String orgcode, String orgname, String name, String identity, String sex, Integer age, String nation, String phone, String mail) {
        this.id = id;
        this.orgcode = orgcode;
        this.orgname = orgname;
        this.name = name;
        this.identity = identity;
        this.sex = sex;
        this.age = age;
        this.nation = nation;
        this.phone = phone;
        this.mail = mail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
