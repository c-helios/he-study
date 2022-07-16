package com.hcw.service.impl;

import com.hcw.dao.StorageDao;
import com.hcw.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hcw
 * @date 2022-06-26
 */
@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {

    private final StorageDao storageDao;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void deduct(Long productId, Integer count) {
        storageDao.deduct(productId, count);
        if (count == 10) {
            int i = 10 / 0;
        }
    }
}
