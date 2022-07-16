package com.hcw.controller;

import com.hcw.feign.PayServiceFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hcw
 * @date 2022-06-16
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final PayServiceFeign payServiceFeign;

    @GetMapping("/test1")
    public String test1() {
        return "test1";
    }

    @GetMapping("/timeout")
    public String timeout() {
        return payServiceFeign.testTimeout();
    }

}
