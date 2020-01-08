/*
 * Copyright (c) 2020 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.shorter.adapter;

import com.dagm.shorter.dto.ShortRecordCacheDTO;
import com.dagm.shorter.dto.ShortRecordDTO;
import com.dagm.shorter.model.ShorterRecordPO;

/**
 * @author: Guimu
 * @created: 2020/01/03
 */
public class ShorterAdapter {

    public static ShorterRecordPO shorterDto2Po(ShortRecordDTO shortRecordDto) {
        if (shortRecordDto == null) {
            return null;
        }
        ShorterRecordPO shorterRecordPO = new ShorterRecordPO();
        shorterRecordPO.setId(shortRecordDto.getId());
        shorterRecordPO.setShouterStr(shortRecordDto.getShouterStr());
        return shorterRecordPO;
    }

    public static ShortRecordCacheDTO shortPo2Dto(ShorterRecordPO shorterRecordPo) {
        if (shorterRecordPo == null) {
            return null;
        }
        ShortRecordCacheDTO shortRecordCacheDTO = new ShortRecordCacheDTO();
        shortRecordCacheDTO.setId(shorterRecordPo.getId());
        shortRecordCacheDTO.setExpires(shorterRecordPo.getExpiredTime());
        shortRecordCacheDTO.setShouterStr(shorterRecordPo.getShouterStr());
        return shortRecordCacheDTO;
    }
}