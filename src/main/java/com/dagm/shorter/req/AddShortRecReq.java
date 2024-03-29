/*
 * Copyright (c) 2020 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.shorter.req;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @author Guimu
 * @create 2020/01/03
 */
@Getter
@Setter
@Accessors(chain = true)
public class AddShortRecReq {

    @NotNull(message = "URL不能为空!")
    private String url;
}