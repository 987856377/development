package com.spring.development.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

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

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    private JoinPoint joinPoint;
    private ServletRequestAttributes attributes;
    private HttpServletRequest request;

    @Pointcut("execution(public * com.spring.development.module.*.controller.*.*(..))")
    public void log(){}

    @Before(value = "log()")
    public void doBefore(JoinPoint joinPoint){
        this.joinPoint = joinPoint;
        startTime.set(System.currentTimeMillis());
        attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        request = attributes.getRequest();
    }

    @AfterReturning(returning = "ret" , pointcut = "log()")
    public void doAfterReturning(Object ret){
        //处理完请求后，返回内容
        logger.info("\n请求URL: "+request.getRequestURL().toString()+"\n入参:"+ Arrays.toString(joinPoint.getArgs())+"\n出参:"+ JSON.toJSONString(ret) +"\n执行时间: "+ (System.currentTimeMillis() - startTime.get())+" 毫秒");
    }
}
