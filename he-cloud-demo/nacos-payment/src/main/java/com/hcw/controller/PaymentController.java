package com.hcw.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hcw
 * @date 2022-06-22
 */
@RestController
@RequestMapping("/payment")
@RefreshScope
public class PaymentController {

    @Value("${curVersion:}")
    private String version;

    @GetMapping("/test")
    public String test() {
        return "testPayment";
    }

    @GetMapping("/version")
    public String version() {
        return version;
    }



}
