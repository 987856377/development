package com.spring.development.module.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.model
 * @Author xuzhenkui
 * @Date 2019/10/2 14:20
 */
@TableName("user_role")
public class UserRole extends Model<UserRole> {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "uid")
    private Long uid;

    @TableField(value = "rid" )
    private Long rid;

    public UserRole(){}

    public UserRole(Long uid, Long rid) {
        this.uid = uid;
        this.rid = rid;
    }

    public UserRole(Long id, Long uid, Long rid) {
        this.id = id;
        this.uid = uid;
        this.rid = rid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", uid=" + uid +
                ", rid=" + rid +
                '}';
    }
}
