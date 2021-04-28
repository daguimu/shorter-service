/*
 * Copyright (c) 2020 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.shorter.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author Guimu
 * @create 2020/01/03
 */
@Getter
@Setter
@Accessors(chain = true)
public class ShortRecordDTO {

    private Long id;
    private String shouterStr;
    /**
     * 有效时间 单位: 秒
     */
    private Long expires;

}