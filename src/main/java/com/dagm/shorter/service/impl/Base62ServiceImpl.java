/*
 * Copyright (c) 2020 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.shorter.service.impl;

import com.dagm.shorter.service.Base62Service;
import com.google.common.collect.BiMap;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: Guimu
 * @created: 2020/01/04
 */

@Slf4j
@Service
public class Base62ServiceImpl implements Base62Service {

    public String enBase62(long val, BiMap<Integer, String> biMap) {
        List<Integer> bitList = new LinkedList<>();
        if (val == 0) {
            bitList.add(0);
        }
        while (val > 0) {
            Long index = val % 62;
            val /= 62;
            bitList.add(index.intValue());
        }
        Collections.reverse(bitList);
        return this.bitListToRadixStr(bitList, biMap);
    }

    public long deBase62(String en62Str, BiMap<String, Integer> inverseBitMap) {
        long sum = 0;
        String[] arrays = en62Str.split("");
        for (int index = 0; index < en62Str.length(); index++) {
            Integer val = inverseBitMap.get(arrays[index]);
            sum += val > 0 ? val * this.pow(62, en62Str.length() - index - 1) : 0;
        }
        return sum;
    }


    private String bitListToRadixStr(List<Integer> bitList, BiMap<Integer, String> biMap) {
        StringBuilder stringBuilder = new StringBuilder();
        bitList.forEach(el -> stringBuilder.append(biMap.get(el)));
        return stringBuilder.toString();
    }


    private long pow(long base, int exponent) {
        long d = 1;
        while (exponent > 0) {
            if (exponent % 2 == 1) {
                d *= base;
            }
            base *= base;
            exponent >>= 1;
        }
        return d;
    }
}