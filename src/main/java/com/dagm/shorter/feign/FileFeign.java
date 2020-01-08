/*
 * Copyright (c) 2020 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.shorter.feign;

import com.dagm.shorter.res.BaseResult;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import feign.Response;

/**
 * @author Guimu
 * @date 2020/01/08
 */
@FeignClient(value = "file-service", configuration = FileFeign.MultipartSupportConfig.class)
public interface FileFeign {

    /**
     * 调用feign 对文件进行上传
     *
     * @param file 待上传的文件
     * @return com.dagm.shorter.res.BaseResult<java.lang.String>
     * @author Guimu
     * @date 2020/1/8
     */
    @PostMapping(value = "/inner/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    BaseResult<String> upload(@RequestPart("file") MultipartFile file);

    /**
     * 文件下载
     *
     * @param filename
     * @return feign.Response
     * @author Guimu
     * @date 2020/1/8
     */
    @GetMapping(value = "/inner/download")
    Response download(@RequestPart("filename") String filename);


    class MultipartSupportConfig {

        @Bean
        public Encoder feignFormEncoder() {
            return new SpringFormEncoder();
        }
    }
}