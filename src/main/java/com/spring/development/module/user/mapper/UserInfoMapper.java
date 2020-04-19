package com.spring.development.module.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spring.development.module.user.entity.UserInfo;
import com.spring.development.module.user.entity.response.UserCountData;
import com.spring.development.module.user.entity.response.UserOrgInfoResponse;
import com.spring.development.module.user.entity.response.UserResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.user.mapper
 * @Author xuzhenkui
 * @Date 2019/10/2 16:25
 */
@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    List<String> getUserNamesByOrgCode(@Param("orgcode") String orgcode, @Param("orgname") String orgname);

    List<UserInfo> getUserInfoByOrgCodeOrName(@Param("orgcode") String orgcode, @Param("orgname") String orgname);

    Page<UserResponse> userInfoPage(Page<UserResponse> page, @Param("orgflag") String orgflag);

    UserResponse getUserInfo(@Param("username") String username);

    UserInfo getUserInfoByUsername(@Param("username") String username);

    List<UserCountData> countUser();

    UserInfo getOne(@Param("id") Long id, @Param("identity") String identity, @Param("phone") String phone, @Param("mail") String mail);

    UserOrgInfoResponse getUserAndOrgInfoByUsername(@Param("username") String username);
}
