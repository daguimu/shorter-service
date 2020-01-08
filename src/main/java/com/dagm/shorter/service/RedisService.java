/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.shorter.service;

/**
 * @author: Guimu
 * @created: 2019/10/06
 */
public interface RedisService {

    void set(String redisKey, String object, long seconds);

    <T> T get(String redisKey);

    void deleteKey(String key);
}
