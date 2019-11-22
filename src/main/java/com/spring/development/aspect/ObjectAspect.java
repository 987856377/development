package com.spring.development.aspect;

import com.spring.development.annotation.NotNull;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.aspect
 * @Author xuzhenkui
 * @Date 2019/11/4 10:14
 * 一、execution：使用“execution(方法表达式)”匹配方法执行
 * 二、within：使用“within(类型表达式)”匹配指定类型内的方法执行
 * 三、this：使用“this(类型全限定名)”匹配当前AOP代理对象类型的执行方法；注意是AOP代理对象的类型匹配，这样就可能包括引入接口方法也可以匹配；注意this中使用的表达式必须是类型全限定名，不支持通配符
 * 四、target：使用“target(类型全限定名)”匹配当前目标对象类型的执行方法；注意是目标对象的类型匹配，这样就不包括引入接口也类型匹配；注意target中使用的表达式必须是类型全限定名，不支持通配符
 * 五、args：使用“args(参数类型列表)”匹配当前执行的方法传入的参数为指定类型的执行方法；注意是匹配传入的参数类型，不是匹配方法签名的参数类型；参数类型列表中的参数必须是类型全限定名，通配符不支持；args属于动态切入点，这种切入点开销非常大，非特殊情况最好不要使用
 * 六、@within：使用“@within(注解类型)”匹配所以持有指定注解类型内的方法；注解类型也必须是全限定类型名,标注在类上
 * 七、@target：使用“@target(注解类型)”匹配当前目标对象类型的执行方法，其中目标对象持有指定的注解；注解类型也必须是全限定类型名
 * 八、@args：使用“@args(注解列表)”匹配当前执行的方法传入的参数持有指定注解的执行；注解类型也必须是全限定类型名
 * 九、@annotation：使用“@annotation(注解类型)”匹配当前执行方法持有指定注解的方法；注解类型也必须是全限定类型名,标注在方法上
 * 十、bean：使用“bean(Bean id或名字通配符)”匹配特定名称的Bean对象的执行方法；Spring ASP扩展的，在AspectJ中无相应概念
 * 十一、reference pointcut：表示引用其他命名切入点，只有@ApectJ风格支持，Schema风格不支持
 */
@Aspect
@Component
public class ObjectAspect {
    Logger logger = LoggerFactory.getLogger(ObjectAspect.class);

    @Pointcut("@within(com.spring.development.annotation.NotNull)")
    public void auth(){}

    @Around(value = "auth()")
    public void doAroundAuth(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Object target = joinPoint.getTarget();
        Object[] args = joinPoint.getArgs();
        Parameter[] parameters = signature.getMethod().getParameters();
        List<Integer> checkResult = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {
            Parameter parameter = parameters[i];
            if (parameter.isAnnotationPresent(NotNull.class)) {
                if (args[i] == null) {
                    checkResult.add(i);
                }
            }
        }
        if (!checkResult.isEmpty()) {
            String result = checkResult.toString();
            throw new IllegalArgumentException("第" + result + "个参数被注解NotNull.class修饰，,不能为Null");
        }
    }
}
