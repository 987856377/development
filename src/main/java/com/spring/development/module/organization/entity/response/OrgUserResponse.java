package com.spring.development.module.organization.entity.response;

import com.spring.development.module.user.entity.UserInfo;

import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.organization.entity.response
 * @Author xuzhenkui
 * @Date 2019/12/9 21:55
 */
public class OrgUserResponse implements Serializable {
    private String code;
    private List<TargetUser> targetUserList;

    public OrgUserResponse() {
    }

    public OrgUserResponse(String code, List<TargetUser> targetUserList) {
        this.code = code;
        this.targetUserList = targetUserList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<TargetUser> getTargetUserList() {
        return targetUserList;
    }

    public void setTargetUserList(List<TargetUser> targetUserList) {
        this.targetUserList = targetUserList;
    }
}
