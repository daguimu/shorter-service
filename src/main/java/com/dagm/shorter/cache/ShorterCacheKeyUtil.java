/*
 * Copyright (c) 2020 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.shorter.cache;

import com.dagm.devtool.cache.CacheKeySetting;
import com.dagm.shorter.consts.ShortCacheConst;

/**
 * @author Guimu
 * @date 2020/01/09
 */
public class ShorterCacheKeyUtil {

    /**
     * 根据leaf id 获取其对应的cacheKey setting
     *
     * @return com.dagm.devtool.cache.CacheKeySetting
     * @author Guimu
     * @date 2020/1/9
     */
    public static CacheKeySetting getLeafCacheKey(Long leafId) {
        return new CacheKeySetting(
                StoreKeyUtil
                        .getStringStoreKey(String.format(ShortCacheConst.OSS_FILE_PATH_LEAF_ID, leafId)),
                ShortCacheConst.OSS_FILE_PATH_LEAF_ID_KEY_EXPIRE);
    }

    /**
     * 获取secret cache key
     *
     * @return com.dagm.devtool.cache.CacheKeySetting
     * @author Guimu
     * @date 2020/1/9
     */
    public static CacheKeySetting getSecretCacheKey(String key) {
        return new CacheKeySetting(
                StoreKeyUtil.getOriginStoreKey(String.format(ShortCacheConst.COMMON, key)),
                ShortCacheConst.COMMON_EXPIRE);
    }
}