package com.lqk.limit.exception;

/**
 * @author liqiankun
 * @date 2024/12/27 16:17
 * @description
 **/
public class RedisLimitException extends Exception{
    public RedisLimitException(String msg)
    {
        super(msg);
    }
}
