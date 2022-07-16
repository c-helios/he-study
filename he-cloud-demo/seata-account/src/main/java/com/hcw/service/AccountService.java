package com.hcw.service;

import java.math.BigDecimal;

/**
 * @author hcw
 * @date 2022-06-26
 */
public interface AccountService {

    void deduct(Long userId, BigDecimal money);

}
