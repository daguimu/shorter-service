/*
 * Copyright (c) 2020 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.shorter.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Guimu
 * @create 2020/01/03
 */
@Component
@ConfigurationProperties(prefix = "spring.shorter")
@Getter
@Setter
public class ShorterConfig {

  private String endpoint;
  private String accessKeyId;
  private String accessKeySecret;
  private String bucketName;
  private String bitStr;
  private String defaultUrl;
  private String baseUrl;
  private String ossSuffix;
}