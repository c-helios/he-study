package com.hcw.controller;

import com.hcw.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author hcw
 * @date 2022-06-26
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/storage")
public class StorageController {

    private final StorageService storageService;

    @PutMapping("/deduct")
    public String deduct(Long productId, Integer count) {
        storageService.deduct(productId, count);
        return "库存扣除成功";
    }
}
