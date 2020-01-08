/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.shorter.utils;

import java.io.UnsupportedEncodingException;
import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author: Guimu
 * @created: 2019/12/10
 */
public class AuthUtil {

    public static String md5(String origStr) {
        try {
            return DigestUtils.md5Hex(origStr.getBytes(Charsets.UTF_8.displayName()));
        } catch (UnsupportedEncodingException e) {
            return DigestUtils.md5Hex(origStr.getBytes());
        }
    }
}