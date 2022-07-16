package com.hcw.service;

import com.hcw.entity.Order;

/**
 * @author hcw
 * @date 2022-06-26
 */
public interface OrderService {

    void updateStatus(Long orderId, Integer status);

    String create(Order order);

}
