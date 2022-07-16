package com.hcw.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hcw
 * @date 2022-06-22
 */
@RestController
@RequestMapping("/order")
public class OrderCOntroller {

    @GetMapping("/test")
    public String test() {
        return "testOrder";
    }
}
