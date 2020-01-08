/*
 * Copyright (c) 2020 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.shorter.service.impl;

import static com.dagm.shorter.consts.DeteConst.EXPIRES;
import static com.dagm.shorter.enums.ShorterTipEnum.URL_BIT_CHECK_ERROR;
import static com.dagm.shorter.enums.ShorterTipEnum.URL_FORMAT_ERROR;
import static com.dagm.shorter.enums.ShorterTipEnum.URL_SAVE_TO_OSS_ERROR;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.OSSObject;
import com.dagm.devtool.utils.PreconditionsUtil;
import com.dagm.shorter.config.ShorterConfig;
import com.dagm.shorter.dto.ShortRecordDTO;
import com.dagm.shorter.service.BaseShorterService;
import com.dagm.shorter.service.BitCheckService;
import com.dagm.shorter.service.LeafGenService;
import com.dagm.shorter.service.ShorterRecordService;
import com.dagm.shorter.service.ShorterService;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Guimu
 * @created: 2020/01/03
 */
@Slf4j
@Service
public class ShorterServiceImpl implements ShorterService {

    @Resource(name = "ossClient")
    private OSS ossClient;
    @Autowired
    private ShorterConfig shorterConfig;
    @Autowired
    private ShorterRecordService shorterRecordService;
    @Autowired
    private BaseShorterService baseShorterService;
    @Autowired
    private BitCheckService bitCheckService;
    @Autowired
    private LeafGenService leafGenService;

    @Override
    public String toBeShorter(String longStr) {
        // 校验longStr 格式是否符合规范避免无效的转换
        PreconditionsUtil.checkArgument(this.checkUrl(longStr), URL_FORMAT_ERROR);
        // 将原longUrl存储在OSS
        Long leafId = leafGenService.getLeafIdByTag();
        String ossFilePath = this.addShortRec(leafId, longStr);
        PreconditionsUtil.checkArgument(StringUtils.isNotEmpty(ossFilePath), URL_SAVE_TO_OSS_ERROR);
        ShortRecordDTO shortRecordDto = new ShortRecordDTO()
            .setShouterStr(this.encodeLeafIdAndCheck(leafId)).setExpires(EXPIRES).setId(leafId);
        shorterRecordService.addRecord(shortRecordDto);
        log.info("添加短网址,longStr:[{}],shorterStr:[{}]", longStr, shortRecordDto.getShouterStr());
        return shorterConfig.getBaseUrl() + shortRecordDto.getShouterStr();
    }

    @Override
    public String backToLongStr(String shortStr) {
        // 获取短码的校验位
        String bitCode = shortStr.substring(shortStr.length() - 1);
        // 获取短码的有效位字符串
        String originStr = shortStr.substring(0, shortStr.length() - 1);
        // 根据短码进行解码得到原leafId
        long leafId = baseShorterService.deCodeBase62(originStr);
        // 根据接收到的 leafId 获取其 位校验值
        String code = bitCheckService.getCheckBitByOriginVal(String.valueOf(leafId));
        if (!StringUtils.equals(bitCode, code)) {
            log.info("msg:[{}] bitCode:[{}]", URL_BIT_CHECK_ERROR.getMsg(), bitCode);
            return shorterConfig.getDefaultUrl();
        }
        boolean isValid = shorterRecordService.getRecordByLeafId(leafId);
        return isValid ? this.getOriginFromOss(leafId + shorterConfig.getOssSuffix())
            : shorterConfig.getDefaultUrl();
    }

    private String addShortRec(Long uniqueId, String longStr) {
        String ossFilePath = uniqueId + shorterConfig.getOssSuffix();
        try (InputStream inputStream = new ByteArrayInputStream(
            longStr.getBytes(StandardCharsets.UTF_8))) {
            ossClient.putObject(shorterConfig.getBucketName(), ossFilePath, inputStream);
            log.info("存储文本到OSS完成,ossFilePath:[{}]", ossFilePath);
        } catch (IOException e) {
            log.info("创建短连接到OSS失败 longStr:[{}]", ossFilePath, e);
            return null;
        }
        return ossFilePath;
    }

    /**
     * 根据filename 从OSS获取短连接文件
     *
     * @author Guimu
     * @date 2020/1/3
     */
    public String getOriginFromOss(String ossFilePath) {
        StringBuilder origStrBuilder = new StringBuilder();
        OSSObject ossObject = ossClient.getObject(shorterConfig.getBucketName(), ossFilePath);
        try (InputStream inputStream = ossObject.getObjectContent()) {
            if (inputStream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                while (true) {
                    String line = reader.readLine();
                    if (line == null) {
                        break;
                    }
                    origStrBuilder.append(line);
                }
                // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
            }
        } catch (IOException e) {
            log.error("根据文件路径获取OSS文件异常,filename:[{}]", ossFilePath, e);
        }
        log.info("根据OSS文件路径获取链接记录文本ossFilePath:[{}],content:[{}]", ossFilePath,
            origStrBuilder);
        return origStrBuilder.toString();
    }

    /**
     * 校验longUrl 格式是否符合规范
     *
     * @author: Guimu
     * @created: 2020/1/3
     */
    private boolean checkUrl(String longUrl) {
        return StringUtils.isNotEmpty(longUrl) && (longUrl.startsWith("http://") || longUrl
            .startsWith("https://"));
    }

    /**
     * 对leafId 进行encode并添加校验码
     */
    private String encodeLeafIdAndCheck(Long leafId) {
        // 得到leafId 对应的62进制字符串
        String leafBasse62Str = baseShorterService.enCodeBase62(leafId);
        // 根据leafId 获取其 位校验值
        String code = bitCheckService.getCheckBitByOriginVal(String.valueOf(leafId));
        return leafBasse62Str + code;
    }
}