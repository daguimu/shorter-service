package com.dagm.shorter.service.impl;

import com.dagm.shorter.service.RedisService;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisServiceImpl implements RedisService {

    @Autowired
    @Qualifier(value = "redisTemplate")
    private RedisTemplate redisTemplate;

    /**
     * set
     *
     * @param redisKey key
     * @param object value
     * @param seconds 超时时间
     */
    @Override
    public void set(String redisKey, String object, long seconds) {
        if (object != null) {
            if (seconds != -1) {
                redisTemplate.opsForValue().set(redisKey, object, seconds, TimeUnit.SECONDS);
            } else {
                redisTemplate.opsForValue().set(redisKey, object);
            }
        }
    }

    /**
     * get方法
     *
     * @param redisKey redisKey
     */
    @Override
    public <T> T get(String redisKey) {
        Object object = redisTemplate.opsForValue().get(redisKey);
        return object == null ? null : (T) object;
    }


    @Override
    public void deleteKey(String redisKey) {
        redisTemplate.delete(redisKey);
    }
}
