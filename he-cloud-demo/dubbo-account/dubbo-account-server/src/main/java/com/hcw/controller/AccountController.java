package com.hcw.controller;

import com.hcw.service.AccountService;
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
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @PutMapping("/deduct")
    public String deduct(Long userId, BigDecimal money) {
        accountService.deduct(userId, money);
        return "额度扣除成功";
    }
}
