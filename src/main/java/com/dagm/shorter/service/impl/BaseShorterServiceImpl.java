/*
 * Copyright (c) 2020 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.shorter.service.impl;

import com.dagm.shorter.service.Base62Service;
import com.dagm.shorter.service.BaseShorterService;
import com.google.common.collect.BiMap;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Guimu
 * @created: 2020/01/03
 */
@Slf4j
@Service
public class BaseShorterServiceImpl implements BaseShorterService {

    @Autowired
    private Base62Service base62Service;

    @Resource(name = "bitMap")
    private BiMap<Integer, String> bitMap;

    @Override
    public String enCodeBase62(long leafId) {
        return base62Service.enBase62(leafId, bitMap);
    }

    @Override
    public long deCodeBase62(String code) {
        return base62Service.deBase62(code, bitMap.inverse());
    }
}