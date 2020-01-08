/*
 * Copyright (c) 2020 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.shorter.configuration;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.dagm.shorter.config.ShorterConfig;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Guimu
 * @created: 2020/01/02
 */
@Configuration
public class ShorterConfiguration {

    @Autowired
    private ShorterConfig shorterConfig;

    @Bean(name = "ossClient")
    public OSS createOssClient() {
        String endpoint = shorterConfig.getEndpoint();
        String accessKeyId = shorterConfig.getAccessKeyId();
        String accessKeySecret = shorterConfig.getAccessKeySecret();
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

    /**
     * 获取进制转化集合
     *
     * @author Guimu
     * @date 2020/1/3
     */
    @Bean(name = "bitMap")
    public BiMap<Integer, String> createBitMap() {
        BiMap<Integer, String> biMap = HashBiMap.create(62);
        String[] biArray = shorterConfig.getBitStr().split("");
        Stream.iterate(0, x -> x + 1).limit(62).forEach(el -> biMap.put(el, biArray[el]));
        return biMap;
    }

    /**
     * 获取进制转化集合
     *
     * @author Guimu
     * @date 2020/1/3
     */
    @Bean(name = "inverseBitMap")
    public BiMap<String, Integer> createInverseBitMap() {
        return this.createBitMap().inverse();
    }
}