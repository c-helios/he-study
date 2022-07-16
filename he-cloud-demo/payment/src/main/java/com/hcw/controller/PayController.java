package com.hcw.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hcw
 * @date 2022-06-22
 */
@RestController
@RequestMapping("/pay")
public class PayController {

    //@HystrixCommand(fallbackMethod = "testCircuitBreakerFallBack", commandProperties = {
    //        // 是否开启断路器。默认为 true。
    //        @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
    //        // 在一定时间内，请求总数达到了阈值，才有资格进行熔断。默认 20 个请求。
    //        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
    //        // 熔断之后，重新尝试恢复服务调用的时间，在此期间，会执行 fallbackMethod 定义的逻辑。默认 5 秒。
    //        @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
    //        // 出错阈值，请求总数超过了阈值，并且调用失败率达到一定比率，会熔断。默认 50%。
    //        @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    //})
    @GetMapping(value = "/wx")
    public String wxPay(String orderId) {
        return orderId + " 微信支付成功";
    }
}
