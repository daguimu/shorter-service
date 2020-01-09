/*
 * Copyright (c) 2020 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.shorter.dto;

import com.dagm.devtool.model.BaseObject;
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
public class ShortRecordCacheDTO extends BaseObject {

    private Long id;
    private String shouterStr;
    /**
     * 截止时间
     */
    private Date expires;

}