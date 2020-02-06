package com.spring.development.module.user.entity;

import com.spring.development.module.user.service.RoleService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module.user.entity
 * @Author xuzhenkui
 * @Date 2019/11/16 17:43
 */
public class UserDetail extends User implements UserDetails {

    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auth = new ArrayList<>();
        List<Role> roles = getRoles();
        for (Role role : roles){
            auth.add(new SimpleGrantedAuthority(role.getCode()));
        }
        return auth;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "roles=" + roles +
                '}';
    }
}
