package com.spring.development.jwt;

import com.spring.development.security.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * token的校验
 * 该类继承自BasicAuthenticationFilter，在doFilterInternal方法中，
 * 从http头的Authorization 项读取token数据，然后用Jwts包提供的方法校验token的合法性。
 * 如果校验通过，就认为这是一个取得授权的合法请求
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


//    在拦截器中获取token并解析，拿到用户信息，放置到SecurityContextHolder，这样便完成了springsecurity和jwt的整合。
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        logger.info("JwtAuthenticationFilter:           doFilterInternal: " + request.getRequestURL());

        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            Claims claims = Jwts.parser().setSigningKey("JsonWebToken").parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody();
            String user  = claims.getSubject();
            @SuppressWarnings("unchecked")
            List<String> roles = claims.get("roles", List.class);
            List<SimpleGrantedAuthority> auth = roles.stream().map(s -> new SimpleGrantedAuthority(s)).collect(Collectors.toList());

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, auth);
            }

//            // parse the token.
//            String user = Jwts.parser()
//                    .setSigningKey("JsonWebToken")
//                    .parseClaimsJws(token.replace("Bearer ", ""))
//                    .getBody()
//                    .getSubject();
//
//            if (user != null) {
//                System.out.println(user);
//                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
//            }
            return null;
        }
        return null;
    }
}
