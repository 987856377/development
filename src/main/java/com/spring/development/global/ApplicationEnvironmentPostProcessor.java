package com.spring.development.global;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * @Description 环境处理器, 加载资源文件, 将文件中的属性添加到应用环境中
 *
 * 虽然在@SpringBootApplication上使用@PropertySource似乎是在环境中加载自定义资源的一种方便方法，
 * 但我们不推荐使用它。
 * 在刷新应用程序上下文之前，不会将此类属性源添加到环境中。
 * 现在配置某些属性 logging。*和 spring.main。* 已经太晚了
 * 应该在刷新开始前读取。
 *
 * 	While using @PropertySource on your @SpringBootApplication may seem to be a convenient way to load a custom resource in the Environment,
 * 	we do not recommend it.
 * 	Such property sources are not added to the Environment until the application context is being refreshed.
 * 	This is too late to configure certain properties such as logging.* and spring.main.*
 * 	which are read before refresh begins.
 *
 * @Project development
 * @Package com.spring.development.global
 * @Author xuzhenkui
 * @Date 2020/1/20 10:23
 */
public class ApplicationEnvironmentPostProcessor implements EnvironmentPostProcessor {

    private final YamlPropertySourceLoader loader = new YamlPropertySourceLoader();

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Resource path = new ClassPathResource("application.yml");
        PropertySource<?> propertySource = loadYaml(path);
        environment.getPropertySources().addLast(propertySource);
    }

    private PropertySource<?> loadYaml(Resource path) {
        if (!path.exists()) {
            throw new IllegalArgumentException("Resource " + path + " does not exist");
        }
        try {
            System.out.println(this.loader.load("custom-resource", path).get(0).getProperty("custom-resource"));
            return this.loader.load("custom-resource", path).get(0);
        }
        catch (IOException ex) {
            throw new IllegalStateException("Failed to load yaml configuration from " + path, ex);
        }
    }

}
