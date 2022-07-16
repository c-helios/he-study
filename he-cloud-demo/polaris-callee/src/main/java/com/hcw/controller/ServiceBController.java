package com.hcw.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hcw
 * @date 2022-07-11
 */
@RestController
@RequestMapping("/example/service/b")
public class ServiceBController {

    @Value("${is-throw-runtime-exception:#{false}}")
    private boolean isThrowRuntimeException;

    /**
     * Get service information.
     * @return service information
     */
    @GetMapping("/info")
    public String info() {
        if (isThrowRuntimeException) {
            throw new RuntimeException("failed for call my service");
        }
        else {
            return "hello world ! I'm a service B2";
        }
    }

}
