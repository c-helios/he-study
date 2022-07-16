package com.hcw.controller;

import com.hcw.entity.Order;
import com.hcw.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hcw
 * @date 2022-06-26
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/create")
    public String create(Order order) {
        orderService.create(order);
        return "下单成功";
    }
}
