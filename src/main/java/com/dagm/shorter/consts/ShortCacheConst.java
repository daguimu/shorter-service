/*
 * Copyright (c) 2020 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.shorter.consts;

/**
 * @author Guimu
 * @create 2020/01/04
 */
public class ShortCacheConst {

    /**
     * 根据leafid 获取缓存信息
     */
    public static final String OSS_FILE_PATH_LEAF_ID = "shorter_%d";
    /**
     * leaf id expire 5 minutes
     */
    public static final int OSS_FILE_PATH_LEAF_ID_KEY_EXPIRE = 5 * 60;


    public static final String COMMON = "common_%s";
    public static final int COMMON_EXPIRE = -1;
}