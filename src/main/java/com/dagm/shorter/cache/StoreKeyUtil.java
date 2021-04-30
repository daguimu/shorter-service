package com.dagm.shorter.cache;


import com.dagm.devtool.cache.StoreKey;


public class StoreKeyUtil {

    public static StoreKey getStringStoreKey(String key) {
        return new StoreKey("shorter_string_category", key);
    }

    public static StoreKey getHashStoreKey(String key) {
        return new StoreKey("shorter_hash_category", key);
    }

    public static StoreKey getSetStoreKey(String key) {
        return new StoreKey("shorter_set_category", key);
    }

    public static StoreKey getSortSetStoreKey(String key) {
        return new StoreKey("shorter_sortset_category", key);
    }

    public static StoreKey getListStoreKey(String key) {
        return new StoreKey("shorter_list_category", key);
    }
    public static StoreKey getOriginStoreKey(String key) {
        return new StoreKey("common", key);
    }
}