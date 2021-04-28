/*
 * Copyright (c) 2020 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.shorter.service.impl;

import com.dagm.devtool.cache.CacheKeySetting;
import com.dagm.devtool.service.RedisStoreClient;
import com.dagm.devtool.utils.DateTimeUtil;
import com.dagm.shorter.adapter.ShorterAdapter;
import com.dagm.shorter.cache.ShorterCacheKeyUtil;
import com.dagm.shorter.dto.ShortRecordCacheDTO;
import com.dagm.shorter.dto.ShortRecordDTO;
import com.dagm.shorter.enums.ShorterTipEnum;
import com.dagm.shorter.mapper.ShorterRecordMapper;
import com.dagm.shorter.model.ShorterRecordPO;
import com.dagm.shorter.service.ShorterRecordService;
import com.dagm.shorter.utils.GenerateTableNameUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

import static com.dagm.shorter.enums.ShorterTipEnum.URL_EXPIRED_ERROR;
import static com.dagm.shorter.enums.ShorterTipEnum.URL_NOT_EXISTED_ERROR;

/**
 * @author Guimu
 * @create 2020/01/03
 */
@Slf4j
@Service
public class ShorterRecordServiceImpl implements ShorterRecordService {

    @Autowired
    private RedisStoreClient redisStoreClient;

    @Autowired
    private ShorterRecordMapper shorterRecordMapper;

    @Override
    public boolean addRecord(ShortRecordDTO shortRecordDto) {
        ShorterRecordPO shorterRecordPo = ShorterAdapter.shorterDto2Po(shortRecordDto);
        shorterRecordPo.setExpiredTime(DateTimeUtil
            .localDateTimeToDate(LocalDateTime.now().plusSeconds(shortRecordDto.getExpires())));
        String tableName = GenerateTableNameUtil.getShorterTableName(shorterRecordPo.getId());
        int count = shorterRecordMapper.addRecord(tableName, shorterRecordPo);
        shortRecordDto.setId(shorterRecordPo.getId());
        return count > 0;
    }

    @Override
    public boolean getRecordByLeafId(Long leafId) {
        //根据leafId 计算tableName
        String tableName = GenerateTableNameUtil.getShorterTableName(leafId);
        CacheKeySetting setting = ShorterCacheKeyUtil.getLeafCacheKey(leafId);
        ShortRecordCacheDTO recordDto = redisStoreClient.get(setting.getKey());
        log.info("从redis中获取recordDto:[{}]", recordDto);
        if (recordDto == null
            && (recordDto = ShorterAdapter
            .shortPo2Dto(shorterRecordMapper.getOssFilePathById(tableName, leafId))) != null) {
            redisStoreClient.set(setting.getKey(), recordDto, 5 * 60);
        }
        boolean result = true;
        //记录不存在或者已过期
        if (recordDto == null || (recordDto.getExpires() != null && new Date()
            .after(recordDto.getExpires()))) {
            ShorterTipEnum tipEnum = recordDto == null ? URL_NOT_EXISTED_ERROR : URL_EXPIRED_ERROR;
            log.info("根据leafId:[{}] 获取链接记录信息错误,msg:[{}]", leafId, tipEnum.getMsg());
            result = false;
        }
        return result;
    }
}