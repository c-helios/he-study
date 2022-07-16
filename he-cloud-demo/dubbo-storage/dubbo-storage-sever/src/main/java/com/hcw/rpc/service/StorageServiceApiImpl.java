package com.hcw.rpc.service;

import com.hcw.service.StorageService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author hcw
 * @date 2022-07-02
 */
@Service(group = "dubbo-storage-service", version = "1.0.0")
public class StorageServiceApiImpl implements StorageServiceApi{

    @Autowired
    private StorageService storageService;

    @Override
    public void deduct(Long productId, Integer count) {
        System.out.println("storage.start.......");
        //storageService.deduct(productId, count);
    }
}
