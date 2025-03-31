package com.lqk.myspringboot.controller;

import com.lqk.limit.annotation.RedisLimitAnnotation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author liqiankun
 * @date 2024/12/27 17:15
 * @description
 **/
@RestController
@RequestMapping("/lqk/test")
public class TestController {
    @RedisLimitAnnotation(key = "redisLimit", permitsPerSecond = 3, expire = 10, msg = "当前访问人数较多，请稍后再试，自定义提示！")
    @GetMapping("/list")
    public String list(){
        return "测试接口："+ UUID.randomUUID().toString();
    }
}
