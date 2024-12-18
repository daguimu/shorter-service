/*
 * Copyright (c) 2020 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.shorter.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Guimu
 * @create 2020/01/03
 */
@Component
@ConfigurationProperties(prefix = "spring.shorter")
@Getter
@Setter
@Slf4j
public class ShorterConfig {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String bitStr;
    private String defaultUrl;
    private String baseUrl;
    private String ossSuffix;

    @Value("${nacos.username}")
    private String uname;
    @Value("${nacos.password}")
    private String passwd;

    @PostConstruct
    public void log() {
        log.info("nacos uname is: {}, passwd is: {}", uname, passwd);
    }
}