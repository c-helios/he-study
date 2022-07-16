package com.hcw.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hcw
 * @date 2022-07-10
 */
@RestController
@RequestMapping("/discovery/service/callee")
public class DiscoveryCalleeController {

    private static Logger LOG = LoggerFactory.getLogger(DiscoveryCalleeController.class);

    @Value("${server.port:0}")
    private int port;

    /**
     * Get information of callee.
     * @return information of callee
     */
    @GetMapping("/info")
    public String info() {
        LOG.info("Discovery Service Callee [{}] is called.", port);
        return String.format("Discovery Service Callee [%s] is called.", port);
    }

    /**
     * Get sum of two value.
     * @param value1 value 1
     * @param value2 value 2
     * @return sum
     */
    @GetMapping("/sum")
    public int sum(@RequestParam int value1, @RequestParam int value2) {
        LOG.info("Discovery Service Callee is called and sum is {}.", value1 + value2);
        return value1 + value2;
    }

}
