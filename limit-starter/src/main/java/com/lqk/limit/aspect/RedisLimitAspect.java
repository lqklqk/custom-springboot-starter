package com.lqk.limit.aspect;

import com.lqk.limit.annotation.RedisLimitAnnotation;
import com.lqk.limit.exception.RedisLimitException;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liqiankun
 * @date 2024/12/27 16:08
 * @description
 **/
@Aspect
@Slf4j
@Component
@AutoConfigureBefore(StringRedisTemplate.class)
public class RedisLimitAspect {
    @Autowired
    private StringRedisTemplate redisTemplate;
    private DefaultRedisScript<Long> redisLuaScript;
    @PostConstruct
    public void init()
    {
        redisLuaScript = new DefaultRedisScript<>();
        redisLuaScript.setResultType(Long.class);
        redisLuaScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("rateLimiter.lua")));
    }
    @Pointcut("@annotation(com.lqk.limit.annotation.RedisLimitAnnotation)")
    public void autoConfigAspectPointCut() {
    }

    @Around("autoConfigAspectPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        log.info("limit-starter：进入切面");
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        //拿到RedisLimitAnnotation注解，如果存在则说明需要限流
        RedisLimitAnnotation redisLimitAnnotation = method.getAnnotation(RedisLimitAnnotation.class);
        if (redisLimitAnnotation != null) {
            //获取redis的key
            String key = redisLimitAnnotation.key();
            String className = method.getDeclaringClass().getName();
            String methodName = method.getName();
            String limitKey = key +"\t"+ className+"\t" + methodName;
            log.info(limitKey);
            if (null == key) {
                throw new RedisLimitException("it's danger,limitKey cannot be null");
            }
            long limit = redisLimitAnnotation.permitsPerSecond();
            long expire = redisLimitAnnotation.expire();
            List<String> keys = new ArrayList<>();
            keys.add(key);
            Long count = redisTemplate.execute(redisLuaScript, keys, String.valueOf(limit), String.valueOf(expire));
            log.info("Access try count is {} \t key={}", count, key);
            if (count != null && count == 0) {
                log.info("启动限流功能key：{}", key);
                // 第一学历统招本科，愿意跟着阳哥学技术，需要内推或高阶java资料学习的同学发邮件zzyybs@126.com
                //throw new RedisLimitException(redisLimitAnnotation.msg());
                return redisLimitAnnotation.msg();
            }
        }
        return point.proceed();
    }
}
