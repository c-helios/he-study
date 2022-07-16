package com.hcw.rpc.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.hcw.rpc.fallback.AccountServiceFallback;
import com.hcw.service.AccountService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Objects;


//@Service(group = "dubbo-account-service", version = "1.0.0")
@DubboService(group = "dubbo-account-service", version = "1.0.0")
public class AccountServiceApiImpl implements AccountServiceApi {

    @Autowired
    private AccountService accountService;

    @Override
    @SentinelResource(value="deduct", fallback = "deduct", fallbackClass = AccountServiceFallback.class)
    public String deduct(Long userId, BigDecimal money) {
        System.out.println("AccountServiceApiImpl.aaaaa");
        if (Objects.nonNull(userId) && userId == 2L) {
            int i = 10/0;
        }
        //accountService.deduct(userId, money);
        return "扣减成功";
    }
}
