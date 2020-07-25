package com.spring.development.aspect;

import com.alibaba.fastjson.JSON;
import com.spring.development.module.log.entity.MethodLog;
import com.spring.development.module.log.service.MethodLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
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

    @Resource
    private MethodLogService methodLogService;

    @Pointcut("execution(public * com.spring.development.module.*.controller.*.*(..))")
    public void log(){}

    @Around("log()")
    public Object around(ProceedingJoinPoint joinPoint) {

        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        try {
            long startTimeMillis = System.currentTimeMillis();
            //调用 proceed() 方法才会真正的执行实际被代理的方法
            Object result = joinPoint.proceed();

            String input = Arrays.toString(joinPoint.getArgs());
            String ouput = JSON.toJSONString(result);

            long consultTime = System.currentTimeMillis() - startTimeMillis;
            logger.info("\n请求URL: "+request.getRequestURI()+"\n入参:"+ input +"\n出参:"+ ouput +"\n执行时间: "+ consultTime +" 毫秒");

            methodLogService.save(new MethodLog(request.getRemoteAddr(),request.getLocalAddr(),request.getRequestURI(), LocalDateTime.now() ,input, ouput, String.valueOf(consultTime)));
            return result;
        } catch (Throwable throwable) {
            logger.error(throwable.getMessage(),throwable);
            throw new RuntimeException(throwable.getMessage());
        }
    }
}
