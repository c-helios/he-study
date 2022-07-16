package com.hcw.service.impl;

import com.hcw.dao.OrderDao;
import com.hcw.entity.Order;
import com.hcw.rpc.service.AccountServiceApi;
import com.hcw.rpc.service.StorageServiceApi;
import com.hcw.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hcw
 * @date 2022-06-26
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    //@Reference(check = false, group = "dubbo-account-service", version= "1.0.0", retries = 0)
    @DubboReference(group = "dubbo-account-service", version = "${dubbo.consumer.accountServiceApi.version}", retries = 0, mock = "com.hcw.rpc.fallback.AccountServiceFallback")
    private AccountServiceApi accountServiceApi;

    //@Reference(check = false, group = "dubbo-storage-service", version= "1.0.0", retries = 0)
    @DubboReference(group = "dubbo-storage-service", version = "${dubbo.consumer.storageServiceApi.version}", retries = 0)
    private StorageServiceApi storageServiceApi;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void updateStatus(Long orderId, Integer status) {
        orderDao.deduct(orderId, status);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    //@GlobalTransactional(name = "test-create-order", rollbackFor = Throwable.class)
    public String create(Order order) {
        log.info("------开始创建订单");
        order.setStatus(0);
        orderDao.save(order);

        log.info("------开始调用库存，做扣减");
        String result1 = accountServiceApi.deduct(order.getUserId(), order.getMoney());
        log.info("------结束调用库存，做扣减,{}", result1);

        //log.info("------开始调用账户，做扣减");
        //storageServiceApi.deduct(order.getProductId(), order.getCount());
        //log.info("------结束调用账户，做扣减");


        //Long orderId = 1L;
        //Integer status = 1;
        this.updateStatus(order.getId(), 1);
        log.info("------结束创建订单");
        return "订单创建成功," + result1;
    }
}
