package com.example.demo.redis;

import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author liurui
 * @date 2020/11/12
 */
public class RedisTemplateDemo {

    public static void main(String[] args) {

        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();

        LettuceConnectionFactory factory = new LettuceConnectionFactory();


    }

}
