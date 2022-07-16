package com.hcw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author hcw
 * @date 2022-06-26
 */
@SpringBootApplication
@EnableDiscoveryClient
public class DubboStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboStorageApplication.class, args);
    }
}
