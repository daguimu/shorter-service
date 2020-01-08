/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.shorter.controller.api;

import static com.dagm.devtool.common.BaseErrorCode.OUTTER_PARAM_ERROR;
import static com.dagm.devtool.common.BaseErrorCode.SYSTEM_ERROR;

import com.dagm.devtool.utils.PreconditionsUtil;
import com.dagm.shorter.config.ShorterConfig;
import com.dagm.shorter.feign.FileFeign;
import com.dagm.shorter.req.AddShortRecReq;
import com.dagm.shorter.res.BaseResult;
import com.dagm.shorter.service.ShorterService;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: Guimu
 * @created: 2019/10/07
 */
@RestController
@RequestMapping(value = "/inner")
@Slf4j
public class ShorterController {

    @Autowired
    private ShorterService shorterService;
    @Autowired
    private ShorterConfig shorterConfig;
    @Autowired
    private FileFeign fileFeign;


    @PostMapping(value = "add")
    public BaseResult<String> test(@RequestBody @Valid AddShortRecReq shortRecReq) {
        return BaseResult.generateSuccessRestlt(shorterService.toBeShorter(shortRecReq.getUrl()));
    }

    @PostMapping(value = "restore")
    public BaseResult<String> getOrigin(@RequestBody @Valid AddShortRecReq recReq) {
        int len = shorterConfig.getBaseUrl().length();
        PreconditionsUtil.checkArgument(recReq.getUrl().length() > len, OUTTER_PARAM_ERROR);
        String shortUrl = recReq.getUrl().substring(len);
        String url = shorterService.backToLongStr(shortUrl);
        return BaseResult.generateSuccessRestlt(url);
    }

    @PostMapping(value = "upload")
    public BaseResult<String> test(MultipartFile file) {
        return fileFeign.upload(file);
    }

    @GetMapping(value = "download")
    public void down(@RequestParam(value = "filename") String filename,
        HttpServletResponse response) {
        ResponseEntity<byte[]> entity = fileFeign.downloadFile(filename, response);
        if (entity.getStatusCode() != HttpStatus.OK && entity.getBody() != null) {
            PreconditionsUtil.checkArgument(false, SYSTEM_ERROR);
        }
        try (OutputStream outputStream = response.getOutputStream();
            InputStream inputStream = new ByteArrayInputStream(
                Objects.requireNonNull(entity.getBody()))) {
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (IOException e) {
            log.error("下载文件异常 filepath:[{}]", filename, e);
        }
    }
}