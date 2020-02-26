package com.spring.development.jwt;

import com.spring.development.datasource.JdbcDataSource;
import com.zaxxer.hikari.HikariDataSource;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;


/**
 * 该拦截器用于获取用户登录的信息，只需创建一个token并调用authenticationManager.authenticate()让spring-security去进行验证就可以了，这一步交给spring去操作。
 *
 * 验证用户名密码正确后，生成一个token，并将token返回给客户端
 * 该类继承自UsernamePasswordAuthenticationFilter，重写了其中的2个方法 ,
 * attemptAuthentication：接收并解析用户凭证。让 spring-security 去验证, 并返回用户信息与角色权限
 * successfulAuthentication：用户成功登录后，这个方法会被调用，我们在这个方法里生成token并返回。
 *
 */
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtLoginFilter.class);

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcDataSource.getDataSource());

    private AuthenticationManager authenticationManager;

    public JwtLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        logger.info("JwtLoginFilter: attemptAuthentication: " + request.getRequestURL()+"   Method: "+request.getMethod());
        logger.info("JwtLoginFilter: username: " + request.getParameter("username"));
        logger.info("JwtLoginFilter: password: " + request.getParameter("password"));

        String username = request.getParameter("username");
        String password = request.getParameter("password");
//        验证用户名密码,让 spring-security 去验证
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>())
        );
    }

//    验证成功后会返回用户信息及角色权限
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        try {

            Claims claims = Jwts.claims();
            claims.put("roles", authResult.getAuthorities().stream().map(s -> s.getAuthority()).collect(Collectors.toList()));
            String token = Jwts.builder()
                    .setClaims(claims)
                    .setSubject(authResult.getName())
                    .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))  // 设置过期时间1天
                    .signWith(SignatureAlgorithm.HS512, "JsonWebToken")        //采用什么算法是可以自己选择的，不一定非要采用HS512
                    .compact();

            String sql = "update user set last_login_time = ? where username = ?";
            jdbcTemplate.update(sql, new Timestamp(System.currentTimeMillis()), authResult.getName());

//            Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
//            // 定义存放角色集合的对象
//            List roleList = new ArrayList<>();
//            for (GrantedAuthority grantedAuthority : authorities) {
//                //noinspection unchecked
//                roleList.add(grantedAuthority.getAuthority());
//            }
//
//            String token = Jwts.builder()
//                    .setSubject(authResult.getName() + "-" + roleList)
//                    .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000)) // 设置过期时间1天
//                    .signWith(SignatureAlgorithm.HS512, "JsonWebToken") //采用什么算法是可以自己选择的，不一定非要采用HS512
//                    .compact();

            // 登录成功后，返回token到header里面
            response.addHeader("Authorization", "Bearer " + token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        //返回json形式的错误信息
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        response.getWriter().println("{\"code\":409,\"message\":\"用户名密码验证失败\",\"data\":\"\"}");
        response.getWriter().flush();
    }


}
