package com.spring.development.util.test;

import com.spring.development.jwt.JwtUtil;
import com.spring.development.util.encrypt.BCryptPasswordEncoder;
import com.spring.development.util.encrypt.PasswordEncoder;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.util.encrypt
 * @Author xuzhenkui
 * @Date 2019/11/16 17:38
 */
public class BCryptTest {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("123456"));

        String token = "admin-[DBA, ADMIN]";
        System.out.println(token.substring(0,token.indexOf("-")));
        System.out.println(token.substring(token.indexOf("[")));
        ArrayList list = new ArrayList(Collections.singleton(token.substring(token.indexOf("[")+1,token.length()-1)));
        System.out.println(list.toString());

    }
}
