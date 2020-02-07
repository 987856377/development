package com.spring.development.jwt;

import com.spring.development.module.user.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil implements Serializable {

    private String generateToken(Map<String, Object> claims) {
        Date expirationDate = new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000);
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, "JsonWebToken").compact();
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey("JsonWebToken").parseClaimsJws(token.replace("Bearer ", "")).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>(2);
        claims.put(Claims.SUBJECT, userDetails.getUsername());
        claims.put(Claims.ISSUED_AT, new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000));
        return generateToken(claims);
    }

    public String getUsernameFromToken(String token) {
        String subject;
        String username;

        try {
            Claims claims = getClaimsFromToken(token);
            subject = claims.getSubject();
            username = subject;
//            username = subject.substring(0,subject.indexOf("-"));
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

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put(Claims.ISSUED_AT, new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000));
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        User user = (User) userDetails;
        String username = getUsernameFromToken(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }

    public static void main(String[] args) {
        JwtUtil util = new JwtUtil();
        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbi1bREJBLCBBRE1JTl0iLCJleHAiOjE1NzQwNTM5Nzd9.oWKIiltjZ6VYt5wlSvwj1QIvZDskvA4YJJaD2sUSn79AB0Wooljr9frxTWMopWyMhKN8GTevnULWlNCimwFEyA";

        String user = Jwts.parser()
                .setSigningKey("JsonWebToken")
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody()
                .getSubject();

        Claims claimsFromToken = util.getClaimsFromToken(token);
        System.out.println(claimsFromToken);

        System.out.println(claimsFromToken.getSubject());

        String t = util.getUsernameFromToken(token);
        System.out.println(t.substring(0,t.indexOf("-")));
        System.out.println(user);
    }
}

