package com.hcw.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author hcw
 * @date 2022-06-26
 */
@FeignClient(name = "seata-storage-service")
public interface StorageService {

    @PutMapping("/storage/deduct")
    void deduct(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);

}
