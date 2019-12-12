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
    private String title;

    /**
     * 父菜单id
     */
    private Integer parentId;

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

    List<RoleModuleResponse> subModule;

    public RoleModuleResponse() {
    }

    public RoleModuleResponse(Long id, String title, Integer parentId, String url, String level, Integer flag, List<RoleModuleResponse> subModule) {
        this.id = id;
        this.title = title;
        this.parentId = parentId;
        this.url = url;
        this.level = level;
        this.flag = flag;
        this.subModule = subModule;
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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

    public List<RoleModuleResponse> getSubModule() {
        return subModule;
    }

    public void setSubModule(List<RoleModuleResponse> subModule) {
        this.subModule = subModule;
    }
}
