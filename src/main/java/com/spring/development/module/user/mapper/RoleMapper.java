package com.spring.development.module.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.development.module.user.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.user.mapper
 * @Author xuzhenkui
 * @Date 2019/10/2 15:34
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {
    Role getRoleById(@Param("id") Long id);
    List<Role> getRolesByUid(@Param("uid") Long uid);
    Role getRoleByCode(@Param("code") String code);
    List<String> getRoleCodes();
    List<Role> getRoleList();

    boolean updateRoleState(@Param("id") Long id, @Param("timestamp") Timestamp timestamp, @Param("flag")  Integer flag);
}
