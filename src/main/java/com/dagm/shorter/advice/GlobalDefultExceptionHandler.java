/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.shorter.advice;

import com.dagm.shorter.exceptions.CommonException;
import com.dagm.shorter.res.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: Guimu
 * @created: 2019/10/07
 */
@Slf4j
@ControllerAdvice
public class GlobalDefultExceptionHandler {


    /**
     * 全局异常处理, 并声明要捕获的异常为 Exception.class
     *
     * @author: Guimu
     * @created: 2020/1/3
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResult<String> globalExceptionHandler(Exception e) {

        BaseResult<String> baseResult = BaseResult.generateFailureRestlt(e.getMessage());
        if (e instanceof CommonException) {
            baseResult.setCode(((CommonException) e).getCode());
            log.info("业务级别异常", e);
        } else {
            log.error("系统级别异常", e);
        }
        return baseResult;
    }
}