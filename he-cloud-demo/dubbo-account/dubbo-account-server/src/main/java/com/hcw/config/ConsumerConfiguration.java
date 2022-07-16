package com.hcw.config;

import org.apache.dubbo.config.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hcw
 * @date 2022-07-05
 */
@Configuration
public class ConsumerConfiguration {

    //@Bean
    //public ApplicationConfig applicationConfig() {
    //    ApplicationConfig applicationConfig = new ApplicationConfig();
    //    applicationConfig.setName("demo-consumer");
    //    return applicationConfig;
    //}

    //@Bean
    //public RegistryConfig registryConfig() {
    //    RegistryConfig registryConfig = new RegistryConfig();
    //    registryConfig.setAddress("multicast://224.5.6.7:1234");
    //    return registryConfig;
    //}

    @Bean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        // Uncomment below line if you don't want to enable Sentinel for Dubbo service consumers.
        // consumerConfig.setFilter("-sentinel.dubbo.consumer.filter");
        return consumerConfig;
    }
}
