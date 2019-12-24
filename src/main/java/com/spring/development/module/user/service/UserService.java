package com.spring.development.module.user.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.development.module.user.entity.UserDetail;
import com.spring.development.module.user.mapper.UserMapper;
import com.spring.development.module.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.user.service
 * @Author xuzhenkui
 * @Date 2019/9/18 15:00
 */
@Service(value = "userService")
@Transactional
public class UserService extends ServiceImpl<UserMapper, User> {
    @Resource
    private UserMapper userMapper;

    public List<User> selectList(Wrapper wrapper){
        return userMapper.selectList(wrapper);
    }

    public List<User> getByUsername(String username){
        if (username == null){
            return null;
        }
        return userMapper.getByUsername(username);
    }

    public Long getIdByUsername(String username){
        if (username == null){
            return null;
        }
        return userMapper.getIdByUsername(username);
    }

    public UserDetail loadUserByUsername(String username){
        return userMapper.loadUserByUsername(username);
    }

    public List<User> getUserByPage(long offset, long pageSize){
        return userMapper.getUserByPage(offset,pageSize);
    }

    public List<User> getByWrapper(Wrapper wrapper){
        return userMapper.getByWrapper(wrapper);
    }

    public Long getIdByNameAndCode(String name, String code){
        return userMapper.getIdByNameAndCode(name,code);
    }

    public boolean updateLastLoginTime(String username, Timestamp lastLoginTime) {
        if (username == null || lastLoginTime == null){
            return false;
        }
        return userMapper.updateLastLoginTime(username, lastLoginTime);
    }

    public boolean cancelUserByOrgCode(String orgcode, Integer flag) {
        if (orgcode == null || flag == null){
            return false;
        }
        return userMapper.cancelUserByOrgCode(orgcode,flag);
    }

    public boolean cancelUserById(Long id, Integer flag) {
        if (id == null | flag == null){
            return false;
        } else if (flag == 1 && userMapper.getOrgFlagByUid(id) == 0){
            return false;
        }
        return userMapper.cancelUserById(id,flag);
    }

    public String getRealNameById(Long id) {
        if (id == null){
            return null;
        }
        return userMapper.getRealNameById(id);
    }
}
