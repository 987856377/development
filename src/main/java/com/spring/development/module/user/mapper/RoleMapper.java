package com.spring.development.module.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.development.module.user.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.user.mapper
 * @Author xuzhenkui
 * @Date 2019/10/2 15:34
 */
public interface RoleMapper extends BaseMapper<Role> {
    public Role getRoleById(@Param("id") Long id);
    public List<Role> getRolesByUid(@Param("uid") Long uid);
    public Role getRoleByCode(@Param("code") String code);
    public List<String> getRoleCodes();
    public List<Role> getRoleList();
}
