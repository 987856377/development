package com.spring.development.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.security
 * @Author xuzhenkui
 * @Date 2019/11/16 17:08
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private CustomUserDetailsService customUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        UserDetails user = customUserDetailsService.loadUserByUsername(username);
        if(user==null) {
            throw new BadCredentialsException("用户名未找到");
        }

        //加密过程在这里体现
        if(!password.equals(user.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}

