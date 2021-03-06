package com.spring.development.module.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.development.module.organization.entity.request.OrgRequest;
import com.spring.development.module.organization.service.OrganizationService;
import com.spring.development.module.user.entity.request.CountUserRequest;
import com.spring.development.module.user.entity.request.UserListRequest;
import com.spring.development.module.user.entity.request.UserRequest;
import com.spring.development.module.user.entity.response.UserCountData;
import com.spring.development.module.user.entity.response.UserCountResponse;
import com.spring.development.module.user.entity.response.UserOrgInfoResponse;
import com.spring.development.module.user.entity.response.UserResponse;
import com.spring.development.module.user.mapper.UserInfoMapper;
import com.spring.development.module.user.entity.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.user.service
 * @Author xuzhenkui
 * @Date 2019/10/2 16:26
 */
@Service(value = "userInfoService")
@Transactional
public class UserInfoService extends ServiceImpl<UserInfoMapper, UserInfo> {
    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private OrganizationService organizationService;

    // 下面两个接口只需要传 orgcode 或 orgname 一个就可以了
    public List<String> getUserNameByOrgCodeOrName(UserInfo userInfo){
        return userInfoMapper.getUserNamesByOrgCode(userInfo.getOrgcode(),userInfo.getOrgname());
    }

    public List<UserInfo> getUserInfoByOrgCodeOrName(UserInfo userInfo){
        return userInfoMapper.getUserInfoByOrgCodeOrName(userInfo.getOrgcode(),userInfo.getOrgname());
    }

    public Page<UserResponse> userInfoPage(UserListRequest request) {
        return userInfoMapper.userInfoPage(request.getPage(),request.getOrgflag());
    }

    public UserResponse getUserInfo(UserRequest request) {
        if (request.getUsername() == null){
            return null;
        }
        return userInfoMapper.getUserInfo(request.getUsername());
    }

    public UserInfo getUserInfoByUsername(UserRequest request) {
        if (request.getUsername() == null){
            return null;
        }
        return userInfoMapper.getUserInfoByUsername(request.getUsername());
    }

    public UserCountResponse countUser(CountUserRequest countUserRequest) {
        UserCountResponse response = new UserCountResponse();
        List<UserCountData> dataList = userInfoMapper.countUser(countUserRequest.getOrgflag());
        response.setOrgNameList(dataList.stream().map(UserCountData::getOrgname).collect(Collectors.toList()));
        response.setOrgManList(dataList.stream().map(UserCountData::getManNum).collect(Collectors.toList()));
        response.setOrgWomanList(dataList.stream().map(UserCountData::getWomanNum).collect(Collectors.toList()));
        response.setOrgTotalList(dataList.stream().map(UserCountData::getTotal).collect(Collectors.toList()));
        return response;
    }

    public UserInfo getOne(UserInfo userInfo) {
        if (userInfo.getId() == null || userInfo.getIdentity() == null || userInfo.getPhone() == null|| userInfo.getMail() == null){
            return null;
        }
        return userInfoMapper.getOne(userInfo.getId(),userInfo.getIdentity(),userInfo.getPhone(),userInfo.getMail());
    }

    public UserOrgInfoResponse getUserAndOrgInfoByUsername(UserRequest request) {
        UserOrgInfoResponse response = userInfoMapper.getUserAndOrgInfoByUsername(request.getUsername());
        OrgRequest orgRequest = new OrgRequest();
        orgRequest.setOrgflag(response.getOrgflag());
        response.setSubOrgList(organizationService.getSubOrg(orgRequest));
        return response;
    }
}
