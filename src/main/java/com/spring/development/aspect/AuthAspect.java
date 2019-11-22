package com.spring.development.aspect;

import com.spring.development.annotation.Auth;
import com.spring.development.common.ResultCode;
import com.spring.development.common.ResultJson;
import com.spring.development.module.user.mapper.RoleMapper;
import com.spring.development.module.user.entity.Role;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.aspect
 * @Author xuzhenkui
 * @Date 2019/10/2 9:35
 */
@Component
@Aspect
public class AuthAspect {

    private Logger logger = LoggerFactory.getLogger(AuthAspect.class);

    @Resource
    private RoleMapper roleMapper;

    @Pointcut("@annotation(auth)")
    public void doAuth(Auth auth){}

    @Around("doAuth(auth)")
    public Object doAround(ProceedingJoinPoint joinPoint, Auth auth) throws Throwable{
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

//        访问服务器资源需要的角色
        List<String> roles = Arrays.asList(auth.roles());

//        访问者拥有的角色
        String code = request.getHeader("role");
//        访问者未携带任何角色
        if (code == null || code.equals("")){
            return ResultJson.failure(ResultCode.UNAUTHORIZED,"对不起,请登录后再试");
        }
        Role role = roleMapper.getRoleByCode(code);
        if (role == null){
            return ResultJson.failure(ResultCode.UNAUTHORIZED,"对不起,该用户角色未定义,详情请咨询管理员");
        }

//        验证访问者具有任何角色与服务器设定的角色是否匹配
//        1. Auth 注解未声明详细的角色    2. Auth 具体声明的角色
        else if (roles.get(0).equals("") || !roles.get(0).equals("") && contains(roles, role)){
            return joinPoint.proceed();
        }
        return ResultJson.failure(ResultCode.UNAUTHORIZED,"对不起,您没有权限访问");
    }

    public Boolean contains(List<String> roles, Role role){
        for (String r : roles){
            Role role1 = roleMapper.getRoleByCode(r);
            if (role1 == null){
                logger.error("controller 层注解书写错误");
                return false;
            }
            if (role.getId() <= role1.getId()){
                return true;
            }
        }
        return false;
    }
}
