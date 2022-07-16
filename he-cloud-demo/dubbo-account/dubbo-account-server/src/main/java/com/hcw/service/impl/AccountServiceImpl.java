package com.hcw.service.impl;

import com.hcw.dao.AccountDao;
import com.hcw.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author hcw
 * @date 2022-06-26
 */
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void deduct(Long userId, BigDecimal money) {
        accountDao.deduct(userId, money);
    }
}
