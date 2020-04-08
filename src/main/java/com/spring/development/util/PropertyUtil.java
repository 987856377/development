package com.spring.development.util;

import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.util
 * @Author xuzhenkui
 * @Date 2020/4/8 15:54
 */
public class PropertyUtil {
    private static final PropertiesPropertySourceLoader propertiesPropertySourceLoader = new PropertiesPropertySourceLoader();
    private static final YamlPropertySourceLoader yamlPropertySourceLoader = new YamlPropertySourceLoader();

    public static List<Object> load(String key, String property) throws IOException {
        Resource resource = new ClassPathResource(property);
        if (property.endsWith(".properties")){
            return propertiesPropertySourceLoader.load(key, resource).stream().map(s -> s.getProperty(key)).filter(Objects::nonNull).collect(Collectors.toList());
        } else if (property.endsWith(".yml")){
            return yamlPropertySourceLoader.load(key, resource).stream().map(s -> s.getProperty(key)).filter(Objects::nonNull).collect(Collectors.toList());
        } else {
            return null;
        }
    }

    public static void main(String[] args) throws IOException {
//        PropertiesPropertySourceLoader loader = new PropertiesPropertySourceLoader();
//        Resource resource = new ClassPathResource("application.properties");
//        try {
//            String path = (String) loader.load("server.port", resource).get(0).getProperty("module.package.path");
//            System.out.println(path);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        System.out.println(PropertyUtil.load("server.port","application.properties"));

    }


}
