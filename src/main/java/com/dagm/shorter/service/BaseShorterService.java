/*
 * Copyright (c) 2020 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.shorter.service;

/**
 * @author Guimu
 * @create 2020/01/03
 */
public interface BaseShorterService {

    /**
     * 对 leafId 进行短码加密处理
     *
     * @author Guimu
     * @date 2020/1/3
     */
    String enCodeBase62(long leafId);

    /**
     * 对 code 进行短码解密处理
     *
     * @author Guimu
     * @date  2020/1/3
     */
    long deCodeBase62(String code);

}
