/*
 * Copyright (c) 2020 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.shorter.service.impl;

import com.dagm.api.feignclient.LeafService;
import com.dagm.shorter.service.LeafGenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Guimu
 * @create 2020/01/04
 */
@Slf4j
@Service
public class LeafGenServiceImpl implements LeafGenService {

    /**
     * 当前服务的biz_tag
     */
    private static final String THIS_TAG = "shorter";
    @Autowired
    private LeafService leafKeyService;

    @Override
    public Long getLeafIdByTag() {
        String leafIdStr = leafKeyService.generateUniqueKey(THIS_TAG);
        log.info("feign client 调用leaf service 获取leafId:[{}]", leafIdStr);
        return Long.valueOf(leafIdStr);
    }
}