package com.spring.development.module.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.model
 * @Author xuzhenkui
 * @Date 2019/10/2 14:06
 */
@TableName("user")
public class User extends Model<User> {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "username")
    private String username;

    @TableField(value = "password")
    private String password;

    @TableField(value = "header")
    private String header;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "register_time")
    private Timestamp registerTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "modify_time")
    private Timestamp modifyTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "last_login_time")
    private Timestamp lastLoginTime;

    @TableField(value = "flag")
    private Integer flag = 1;

    public User(){}

    public User(Long id, String username, String password, Timestamp registerTime, Timestamp modifyTime, Timestamp lastLoginTime, Integer flag) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.registerTime = registerTime;
        this.modifyTime = modifyTime;
        this.lastLoginTime = lastLoginTime;
        this.flag = flag;
    }

    public User(Long id, String username, String password, String header, Timestamp registerTime, Timestamp modifyTime, Timestamp lastLoginTime, Integer flag) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.header = header;
        this.registerTime = registerTime;
        this.modifyTime = modifyTime;
        this.lastLoginTime = lastLoginTime;
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

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", registerTime=" + registerTime +
                ", modifyTime=" + modifyTime +
                ", lastLoginTime=" + lastLoginTime +
                ", flag=" + flag +
                '}';
    }
}
