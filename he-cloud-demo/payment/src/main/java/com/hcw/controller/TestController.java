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
@RequestMapping("/test")
public class TestController {

    // 定义服务降级策略
    @HystrixCommand(
            // 当请求超时 或者 接口异常时，会调用 fallbackMethod 声明的方法（方法参数要一致）
            fallbackMethod = "testTimeoutReserveCase",
            commandProperties = {
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="1500")
            }
    )
    @GetMapping("/testTimeout")
    public String testTimeout() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "请求testTimeout成功";
    }

    public String testTimeoutReserveCase() {
        return "服务繁忙，请稍后再试";
    }
}
