package com.hcw.service.impl;

import com.hcw.dao.OrderDao;
import com.hcw.entity.Order;
import com.hcw.feign.AccountService;
import com.hcw.feign.StorageService;
import com.hcw.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final AccountService accountService;
    private final StorageService storageService;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void updateStatus(Long orderId, Integer status) {
        orderDao.deduct(orderId, status);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    @GlobalTransactional(name = "test-create-order", rollbackFor = Throwable.class)
    public String create(Order order) {
        log.info("------开始创建订单");
        order.setStatus(0);
        orderDao.save(order);

        log.info("------开始调用库存，做扣减");
        accountService.deduct(order.getUserId(), order.getMoney());
        log.info("------结束调用库存，做扣减");

        log.info("------开始调用账户，做扣减");
        storageService.deduct(order.getProductId(), order.getCount());
        log.info("------结束调用账户，做扣减");


        //Long orderId = 1L;
        //Integer status = 1;
        this.updateStatus(order.getId(), 1);
        log.info("------结束创建订单");
        return "订单创建成功";
    }
}
