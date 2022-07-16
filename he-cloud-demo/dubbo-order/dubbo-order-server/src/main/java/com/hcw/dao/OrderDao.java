package com.hcw.dao;

import com.hcw.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author hcw
 * @date 2022-06-26
 */
public interface OrderDao extends JpaRepository<Order, Long> {

    @Modifying
    @Query(value = "update t_order set status = :status where id = :orderId", nativeQuery = true)
    void deduct(@Param("orderId") Long orderId, @Param("status") Integer status);
}
