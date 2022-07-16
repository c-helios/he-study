package com.hcw.rpc.fallback;

import com.hcw.rpc.service.AccountServiceApi;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @author hcw
 * @date 2022-07-03
 */
@Slf4j
public class AccountServiceFallback implements AccountServiceApi {

    @Override
    public String deduct(Long userId, BigDecimal money) {
        System.out.println("失败。。。。");
        log.error("失败......");
        return "失败......";
    }
}
