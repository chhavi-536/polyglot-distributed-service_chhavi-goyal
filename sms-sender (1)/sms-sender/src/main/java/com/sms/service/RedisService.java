package com.sms.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    private final StringRedisTemplate redisTemplate;

    public RedisService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean isBlocked(String phoneNumber) {
        String key = "blocked:" + phoneNumber;

        String value = redisTemplate.opsForValue().get(key);

        return "true".equals(value);
    }
}
