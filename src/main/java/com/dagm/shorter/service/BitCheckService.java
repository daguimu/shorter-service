/*
 * Copyright (c) 2020 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.shorter.service;

/**
 * @author Guimu
 * @create 2020/01/04
 */
public interface BitCheckService {

    /**
     * 根据密文+位校验码进行 校验是否正确
     *
     * @author Guimu
     * @create 2020/1/4
     */
    boolean checkBit(String val);

    /**
     * 根据密文值获取位校验码
     *
     * @author Guimu
     * @create 2020/1/4
     */
    String getCheckBitByOriginVal(String originVal);
}
