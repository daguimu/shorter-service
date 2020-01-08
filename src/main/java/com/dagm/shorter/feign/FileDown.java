/*
 * Copyright (c) 2020 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.shorter.feign;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestPart;

/**
 * @author Guimu
 * @date 2020/01/09
 */
@FeignClient(value = "file-service")
public interface FileDown {

    /**
     * 文件下载
     *
     * @return feign.Response
     * @author Guimu
     * @date 2020/1/8
     */
    @GetMapping(value = "/inner/download")
    Response download(@RequestPart("filename") String filename);
}