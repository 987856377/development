package com.spring.development.module.user.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.spring.development.module.user.entity.User;
import com.spring.development.module.user.entity.UserDetail;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.dao
 * @Author xuzhenkui
 * @Date 2019/9/18 14:59
 */
public interface UserMapper extends BaseMapper<User> {

//    自定义SQL查询: 1.注解方式 2.xml方式
//    @Select("select * from user where username = #{username}")
    List<User> getByUsername(@Param("username") String username);

//    @Select("select * from user limit #{offset}, #{pageSize}")
    List<User> getUserByPage(@Param("offset") long offset, @Param("pageSize") long pageSize);

//    自定义SQL查询: 使用Wrapper
//    @Select("select * from user ${ew.customSqlSegment}")
    List<User> getByWrapper(@Param(Constants.WRAPPER) Wrapper wrapper);

    Long getIdByNameAndCode(@Param("name") String name,@Param("code")  String code);

    boolean cancelUserByOrgCode(@Param("orgcode") String orgcode,@Param("flag")  Integer flag);

    boolean cancelUserById(@Param("id") Long id,@Param("flag")  Integer flag);

    UserDetail loadUserByUsername(@Param("username") String username);

    boolean updateLastLoginTime(@Param("username") String username, @Param("lastLoginTime") Timestamp lastLoginTime);

    Long getIdByUsername(@Param("username") String username);

    String getRealNameById(@Param("id") Long id);
}

