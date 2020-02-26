package com.spring.development.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.datasource
 * @Author xuzhenkui
 * @Date 2020/2/26 18:27
 */
public class JdbcDataSource {
    private static final ThreadLocal<DataSource> contextHolder = new ThreadLocal<>();

    static {
        contextHolder.set(DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/development?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Shanghai&useSSL=false")
                .username("root")
                .password("root").build());
    }

    public static DataSource getDataSource(){
        return contextHolder.get();
    }

    public static void setDataSource(DataSource dataSource){
        contextHolder.set(dataSource);
    }
}
