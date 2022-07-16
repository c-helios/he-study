package com.hcw.controller;

import com.hcw.feign.DiscoveryCalleeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author hcw
 * @date 2022-07-10
 */
@RestController
@RequestMapping("/discovery/service/caller")
public class DiscoveryCallerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryCalleeService discoveryCalleeService;

    /**
     * Get sum of two value.
     * @param value1 value 1
     * @param value2 value 2
     * @return sum
     */
    @GetMapping("/feign")
    public int feign(@RequestParam int value1, @RequestParam int value2) {
        return discoveryCalleeService.sum(value1, value2);
    }

    /**
     * Get information of callee.
     * @return information of callee
     */
    @GetMapping("/rest")
    public String rest() {
        return restTemplate.getForObject(
                "http://polaris-callee-service/discovery/service/callee/info",
                String.class);
    }

    /**
     * health check.
     * @return health check info
     */
    @GetMapping("/healthCheck")
    public String healthCheck() {
        return "pk ok";
    }

}
