package com.hcw.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author hcw
 * @date 2022-06-26
 */
@Table(name = "t_order")
@Entity
@Data
public class Order extends AbstractEntity<Long> {

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 产品id
     */
    private Long productId;
    /**
     * 数量
     */
    private Integer count;
    /**
     * 金额
     */
    private BigDecimal money;
    /**
     * 订单状态：0:创建中 1:已完结
     */
    private Integer status;

}
