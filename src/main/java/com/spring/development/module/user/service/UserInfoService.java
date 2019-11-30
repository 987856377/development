package com.spring.development.module.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.development.module.user.entity.request.UserRequest;
import com.spring.development.module.user.entity.response.UserResponse;
import com.spring.development.module.user.mapper.UserInfoMapper;
import com.spring.development.module.user.entity.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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

    // 下面两个接口只需要传 orgcode 或 orgname 一个就可以了
    public List<String> getUserNameByOrgCodeOrName(UserInfo userInfo){
        return userInfoMapper.getUserNamesByOrgCode(userInfo.getOrgcode(),userInfo.getOrgname());
    }

    public List<UserInfo> getUserInfoByOrgCodeOrName(UserInfo userInfo){
        return userInfoMapper.getUserInfoByOrgCodeOrName(userInfo.getOrgcode(),userInfo.getOrgname());
    }

    public Page<UserResponse> userInfoPage(Page<UserResponse> page) {
        return userInfoMapper.userInfoPage(page);
    }

    public UserResponse getUserInfo(UserRequest request) {
        if (request.getUsername() == null){
            return null;
        }
        return userInfoMapper.getUserInfo(request.getUsername());
    }
}
