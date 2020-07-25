package com.spring.development.module;

import com.spring.development.util.RedisUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Project development
 * @Package com.spring.development.module
 * @Author xuzhenkui
 * @Date 2020/6/21 15:43
 */
@RestController
public class CommonController {
    @Resource
    private RedisUtil redisUtil;

    @RequestMapping("/set")
    public String setValue(){
        redisUtil.set("name","frank");
        return (String) redisUtil.get("name");
    }

    @RequestMapping("/get")
    public String getValue(){
        if (redisUtil.get("name") == null) {
            redisUtil.set("name","frank");
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            return (String) redisUtil.get("name");
        }
        return (String) redisUtil.get("name");
    }
}
