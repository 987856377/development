package com.spring.development.test;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;

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
