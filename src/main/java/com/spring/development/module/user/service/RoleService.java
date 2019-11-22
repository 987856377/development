package com.spring.development.module.user.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.development.module.user.mapper.RoleMapper;
import com.spring.development.module.user.entity.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.user.service
 * @Author xuzhenkui
 * @Date 2019/10/2 15:35
 */
@Service(value = "roleService")
@Transactional
public class RoleService extends ServiceImpl<RoleMapper, Role> {
    @Resource
    private RoleMapper roleMapper;

    public Role getRoleById(Long id){
        if (id == null){
            return null;
        }
        return roleMapper.getRoleById(id);
    }

    public List<Role> getRolesByUid(Long uid){
        if (uid == null){
            return null;
        }
        return roleMapper.getRolesByUid(uid);
    }

    public Role getRoleByCode(String code){
        if (code == null){
            return null;
        }
        return roleMapper.getRoleByCode(code);
    }

    public List<String> getRoleCodes(){
        return roleMapper.getRoleCodes();
    }

    public List<Role> getRoleList(){
        return roleMapper.getRoleList();
    }
}
