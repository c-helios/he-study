package com.hcw.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author hcw
 * @date 2022-06-26
 */

@FeignClient(name = "seata-account-service")
public interface AccountService {

    @PutMapping("/account/deduct")
    void deduct(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);

}
