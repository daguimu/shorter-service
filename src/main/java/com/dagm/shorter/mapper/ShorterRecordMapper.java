/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.shorter.mapper;

import com.dagm.shorter.model.ShorterRecordPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Guimu
 * @create 2019/07/25
 */
@Mapper
public interface ShorterRecordMapper {

    int addRecord(@Param("tableName") String tableName, @Param("record") ShorterRecordPO record);

    ShorterRecordPO getOssFilePathById(@Param("tableName") String tableName,
        @Param("leafId") Long leafId);
}