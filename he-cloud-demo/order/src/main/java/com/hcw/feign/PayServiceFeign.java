package com.hcw.feign;

import com.hcw.feign.fallback.PayServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author hcw
 * @date 2022-06-22
 */
@FeignClient(name = "payment-service", fallback = PayServiceFallback.class)
public interface PayServiceFeign {

    /**
     * 有个小坑，如果要使用get请求，method为get且在所有参数前加上@RequestParam注解
     */
    @GetMapping(value = "/pay/wx")
    String wxPay(@RequestParam("orderId") String orderId);

    @GetMapping(value = "/test/testTimeout")
    String testTimeout();

}
