package com.hcw.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hcw
 * @date 2022-06-25
 */
@RestController
@Slf4j
@RequestMapping("/flowLimit")
public class FlowLimitController {

    @GetMapping("/test1")
    public String test1() {
        return "test1";
    }

    @GetMapping("/test2")
    public String test2() {
        log.info("{}....{}",Thread.currentThread().getName(), "test2");
        return "test2";
    }

    @GetMapping("/test3")
    public String test3() {
        int i = 10/0;
        return "test2";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1, @RequestParam(value = "p2", required = false) String p2) {
        return "testHotKey";
    }

    public String deal_testHotKey(String p1, String p2, BlockException exception) {
        return "-------deal_testHotKey";
    }

}
