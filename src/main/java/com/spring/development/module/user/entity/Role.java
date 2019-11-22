package com.spring.development.module.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.model
 * @Author xuzhenkui
 * @Date 2019/10/2 14:17
 */
@TableName("role")
public class Role extends Model<Role> {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "title")
    private String title;

    @TableField(value = "code")
    private String code;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "create_time")
    private Timestamp createTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "modify_time")
    private Timestamp modifyTime;

    @TableField(value = "flag")
    private Integer flag = 1;

    public Role(){}

    public Role(Long id, String title, String code, Timestamp createTime, Timestamp modifyTime, Integer flag) {
        this.id = id;
        this.title = title;
        this.code = code;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.flag = flag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", code='" + code + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", flag=" + flag +
                '}';
    }
}
