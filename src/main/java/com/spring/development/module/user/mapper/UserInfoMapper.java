package com.spring.development.module.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.development.module.user.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.user.mapper
 * @Author xuzhenkui
 * @Date 2019/10/2 16:25
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    List<String> getUserNamesByOrgCode(@Param("orgcode") String orgcode, @Param("orgname") String orgname);

    List<UserInfo> getUserInfoByOrgCodeOrName(@Param("orgcode") String orgcode, @Param("orgname") String orgname);
}
