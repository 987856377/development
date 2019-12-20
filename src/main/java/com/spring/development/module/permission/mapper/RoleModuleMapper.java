package com.spring.development.module.permission.mapper;

import com.spring.development.module.permission.entity.Module;
import com.spring.development.module.permission.entity.RoleModule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.development.module.permission.entity.response.RoleModuleResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author XuZhenkui
 * @since 2019-12-12
 */
public interface RoleModuleMapper extends BaseMapper<RoleModule> {

    List<RoleModuleResponse> getModulesByRoles(@Param("userRoles") List<String> userRoles, @Param("level") Integer level);

    List<RoleModuleResponse> getSubModulesByRoles(@Param("userRoles") List<String> userRoles, @Param("pid") Long pid, @Param("level") Integer level);

    List<RoleModuleResponse> getModulesByRoleList(@Param("own") Integer own, @Param("userRoles") List<String> userRoles, @Param("pid") String pid, @Param("level") Integer level);

    boolean addModulesByRole(@Param("rid") Long rid, @Param("midList") List<Long> midList);

    boolean delModulesByRole(@Param("rid") Long rid, @Param("midList") List<Long> midList);
}
