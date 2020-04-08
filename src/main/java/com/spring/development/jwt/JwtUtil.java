package com.spring.development.jwt;

import com.spring.development.module.user.entity.Role;
import com.spring.development.module.user.entity.User;
import com.spring.development.module.user.entity.UserDetail;
import com.spring.development.util.PropertyUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtil implements Serializable {

/*
    private static final String ISSUER = "Prescription Circulation Platform";   // 发行机构
    private static final String SUBJECT = "Authorization Token";                // 主题
    private static final long EXPIRATION = 1000 * 60 * 60 * 24 * 7;             // 过期时间为一周

    private static final String APPSECRET = "JsonWebToken";                     // 签名密钥
    */

    private String ISSUER = (String) PropertyUtil.load("JWT_ISSUER","application.properties").get(0);
    private String SUBJECT = (String) PropertyUtil.load("JWT_SUBJECT","application.properties").get(0);
    private String EXPIRATION = (String) PropertyUtil.load("JWT_EXPIRATION","application.properties").get(0);
    private String APPSECRET = (String) PropertyUtil.load("JWT_APPSECRET","application.properties").get(0);

    public JwtUtil() throws IOException {
    }


    private String tokenBuilder(Claims claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuer(ISSUER)      // 发行人
                .setSubject(SUBJECT)    // 主题
                .setAudience(claims.get("username").toString())  // 接收方 用户
                .setIssuedAt(new Date())    // 发行时间
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(EXPIRATION)))  // 设置过期时间7天
                .signWith(SignatureAlgorithm.HS512, APPSECRET)        // 签名算法
                .compact();
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(APPSECRET).parseClaimsJws(token.replace("Bearer ", "")).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public String generateToken(UserDetails userDetails) {
        Claims claims = Jwts.claims();
        claims.put("username", userDetails.getUsername());
        claims.put("roles", userDetails.getAuthorities().stream().map(s -> s.getAuthority()).collect(Collectors.toList()));
        return tokenBuilder(claims);
    }

    public String generateToken(Authentication authentication) {
        Claims claims = Jwts.claims();
        claims.put("username", authentication.getName());
        claims.put("roles", authentication.getAuthorities().stream().map(s -> s.getAuthority()).collect(Collectors.toList()));
        return tokenBuilder(claims);
    }

    public String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getAudience();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        User user = (User) userDetails;
        String username = getUsernameFromToken(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }

    public static void main(String[] args) throws IOException {
        JwtUtil util = new JwtUtil();

        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJ1c2VybmFtZSI6ImFkbWluIiwicm9sZXMiOlsiQURNSU4iLCJEQkEiLCJBVURJVCJdLCJpc3MiOiJQcmVzY3JpcHRpb24gQ2lyY3VsYXRpb24gUGxhdGZvcm0iLCJzdWIiOiJBdXRob3JpemF0aW9uIFRva2VuIiwiYXVkIjoiYWRtaW4iLCJpYXQiOjE1ODU5ODU2ODEsImV4cCI6MTU4NjU5MDQ4MX0.3IGusrZXpu_HkiZhf3B-MbKzKZrVFnS1CuD-iHLr4PEJU9kHIkQieIl5piSlqvJuXLxM-uXLw05TCZNALjgSJA";

        String user = Jwts.parser()
                .setSigningKey("JsonWebToken")
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody()
                .getSubject();

        Claims claimsFromToken = util.getClaimsFromToken(token);
        System.out.println(claimsFromToken);

        System.out.println(claimsFromToken.get("username"));
        System.out.println(claimsFromToken.getAudience());

        UserDetail userDetail = new UserDetail();
        userDetail.setUsername("frank");
        List<Role> list = new ArrayList();
        Role role = new Role();
        role.setCode("ADMIN");
        list.add(role);
        userDetail.setRoles(list);

        String token1 = "Bearer " + util.generateToken((UserDetails) userDetail);
        System.out.println(token1);

        System.out.println(util.getUsernameFromToken(token1));
    }
}

