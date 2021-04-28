/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.shorter.enums;

import com.dagm.devtool.common.BaseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Guimu
 * @create 2019/10/07
 */
@Getter
@AllArgsConstructor
public enum ShorterTipEnum implements BaseCode {
    /**
     * OSS存储异常
     */
    URL_SAVE_TO_OSS_ERROR("9006", "OSS存储异常!"),
    /**
     * 校验失败!
     */
    URL_BIT_CHECK_ERROR("9007", "校验失败!"),
    /**
     * 链接已过期
     */
    URL_EXPIRED_ERROR("9008", "链接已过期!"),
    /**
     * 链接不存在!
     */
    URL_NOT_EXISTED_ERROR("9009", "链接不存在!"),
    /**
     * URL格式不正确!
     */
    URL_FORMAT_ERROR("9010", "URL格式不正确，请加上协议，如：http://www.dagm.com或https://www.dagm.com!"),
    ;
    private String code;

    private String msg;
}