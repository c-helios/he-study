package com.hcw.controller;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.hcw.feign.PayServiceFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hcw
 * @date 2022-06-22
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final PayServiceFeign payServiceFeign;

    @GetMapping("/shop")
    public String shop() {
        String nanoId = NanoIdUtils.randomNanoId();
        return payServiceFeign.wxPay(nanoId);
    }
}
