/*
 * Copyright (c) 2019 maoyan.com
 * All rights reserved.
 *
 */
package com.dagm.shorter.res;


import static com.dagm.devtool.common.BaseTipCode.FAILURE;
import static com.dagm.devtool.common.BaseTipCode.SUCCESS;

import com.dagm.devtool.common.BaseCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author: Guimu
 * @created: 2019/10/07
 */
@Getter
@Setter
@Accessors(chain = true)
public class BaseResult<T> {

    private Boolean success;
    private String code;
    private String msg;
    private T data;


    public static BaseResult<String> generateSuccessRestlt() {
        return new BaseResult<String>().setSuccess(true).setCode(SUCCESS.getCode())
            .setMsg(SUCCESS.getMsg());
    }

    public static BaseResult<String> generateFailureRestlt(BaseCode baseCode) {
        return new BaseResult<String>().setSuccess(false).setCode(FAILURE.getCode())
            .setMsg(baseCode.getMsg());
    }

    public static BaseResult<String> generateFailureRestlt(String errorMsg) {
        return new BaseResult<String>().setSuccess(false).setCode(FAILURE.getCode())
            .setMsg(errorMsg);
    }

    public static <T> BaseResult<T> generateSuccessRestlt(T data) {
        return new BaseResult<T>().setSuccess(true).setCode(SUCCESS.getCode())
            .setMsg(SUCCESS.getMsg()).setData(data);
    }
}