/*
 * Copyright (c) 2020 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.shorter.service.impl;

import com.dagm.shorter.service.BitCheckService;
import com.google.common.collect.BiMap;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: Guimu
 * @created: 2020/01/04
 */
@Slf4j
@Service
public class BitCheckServiceImpl implements BitCheckService {

    @Resource(name = "inverseBitMap")
    private BiMap<String, Integer> integerBiMap;

    @Resource(name = "bitMap")
    private BiMap<Integer, String> bitMap;

    @Override
    public boolean checkBit(String val) {
        return false;
    }


    /**
     * Luhm 校验算法获得校验位
     */
    @Override
    public String getCheckBitByOriginVal(String originVal) {
        char[] chs = originVal.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        char code = (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
        return String.valueOf(code);
    }
}