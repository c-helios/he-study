package com.hcw.controller;

import com.hcw.feign.DiscoveryCalleeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author hcw
 * @date 2022-07-11
 */
@RestController
@RequestMapping("/example/service/a")
public class ServiceAController {

    @Autowired
    private DiscoveryCalleeService discoveryCalleeService;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Get info of Service B by Feign.
     * @return info of Service B
     */
    @GetMapping("/getBServiceInfo")
    public String getBServiceInfo() {
        return discoveryCalleeService.info();
    }

    /**
     * Get info of Service B by RestTemplate.
     * @return info of Service B
     */
    @GetMapping("/testRest")
    public String testRest() {
        ResponseEntity<String> entity = restTemplate.getForEntity(
                "http://polaris-callee-service/example/service/b/info",
                String.class);
        return entity.getBody();
    }

}