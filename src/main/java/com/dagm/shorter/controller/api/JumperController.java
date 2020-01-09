/*
 * Copyright (c) 2020 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.shorter.controller.api;

import com.dagm.devtool.cache.CacheKeySetting;
import com.dagm.devtool.service.RedisStoreClient;
import com.dagm.devtool.utils.DateTimeUtil;
import com.dagm.shorter.cache.ShorterCacheKeyUtil;
import com.dagm.shorter.dto.ShortRecordCacheDTO;
import com.dagm.shorter.service.ShorterService;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Guimu
 * @created: 2020/01/04
 */
@RestController
@Slf4j
public class JumperController {

    @Autowired
    private ShorterService shorterService;
    @Autowired
    private RedisStoreClient redisStoreClient;

    @GetMapping(value = "/{shortCode:[0-9a-zA-Z]+}")
    public void smsJumpUrl(@PathVariable(value = "shortCode") String shortCode,
        HttpServletResponse response) throws IOException {
        String url = shorterService.backToLongStr(shortCode);
        response.sendRedirect(url);
    }

    @GetMapping(value = "/redis")
    public String redis(@RequestParam(value = "id") Long id) {
        CacheKeySetting setting = ShorterCacheKeyUtil.getLeafCacheKey(id);
        return redisStoreClient.get(setting.getKey());
    }

    @GetMapping(value = "/redis1")
    public Long redis1() {
        Long time = DateTimeUtil.currentTimeStamp();
        CacheKeySetting setting = ShorterCacheKeyUtil
            .getLeafCacheKey(time);
        redisStoreClient.set(setting.getKey(),
            new ShortRecordCacheDTO().setId(time).setShouterStr("http://baidu.com"),
            setting.getExpire());
        return time;
    }
}