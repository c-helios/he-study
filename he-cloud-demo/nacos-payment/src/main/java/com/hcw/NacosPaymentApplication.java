package com.hcw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author hcw
 * @date 2022-06-22
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosPaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosPaymentApplication.class, args);
    }
}
