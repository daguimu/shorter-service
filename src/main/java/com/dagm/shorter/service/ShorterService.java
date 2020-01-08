/*
 * Copyright (c) 2020 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.shorter.service;

/**
 * @author: Guimu
 * @created: 2020/01/03
 */
public interface ShorterService {

    String toBeShorter(String longStr);

    String backToLongStr(String shortStr);
}
