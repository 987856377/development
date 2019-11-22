package com.spring.development.annotation;

import java.lang.annotation.*;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.annotation
 * @Author xuzhenkui
 * @Date 2019/11/4 10:33
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
public @interface NotNull {
    String message() default "javax.validation.constraints.NotNull.message";
    Class<?>[] groups() default {};
}
