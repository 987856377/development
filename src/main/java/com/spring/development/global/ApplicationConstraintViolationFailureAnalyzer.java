package com.spring.development.global;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.diagnostics.FailureAnalysis;
import org.springframework.boot.diagnostics.FailureAnalyzer;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import java.util.Arrays;

/**
 * @Description 程序启动错误分析器
 * @Project development
 * @Package com.spring.development.global
 * @Author xuzhenkui
 * @Date 2020/1/20 9:42
 */
public class ApplicationConstraintViolationFailureAnalyzer implements FailureAnalyzer, EnvironmentAware {

//    @Autowired
//    private Environment environment;

    @Override
    public FailureAnalysis analyze(Throwable failure) {
        failure.printStackTrace();
        return new FailureAnalysis("FAILURE INFO  =====>  [ "+failure.getMessage()+" ]\n",
                "ACTION INFO =====>  [ 程序已停止运行 ]",
                failure);
    }

    @Override
    public void setEnvironment(Environment environment) {
        Arrays.stream(environment.getDefaultProfiles()).forEach(s-> System.out.println(s));
    }
}
