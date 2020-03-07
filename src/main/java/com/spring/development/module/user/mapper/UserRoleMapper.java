package com.spring.development.module.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.development.module.user.entity.Role;
import com.spring.development.module.user.entity.UserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.user.mapper
 * @Author xuzhenkui
 * @Date 2019/10/2 16:23
 */
@Repository
public interface UserRoleMapper extends BaseMapper<UserRole> {
    Boolean create(@Param("uid") Long uid, @Param("rid") Long rid, @Param("flag") Integer flag);
    Boolean setUserRole(@Param("uid") Long uid, @Param("sourceId") Long sourceId, @Param("destId") Long destId);
    Boolean delUserRole(@Param("uid") Long uid, @Param("rid") Long rid);
    List<UserRole> getUserRoleByUid(@Param("uid") Long uid);

    List<Role> getRoleList(@Param("uid") Long uid);

    List<Role> getRoleListAvalible(@Param("uid") Long uid, @Param("flag") Integer flag);

    boolean updateRoleStateFlag(@Param("rid") Long rid, @Param("flag") Integer flag);
}
