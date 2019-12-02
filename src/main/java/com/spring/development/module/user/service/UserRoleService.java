package com.spring.development.module.user.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.development.module.user.entity.Role;
import com.spring.development.module.user.entity.request.UserRoleRequest;
import com.spring.development.module.user.mapper.UserRoleMapper;
import com.spring.development.module.user.entity.UserRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.user.service
 * @Author xuzhenkui
 * @Date 2019/10/2 16:24
 */
@Service(value = "userRoleService")
@Transactional
public class UserRoleService extends ServiceImpl<UserRoleMapper, UserRole> {
    @Resource
    private UserRoleMapper userRoleMapper;

    public Boolean addUserRole(Long uid, Long rid){
        if (uid == null || rid == null){
            return false;
        }
        return userRoleMapper.create(uid, rid);
    }

    public Boolean setUserRole(Long uid, Long sourceId, Long destId){
        if (uid == null || sourceId == null || destId == null){
            return false;
        }
        return userRoleMapper.setUserRole(uid, sourceId, destId);
    }

    public Boolean delUserRole(Long uid, Long rid){
        if (uid == null || rid == null){
            return false;
        }
        return userRoleMapper.delUserRole(uid, rid);
    }

    public List<Role> getRoleList(UserRoleRequest request) {
        if (request.getUid() == null){
            return null;
        }
        return userRoleMapper.getRoleList(request.getUid());
    }

    public List<Role> getRoleListAvalible(UserRoleRequest request) {
        if (request.getUid() == null){
            return null;
        }
        return userRoleMapper.getRoleListAvalible(request.getUid());
    }
}
