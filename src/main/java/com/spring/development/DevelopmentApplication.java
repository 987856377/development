package com.spring.development;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class DevelopmentApplication {

    public static void main(String[] args) {
//        修改 spring application 配置
//        new SpringApplicationBuilder()
//                .sources(DevelopmentApplication.class)
//                .run(args);
        SpringApplication.run(DevelopmentApplication.class, args);
    }

}
