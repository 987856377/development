package com.spring.development.annotation;


import java.lang.annotation.*;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.annotation
 * @Author xuzhenkui
 * @Date 2019/10/2 9:30
 */
@Documented
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
    String[] roles() default "";
}
