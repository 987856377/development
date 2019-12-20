package com.spring.development.module.permission.entity.response;

import com.spring.development.module.permission.entity.Module;

import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.permission.entity
 * @Author xuzhenkui
 * @Date 2019/12/12 23:46
 */
public class RoleModuleResponse implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 菜单名称
     */
    private String label;

    /**
     * 父菜单id
     */
    private Integer pid;

    /**
     * 菜单url
     */
    private String url;

    /**
     * 菜单层级
     */
    private String level;

    /**
     * 启用标志
     */
    private Integer flag;

    List<RoleModuleResponse> children;

    public RoleModuleResponse() {
    }

    public RoleModuleResponse(Long id, String label, Integer pid, String url, String level, Integer flag, List<RoleModuleResponse> children) {
        this.id = id;
        this.label = label;
        this.pid = pid;
        this.url = url;
        this.level = level;
        this.flag = flag;
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public List<RoleModuleResponse> getChildren() {
        return children;
    }

    public void setChildren(List<RoleModuleResponse> children) {
        this.children = children;
    }
}
