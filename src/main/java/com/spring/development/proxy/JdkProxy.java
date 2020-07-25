package com.spring.development.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description
 * @Project test
 * @Package proxy.dynamicProxy
 * @Author xuzhenkui
 * @Date 2020/4/19 9:55
 */
public class JdkProxy implements InvocationHandler {
    private Object targetClass;

    public Object getProxyInstance(Object targetClass) {
        this.targetClass = targetClass;
        return Proxy.newProxyInstance(targetClass.getClass().getClassLoader(), targetClass.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long l = System.currentTimeMillis();
        Object result = method.invoke(targetClass,args);
        System.out.println("调用方法: " + method.getName() + "调用耗时: " + (System.currentTimeMillis() - l) + " ms");
        return result;
    }
}
