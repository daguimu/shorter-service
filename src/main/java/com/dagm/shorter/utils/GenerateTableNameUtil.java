/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.shorter.utils;

import static com.dagm.shorter.consts.TableConst.SHORTER_TABLE_NAME;
import static com.dagm.shorter.consts.TableConst.SHORTER_TABLE_NAME_TOTAL;

import com.dagm.shorter.consts.TableConst;


public class GenerateTableNameUtil {

    /**
     * 分表时根据ID和name生成数据库表名称
     */
    private static String baseGetTableName(Number id, String name, Integer tableTotal) {
        long tableNum = id.longValue() % TableConst.ROUTE_BIT % tableTotal;
        // TODO 临时
//        return name + tableNum;
        return name;
    }

    /**
     * shorter 表名
     */
    public static String getShorterTableName(Long leafId) {
        return baseGetTableName(leafId, SHORTER_TABLE_NAME, SHORTER_TABLE_NAME_TOTAL);
    }
}
