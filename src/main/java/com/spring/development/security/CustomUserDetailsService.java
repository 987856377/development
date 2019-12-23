package com.spring.development.security;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.spring.development.module.user.entity.User;
import com.spring.development.module.user.entity.UserDetail;
import com.spring.development.module.user.service.RoleService;
import com.spring.development.module.user.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.security
 * @Author xuzhenkui
 * @Date 2019/11/16 16:56
 */
@Service
public class CustomUserDetailsService  implements UserDetailsService {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetail user = userService.loadUserByUsername(s);
        if (user == null){
            throw new UsernameNotFoundException("未查询到用户："+s+"信息！");
        }
        userService.update(new UpdateWrapper<User>().set("modify_time",new Timestamp(System.currentTimeMillis())).eq("username",user.getUsername()));
        user.setRoles(roleService.getRolesByUid(user.getId()));
        return user;
    }
}
