package com.spring.development.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Objects;

/**
 * @Description
 * @Project mybatisplus
 * @Package com.spring.development.utils
 * @Author xuzhenkui
 * @Date 2019/9/19 14:16
 */
@Aspect
@Component
public class LogAspect {
    private Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(public * com.spring.development.module.*.controller.*.*(..))")
    public void log(){}

    @Around("log()")
    public Object around(ProceedingJoinPoint joinPoint) {

        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        try {
            long startTimeMillis = System.currentTimeMillis();
            //调用 proceed() 方法才会真正的执行实际被代理的方法
            Object result = joinPoint.proceed();

            logger.info("\n请求URL: "+request.getRequestURI()+"\n入参:"+ Arrays.toString(joinPoint.getArgs())+"\n出参:"+
                JSON.toJSONString(result) +"\n执行时间: "+ (System.currentTimeMillis() - startTimeMillis)+" 毫秒");

            return result;
        } catch (Throwable throwable) {
            logger.error(throwable.getMessage(),throwable);
            throw new RuntimeException(throwable.getMessage());
        }
    }
}
