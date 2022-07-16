package com.hcw.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author hcw
 * @date 2022-06-26
 */
@Table(name = "t_account")
@Entity
@Data
public class Account extends AbstractEntity<Long> {

    private Long userId;
    private BigDecimal total;
    private BigDecimal used;
    private BigDecimal residue;
}
