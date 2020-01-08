/*
 * Copyright (c) 2020 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.shorter.model;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author: Guimu
 * @created: 2020/01/03
 */
@Getter
@Setter
@Accessors(chain = true)
public class ShorterRecordPO {

    private Long id;
    private String shouterStr;
    private Date addTime;
    private Date modTime;
    private Date expiredTime;
}