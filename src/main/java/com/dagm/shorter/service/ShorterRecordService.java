/*
 * Copyright (c) 2020 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.shorter.service;

import com.dagm.shorter.dto.ShortRecordDTO;

/**
 * @author Guimu
 * @date 2020/01/03
 */
public interface ShorterRecordService {

    /**
     * 添加短连接记录到db
     *
     * @author Guimu
     * @date 2020/1/3
     */
    boolean addRecord(ShortRecordDTO shortRecordDto);

    /**
     * 校验leafId 对应的链接记录的有效性
     *
     * @author Guimu
     * @date 2020/1/4
     */
    boolean getRecordByLeafId(Long leafId);
}
