package com.spring.development.jwt;

import com.spring.development.security.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * OncePerRequestFilter过滤器保证一次请求只调用一次doFilterInternal方法;如内部的forward不会再多执行一次
 */
// 不启用 JwtAuthenticationTokenFilter , 将 @Component 注释掉, 如果启用, 在取消注释
//@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        logger.info("JwtAuthenticationTokenFilter: " + request.getRequestURL());

        String token = request.getHeader("Authorization");

        if (!StringUtils.isEmpty(token)) {
            String username = jwtUtil.getUsernameFromToken(token);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
                if (jwtUtil.validateToken(token, userDetails)){
                    // 将用户信息存入 authentication，方便后续校验
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // 将 authentication 存入 ThreadLocal，方便后续获取用户信息
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }
}

