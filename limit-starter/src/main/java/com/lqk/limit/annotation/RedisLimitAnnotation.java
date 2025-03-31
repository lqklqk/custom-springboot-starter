package com.lqk.limit.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author liqiankun
 * @date 2024/12/27 15:40
 * @description
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Configuration
@ComponentScan
// @Import(XushuImportSeclet.class)
public @interface RedisLimitAnnotation {
    /**
     * 资源的key,唯一
     * 作用：不同的接口，不同的流量控制
     */
    String key() default "";

    /**
     * 最多的访问限制次数
     */
    long permitsPerSecond() default 3;

    /**
     * 过期时间(计算窗口时间)，单位秒默认30
     */
    long expire() default 30;

    /**
     * 默认温馨提示语
     */
    String msg() default "default message:系统繁忙,请稍后再试，谢谢";
}
