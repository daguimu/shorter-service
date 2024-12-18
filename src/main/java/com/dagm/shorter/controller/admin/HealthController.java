/*
 * Copyright (c) 2024 ly.com
 * All rights reserved.
 *
 */
package com.dagm.shorter.controller.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Guimu
 * @date 2024/12/18
 */

@RestController
public class HealthController {

    @GetMapping("health")
    public String health(){
        return "OK";
    }
}