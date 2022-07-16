package com.hcw.rpc.service;

/**
 * @author hcw
 * @date 2022-06-26
 */
public interface StorageServiceApi {

    void deduct(Long productId, Integer count);

}
