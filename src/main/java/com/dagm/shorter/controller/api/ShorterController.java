/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.shorter.controller.api;

import static com.dagm.devtool.common.BaseErrorCode.OUTTER_PARAM_ERROR;

import com.dagm.devtool.utils.PreconditionsUtil;
import com.dagm.shorter.config.ShorterConfig;
import com.dagm.shorter.req.AddShortRecReq;
import com.dagm.shorter.res.BaseResult;
import com.dagm.shorter.service.ShorterService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}